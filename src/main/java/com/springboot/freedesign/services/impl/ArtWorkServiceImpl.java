package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.dao.ArtWorkDAO;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkNotFoundException;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkParsingException;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.populators.ArtWorkPopulator;
import com.springboot.freedesign.security.MyUserDetails;
import com.springboot.freedesign.services.ArtWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArtWorkServiceImpl implements ArtWorkService
{
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	@Autowired
	private ArtWorkDAO artWorkDAO;

	@Autowired
	private ArtWorkPopulator artWorkPopulator;

	@Autowired
	private ImageServiceImpl imageService;

	@Override
	public List<ArtWork> getCreatedArtWorks()
	{
		return artWorkDAO.findByUserId(getCurrentUserId());
	}

	private int getCurrentUserId()
	{
		final MyUserDetails currentUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return currentUserDetails.getUser().getId();
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
	public void populateAndSaveArtWork(final ArtWorkDTO artWorkDTO, final ArtWork artWork)
	{
		artWorkPopulator.populateArtWorkForDTO(artWorkDTO, artWork);
		artWorkPopulator.populateImage(artWorkDTO, artWork);

		saveArtWork(artWorkDTO, artWork);
	}

	@Override
	public void updateArtWorkNoNewImage(final ArtWorkDTO artWorkDTO, final ArtWork artWork)
	{
		artWorkPopulator.populateArtWorkForDTO(artWorkDTO, artWork);

		logger.info(String.format(FreeDesignConstants.SAVING_UPDATED_ARTWORK, artWork.getId()));

		artWorkDAO.save(artWork);
	}

	@Override
	public ArtWorkDTO getCreatedDtoForArtWork(final ArtWork artWork)
	{
		final ArtWorkDTO artWorkDTO = new ArtWorkDTO();

		artWorkPopulator.populateDtoForArkWork(artWorkDTO, artWork);

		return artWorkDTO;
	}

	private void saveArtWork(final ArtWorkDTO artWorkDTO, final ArtWork artWork)
	{
		logger.info(String.format(FreeDesignConstants.SAVING_ARTWORK, artWork.getId()));

		imageService.saveImage(artWork.getImageFileName(), artWorkDTO.getImageFile());

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
}
