/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipologiaSoggettoEnum {
	PERSONA_FISICA("F", "Persona fisica"), PERSONA_GIURIDICA("G", "Persona giuridica");

	private final String id;
	private final String des;

	private TipologiaSoggettoEnum(String id, String des) {
		this.id = id;
		this.des = des;
	}

	public String getId() {
		return id;
	}

	public String getDes() {
		return des;
	}

	public static TipologiaSoggettoEnum fromId ( String id ) {
		for (TipologiaSoggettoEnum en : TipologiaSoggettoEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}
}
