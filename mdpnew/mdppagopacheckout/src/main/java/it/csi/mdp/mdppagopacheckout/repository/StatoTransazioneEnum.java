/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

@SuppressWarnings ( "unused" )
public enum StatoTransazioneEnum {
	NOT_INITIALIZED ( 0L, "Not initialized" ),
	INITIALIZED ( 1L, "Initialized" ),
	CONFIGURED ( 2L, "Configured" ),
	STARTED ( 3L, "Started" ),
	SUCCESFUL ( 4L, "Successful" ),
	UNSUCCESFUL ( 5L, "Unsuccessful" ),
	CANCELED ( 6L, "Canceled" ),
	REFOUNDED ( 7L, "Refunded" ),
	TO_BE_CONFIRMED ( 8L, "To be confirmed" ),
	ATTESA_RT ( 9L, "Attesa RT" );

	private final Long codice;

	private final String descrizione;

	StatoTransazioneEnum ( Long codice, String descrizione ) {
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public Long getCodice () {
		return codice;
	}

	public String getDescrizione () {
		return descrizione;
	}

}
