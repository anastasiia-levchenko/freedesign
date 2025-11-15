package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.DTO.ArtWorkUploadDTO;
import com.springboot.freedesign.DTO.ArtWorkViewDTO;
import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.dao.ArtWorkDAO;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkNotFoundException;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkParsingException;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.models.enums.ArtWorkStatus;
import com.springboot.freedesign.populators.ArtWorkPopulator;
import com.springboot.freedesign.services.ArtWorkService;
import com.springboot.freedesign.services.ImageService;
import com.springboot.freedesign.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArtWorkServiceImpl implements ArtWorkService
{
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	private UserService userService;

	@Autowired
	private ArtWorkPopulator artWorkPopulator;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ArtWorkDAO artWorkDAO;

	@Override
	public List<ArtWork> getCreatedArtWorks()
	{
		return artWorkDAO.findByUserId(userService.getCurrentSessionUserId());
	}

	@Override
	public void deleteById(final String id)
	{
		logger.info(String.format(FreeDesignConstants.DELETING_ARTWORK, id));

		final int artworkId = getParsedId(id);

		artWorkDAO.deleteById(artworkId);
	}

	@Override
	public ArtWork findById(final String id)
	{
		return artWorkDAO.findById(getParsedId(id))
				.orElseThrow(() -> new ArtWorkNotFoundException(FreeDesignConstants.ART_WORK_NOT_FOUND));
	}

	@Override
	public void deleteAllByUser()
	{
		final List<ArtWork> artWorksByUser = artWorkDAO.findByUserId(userService.getCurrentSessionUserId());
		deleteImagesByArtWorks(artWorksByUser);
		artWorkDAO.deleteAll(artWorksByUser);
	}

	@Override
	public Page<ArtWorkViewDTO> getPublishedArtworks(final int page, final String searchTerm, int size)
	{
		final Pageable pageable = PageRequest.of(page, size);
		final Page<ArtWork> artworkPage = artWorkDAO.searchPublishedArtWorks(pageable, searchTerm);
		return artworkPage.map(artWorkPopulator::toViewDto);
	}

	@Override
	public List<ArtWorkViewDTO> getViewDtos(final List<ArtWork> artWorks)
	{
		return artWorks.stream()
				.map(artWorkPopulator::toViewDto)
				.collect(Collectors.toList());
	}

	@Override
	public void saveNewArtwork(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork)
	{
		artWork.setUser(userService.getUserById(userService.getCurrentSessionUserId()));
		populateAndSaveArtWork(artWorkUploadDTO, artWork);
	}

	@Override
	public void populateAndSaveArtWork(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork)
	{
		artWorkPopulator.populateArtWorkForDTO(artWorkUploadDTO, artWork);
		artWorkPopulator.populateImage(artWorkUploadDTO, artWork);

		saveArtWork(artWorkUploadDTO, artWork);
	}

	@Override
	public void publishArtWork(final ArtWork artWork) {
		artWork.setStatus(ArtWorkStatus.PENDING_REVIEW);
		artWorkDAO.save(artWork);
		logger.info("Artwork {} published successfully", artWork.getId());
	}

	@Override
	public void unpublishArtWork(final ArtWork artWork) {
		artWork.setStatus(ArtWorkStatus.DRAFT);
		artWorkDAO.save(artWork);
		logger.info("Artwork {} unpublished successfully", artWork.getId());
	}

	@Override
	public void updateArtWorkNoNewImage(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork)
	{
		artWorkPopulator.populateArtWorkForDTO(artWorkUploadDTO, artWork);

		logger.info(String.format(FreeDesignConstants.SAVING_UPDATED_ARTWORK, artWork.getId()));

		artWorkDAO.save(artWork);
	}

	@Override
	public ArtWorkUploadDTO getCreatedDtoForArtWork(final ArtWork artWork)
	{
		return artWorkPopulator.convertToDto(artWork);
	}

	private void saveArtWork(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork)
	{
		logger.info(String.format(FreeDesignConstants.SAVING_ARTWORK, artWork.getId()));

		imageService.saveImage(artWork.getImageFileName(), artWorkUploadDTO.getImageFile());

		artWorkDAO.save(artWork);
	}

	private int getParsedId(final String id)
	{
		final int artworkId;

		try
		{
			artworkId = Integer.parseInt(id);
		}
		catch (final NumberFormatException ex)
		{
			logger.error(ex.getMessage());
			throw new ArtWorkParsingException(ex.getMessage());
		}
		return artworkId;
	}

	private void deleteImagesByArtWorks(final List<ArtWork> artWorks)
	{
		artWorks.forEach(artWork -> imageService.deleteArtWorkRelatedImage(artWork.getImageFileName()));
	}


}
