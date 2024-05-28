package com.springboot.freedesign.factories.impl;

import com.springboot.freedesign.factories.ExportStrategyFactory;
import com.springboot.freedesign.strategies.ExportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExportStrategyFactoryImpl implements ExportStrategyFactory
{
	@Autowired
	private List<ExportStrategy> exportStrategies;

	@Override
	public ExportStrategy obtainStrategyByIdentifier(final String identifier)
	{
		return exportStrategies.stream().filter(strategy -> strategy.getExtensionName().equals(identifier)).findFirst().get();
	}
}
