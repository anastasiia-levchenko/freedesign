package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.services.ValidatorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ValidatorServiceImpl implements ValidatorService
{
	@Override
	public BindingResult validateCreatedArtWork(final BindingResult result, final ArtWorkDTO artWorksDTO)
	{
		checkIfImageIsAttached(result, artWorksDTO.getImageFile());
		return result;
	}

	private void checkIfImageIsAttached(final BindingResult result, final MultipartFile imageName)
	{
		if (imageName.isEmpty())
		{
			result.addError(new FieldError("artWorkDTO", "imageFile", "Please attach the image of the Art Work"));
		}
	}
}
