/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

public enum CodiceEsitoPagamento {
		PAGAMENTO_ESEGUITO ( 0 ),
		PAGAMENTO_NON_ESEGUITO ( 1 );

	private Integer id;

	private CodiceEsitoPagamento ( int id ) {
		this.id = id;
	}

	public static CodiceEsitoPagamento findById ( Integer id ) throws IllegalArgumentException {
		for ( CodiceEsitoPagamento r: CodiceEsitoPagamento.values () ) {
			if ( r.id.equals ( id ) ) {
				return r;
			}
		}
		throw new IllegalArgumentException ( "\"id\" non corrisponde a nessun Risultato." );
	}

	public Integer getId () {
		return id;
	}
}
