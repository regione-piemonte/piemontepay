/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.enums;

public enum TipoFileReportEnum {
	CSV("CSV"),
	XLS("XLS");
	
	private String codice;
	
	private TipoFileReportEnum(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}
	
	public static TipoFileReportEnum fromId(String codice) {
		for (TipoFileReportEnum en : TipoFileReportEnum.values()) {
			if (en.codice.equals(codice)) {
				return en;
			}
		}
		return null;
	}
	
}
