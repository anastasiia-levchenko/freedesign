package com.springboot.freedesign.populators;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.services.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ArtWorkPopulator
{
	@Autowired
	private ImageServiceImpl imageService;

	public void populateArtWorkForDTO(final ArtWorkDTO artWorkDTO, final ArtWork artWork)
	{
		artWork.setName(artWorkDTO.getName());
		artWork.setPrice(artWorkDTO.getPrice());
		artWork.setWantToSell(artWorkDTO.isWantToSell());
		artWork.setNotes(artWorkDTO.getNotes());
	}

	public void populateImage(final ArtWorkDTO artWorkDTO, final ArtWork artWork)
	{
		artWork.setImageFileName(imageService.getGeneratedFileNameForImage(artWorkDTO.getImageFile().getOriginalFilename()));
		imageService.saveImage(artWork.getImageFileName(), artWorkDTO.getImageFile());
	}

	public void populateDtoForArkWork(final ArtWorkDTO artWorkDTO, final ArtWork artWork)
	{
		artWorkDTO.setId(artWork.getId());
		artWorkDTO.setName(artWork.getName());
		artWorkDTO.setPrice(artWork.getPrice());
		artWorkDTO.setWantToSell(artWork.isWantToSell());
		artWorkDTO.setNotes(artWork.getNotes());
	}
}
