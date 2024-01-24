/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 *
 * @author vsgro
 *
 *         000 = OK da 001 a 099: codici da non utilizzare --> sono esiti abbastanza statici fissati dal sistema da 100 a 199: codici da utilizzare per gli
 *         errori applicativi (il 100 e' l'errore applicativo generico, quelli specializzati dal 101) da 200 in poi: codici da utilizzare per gli errori di
 *         sistema (il 200 e' l'errore generico di sistema, quelli specializzati dal 201)
 */
public abstract class CostantiErrori {

    final static Logger logger = LogManager.getLogger(CostantiErrori.class);

    public static final String TIPOLOGIA_FATAL = "FATAL";
    public static final String TIPOLOGIA_ERROR = "ERROR";
    public static final String TIPOLOGIA_WARNING = "WARNING";
    public static final String TIPOLOGIA_MESSAGE = "MESSAGE";

    public static final String ERRORE_DI_SISTEMA = "200";
    public static final String ERRORE_DATI_MANCANTI = "201";
    public static final String ERRORE_SPACCHETTAMENTO = "202";
    public static final String ERRORE_SPACCHETTAMENTO_ESISTENTE = "203";
    // error
    public static final String ENTE_NON_PRESENTE = "220";
    public static final String ELABORAZIONE_NON_PRESENTE = "221";
    public static final String SCHEDULAZIONE_NON_ATTIVA = "222";
    public static final String NON_RICONCILIABILE_IMPORTI = "223";
    public static final String NO_DATI_RISCOSSIONE = "224";
    public static final String NO_DATI_ACCERTAMENTO = "225";
    public static final String NUM_MAX_TENTATIVI = "226";
    public static final String NO_RIF_CONTABILI = "227";
    public static final String NO_CODICE_VERSAMENTO = "228";
    public static final String DATI_VERSAMENTO_MANCANTI = "229";
    public static final String NO_AGGIORNAMENTO_DATI_PROVVISORI_SINTESI = "230";
    public static final String DATI_PROVVISORI_DUPLICATO_SU_SINTESI = "231";
    public static final String WS_ESITO_KO_DEFAULT = "240";
    public static final String WS_ESITO_KO_IS_EMPTY = "241";
    public static final String WS_ESITO_KO_NO_PARAM = "242";
    public static final String ERRORE_SALVATAGGIO_PROVVISORI = "243";
    public static final String WS_ESITO_KO_PARAM_REQUIRED = "244";
    public static final String TOTALE_PAGAMENTI_INTERMEDIATI = "245";
    public static final String RECORD_SINTESI_ASSENTI = "247";
    public static final String RECORD_DETTGLIO_ASSENTI = "248";
    public static final String ALCUNI_FLUSSI_NON_SPACCHETTATI = "249";
    public static final String FLUSSO_ORIGINE_NON_INSERITO = "250";
    public static final String FLUSSO_ORIGINE_PRESENTE = "251";
    public static final String FLUSSO_ORIGINE_NULLFIELD = "252";

    // warning
    public static final String ENTE_NO_PLURIMANDATARIO = "300";
    public static final String SCHEDULAZIONE_GG_NON_CORRETTO = "301";
    public static final String ENTE_NON_RICONCILIABILE = "302";
    public static final String ENTE_NO_ACCERTAMENTO = "303";
    public static final String PSP_NON_VALORIZZATO = "304";
    public static final String PSP_NON_RICONCILIABILE = "305";
    public static final String ERRORE_NO_PROVVISORI = "306";
    public static final String CODICI_VERSAMENTO_DA_ESCLUDERE = "307";
    public static final String SCHEDULAZIONE_GG_NON_VERIFICABILE = "308";
    public static final String FLUSSI_NON_PRESENTI = "309";
    public static final String ELABORAZIONE_TERMINATA_IN_ERRORE = "310";
    // messages
    public static final String PARAM_ENTE_NON_PRESENTE = "401";
    public static final String PARAM_RIELABORAZIONE_NON_PRESENTE = "402";

    public static final String WS_ESITO_OK_DEFAULT = "000";
    public static final String WS_ESITO_OK_NO_RESULT = "404";
    public static final String NESSUN_FLUSSO_ELABORATO = "405";

    public static final String DTO_ERRORE_TIPOLOGIA_FO = "FLUSSO ORIGINE";
    public static final String DTO_ERRORE_TIPOLOGIA_FS = "FLUSSO SINTESI";
    
    public static final String NUM_MAX_RECORDS_EXPORT = "NUM_MAX_RECORDS_EXPORT";
    public static final String NUM_MAX_RECORDS_REPORT = "NUM_MAX_RECORDS_REPORT";
    

}


