package com.springboot.freedesign.services;

import org.springframework.web.multipart.MultipartFile;


public interface ImageService
{
	String getGeneratedFileNameForImage(final String originalFilename);

	void saveImage(final String updatedFileName, final MultipartFile initialImage);

	void deleteArtWorkRelatedImage(final String imageFileName);
}
