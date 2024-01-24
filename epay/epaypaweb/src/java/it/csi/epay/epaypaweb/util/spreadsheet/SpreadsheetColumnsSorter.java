/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.util.Comparator;

public class SpreadsheetColumnsSorter implements Comparator<SpreadsheetColumn> {

	@Override
	public int compare(SpreadsheetColumn col1, SpreadsheetColumn col2) {
		return
			col1.getOrdinal() < col2.getOrdinal() ? -1 :
			col1.getOrdinal() == col2.getOrdinal() ? 0 : 1;
	}
}
