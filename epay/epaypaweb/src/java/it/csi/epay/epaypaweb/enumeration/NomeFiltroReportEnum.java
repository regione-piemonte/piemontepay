/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum NomeFiltroReportEnum {

	TIPO_PAGAMENTO ( "TIPO_PAGAMENTO" ),
	ID_CODICE_VERSAMENTO ( "ID_CODICE_VERSAMENTO" ),
	COGNOME ( "COGNOME" ),
	CODICE_FISCALE ( "CODICE_FISCALE" ),
	IUV ( "IUV" ),
	DATA_PAGAMENTO_INIZIO ( "DATA_PAGAMENTO_INIZIO" ),
	DATA_PAGAMENTO_FINE ( "DATA_PAGAMENTO_FINE" ),
	DATA_RICEZIONE_INIZIO ( "DATA_RICEZIONE_INIZIO" ),
	DATA_RICEZIONE_FINE ( "DATA_RICEZIONE_FINE" ),
	COSTI_NOTIFICA ( "COSTI_NOTIFICA" ),
	CODICE_DESCRIZIONE_PSP ( "CODICE_DESCRIZIONE_PSP" );

	private final String code;

	private NomeFiltroReportEnum ( String code ) {
		this.code = code;
	}

	public String getCode () {
		return code;
	}

	public static NomeFiltroReportEnum fromId ( String code ) {
		for ( NomeFiltroReportEnum en : NomeFiltroReportEnum.values () ) {
			if ( en.code.equals ( code ) ) {
				return en;
			}
		}
		return null;
	}
}
