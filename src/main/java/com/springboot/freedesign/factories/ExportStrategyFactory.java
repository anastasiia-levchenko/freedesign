package com.springboot.freedesign.factories;

import com.springboot.freedesign.strategies.ExportStrategy;


public interface ExportStrategyFactory
{
	ExportStrategy obtainStrategyByIdentifier(final String identifier);
}
