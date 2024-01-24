/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeneratorXLSX extends SpreadsheetGenerator {
	protected Workbook wb;
	protected Sheet sheet;
	protected CreationHelper createHelper;
	protected ArrayList<CellStyle> cellStyles;
	protected ByteArrayOutputStream dataOut;

	public GeneratorXLSX() {
		super();
		wb = new XSSFWorkbook();
		sheet = wb.createSheet();
		createHelper = wb.getCreationHelper();
		cellStyles = new ArrayList<>();
		dataOut = new ByteArrayOutputStream();
	}

	@Override
    protected void insertColumnTitles() {
		Row row = sheet.createRow(0);
		for (int c = 0, len = columns.size(); c < len; c++) {
			SpreadsheetColumn column = columns.get(c);
			String columnTitle = (column != null) ? column.getTitle() : null;
			row.createCell(c).setCellValue(columnTitle);
			CellStyle cellStyle = null;
			if (column.getFormat() != null) {
				short dataFormat = createHelper.createDataFormat().getFormat(column.getFormat());
				cellStyle = wb.createCellStyle();
				cellStyle.setDataFormat(dataFormat);
			}
			cellStyles.add(cellStyle);
		}
	}

	@Override
    protected void formatRow() {
		Row row = sheet.createRow(sheet.getLastRowNum() + 1);
		for (int c = 0, len = columns.size(); c < len; c++) {
			SpreadsheetColumn column = columns.get(c);
			Object value = columnValues.get(column.getId());

			Cell cell = row.createCell(c);
			if (value != null) {
				if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Float) {
                    cell.setCellValue((Float) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                } else {
                    cell.setCellValue("ERRORE: TIPO DI DATO IMPREVISTO: " + value.getClass().getName());
                }
			}

			CellStyle cellStyle = cellStyles.get(c);
			if (cellStyle != null) {
				cell.setCellStyle(cellStyle);
			}
		}
        for ( int c = 0, len = columns.size (); c < len; c++ ) {
            sheet.autoSizeColumn ( c );
        }
		columnValues = null;
	}

	@Override
    public byte[] getData() throws IOException {
		if (columnValues != null) {
			formatRow();
		}
		wb.write(dataOut);
		dataOut.close();
		return dataOut.toByteArray();
	}

}
