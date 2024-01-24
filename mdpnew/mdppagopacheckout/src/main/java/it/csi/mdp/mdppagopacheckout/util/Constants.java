/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.util;

public final class Constants {

	public static final String PASSPHRASE = "passphrase";

	public static final String APPLICATION_JSON = "application/json";

	public static final String OCP_APIM_SUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key";

	// error messages
	public static final String APPLICATION_ID_UNMATCH = "APPLICATION:ID non corrispondente";

	public static final String IUV_NOT_FOUND = "Pagamento in attesa risulta sconosciuto all'Ente Creditore";

	public static final String EMAIL_NOT_VALID = "Formato email non valido";

	public static final String RETURN_URLS_NOT_FOUNDS = "returnUrl non valorizzate";

	public static final String CALL_TO_PAGOPA_ERROR = "Errore interno al sistema";

	public static final String TRANSACTION_ID_ERROR = "Transaction ID should contain only letters and digits.";

	// regex
	public static final String MAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	public static final String TRANSACTION_ID_REGEX = "^[a-zA-Z0-9]+$";

	// custom fields fieldnames
	public static final String RETURN_OK_URL_FIELDNAME = "returnOkUrl";

	public static final String RETURN_CANCEL_URL_FIELDNAME = "returnCancelUrl";

	public static final String RETURN_ERROR_URL_FIELDNAME = "returnErrorUrl";

	public static final String CONTO_POSTE_FIELDNAME = "contoPoste";

	public static final String CODICE_INDENTIFICATIVO_UNIVOCO_BENEFICIARIO_FIELDNAME = "codiceIdentificativoUnivocoBeneficiario";

	public static final String DENOMINAZIONE_BENEFICIARIO = "denominazioneBeneficiario";

	// transaction options
	public static final String LANGUAGE_TRANSACTION = "ITA";

	// urls
	public static final String MDP_RETURN_URL_OK = "mdp.return.url.ok";

	public static final String MDP_RETURN_URL_CANCEL = "mdp.return.url.cancel";

	public static final String MDP_RETURN_URL_ERROR = "mdp.return.url.error";

	public static final String T_ID = "tranId"; // old transactionId

	public static final String M_ID = "macId";

	public static final String Q_PARAM = "?" + T_ID + "=";

	public static final String M_PARAM = "&" + M_ID + "=";

	// var
	public static final String EMPTY_STRING = "";

	public static final String NOT_AVAILABLE = "N/A";

	public static final String COMPONENTE = "mdppagopacheckout";

	public static final String INTERFACCIA = "INTERFACCIA";

	public static final String TIPO_EVENTO_PAGOPA_CHECKOUT = "pagopacheckout";

	public static final String SOTTOTIPO_EVENTO_PAGOPA_CHECKOUT = "iuvnotfound";

	public static final String KO = "KO";

	//	Nuove connettivita

	public static final String OCP_APIM_SUBSCRIPTION = OCP_APIM_SUBSCRIPTION_KEY + "-pagoPA";
}
