/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.dto.security;

/**
 * Enumerazione contenente tutti gli Scopes gestiti dall'applicativo
 */
public enum Scopes {
	VERIFICA_STATO ( "Monitoraggio dello stato applicativo" ),
	CREAZIONE_IUV ( "Creazione IUV" ),
	CREAZIONE_IUV_MULTIBEN ( "Creazione IUV multibeneficiario" ),
	PLATFORM_MANAGEMENT ( "Gestione della piattaforma (backoffice)" ),
	STAMPA_RT_GE ( "Stampa RT da gestionale esterno" ),
	RICHIESTA_STATO_POS_DEB ( "Ritorna lo stato di una posizione debitoria" ),
	RICHIESTA_DATI_PAGAMENTO ( "Ritorna dati su il pagamento" ),
	STAMPA_AVVISO_PAGAMENTO ( "Stampa avviso pagamento" ),
    STAMPA_XML_MB ( "Stampa XML Marca da Bollo" );

	private final String descrizione;

	Scopes ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public String getDescrizione () {
		return descrizione;
	}

}
