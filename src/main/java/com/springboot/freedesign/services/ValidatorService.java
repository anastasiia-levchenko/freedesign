package com.springboot.freedesign.services;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import org.springframework.validation.BindingResult;


public interface ValidatorService
{
	BindingResult validateCreatedArtWork(final BindingResult result, final ArtWorkDTO artWorksDTO);

	void validateUserAuthorization(final int userId);
}
