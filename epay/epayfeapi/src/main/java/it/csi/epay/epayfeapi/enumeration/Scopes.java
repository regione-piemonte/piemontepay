/*
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 *
 * SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.enumeration;

@SuppressWarnings ( "unused" )
public enum Scopes {

	DEBT_POSITION ( "Elenco pagamenti DEBT POSITION" ), // CDU 1

	TIPOLOGIE_VERS_SPONTANEO ( "Elenco tipologie versamento spontaneo" ), // CDU 5

	PAGAMENTO_SPONTANEO ( "Effettuare pagamento spontaneo" ), // CDU 6

	PAGAMENTO_URL ( "Ottiene l'url pagoPA di un pagamento" ), // CDU 7

	RICEVUTA_PAGAMENTO ( "Ricevuta del pagamento" ), // CDU 4

	FLAG_ARCHIVIAZIONE ( "Flag preferenza di archiviazione quietanze" ), // CDU 8

	GET_ARCHIVIAZIONE ( "Leggi preferenza di archiviazione quietanze" ), // CDU 9

	AVVISO_PAGAMENTO ( "Avviso di pagamento Citta' Facile" ), // CDU 3

	CREAZIONE_IUV ( "Richiesta di creazione IUV" ), // CDU 2

	STAMPA_AVVISO_PAGAMENTO ( "Stampa dell'avviso pagamento" ); // CDU 4

	private final String description;

	Scopes ( String description ) {
		this.description = description;
	}

	public String getDescription () {
		return description;
	}
}
