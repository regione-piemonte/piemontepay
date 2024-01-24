/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.enumeration;

// TODO alla fine rinumerare e censire
public enum IssueEnum {
	//@formatter:off
	// esito positivo con possibili avvisi (warning)
	OK("000", "OK"),

	// esito negativo per motivazioni applicative
	GENERIC_APPLICATION_ERROR("100"),
	//
	XSD_ERROR_2ARGS("101", "Dati in input non congruenti con XSD: il valore \"{0}\" del campo XML {1} non ha il formato atteso"),
	TIPI_RICHIESTE_ASSENTI("119", "Necessario specificare almeno un tipo richiesta"),
	COD_FISCALE_ENTE_NON_CENSITO_O_NON_VALIDO_1ARG("102", "Codice fiscale ente: {0}"),
	COD_FISCALE_ENTE_NON_UNIVOCO_1ARG("118", "Codice fiscale ente: {0}"),
	COD_VERSAMENTO_NON_CENSITO_O_NON_VALIDO_1ARG("103", "Codice versamento: {0}"),
	APPLICATIVO_NON_CENSITO_O_NON_VALIDO_1ARG("104", "Codice fiscale ente: {0}"),
	CONFIG_APP_NON_CENSITA_O_NON_VALIDA_2ARG("105", "Codice fiscale ente: {0}, codice/i versamento: {1}"),
	CONFIG_APP_NON_UNIVOCA_2ARG("106", "Codice fiscale ente: {0}, codice/i versamento: {1}"),
	ID_MESSAGGIO_DUPLICATO_1ARG("112", "Id messaggio: {0}"),
	ID_MESSAGGIO_DUPLICATO_1ARG_WARNING("012", "Id messaggio: {0}"),
	NO_ALTRI_ENDPOINT("120", "Nessun altro endpoint disponibile"),
	//
	XML_NULL_1ARG("180", "Elemento XML atteso {0} non presente"),
	XML_ZERO_1ARG("181", "Il campo XML {0} deve essere valorizzato diverso da zero"),
	XML_MANDATORY_1ARG("182", "Il campo XML {0} deve essere valorizzato"),
	XML_MINLENGTH_MAXLENGTH_3ARG("183", "Il campo XML {0} deve essere valorizzato con non meno di {1} caratteri e non pi\u00F9 di {2} caratteri"),
	DB_NO_DATA_FOUND_3ARGS("184", "Nessun {0} mappato/a sulla base dati di EPAYWSO per {1}: {2}"),
	DB_MORE_THAN_ONE_RESULT_3ARGS("185", "Trovati pi\u00F9 di un {0} sulla base dati di EPAYWSO per {1}: {2}"),
	DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS("185", "Trovati pi\u00F9 di un {0} sulla base dati di EPAYWSO per {1}: {2} all'istante: {3}"),
	DB_MORE_THAN_ONE_RESULT_AT_TIME_5ARGS("185", "Trovati pi\u00F9 di un {0} sulla base dati di EPAYWSO per {1}: {2}, {3}: {4}"),

	// esito negativo per cause impreviste (problemi di tipo sistemistico)
	GENERIC_SYSTEM_ERROR("200"),
	//
	DB_ERROR_INSERT_RICHIESTA_1ARG("201", "Mancato inserimento della richiesta nella base dati EPAYWSO {0}"),
	DB_ERROR_UPDATE_RICHIESTA_1ARG("203", "Mancato update della richiesta nella base dati EPAYWSO {0}"),
	DB_ERROR_SAVE_ESITO_INVIO_1ARG("208", "Mancato salvataggio dell'esito invio nella base dati EPAYWSO {0}")
	;
	//@formatter:on

	private String cod;
	private String des;

	private IssueEnum(String cod) {
		this(cod, "");
	}

	private IssueEnum(String cod, String des) {
		this.cod = cod;
		this.des = des;
	}

	public String getCod() {
		return cod;
	}

	public String getDes() {
		return des;
	}

	static public IssueEnum fromCod(String cod) {
		for (IssueEnum en : IssueEnum.values()) {
			if (en.cod.equals(cod)) {
				return en;
			}
		}
		return null;
	}

}
