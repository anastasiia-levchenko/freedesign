package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;


@Service
public class ImageServiceImpl implements ImageService
{
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Override
	public String getGeneratedFileNameForImage(final String originalFilename)
	{
		logger.info(FreeDesignConstants.GENERATE_IMAGE_NAME);

		return new Date().getTime() + "_" + originalFilename;
	}

	@Override
	public void saveImage(final String updatedFileName, final MultipartFile initialImage)
	{
		try
		{
			logger.info(FreeDesignConstants.SAVING_IMAGE);

			final String uploadDir = "public/images/";
			final Path uploadPath = Paths.get(uploadDir);

			createDirIfAbsent(uploadPath);

			try (final InputStream inputStream = initialImage.getInputStream())
			{
				Files.copy(inputStream, Paths.get(uploadDir + updatedFileName), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (final IOException ex)
		{
			logger.error(FreeDesignConstants.IMAGE_SAVE_FAIL);
		}
	}

	public void deleteArtWorkRelatedImage(final String imageFileName)
	{
		final Path pathToImage = Paths.get(FreeDesignConstants.PATH_TO_IMAGES.concat(imageFileName));

		try
		{
			logger.info(FreeDesignConstants.DELETING_RELATED_IMAGE);
			Files.delete(pathToImage);
		}
		catch (final IOException ex)
		{
			logger.error(FreeDesignConstants.IMAGE_DELETION_FAIL);
			System.out.println();
		}
	}

	private void createDirIfAbsent(final Path uploadPath) throws IOException
	{
		if (!Files.exists(uploadPath))
		{
			Files.createDirectories(uploadPath);
		}
	}
}
