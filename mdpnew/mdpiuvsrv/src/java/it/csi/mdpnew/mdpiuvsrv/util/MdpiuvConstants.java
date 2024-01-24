/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.util;

public interface MdpiuvConstants {

	public static final String LOGGER_PREFIX = "mdpiuvsrv";
	public static final String MDPIUV_ROOT_LOG_CATEGORY = "mdpiuvsrv";
	public static final String MDPIUV_WEBSERVICES_LOG_CATEGORY = MDPIUV_ROOT_LOG_CATEGORY + ".webservices";
	
	// errori
	public static final String ERRORE_ID_APPLICATION_IS_NECESSARY = "ID Application e' obbligatorio";
	public static final String ERRORE_ID_APPLICATION_NOT_EXIST = "ID Application non e' presente nella base dati";
	public static final String ERRORE_CONFIGURATION_APPLICATION = "ID Application non e' correttamente configurato nella base dati";
	public static final String ERRORE_IUV_ATTRIBUTE_ASSIGN = "Errore in assegnazione del progressivo";
	public static final String ERRORE_CKDGTS_ASSIGN = "Errore durante il calcolo del check digit";
	public static final String ERRORE_NO_MULTI_IUV = "Inserire la quantita' di IUV che si vuole generare";
	public static final String ERRORE_MAX_MULTI_IUV = "Non e' possibile generare piu' di 100 IUV per volta";
	public static final String ERRORE_LUNGH_DETTAGLIO_PAGAM = "Lunghezza della stringa ID del dettaglio di pagamento non corretta";
	public static final String ERRORE_CARAT_NO_DETTAGLIO_PAGAM = "Carattere non ammesso nel dettaglio pagamento";

	//MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
    public static final String ERRORE_KEYDB = "Il FIle skeydb.txt non e' stato trovato";
    public static final String ERRORE_ACCESSO_DATI = "Errore di accesso ai dati";
    public static final String ERRORE_GENERICO = "General Error";
    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP
}
