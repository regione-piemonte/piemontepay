/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.util;

/**
 * Classe di utility contenente tutte le costanti
 */
public abstract class Constants {

	private Constants () {
	}

    /**
     * Nome della componente da utilizzare per il logging
     */
	public static final String COMPONENT_NAME = "mdppagopaapi";

    /**
     * Classe di utility contenente le cache predisposte
     */
    public static class CACHES {

		private CACHES () {
		}
        /**
         * Cache di default
         */
		public static final String DEFAULT = "defaultCache";

        /**
         * Cache per i parametri di configurazione
         */
		public static final String CONFIGURATION = "configurationCache";

        /**
         * Cache per i messaggi
         */
		public static final String MESSAGES = "messageCache";

    }

	public static final String DEFAULT_PROTOCOL = "application/json";

    public static final String TRUE = "S";

    public static final String OK = "OK";

    public static final String KO = "KO";

    public static final String FALSE = "N";

    public static final String NOT_AVAILABLE = "N/A";

    public static final String COMPONENTE = "MDPAPI";

    public static final String PA_VERIFY_PAYMENT_NOTICE = "paVerifyPaymentNotice";

    public static final String TIPO_EVENTO_GET_PAYMENT = "paGetPayment";

    public static final String PA_SEND_RT = "paSendRT";

    public static final String INTERFACCIA = "INTERFACCIA";

    public static final String LANGUAGE_TRANSACTION = "ITA";

    public static final String WSDL = "?wsdl";

    public static final String TRANSACTION_ID = "transaction_id";

    public static final String FLAG_RECEIPT = "flagReceipt";

    public static final String CONTO_POSTE = "contoPoste";

	public static final String IBAN_ACCREDITO = "ibanAccredito";

	public static final String IBAN_APPOGGIO = "ibanAppoggio";

	public static final String NUOVO_MODELLO_3_IDENTIFICATIVO_CONTI_POSTALI = "nuovoModello3.identificativoContiPostali";

    public static final String CODICE_FISCALE = "codiceFiscale";

    public static final String DENOMINAZIONE_BENEFICIARIO = "denominazioneBeneficiario";

    public static final String IDENTIFICATIVO_DOMINIO = "identificativoDominio";

    public static final String STABILIMENTO = "stabilimento";

    public static final int GETPAYMENT_INVIATO_OK = 1;

    public static final Integer RECEIPT_RICEVUTA = 3;

    public static final int GETPAYMENT_INVIATO_KO = 2;
    public static final String CODICE_ESITO_PAGAMENTO_MOD_3 = "9";

    public static final int GETPAYMENT_INVIATO_RICEVUTA = 3;
    public static final String DESC_PAGAMENTO_MOD_3 = "pagamento eseguito";

	public static final String POSTAL = "POSTAL";
	
	 public static final String PAA_PAGAMENTO_DUPLICATO = "PAA_PAGAMENTO_DUPLICATO";
	 
	 public static final String GATEWAY_ID_PAGOPA_V2 = "0A184049-2519-454F-A2BA-6609FBCBEFBE";
	 
	 public static final String PAYMENTMODE_ID_MODELLO3 = "434D700A-9C57-44AE-B842-D8F858FE6DE4";
	 
	 
	 
	 
}
