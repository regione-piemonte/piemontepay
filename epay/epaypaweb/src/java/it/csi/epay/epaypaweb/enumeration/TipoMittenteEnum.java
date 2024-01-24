/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoMittenteEnum {
	PERSONA_GIURIDICA("G", "Persona giuridica"), CODICE_ABI("A", "Codice ABI"), CODICE_BIC("B", "Codice BIC");

	private String id;
	private String des;

	private TipoMittenteEnum(String id, String des) {
		this.id = id;
		this.des = des;
	}

	public String getId() {
		return id;
	}

	public String getDes() {
		return des;
	}

	static public TipoMittenteEnum fromId(String id) {
		for (TipoMittenteEnum en : TipoMittenteEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}
}
