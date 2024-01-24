/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.io.IOException;

import it.csi.epay.epaypaweb.presentation.Constants;

public class GeneratorCSV extends SpreadsheetGenerator {
	private char columnSeparator;
	private String endOfLine;
	protected StringBuilder dataOut;

	public GeneratorCSV() {
		super();
		columnSeparator = ';';
		endOfLine = "\r\n";
		dataOut = new StringBuilder();
	}

	protected void insertColumnTitles() {
		for (SpreadsheetColumn column : columns) {
			if (column.getTitle() != null) {
				dataOut.append(column.getTitle());
			}
			dataOut.append(columnSeparator);
		}
		dataOut.append(endOfLine);
	}

	protected void formatRow() {
		StringBuilder formattedRow = new StringBuilder();
		for (SpreadsheetColumn column : columns) {
			Object value = columnValues.get(column.getId());
			if (value != null) {
				String formattedValue = null;
				if (column.getFormat() != null && !"".equals(column.getFormat()))
					formattedValue = String.format(column.getFormat(), value);
				else
					formattedValue = value.toString();

				formattedValue = formattedValue.replace("\"", "\"\"").replace("\r\n", "\n").replace('\t', ' ');

				if (formattedValue.contains(new String(new char[] { columnSeparator })) || formattedValue.contains("\n"))
					formattedRow.append("\"").append(formattedValue).append("\"");
				else
					formattedRow.append(formattedValue);
			}
			formattedRow.append(columnSeparator);
		}
		formattedRow.append(endOfLine);

		columnValues = null;

		dataOut.append(formattedRow.toString());
	}

	public byte[] getData() throws IOException {
		if (columnValues != null) {
			formatRow();
		}
		return dataOut.toString().getBytes(Constants.ENCODING);
	}

}
