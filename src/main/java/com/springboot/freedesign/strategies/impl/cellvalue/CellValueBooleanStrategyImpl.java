package com.springboot.freedesign.strategies.impl.cellvalue;

import com.springboot.freedesign.strategies.CellValueStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;


@Component
public class CellValueBooleanStrategyImpl implements CellValueStrategy
{

	@Override
	public boolean canApply(final Object value)
	{
		return value instanceof Boolean;
	}

	@Override
	public void setCellValue(final Cell cell, final Object value)
	{
		cell.setCellValue((Boolean) value);
	}
}
