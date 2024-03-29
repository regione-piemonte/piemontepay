/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum ParseErrorEnum {
	PARSE_ERROR_FILE_VUOTO,
	PARSE_ERROR_ASSENZA_DATI,
	PARSE_ERROR_NUMERO_POSIZIONI_DEBITORIE,
	PARSE_ERROR_TOTALE_IMPORTO_POSIZIONI_DEBITORIE,
	PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_SECONDARIE,
	PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_PRINCIPALI_E_SECONDARIE,
	PARSE_ERROR_INTESTAZIONE,
	PARSE_ERROR_NUMERO_COLONNE,
	PARSE_ERROR_MIN_MAX_LENGTH,
	PARSE_ERROR_DATE,
	PARSE_ERROR_TYPE_OR_FORMAT,
	PARSE_ERROR_MANDATORY,
	PARSE_ERROR_FIELD_NOT_REQUESTED,
	PARSE_ERROR_AT_LEAST_ONE_FIELD_REQUESTED,
	PARSE_ERROR_TIPO_SOGGETTO_DEBITORE,
	PARSE_ERROR_COGNOME_NOME_SOGGETTO_DEBITORE,
	PARSE_ERROR_RAGIONE_SOCIALE_SOGGETTO_DEBITORE,
	PARSE_ERROR_TIPO_AGGIORNAMENTO_POSIZIONE_DEBITORIA,
	PARSE_ERROR_EMAIL,
	PARSE_ERROR_ANONIMO_POS_DEBITORIA,
	PARSE_ERROR_DUPLICATE_POS_DEBITORIA;
}
