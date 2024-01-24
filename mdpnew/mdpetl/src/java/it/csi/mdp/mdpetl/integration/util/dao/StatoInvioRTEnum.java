/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

public enum StatoInvioRTEnum {
	
	DA_INVIARE(1),
	INVIATA(2),
	INVIATA_FLUSSO(3),
	ERRORE_INVIO(4),
	ERRORE_INVIO_FLUSSO(5);
	
	private StatoInvioRTEnum(int codiceStato) {
		this.codiceStato = codiceStato;
	}
	
	private int codiceStato;

	public int getCodiceStato() {
		return codiceStato;
	}

	public void setCodiceStato(int codiceStato) {
		this.codiceStato = codiceStato;
	}

}
