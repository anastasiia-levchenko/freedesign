package com.springboot.freedesign.factories.impl;

import com.springboot.freedesign.factories.CellValueFactory;
import com.springboot.freedesign.strategies.CellValueStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CellValueFactoryImpl implements CellValueFactory
{
	@Autowired
	private List<CellValueStrategy> cellValueStrategies;

	@Autowired
	@Qualifier("cellValueStringStrategyImpl")
	private CellValueStrategy defaultStrategy;

	@Override
	public CellValueStrategy obtainCellValueStrategyByType(final Object value)
	{
		return cellValueStrategies.stream().filter(strategy -> strategy.canApply(value)).findFirst().orElse(defaultStrategy);
	}
}
