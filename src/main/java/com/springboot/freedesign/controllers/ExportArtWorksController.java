package com.springboot.freedesign.controllers;

import com.springboot.freedesign.services.ExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;



@Controller
@RequestMapping("artworks/export")
public class ExportArtWorksController
{
	@Autowired
	private ExportService exportService;

	@GetMapping
	public void exportArtWorks(final HttpServletResponse response, @RequestParam(name = "exportId") final String exportId)
			throws IOException
	{
		exportService.export(response, exportId);
	}

}
