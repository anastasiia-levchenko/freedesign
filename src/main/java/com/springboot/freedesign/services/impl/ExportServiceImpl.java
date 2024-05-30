package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.factories.ExportStrategyFactory;
import com.springboot.freedesign.services.ExportService;
import com.springboot.freedesign.strategies.ExportStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ExportServiceImpl implements ExportService
{
	@Autowired
	private ExportStrategyFactory exportStrategyFactory;

	@Override
	public void export(final HttpServletResponse response, final String exportId)
	{
		final ExportStrategy exportStrategy = exportStrategyFactory.obtainStrategyByIdentifier(exportId);

		exportStrategy.export(response);
	}
}
