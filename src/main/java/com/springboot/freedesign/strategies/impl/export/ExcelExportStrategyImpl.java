package com.springboot.freedesign.strategies.impl.export;

import com.springboot.freedesign.factories.CellValueFactory;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.services.ArtWorkService;
import com.springboot.freedesign.strategies.CellValueStrategy;
import com.springboot.freedesign.strategies.ExportStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Component
public class ExcelExportStrategyImpl implements ExportStrategy
{
	public static final String IDENTIFIER = "xlsx";
	public static final String CONTENT_TYPE = "application/octet-stream";
	public static final String ART_WORKS = "ArtWorks";

	@Autowired
	private CellValueFactory cellValueFactory;

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	@Autowired
	private ArtWorkService artWorkService;

	@Override
	public String getExtensionName()
	{
		return IDENTIFIER;
	}

	@Override
	public String getContentType()
	{
		return CONTENT_TYPE;
	}

	@Override
	public void export(final HttpServletResponse response) throws IOException
	{
		setUpResponse(response);
		writeHeaderLine();
		writeDataLines();

		final OutputStream outputStream = response.getOutputStream();

		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	private void writeHeaderLine()
	{
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet(ART_WORKS);

		final Row row = sheet.createRow(0);
		final CellStyle style = workbook.createCellStyle();
		final XSSFFont font = setUpFont();

		style.setFont(font);
		createCells(row, style);
	}

	private void writeDataLines()
	{
		int rowCount = 1;

		final CellStyle style = workbook.createCellStyle();
		final XSSFFont font = workbook.createFont();

		font.setFontHeight(14);
		style.setFont(font);

		final List<ArtWork> artWorks = artWorkService.getCreatedArtWorks();

		for (final ArtWork artWork : artWorks)
		{
			final Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, artWork.getId(), style);
			createCell(row, columnCount++, artWork.getName(), style);
			createCell(row, columnCount++, artWork.getPrice().toString(), style);
			createCell(row, columnCount++, artWork.isWantToSell(), style);
			createCell(row, columnCount++, artWork.getImageFileName(), style);
			createCell(row, columnCount, artWork.getNotes(), style);
		}
	}

	private XSSFFont setUpFont()
	{
		final XSSFFont font = workbook.createFont();

		font.setBold(true);
		font.setFontHeight(16);

		return font;
	}

	private void createCells(final Row row, final CellStyle style)
	{
		createCell(row, 0, "ID", style);
		createCell(row, 1, "Name", style);
		createCell(row, 2, "Price", style);
		createCell(row, 3, "Wish to sell", style);
		createCell(row, 4, "Image Name", style);
		createCell(row, 5, "Notes", style);
	}

	private void createCell(final Row row, final int columnCount, final Object value, final CellStyle style)
	{
		sheet.autoSizeColumn(columnCount);

		final Cell cell = row.createCell(columnCount);
		final CellValueStrategy strategy = cellValueFactory.obtainCellValueStrategyByType(value);

		strategy.setCellValue(cell, value);
		cell.setCellStyle(style);
	}

}
