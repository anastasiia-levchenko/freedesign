package com.springboot.freedesign.strategies.impl.export;

import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.exceptions.exceptions.ArtWorksExportException;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.services.ArtWorkService;
import com.springboot.freedesign.strategies.ExportStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;


@Component
public class CSVExportStrategyImpl implements ExportStrategy
{
	public static final String EXTENSION_NAME = "csv";
	public static final String CONTENT_TYPE = "text/csv";

	@Autowired
	private ArtWorkService artWorkService;

	@Override
	public String getExtensionName()
	{
		return EXTENSION_NAME;
	}

	@Override
	public String getContentType()
	{
		return CONTENT_TYPE;
	}

	@Override
	public void export(final HttpServletResponse response)
	{
		setUpResponse(response);

		final List<ArtWork> artWorks = artWorkService.getCreatedArtWorks();

		final String[] csvHeader = { "ID", "Name", "Price", "Wish to sell", "Image Name", "Notes" };
		final String[] nameMapping = { "id", "name", "price", "wantToSell", "imageFileName", "notes" };

		try
		{
			final ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

			csvWriter.writeHeader(csvHeader);

			for (final ArtWork artWork : artWorks)
			{
				csvWriter.write(artWork, nameMapping);
			}

			csvWriter.close();
		}
		catch (final IOException ex)
		{
			throw new ArtWorksExportException(FreeDesignConstants.EXPORT_ERROR, ex);
		}
	}
}
