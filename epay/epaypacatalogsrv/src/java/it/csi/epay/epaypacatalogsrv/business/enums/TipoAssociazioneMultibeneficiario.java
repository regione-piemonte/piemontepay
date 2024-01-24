/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.enums;

public enum TipoAssociazioneMultibeneficiario {
	SECONDARIO("1","Abilita gestione esterna di questo Codice Versamento"),
	PRIMARIO("2","Associa questo Codice Versamento a Ente Beneficiario Secondario");
    
	
	private String code;
	private String id;
	
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
