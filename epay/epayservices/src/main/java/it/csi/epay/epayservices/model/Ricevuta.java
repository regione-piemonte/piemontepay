/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

public enum Ricevuta {
	RECEIPT ( "receipt" ), RT ( "rt" ), FLUSSO_DI_RIVERSAMENTO ( "flusso di riversamento" );

	private final String tipologia;

	Ricevuta ( final String tipologia ) {
		this.tipologia = tipologia;
	}

	public String getTipologia () {
		return tipologia;
	}
}
