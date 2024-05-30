package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.dao.ArtWorkDAO;
import com.springboot.freedesign.exceptions.exceptions.ArtWorkNotFoundException;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.populators.ArtWorkPopulator;
import com.springboot.freedesign.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArtWorkServiceImpl implements ArtWorkService
{
	@Autowired
	private ArtWorkDAO artWorkDAO;

	@Autowired
	private ArtWorkPopulator artWorkPopulator;

	@Autowired
	private ImageServiceImpl imageService;

	public List<ArtWork> getCreatedArtWorks()
	{
		return artWorkDAO.findAll();
	}

	@Override
	public void deleteById(final String id)
	{
		final int artworkId = Integer.parseInt(id);

		artWorkDAO.deleteById(artworkId);
	}

	@Override
	public ArtWork findById(final String id)
	{
		return artWorkDAO.findById(Integer.valueOf(id))
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

		artWorkDAO.save(artWork);
	}

	private void saveArtWork(final ArtWorkDTO artWorkDTO, final ArtWork artWork)
	{
		imageService.saveImage(artWork.getImageFileName(), artWorkDTO.getImageFile());

		artWorkDAO.save(artWork);
	}

	@Override
	public ArtWorkDTO getCreatedDtoForArtWork(final ArtWork artWork)
	{
		final ArtWorkDTO artWorkDTO = new ArtWorkDTO();
		artWorkPopulator.populateDtoForArkWork(artWorkDTO, artWork);

		return artWorkDTO;
	}
}
