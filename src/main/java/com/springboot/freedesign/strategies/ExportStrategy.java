package com.springboot.freedesign.strategies;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public interface ExportStrategy
{
	String getExtensionName();

	String getContentType();

	void export(final HttpServletResponse response);

	default void setUpResponse(final HttpServletResponse response)
	{
		final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		final String currentDateTime = dateFormatter.format(new Date());
		final String headerValue = "attachment; filename=artworks_" + currentDateTime + "." + this.getExtensionName();

		response.setContentType(getContentType());
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, headerValue);
	}
}
