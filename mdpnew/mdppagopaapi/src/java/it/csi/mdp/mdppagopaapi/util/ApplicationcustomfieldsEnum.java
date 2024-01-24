/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util;

/**
 *
 */
//public enum ApplicationcustomfieldsEnum {
//    IDENTIFICATIVODOMINIO ( "identificativoDominio" ),
//        IDENTIFICATIVOINTERMEDIARIOPA ( "identificativointermediarioPA" ),
//        ENDPOINTSERVIZINODO ( "endpointServiziNodo" ),
//        IDENTIFICATIVOSTAZIONEINTERMEDIARIOPA ( "identificativoStazioneIntermediarioPA" ),
//        FLAGINTERMEDIATO ( "flagIntermediato" ),
//        INTERMEDIATO ( "intermediato" ), //Non esiste come fieldName nella tabella Applicationcustomfields
//        IBANACCREDITO ( "ibanAccredito" ),
//        DENOMINAZIONEBENEFICIARIO ( "denominazioneBeneficiario" ),
//        STABILIMENTO ( "stabilimento" ),
//        CONTOPOSTALE ( "contoPostale" ), //Non esiste come fieldName nella tabella Applicationcustomfields
//        FISCALCODEPA ( "fiscalCodePA" ); //Non esiste come fieldName nella tabella Applicationcustomfields

public enum ApplicationcustomfieldsEnum {

	IDENTIFICATIVODOMINIO ( "identificativoDominio" ),
	IDENTIFICATIVOINTERMEDIARIOPA ( "identificativointermediarioPA" ),
	IDENTIFICATIVOSTAZIONEPA ( "identificativostazionePA" ),
	ENDPOINTSERVIZINODO ( "endpointServiziNodo" ),
	IDENTIFICATIVOSTAZIONEINTERMEDIARIOPA ( "identificativoStazioneIntermediarioPA" ),
	FLAGINTERMEDIATO ( "flagIntermediato" ),
	PASSPHRASE ( "passphrase" ),
	CODICEFISCALE ( "codiceFiscale" ),
	DENOMINZIONEBENEFICIARIO ( "denominazioneBeneficiario" ),
	STABILIMENTO ( "stabilimento" ),
	IBANACCREDITO ( "ibanAccredito" ),
	IBANAPPOGGIO ( "ibanAppoggio" ),
	DATISPECIFICIRISCOSSIONE ( "datiSpecificiRiscossione" );

	private final String codice;

	public String getCodice () {
		return codice;
	}

	ApplicationcustomfieldsEnum ( String codice ) {
		this.codice = codice;
	}

}
