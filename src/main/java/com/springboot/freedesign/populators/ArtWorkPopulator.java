package com.springboot.freedesign.populators;

import com.springboot.freedesign.DTO.ArtWorkUploadDTO;
import com.springboot.freedesign.DTO.ArtWorkViewDTO;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.services.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ArtWorkPopulator
{
	@Autowired
	private ImageServiceImpl imageService;

	public void populateArtWorkForDTO(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork)
	{
		artWork.setName(artWorkUploadDTO.getName());
		artWork.setPrice(artWorkUploadDTO.getPrice());
		artWork.setNotes(artWorkUploadDTO.getNotes());
	}

	public void populateImage(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork)
	{
		artWork.setImageFileName(imageService.getGeneratedFileNameForImage(artWorkUploadDTO.getImageFile().getOriginalFilename()));
		imageService.saveImage(artWork.getImageFileName(), artWorkUploadDTO.getImageFile());
	}

	public ArtWorkUploadDTO convertToDto(final ArtWork artWork)
	{
		final ArtWorkUploadDTO artWorkDTO = new ArtWorkUploadDTO();
		artWorkDTO.setId(artWork.getId());
		artWorkDTO.setName(artWork.getName());
		artWorkDTO.setPrice(artWork.getPrice());
		artWorkDTO.setNotes(artWork.getNotes());
		return artWorkDTO;
	}

	public ArtWorkViewDTO toViewDto(final ArtWork artWork)
	{
		return ArtWorkViewDTO.builder().withId(artWork.getId())
				.withName(artWork.getName())
				.withPrice(artWork.getPrice())
				.withImageFileName(artWork.getImageFileName())
				.withStatus(artWork.getStatus())
				.withNotes(artWork.getNotes())
				.build();
	}
}
