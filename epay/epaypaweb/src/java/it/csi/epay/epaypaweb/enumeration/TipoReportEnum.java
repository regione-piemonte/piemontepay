/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoReportEnum {

	PAGAMENTI("PAGAMENTI"),
	RENDICONTAZIONE("RENDICONTAZIONE");
	
	private String code;

	private TipoReportEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	static public TipoReportEnum fromId(String code) {
		for (TipoReportEnum en : TipoReportEnum.values()) {
			if (en.code.equals(code)) {
				return en;
			}
		}
		return null;
	}
}
