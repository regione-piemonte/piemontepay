package it.csi.epay.epayfeapi.enumeration;
/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


@SuppressWarnings ( "unused" )
public enum OrigineChiamata {

	CITTA_FACILE ( 1, "epayfeapi", "Città Facile" );

	private final Integer id;

	private final String codice;

	private final String descrizione;

	OrigineChiamata ( Integer id, String codice, String descrizione ) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public static OrigineChiamata findById ( Integer id ) throws IllegalArgumentException {
		for ( OrigineChiamata r : OrigineChiamata.values () ) {
			if ( r.id.equals ( id ) ) {
				return r;
			}
		}
		throw new IllegalArgumentException ( "\"id\" non corrisponde ad alcun Risultato." );
	}

	public Integer getId () {
		return id;
	}

	public String getCodice () {
		return codice;
	}

	public String getDescrizione () {
		return descrizione;
	}
}
