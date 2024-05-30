package com.springboot.freedesign.controllers;

import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.services.ExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("artworks/export")
public class ExportArtWorksController
{
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	private ExportService exportService;

	@GetMapping
	public void exportArtWorks(final HttpServletResponse response, @RequestParam(name = "exportId") final String exportId)
	{
		logger.info(String.format(FreeDesignConstants.START_EXPORT, exportId));
		exportService.export(response, exportId);
	}

}
