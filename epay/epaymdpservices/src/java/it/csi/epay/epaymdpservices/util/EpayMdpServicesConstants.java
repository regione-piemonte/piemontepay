/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.util;

public interface EpayMdpServicesConstants {

    public static final String LOGGER_PREFIX = "epaymdpservices";
    public static final String EPAYMDP_ROOT_LOG_CATEGORY = "epaymdpservices";
    public static final String EPAYMDP_WEBSERVICES_LOG_CATEGORY = EPAYMDP_ROOT_LOG_CATEGORY + ".webservices";

    // errori
    public static final String ERRORE_ID_APPLICATION_IS_NECESSARY = "Errore: IdApplication dato obbligatorio";
    public static final String ERRORE_IUV_IS_NECESSARY = "Errore: IUV dato obbligatorio";
    public static final String ERRORE_TRANSACTION_ID_IS_NECESSARY = "Errore: TrandactionId dato obbligatorio";
    public static final String ERRORE_ORA_MSG_IS_NECESSARY = "Errore: dataOraMessaggioRicevuta dato obbligatorio";
    public static final String ERRORE_FORMATO_ORA_MSG = "Errore: dataOraMessaggioRicevuta: formato non corretto";
    public static final String ERRORE_ID_MSG_RICEVUTA_IS_NECESSARY = "Errore: idMessaggioRicevuta dato obbligatorio";
    public static final String ERRORE_TIPO_FIRMA_IS_NECESSARY = "Errore: TipoFirma Id dato obbligatorio";
    public static final String ERRORE_COD_ESITO_PAGAM_IS_NECESSARY = "Errore: Codice Esito Pagamento dato obbligatorio";
    public static final String ERRORE_DESC_ESITO_PAGAM_IS_NECESSARY = "Errore: Descrizione Esito Pagamento dato obbligatorio";
    public static final String ERRORE_ID_MSG_RICHIESTA_IS_NECESSARY = "Errore: idMessaggioRichiesta dato obbligatorio";
    public static final String ERRORE_TIMESTAMP_IS_NECESSARY = "Errore: Timestamp dato obbligatorio";
    public static final String ERRORE_NO_MAC = "Errore: MAC dato obbligatorio";
    public static final String ERRORE_MAC_NON_RICONOSCIUTO = "Errore: MAC non riconosciuto";
    public static final String ERRORE_NO_RECORD_TRANSACTION_FOUND = "Errore: non ci sono record con id transazione e iuv uguali a quelli invocati dalla riceviRT";
    public static final String ERRORE_PASSHPRASE = "Errore: passphrase non valorizzata correttamente";
    public static final String ERRORE_RT_NEGATIVA_PER_PAGAMENTO_ESEGUITO = "Errore: ricevuta nuova RT Negativa su un pagamento gia' registrato con successo";
    public static final String ERRORE_MORE_RESULT_FOUND = "Errore: atteso un solo risultato dalla ricerca di pagamento secondario";
    public static final String ERRORE_RT_NON_ASSOCIABILE_PAGAMENTO = "Errore: Rt non associabile a nessun pagamento";

    //Esito
    public static final String ESITO_OK = "OK";
    public static final String ESITO_KO = "KO";
    public static final String ORIGINE_INSERIMENTO = "MDPSERVICES-riceviRT";
    public static final String ORIGINE_INSERIMENTO_RICEVI_ESITO = "MDPSERVICES-riceviEsito";
    public static final String GRUPPO_PARAM_MDP = "mdp";
    public static final String PARAM_PASSPHRASE = "passphrase";

    public static final String ORIGINE_MDPSERVICES_VERIFICA_DATI_PAGAMENTO = "MDPSERVICES_verificaDatiPagamento";
    public static final String ORIGINE_MDPSERVICES_CHIEDI_DATI_PAGAMENTO = "MDPSERVICES_chiediDatiPagamento";
    public static final String PAA_PAGAMENTO_IN_CORSO = "PAA_PAGAMENTO_IN_CORSO";
    public static final String PAA_PAGAMENTO_ANNULLATO = "PAA_PAGAMENTO_ANNULLATO";
    public static final String PAA_PAGAMENTO_SCADUTO = "PAA_PAGAMENTO_SCADUTO";
    public static final String PAA_PAGAMENTO_DUPLICATO = "PAA_PAGAMENTO_DUPLICATO";
    public static final String PAA_PAGAMENTO_SCONOSCIUTO = "PAA_PAGAMENTO_SCONOSCIUTO";

    public static final String PAA_PAGAMENTO_REVOCATO = "PAA_PAGAMENTO_REVOCATO";
    public static final String PAA_ATTIVA_RPT_IMPORTO_NON_VALIDO = "PAA_ATTIVA_RPT_IMPORTO_NON_VALIDO";
    public static final String PAA_SYSTEM_ERROR = "PAA_SYSTEM_ERROR";

    public static final String MODALITARICEZIONE_ESITO_BATCH = "1";

    public static final String MODALITARICEZIONE_ESITO_BACK_OFFICE = "2";

    public static final String MODALITARICEZIONE_ESITO_TRATATMENTO_DATI = "3";

    public static final String MODALITARICEZIONE_ESITO_NODO_SPC = "4";

    public static final String MODALITARICEZIONE_ESITO_PAGO_PA = "5";
    
    public static final String KEY_TARI_TEFA_PAGAMENTO_PRINCIPALE = "TARI-TEFA-1";
    
    public static final String KEY_TARI_TEFA_PAGAMENTO_SECONDARIO = "TARI-TEFA-2";
    
    public static final String CODICE_TRIBUTO_TARI_TEFA_PAGAMENTO_SECONDARIO = "TEFA";
    
    

}
