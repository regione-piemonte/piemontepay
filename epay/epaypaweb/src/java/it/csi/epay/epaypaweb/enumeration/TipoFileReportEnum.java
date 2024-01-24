/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoFileReportEnum {

	CSV("CSV"),
	XLS("XLS");

	private String code;

	private TipoFileReportEnum (String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	static public TipoFileReportEnum fromId(String code) {
		for (TipoFileReportEnum en : TipoFileReportEnum.values()) {
			if (en.code.equals(code)) {
				return en;
			}
		}
		return null;
	}
}
