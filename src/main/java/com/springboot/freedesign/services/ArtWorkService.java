package com.springboot.freedesign.services;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.models.ArtWork;

import java.util.List;


public interface ArtWorkService
{
	List<ArtWork> getCreatedArtWorks();

	void deleteById(final String id);

	ArtWork findById(final String id);

	void populateAndSaveArtWork(final ArtWorkDTO artWorkDTO, final ArtWork artWork);

	ArtWorkDTO getCreatedDtoForArtWork(final ArtWork artWork);

	void updateArtWorkNoNewImage(final ArtWorkDTO artWorkDTO, final ArtWork artWork);
}
