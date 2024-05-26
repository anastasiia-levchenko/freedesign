package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.services.ImageService;
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
	@Override
	public String getGeneratedFileNameForImage(final String originalFilename)
	{
		return new Date().getTime() + "_" + originalFilename;
	}

	@Override
	public void saveImage(final String updatedFileName, final MultipartFile initialImage)
	{
		try
		{
			final String uploadDir = "public/images/";
			final Path uploadPath = Paths.get(uploadDir);

			createDirIfAbsent(uploadPath);

			try (final InputStream inputStream = initialImage.getInputStream())
			{
				Files.copy(inputStream, Paths.get(uploadDir + updatedFileName), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e)
		{
			System.out.println("Exception");
		}

	}

	public void deleteArtWorkRelatedImage(final String imageFileName)
	{
		final Path pathToImage = Paths.get(FreeDesignConstants.PATH_TO_IMAGES.concat(imageFileName));

		try
		{
			Files.delete(pathToImage);
		}
		catch (Exception ex)
		{
			System.out.println("Could not delete the file or the file is absent");
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
