/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.enums;

public enum TipoReportEnum {

	FLUSSI_COMPLETI_RICONCILIATI("RICON"),
	CONTABILE("CONT");
	
	private String code;

	private TipoReportEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static TipoReportEnum fromId(String code) {
		for (TipoReportEnum en : TipoReportEnum.values()) {
			if (en.code.equals(code)) {
				return en;
			}
		}
		return null;
	}
}
