/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

public enum FiltroFlussiFlagInvioEnum {
	
	FLUSSO_BASE("stato_invio_flusso_base"),
	FLUSSO_ESTESO("stato_invio_flusso_esteso");
	
	private String nomeAttributo;
	
	public String getNomeAttributo() {
		return nomeAttributo;
	}

	private FiltroFlussiFlagInvioEnum(String nomeAttributo) {
		this.nomeAttributo = nomeAttributo;
	}

}
