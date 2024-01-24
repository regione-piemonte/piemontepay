/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.enums;

public enum TipoCampoFiltroEnum {
	
	STRING ("STRING"),
	INTEGER ("INTEGER"),
	LONG ("LONG"),
	DATE ("DATE");

	private String code;

	private TipoCampoFiltroEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	static public TipoCampoFiltroEnum fromId(String code) {
		for (TipoCampoFiltroEnum en : TipoCampoFiltroEnum.values()) {
			if (en.code.equals(code)) {
				return en;
			}
		}
		return null;
	}
}
