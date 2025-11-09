package com.springboot.freedesign.services;

import com.springboot.freedesign.DTO.ArtWorkUploadDTO;
import com.springboot.freedesign.DTO.ArtWorkViewDTO;
import com.springboot.freedesign.models.ArtWork;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ArtWorkService
{
	List<ArtWork> getCreatedArtWorks();

	void deleteById(final String id);

	ArtWork findById(final String id);

	void saveNewArtwork(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork);

	void unpublishArtWork(final ArtWork artWork);

	void publishArtWork(final ArtWork artWork);

	void populateAndSaveArtWork(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork);

	void updateArtWorkNoNewImage(final ArtWorkUploadDTO artWorkUploadDTO, final ArtWork artWork);

	ArtWorkUploadDTO getCreatedDtoForArtWork(final ArtWork artWork);

	void deleteAllByUser();

	Page<ArtWorkUploadDTO> getPublishedArtworks(int page, String searchTerm, int size);

	List<ArtWorkViewDTO> getViewDtos(List<ArtWork> artWorks);
}
