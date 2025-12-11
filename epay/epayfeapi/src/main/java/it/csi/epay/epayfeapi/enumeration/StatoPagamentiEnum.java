/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.enumeration;

@SuppressWarnings ( "unused" )
public enum StatoPagamentiEnum {
	IN_CORSO_DI_ACQUISIZIONE ( 1 ),
	ERRORE_IN_FASE_DI_ACQUISIZIONE ( 2 ),
	ACQUISITO ( 3 ),
	INOLTRATO ( 4 ),
	IN_CORSO_DI_REDAZIONE ( 5 ),
	ERRORE_IN_FASE_DI_INVIO ( 6 ),
	INVIATO ( 7 ),
	ESITO_RICEVUTO ( 8 ),
	ESITO_INOLTRATO ( 9 ),
	ERRORE_IN_FASE_DI_INOLTRO ( 10 ),
	BOZZA ( 11 );

	private final Integer id;

	StatoPagamentiEnum ( Integer id ) {
		this.id = id;
	}

	static public StatoPagamentiEnum fromId ( Integer id ) {
		for ( StatoPagamentiEnum en : StatoPagamentiEnum.values () ) {
			if ( en.id.equals ( id ) ) {
				return en;
			}
		}
		return null;
	}

	public Integer getId () {
		return id;
	}

}
