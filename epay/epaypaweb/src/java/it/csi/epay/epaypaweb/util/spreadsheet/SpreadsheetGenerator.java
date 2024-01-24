/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SpreadsheetGenerator {
	protected Map<String, Object> columnValues;
	protected List<SpreadsheetColumn> columns;

	protected SpreadsheetGenerator () {
		columns = new ArrayList<> ();
	}

	public void addColumn(SpreadsheetColumn column) {
		if (columnValues == null)
			columns.add(column);
		else
			throw new IllegalStateException("Define columns before inserting values");
	}

	public void newRow() {
		if (columns.size() > 0) {
			if (columnValues != null)
				formatRow();
			else {
				Collections.sort(columns, new SpreadsheetColumnsSorter());
				insertColumnTitles();
			}
			columnValues = new HashMap<> ();
		} else
			throw new IllegalStateException("No columns defined");
	}

	public void setColumnValue(String id, Object value) {
		if (columns != null) {
			boolean found = false;
			for (SpreadsheetColumn col : columns) {
				if (id.equals(col.getId())) {
					found = true;
					break;
				}
			}
			if (found)
				columnValues.put(id, value);
			else
				throw new IllegalArgumentException("Column " + id + " does not exists");

		} else
			throw new IllegalStateException("Need to create a new row first");
	}

	protected abstract void insertColumnTitles ();

	protected abstract void formatRow ();

	public abstract byte [] getData () throws IOException;

}
