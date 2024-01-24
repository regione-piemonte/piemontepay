/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

public enum StatoInvioFlussoEnum {
	
	NESSUNA_OPERAZIONE(0),
	DA_INVIARE(1),
	INVIATO(4),
	NON_INVIATO(5);
	
	private Integer codStato;
	
	public Integer getCodStato() {
		return codStato;
	}

	private StatoInvioFlussoEnum(Integer codStato) {
		this.codStato = codStato;
	}

}
