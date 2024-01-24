/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoFormatoFileEnum {
	//@formatter:off
	CSV(1, "text/csv"),
	XLSX(2, "application/vnd.ms-excel");
	//@formatter:on

	private Integer id;
	private String contentType;

	private TipoFormatoFileEnum(Integer id, String contentType) {
		this.id = id;
		this.contentType = contentType;
	}

	public Integer getId() {
		return id;
	}

	public String getContentType() {
		return contentType;
	}

	public static TipoFormatoFileEnum fromId ( Integer id ) {
		for (TipoFormatoFileEnum en : TipoFormatoFileEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}
}
