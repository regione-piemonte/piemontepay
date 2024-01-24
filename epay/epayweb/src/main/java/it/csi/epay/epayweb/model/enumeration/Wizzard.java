/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.model.enumeration;


/**
 * tipi di percorsi
 * - paga (1) / verifica (2) / stampa (3)
 * - Accesso libero (0) o autenticato (1)
 * - paga con iuv (0) o senza  (1)
 * - paga (autenticato) conto terzi con iuv (2) o senza (3)
 *  
  
 */
public enum Wizzard {
	/* codice, descrizione, url di ritorno del wisp (errore), url di ritorno del wisp (ok), url di ritorno a fine pagamento*/
	PAGA_FREE_CONIUV	(100, "paga con iuv accesso libero",            "/accessoLibero/pagaConIuv/riepilogo",   "/payment/execution",  "/accessoLibero/pagaConIuv/conclusione"),
	PAGA_FREE_NOIUV		(101, "paga senza iuv accesso libero",          "/accessoLibero/pagaSenzaIuv/riepilogo", "/payment/execution" , "/accessoLibero/pagaSenzaIuv/conclusione"),
	PAGA_AUTH_CONIUV	(110, "paga con iuv autenticato",               "/private/pagaConIuv/riepilogo",         "/payment/execution" , "/private/pagaConIuv/conclusione"),
	PAGA_AUTH_NOIUV		(111, "paga senza iuv autenticato",             "/private/pagaSenzaIuv/riepilogo",       "/payment/execution" , "/private/pagaSenzaIuv/conclusione"),
/*
 * 	PAGA_AUTH_CONIUV_3	(112, "paga con iuv autenticato conto terzi",   "", "/payment/execution", ""),
 *  PAGA_AUTH_NOIUV_3	(113, "paga senza iuv autenticato conto terzi", "", "/payment/execution", ""),
 */
        PAGA_AUTH_CONIUV_RF ( 114, "paga con iuv altro riferimento autenticato", "/private/pagaAltroRiferimentoConIuv/riepilogo", "/payment/execution",
            "/private/pagaAltroRiferimentoConIuv/conclusione" ),

	VERIFICA_FREE		(200, "verifica pagamento iuv accesso libero"),
	VERIFICA_AUTH		(210, "verifica pagamento iuv autenticato"),
	STAMPA_FREE			(301, "stampa bollettino accesso libero"),
	STAMPA_AUTH			(311, "stampa bollettino autenticato");
	
	private int codice;
	private String descrizione;
	private String urlWispBack;
	private String urlWispReturn;
	private String urlEndPaymant;	
	
	private Wizzard(int codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}
	
	private Wizzard(int codice, String descrizione, String urlWispBack, String urlWispReturn, String urlEndPaymant) {
		this.codice = codice;
		this.descrizione = descrizione;
		
		this.urlWispBack = urlWispBack;
		this.urlWispReturn = urlWispReturn;
		this.urlEndPaymant = urlEndPaymant;
	}

	/**
	 * @return the codice
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @return the urlWispBack
	 */
	public String getUrlWispBack() {
		return urlWispBack;
	}

	/**
	 * @return the urlWispReturn
	 */
	public String getUrlWispReturn() {
		return urlWispReturn;
	}

	/**
	 * @return the urlPaymantSuccess
	 */
	public String getUrlEndPaymant() {
		return urlEndPaymant;
	}
}
