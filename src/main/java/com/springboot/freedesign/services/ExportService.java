package com.springboot.freedesign.services;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface ExportService
{
	void export(final HttpServletResponse response, final String exportId);
}
