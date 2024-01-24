/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoOperativitaEnum {
	INSERISCI("Inserisci"), MODIFICA("Modifica"), VISUALIZZA("Visualizza");

	private String des;

	private TipoOperativitaEnum(String des) {
		this.des = des;
	}

	public String getDes() {
		return des;
	}
}
