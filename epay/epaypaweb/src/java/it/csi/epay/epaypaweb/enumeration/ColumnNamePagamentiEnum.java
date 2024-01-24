/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum ColumnNamePagamentiEnum {

		//@formatter:off
	//	ID_NOTIFICA_PAGAMENTO,
	//	ID_FLUSSO,
	//	ID_POSIZIONE_DEBITORIA,
		IUV,
		CAUSALE,
		COGNOME,
		DESCRIZIONE_CAUSALE_VERSAMENTO,
		IMPORTO_PAGATO,
		IMPORTO_DOVUTO,
		DATA_ESITO_PAGAMENTO,
		DATA_SCADENZA,
		DATA_PAGAMENTO,
		STATO_PAGAMENTO,
		COD_VERSAMENTO,
		
		
		ID_FISCALE_DEBITORE,
		COGNOME_OR_RAGIONE_SOCIALE_DEBITORE,
		NOME_DEBITORE,
		CONCAT_COGNOME_NOME_OR_RAGIONE_SOCIALE_DEBITORE,

	    //--------------------------------//
	    //RDI-048 - START
	    //--------------------------------//    	
		//DATI CHE STANNO SULLA CORRELATA FLUSSO
		PAGAMENTI_SPONTANEI,
		DATA_INSERIMENTO,
		TIPO_FLUSSO,
		UTENTE_ULTIMA_VARIAZIONE,
		STATO_FLUSSO,
//		COD_VERSAMENTO,
		CODICE_ESITO,
		DATA_ULTIMA_VARIAZIONE,
		DETTAGLIO_ESITO,
		ID_CODICE_VERSAMENTO,
		ID_ENTE,
		ID_MESSAGGIO,
	    //--------------------------------//
	    //RDI-048 - STOP
	    //--------------------------------//    

		//@formatter:on

}
