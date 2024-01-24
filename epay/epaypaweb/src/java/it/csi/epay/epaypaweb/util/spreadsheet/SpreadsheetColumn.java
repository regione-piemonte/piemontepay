/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

public class SpreadsheetColumn {
	private String id;
	private int ordinal;
	private String title;
	private String format;

	public SpreadsheetColumn(String id, String title) {
		this.id = id;
		this.title = title;
	}

	public SpreadsheetColumn(String id, String title, String format) {
		this.id = id;
		this.title = title;
		this.format = format;
	}

	public SpreadsheetColumn(String id, String title, int ordinal) {
		this.id = id;
		this.title = title;
		this.ordinal = ordinal;
	}

	public SpreadsheetColumn(String id, String title, String format, int ordinal) {
		this.id = id;
		this.title = title;
		this.format = format;
		this.ordinal = ordinal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
