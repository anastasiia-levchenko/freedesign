package com.springboot.freedesign.strategies;

import org.apache.poi.ss.usermodel.Cell;


public interface CellValueStrategy
{
	boolean canApply(final Object value);

	void setCellValue(final Cell cell, final Object value);
}
