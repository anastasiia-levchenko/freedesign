package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.exceptions.exceptions.UserHasNoPermissionToEdit;
import com.springboot.freedesign.services.UserService;
import com.springboot.freedesign.services.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ValidatorServiceImpl implements ValidatorService
{
	@Autowired
	private UserService userService;
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Override
	public BindingResult validateCreatedArtWork(final BindingResult result, final ArtWorkDTO artWorksDTO)
	{
		checkIfImageIsAttached(result, artWorksDTO.getImageFile());

		return result;
	}

	@Override
	public void validateUserAuthorization(final int userId)
	{
		if (userService.getCurrentSessionUserId() != userId)
		{
			logger.error(FreeDesignConstants.USER_HAS_NO_PERMISSION_TO_EDIT);
			throw new UserHasNoPermissionToEdit("The ArtWork was not found by the provided id");
		}
	}

	private void checkIfImageIsAttached(final BindingResult result, final MultipartFile imageName)
	{
		if (imageName.isEmpty())
		{
			logger.error(FreeDesignConstants.NO_IMAGE);

			result.addError(new FieldError("artWorkDTO", "imageFile", "Please attach the image of the Art Work"));
		}
	}
}
