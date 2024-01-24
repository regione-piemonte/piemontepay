/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend.util;

public enum TipoAssociazioneMultibeneficiario {
	SECONDARIO ("1","Ente secondario - abilita altro ente ad effettuare versamenti su questo codice"),
	PRIMARIO("2","Ente primario - associa questo codice ad altro ente per abilitare versamento quota parte");
	
	private final String code;
	private final String id;
	
	private TipoAssociazioneMultibeneficiario(String id,String code) {
		this.id = id;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getId() {
		return id;
	}
	
	static public TipoAssociazioneMultibeneficiario fromId(String code) {
		for (TipoAssociazioneMultibeneficiario en : TipoAssociazioneMultibeneficiario.values()) {
			if (en.code.equals(code)) {
				return en;
			}
		}
		return null;
	}
}
