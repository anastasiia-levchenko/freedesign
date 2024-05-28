package com.springboot.freedesign.factories;

import com.springboot.freedesign.strategies.CellValueStrategy;


public interface CellValueFactory
{
	CellValueStrategy obtainCellValueStrategyByType(final Object value);
}
