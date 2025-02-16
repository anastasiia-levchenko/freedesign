package com.springboot.freedesign.services;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.dao.ArtWorkDAO;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkNotFoundException;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkParsingException;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.models.User;
import com.springboot.freedesign.populators.ArtWorkPopulator;
import com.springboot.freedesign.security.MyUserDetails;
import com.springboot.freedesign.services.impl.ArtWorkServiceImpl;
import com.springboot.freedesign.services.impl.ImageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ArtWorkServiceImplUnitTest
{
	@InjectMocks
	private ArtWorkServiceImpl artWorkService;

	@Mock
	private UserService userService;
	@Mock
	private ImageServiceImpl imageService;

	@Mock
	private ArtWorkDAO artWorkDAO;
	@Mock
	private ArtWorkDTO artWorkDTO;

	@Mock
	private ArtWorkPopulator artWorkPopulator;

	@Mock
	private User user;
	@Mock
	private ArtWork artWork;
	@Mock
	private MultipartFile file;

	@Mock
	private Authentication auth;
	@Mock
	private SecurityContext context;
	@Mock
	private MyUserDetails myUserDetails;

	@Test
	public void verifyArtWorkDaoFindByUserIdWasCalledTest()
	{
		final int expectedUserId = 1;

		setUpSecurityContext(expectedUserId);

		artWorkService.getCreatedArtWorks();

		verify(artWorkDAO).findByUserId(anyInt());
	}

	@Test
	public void checkPassedArtWorkIdIntArgumentTest()
	{
		final String testIdString = "1";
		final int testId = Integer.parseInt(testIdString);

		artWorkService.deleteById(testIdString);

		verify(artWorkDAO).deleteById(testId);
	}

	@Test
	public void unparsableArtWorkIdPassedForDeletionThrowExceptionTest()
	{
		final String testIdString = "abc";

		assertThrows(ArtWorkParsingException.class, () -> {
			artWorkService.deleteById(testIdString);
		});
	}

	@Test
	public void checkArtWorkNotFoundByUserIdThrowExceptionTest()
	{
		final int userId = 1;

		when(artWorkDAO.findById(userId)).thenReturn(Optional.empty());

		assertThrows(ArtWorkNotFoundException.class, () -> {
			artWorkService.findById(String.valueOf(userId));
		});
	}

	@Test
	public void checkArtWorkFoundByUserIdTest()
	{
		final int userId = 1;

		when(artWorkDAO.findById(userId)).thenReturn(Optional.of(artWork));

		assertEquals(artWork, artWorkService.findById(String.valueOf(userId)));
	}

	@Test
	public void verifyNewArtWorkPopulatedAndSavedTest()
	{
		final String fileName = "fileName";
		final int expectedUserId = 1;

		when(artWorkDTO.getImageFile()).thenReturn(file);
		when(artWork.getImageFileName()).thenReturn(fileName);
		setUpSecurityContext(expectedUserId);

		artWorkService.saveNewArtwork(artWorkDTO, artWork);

		verify(userService).getUserById(expectedUserId);
		verifyArtWorkPopulated(fileName);
	}

	@Test
	public void verifyDeleteAllDaoMethodWasCalledTest()
	{
		artWorkService.deleteAllByUser();

		verify(artWorkDAO).deleteAll();
	}

	@Test
	public void verifyImagesDeleteMethodWasCalledThreeTimesTest()
	{
		when(artWorkDAO.findAll()).thenReturn(
				Arrays.asList(getArtWorkWithImageName(), getArtWorkWithImageName(), getArtWorkWithImageName()));

		artWorkService.deleteAllByUser();

		verify(imageService, times(3)).deleteArtWorkRelatedImage(anyString());
	}

	@Test
	public void unparsableArtWorkIdPassedToFindThrowExceptionTest()
	{
		final String testIdString = "abc";

		assertThrows(ArtWorkParsingException.class, () -> {
			artWorkService.findById(testIdString);
		});
	}


	@Test
	public void verifyFindByIdDaoMethodReturnsArtWorkTest()
	{
		final String testIdString = "1";
		when(artWorkDAO.findById(Integer.parseInt(testIdString))).thenReturn(Optional.of(artWork));

		final ArtWork actualResult = artWorkService.findById(testIdString);

		verify(artWorkDAO).findById(anyInt());
		assertEquals(artWork, actualResult);
	}

	@Test
	public void verifyFindByIdDaoMethodReturnsNullAndThrowsExceptionTest()
	{
		final String testIdString = "1";
		when(artWorkDAO.findById(Integer.parseInt(testIdString))).thenReturn(Optional.empty());

		assertThrows(ArtWorkNotFoundException.class, () -> {
			artWorkService.findById(testIdString);
		});
	}

	@Test
	public void verifyArtWorkPopulatedAndSavedTest()
	{
		final String fileName = "fileName";
		when(artWorkDTO.getImageFile()).thenReturn(file);
		when(artWork.getImageFileName()).thenReturn(fileName);

		artWorkService.populateAndSaveArtWork(artWorkDTO, artWork);

		verifyArtWorkPopulated(fileName);
	}

	@Test
	public void verifyArtWorkUpdatedTest()
	{
		artWorkService.updateArtWorkNoNewImage(artWorkDTO, artWork);

		verify(artWorkPopulator).populateArtWorkForDTO(artWorkDTO, artWork);
		verify(artWorkDAO).save(artWork);
	}

	@Test
	public void verifyPopulatorCalledForArtWorkTest()
	{
		final ArtWork artWorkParam = new ArtWork();

		artWorkService.getCreatedDtoForArtWork(artWorkParam);

		verify(artWorkPopulator).populateDtoForArkWork(isA(ArtWorkDTO.class), eq(artWorkParam));
	}


	private ArtWork getArtWorkWithImageName()
	{
		final ArtWork artWork = new ArtWork();

		artWork.setImageFileName("");

		return artWork;
	}

	private void verifyArtWorkPopulated(final String fileName)
	{
		verify(artWorkPopulator).populateArtWorkForDTO(artWorkDTO, artWork);
		verify(artWorkPopulator).populateImage(artWorkDTO, artWork);
		verify(imageService).saveImage(fileName, file);
		verify(artWorkDAO).save(artWork);
	}

	private void setUpSecurityContext(final int expectedUserId)
	{
		when(context.getAuthentication()).thenReturn(auth);
		when(auth.getPrincipal()).thenReturn(myUserDetails);
		when(myUserDetails.getUser()).thenReturn(user);
		when(user.getId()).thenReturn(expectedUserId);
		SecurityContextHolder.setContext(context);
	}

}
