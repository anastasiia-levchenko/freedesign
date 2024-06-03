package com.springboot.freedesign.services;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.dao.ArtWorkDAO;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkNotFoundException;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkParsingException;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.populators.ArtWorkPopulator;
import com.springboot.freedesign.services.impl.ArtWorkServiceImpl;
import com.springboot.freedesign.services.impl.ImageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ArtWorkServiceImplUnitTest
{
	@InjectMocks
	private ArtWorkServiceImpl artWorkService;

	@Mock
	private ArtWorkDAO artWorkDAO;
	@Mock
	private ArtWorkPopulator artWorkPopulator;
	@Mock
	private ImageServiceImpl imageService;
	@Mock
	private ArtWork artWork;
	@Mock
	private ArtWorkDTO artWorkDTO;
	@Mock
	private MultipartFile file;

	@Test
	public void verifyArtWorkDaoWasCalledTest()
	{
		artWorkService.getCreatedArtWorks();

		verify(artWorkDAO).findAll();
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

		verify(artWorkPopulator).populateArtWorkForDTO(artWorkDTO, artWork);
		verify(artWorkPopulator).populateImage(artWorkDTO, artWork);
		verify(imageService).saveImage(fileName, file);
		verify(artWorkDAO).save(artWork);
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

}
