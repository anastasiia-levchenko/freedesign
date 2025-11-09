package com.springboot.freedesign.services;

import com.springboot.freedesign.DTO.ArtWorkUploadDTO;
import org.springframework.validation.BindingResult;


public interface ValidatorService
{
	BindingResult validateCreatedArtWork(final BindingResult result, final ArtWorkUploadDTO artWorksDTO);

	void validateUserAuthorization(final int userId);
}
