/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoAggiornamentoPosizioneDebitoriaEnum {
	ANNULLAMENTO("A", "Annullamento"), MODIFICA("M", "Modifica");

	private String id;
	private String des;

	private TipoAggiornamentoPosizioneDebitoriaEnum(String id, String des) {
		this.id = id;
		this.des = des;
	}

	public String getId() {
		return id;
	}

	public String getDes() {
		return des;
	}

	public static TipoAggiornamentoPosizioneDebitoriaEnum fromId ( String id ) {
		for (TipoAggiornamentoPosizioneDebitoriaEnum en : TipoAggiornamentoPosizioneDebitoriaEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}

}
