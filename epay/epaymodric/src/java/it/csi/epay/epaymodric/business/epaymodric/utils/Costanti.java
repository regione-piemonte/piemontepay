/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 *
 * @author vsgro
 */
public abstract class Costanti {

    final static Logger logger = LogManager.getLogger ( Costanti.class );

    public static final String CODICE_APPLICAZIONE = "EPAYMODRIC";

    public static final String COSTANTE_SPAZIO = " ";

    public static final String COSTANTE_SLASH = "/";

    public static final String PREFISSO_CAUSALE_PROVVISORI = "/PUR/LGPE-RIVERSAMENTO/URI/";

    public static final String PREFISSO_CAUSALE_PROVVISORI_RFS = "/RFS/";

    public static final String PREFISSO_CAUSALE_PROVVISORI_RFB = "/RFB/";

    public static final String DATI_PROVVISORI = "PROVVISORI";

    public static final String DATI_CATALOGO = "CATALOGO";

    public static final String DATI_LISTA_DI_CARICO = "LISTA_DI_CARICO";

    public static final String CODICE_VERAMENTO_DI_TEST = "TSTA";

    public static final String CONFIG_ATTR_MAX_NUM_TENTATIVI = "LIMITE_TENTATIVI";

    public static final String CONFIG_ATTR_MAX_NUM_TENTATIVI_PROPERTY = "default.num.max.tentativi.riconciliazione";

    public static final String CONFIG_ATTR_NUM_MAX_THREAD = "NUM_MAX_THREAD";

    public static final String CONFIG_TEMPO_MAX_ATTESA = "TEMPO_MAX_ATTESA";

    public static final String PAGE_SIZE_FLUSSI_ORIG = "PAGE_SIZE_FLUSSI_ORIG";

    public static final String PAGE_SIZE_FLUSSI_ORIG_PROPERTY = "page.size.flussi.orig";

    public static final String ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI_PROPERTY = "default.abilita.invio.pagamenti.sconosciuti";

    //NG:2.2.18
    public static final String CONFIG_ATTR_MAX_NUM_ELAB_PRECEDENTI = "LIMITE_ELAB_PRECED";

    public static final String CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI = "CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI";

    public static final String CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI_PROPERTY = "service.recupero.provvisori";

    public static final String CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI = "CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI";

    public static final String CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI_PROPERTY = "service.invio.flussi";

    public static final String CONFIG_HEADER_SERVICE_ENDPOINT_EPAYBEAPI = "epaybeapi.header";

    //NG:2.2.12 Aggiorna Stato elaborazione

    public static final String STATO_CBL_T_ELABORAZIONE_TERMINATA = "TERMINATA";

    public static final String STATO_CBL_T_ELABORAZIONE_IN_ERRORE = "IN ERRORE";

    public static final String STATO_CBL_T_ELABORAZIONE_BOZZA = "BOZZA";

    public static final String STATO_CBL_T_ELABORAZIONE_FORZATO = "FORZATO";

    public static final Integer PERIODICITA_SCHEDULAZIONE_SINGOLO_FLUSSO_CBL_T_ENTE = 99;

    public static final String SEPARATORE_LISTA_FLUSSI = ";";

    public static final String RESPONSE_CONTABILIA_OK = "000";

    public static final String RESPONSE_WS_OK = "000";

    public static final String RESPONSE_CONTABILIA_KO = "OK";

    public static final String DEFAULT_UTENTE_SISTEMA = "SYSTEM";

    public static final String NOME_CAMPO_DATAELABORAZIONE = "dataElaborazione";

    //NG costanti per chiamata Ws esitoFlussiPagoPA
    public static final String VALORE_CAMPO_PROVVISORI_VALIDO = "VALIDO";

    public static final String STATOFLUSSO_CONFRONTO_WS_ESITOFLUSSIPAGOPA_IN_ERRORE = "IN_ERRORE";

    public static final String STATOFLUSSO_CONFRONTO_WS_ESITOFLUSSIPAGOPA_ELABORATO = "ELABORATO";
    //NG Fine costanti per chiamata Ws esitoFlussiPagoPA

    public static final int MODALITA_ACQUISIZIONE_PROVVISORI_SERVIZI = 1;

    public static final int MODALITA_ACQUISIZIONE_PROVVISORI_NESSUNA = 0;

    public static final String CONFIG_ATTR_SERVICE_ENDPOINT_NESSUNA_INTEGRAZIONE_ESTERNA = "NESSUNA_INTEGRAZIONE_ESTERNA";

    public static final String ATTRIBUTO_CONF_ENTE_NON_PRESENTE_SU_DB = "Attributo %s non presente su DB per l'ente %s.";

    public static final String ATTRIBUTO_CONF_ENTE_PRESENTE_SU_DB = "Attributo %s recuperato da DB per l'ente %s.";

    public static final String ATTRIBUTO_CONF_NON_PRESENTE_SU_PROPERTY = "Property %s non presente.";

    public static final String ATTRIBUTO_CONF_PRESENTE_SU_PROPERTY = "Property %s recuperata.";

    public static final String ATTRIBUTO_CONF_NON_PRESENTE_SU_DB = "Attributo %s non presente.";

    public static final String ATTRIBUTO_CONF_PRESENTE_SU_DB = "Attributo %s recuperata.";

    public static final String TRANSACTION_ID_STATO_9 = "PRD00000000XXX";

    public static final String DATI_SPECIFICI_RISCOSSIONE_STATO_9 = "9/000";

    public static final String ESITO_PAGAMENTO_9 = "9";

    public static final String CAUSALE_PROVVISORIO_FORMATO_URI = "URI";

    public static final String CAUSALE_PROVVISORIO_FORMATO_RFS = "RFS";

    public static final String CAUSALE_PROVVISORIO_FORMATO_RFB = "RFB";

    public static final String ATTRIBUTO_CONF_NUM_MAX_RECORDS_EXPORT = "NUM_MAX_RECORDS_EXPORT";
    
    public static final String ATTRIBUTO_CONF_NUM_MAX_RECORDS_REPORT = "NUM_MAX_RECORDS_REPORT";

    public static final String ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI = "ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI";
    
    public static final String MESSAGE_SOLO_FLUSSI_SCONOSCIUTI = "MESSAGE_SOLO_FLUSSI_SCONOSCIUTI";

}
