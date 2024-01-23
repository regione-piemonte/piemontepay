/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

public enum StatiRPTEnum {
	IUV_APPENA_CREATO(10), IN_ATTESA_PSP(20), ANNULLATA(30), COMPLETATA(40) , RIFIUTATA(50), ATTESA_RT(60), RT_ERRATA(70), RT_VERIFICATA(80);
	
	private int id;
	
	StatiRPTEnum(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}
	
}
