/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.util;

public final class Constants {

	/*
	 * serivizi generali
	 */
	public static final String SERVICE_PAYMENT_RECEIPT = "getRicevutaPagamento";

	public static final String SERVICE_PAYMENT = "getPagamento";

	public static final String SERVICE_GET_PAYMENT_URL = "getPaymentUrl";

	public static final String SERVICE_PAYMENT_TYPES = "getPaymentTypes";

	public static final String SERVICE_ENABLE_RECEIPT_STORAGE = "enableReceiptStorage";

	public static final String SERVICE_GET_RECEIPT_STORAGE = "getReceiptStorage";

	public static final String SERVICE_PAYMENT_NOTICE = "getPaymentNotice";

	public static final String SERVICE_DEBT_POSITION = "createDebtPosition";

	public static final String SERVICE_LIST_DEBT_POSITION = "findDebtPositions";

	/*
	 * fields dei serivizi generali
	 */
	public static final String SERVICE_FIELDS_GENERAL__CURRENT_PAGE = "currentPage";

	public static final String SERVICE_FIELDS_GENERAL__ELEMENTS = "elements";

	public static final String SERVICE_FIELDS_GENERAL__FIELDS = "fields";

	public static final String SERVICE_FIELDS_GENERAL__SORT = "sort";

	public static final String CONFIG_MAX_ELEMENTS_PER_PAGE = "CONFIG_MAX_NUM_ELEMENTI_PER_PAGINA";

	// LIST DEBT POSITIONS
	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__CITIZEN_FISCAL_CODE = "citizenFiscalCode";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__STATUS = "status";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_AVVISO = "codiceAvviso";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV = "iuv";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO = "codiceVersamento";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PORTALE = "descrizionePortale";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO = "importo";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__COMPONENTI_PAGAMENTO = "componentiPagamento";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PAGAMENTO = "descrizionePagamento";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA = "dataScadenza";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ORA_OPERAZIONE = "dataOraOperazione";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ESITO_PAGAMENTO = "dataEsitoPagamento";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__IDENTIFICATIVO_UNIVOCO_RISCOSSIONE = "identificativoUnicoRiscossione";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__NUMERO_TRANSAZIONE = "numeroTransazione";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESTATORE_SERVIZI_PAGAMENTO = "prestatoreServiziPagamento";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__INFO_AGGIUNTIVE = "infoAggiuntive";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESENZA_QUIETANZA = "presenzaQuietanza";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_STATO_PAGAMENTO = "codiceStatoPagamento";

	public static final String SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_STATO_PAGAMENTO = "descrizioneStatoPagamento";

	// PAYMENT_TYPES

	public static final String SERVICE_FIELDS_PAYMENT_TYPES__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_PAYMENT_TYPES__PAYMENT_TYPES_DESCRIPTION = "paymentTypesDescription";

	public static final String SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO = "codiceVersamento";

	public static final String SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO = "descrizioneVersamento";

	public static final String SERVICE_FIELDS_PAYMENT_TYPES__NOTE_PER_IL_PAGATORE = "notePerIlPagatore";

	public static final String SERVICE_FIELDS_PAYMENT_TYPES__NOTE_OBBLIGATORIE = "noteObbligatorie";

	// PAGAMENTO_SPONTANEO

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_TYPE = "paymenttype";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA = "paymentdata";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOTE = "note";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_IMPORTO = "importo";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_EMAIL = "email";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOME = "nome";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_COGNOME = "cognome";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE = "codiceFiscalePartitaIVAPagatore";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MINORE_DI_11 =
					"codiceFiscalePartitaIVAPagatore (lunghezza minore di 11 caratteri e diverso da 'ANONIMO')";

	public static final String SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MAGGIORE_DI_16 =
					"codiceFiscalePartitaIVAPagatore (lunghezza maggiore di 16 caratteri)";

	// GET_PAYMENT_URL

	public static final String SERVICE_FIELDS_GET_PAYMENT_URL__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_GET_PAYMENT_URL__IUV = "iuv";

	// RECEIPT_STORAGE

	public static final String SERVICE_FIELDS_RECEIPT_STORAGE__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_RECEIPT_STORAGE__CITIZEN_FISCAL_CODE = "citizenFiscalCode";

	public static final String SERVICE_FIELDS_RECEIPT_STORAGE__ENABLE_RECEIPT_STORAGE = "enableReceiptStorage";

	// PAYMENT RECEIPT

	public static final String SERVICE_FIELDS_PAYMENT_RECEIPT__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_PAYMENT_RECEIPT__CITIZEN_FISCAL_CODE = "citizenFiscalCode";

	public static final String SERVICE_FIELDS_PAYMENT_RECEIPT__IUV = "iuv";

	// PAYMENT NOTICE

	public static final String SERVICE_FIELDS_PAYMENT_NOTICE__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_PAYMENT_NOTICE__IUV = "iuv";

	// DEBT POSITION

	public static final String SERVICE_FIELDS_DEBT_POSITION__ORGANIZATION_FISCAL_CODE = "organizationFiscalCode";

	public static final String SERVICE_FIELDS_DEBT_POSITION__CITIZEN_FISCAL_CODE = "citizenFiscalCode";

	public static final String SERVICE_FIELDS_DEBT_POSITION__PAYMENT_TYPE = "paymenttype";

	public static final String SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA = "paymentData";

	public static final String SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_IMPORTO = "importo";

	public static final String SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE = "note";

	public static final String SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOME = "nome";

	public static final String SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_COGNOME = "cognome";

	public static final String SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_EMAIL = "email";

	public static final String ERROR_ENTE_NOT_FOUND = "Non trovato ente con codice fiscale uguale a %s";

	public static final String ERROR_PAYMENT_TYPE_NOT_FOUND = "Non trovato tipo pagamento per ente con codice fiscale uguale a %s";

	public static final String ERROR_TIPO_PAGAMENTO_NOT_FOUND = "Tipo pagamento con codice versamento %s non trovato per ente con codice fiscale %s";

	public static final String ERROR_TIPO_PAGAMENTO_NOT_UNIQUE = "Tipo pagamento con codice versamento %s non univoco per ente con codice fiscale %s";

	public static final String ERROR_TIPOLOGIA_PAGAMENTO_NOT_DEFINED = "Tipologia pagamento %s non definita";

	public static final String ERROR_VALORE_DI_CONFIGURAZIONE_NOT_DEFINED = "Valore di configurazione non definito per codice %s";

	public static final String ERROR_CODICE_VERSAMENTO_NOT_ENABLED_YET = "Codice versamento [%s] non ancora in periodo di validita'";

	public static final String ERROR_CODICE_VERSAMENTO_NOT_VALID = "Codice versamento [%s] non piu' valido";

	public static final String ERROR_CODICE_VERSAMENTO_MULTIBENEFICIARIO = "Codice versamento [%s] multibeneficiario";

	public static final String ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO = "Codice versamento [%s] non spontaneo";

	public static final String ERROR_PAGAMENTO_NOT_FOUND = "Pagamento non presente su Sportello";

	public static final String ERROR_PAGAMENTO_NOT_FOUND_OR_NON_VALIDO_OR_NON_PAGABILE = "Pagamento non presente su Sportello oppure non valido o non pagabile";

	public static final String ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE = "Pagamento non associato al codice fiscale ricevuto";

	public static final String ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE = "Pagamento non associato al codice fiscale dell'Ente ricevuto";

	public static final String ERROR_TIPOLOGIA_PAGAMENTO_MARCA_DA_BOLLO = "Operazione non permessa in quanto pagamento afferente a marca da bollo";

	public static final String ERROR_REGISTRO_NOT_FOUND = "Pagamento con iuv: %s non presente nel registro versamenti";

	public static final String ERROR_PREFERENZA_ARCHIVIAZIONE_NOT_FOUND = "Nessuna preferenza individuata";

	public static final String ERROR_RECEIPT_NOT_FOUND = "Non esiste alcuna Ricevuta o quietanza per il pagamento richiesto, idPagamento = [%s]";

	public static final String ERROR_IUV_GENERATION = "Errore nel generare IUV da IdApplicazione [%s] e codice versamento [%s]";

	public static final String ERROR_MDP_DATI_COMPONENTI_NON_CONGRUENTI
					= "Il pagamento non puo' essere effettuato perche' risulta gia' una transazione di pagamento in corso.";

	public static final String ERROR_MDP_ERRORE_GENERICO
					=
					"Errore in fase di pagamento o errore temporaneo di comunicazione. Si prega di riprovare piu' tardi. Se l'errore dovesse persiste contattare l'assistenza.";

	public static final String ERROR_REQUIRES_COST_UPDATE_TRUE =
					"Non e' possibile effettuare il pagamento da questo sito. Effettuare il pagamento dal sito PagoPa o rivolgersi all'ente creditore per ulteriori indicazioni";

	public static final String ERROR_GET_DATI_SPECIFICI_RISCOSSIONE
					= "Errore in fase di reperimento del dato specifico riscossione, configurazione  CONFIG_ENDPOINT_SERVICE_TASSONOMIA mancante";

	public static final String ERROR_PDF_GENERATION = "Errore nel generare il pdf dell'avviso di pagamento, idPagamento = [%s]";

	public static final String ERROR_CAMPO_NOTE_OBBLIGATORIO = "Il campo note e' obbligatorio per codice versamento [%s]";

	public static final String ERROR_IUV_NOT_FOUND_OR_INVALID_PAYMENT
					= "Codice Iuv riferito a pagamento effettuato, annullato, in attesa di ricevuta, non piu' valido perché scaduto oppure non trovato.";

	public static final String ERROR_IUV_IS_OF_AN_INVALID_PAYMENT = "Codice Iuv riferito a pagamento annullato, fallito o non piu' valido.";

	/*
	 * var
	 */
	public static final int MAX_ERROR_MESSAGE_WIDTH = 200;

	public static final String REGEX_FIELD_SEPARATOR = "\\s*,\\s*";

	public static final String REGEX_NORMAL_TEXT = "[[^\\x00-\\x19]\\s]*"; // testo libero senza caratteri speciali tipu \u0000

	public static final String REGEX_IUV_CF = "[a-zA-Z0-9]+";

	public static final String REGEX_EMAIL = "[a-zA-Z0-9_.+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*";

	public static final String REGEX_PAYMENT_TYPE = "[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]";

	// zero o piu caratteri whitespace seguiti da un virgola seguita da zero o più caratteri whitespace (un whitespace sta per uno spazio, un tab, ecc.)

	public static final char SORT_ASCENDENT_PREFIX = '+';

	public static final char SORT_DESCENDENT_PREFIX = '-';

	public static final String COMMA_AND_SPACE = ", ";

	public static final String INTERNAL_ERROR_CODE = "500";

	public static final String PAGAMENTO_SPONTANEO_CODE = "PS";

	public static final String BEGIN = " BEGIN";

	public static final String END = " END";

	public static final String SYSTEM_USER_DEFAULT = "System";

	public static final String ESITO_CHIAMATA_ESTERNA_OPERAZIONE_COMPLETATA_CON_SUCCESSO = "000";

	public static final String ESITO_CHIAMATA_ESTERNA_ERRORE_DATI_COMPONENTI_NON_CONGRUENTI = "300";

	public static final String ESITO_CHIAMATA_ESTERNA_ERRORE_GENERICO = "100";

	public static final String RPT_DUPLICATA = "RPT duplicata";

	public static final String F = "F";

	public static final String G = "G";

	public static final String CODICE_PAGAMENTO__PAGATO = "0";

	public static final String DESCRIZIONE_PAGAMENTO__PAGATO = "Pagato";

	public static final String CODICE_PAGAMENTO__ANNULLATO_DALL_ENTE = "2";

	public static final String DESCRIZIONE_PAGAMENTO__ANNULLATO_DALL_ENTE = "Annullato dall'Ente";

	public static final String CODICE_PAGAMENTO__NON_PAGATO = "1";

	public static final String DESCRIZIONE_PAGAMENTO__NON_PAGATO = "Non Pagato";

	//	Generazione PDF

	public static final int DATAMATRIX_LENGTH = 256;

	public static final int SCALED_DIMENSION = 256;

	public static final String QRCODE_IDENTIFICATIVO = "PAGOPA";

	public static final String QRCODE_SEPARATOR = "|";

	public static final String QRCODE_VERSIONE = "002";

	public static final int QRCODE_MODULES = 33;

	public static final String DATAMATRIX_INDIRIZZAMENTO_FASE = "codfase=";

	public static final String DATAMATRIX_CODICE_FASE_ACCETTAZIONE = "NBPA";

	public static final String DATAMATRIX_SEPARATORE = ";";

	public static final String DATAMATRIX_LUNGHEZZA_CODICE_AVVISO = "18";

	public static final String DATAMATRIX_LUNGHEZZA_IMPORTO = "10";

	public static final String DATAMATRIX_LUNGHEZZA_TIPO_DOCUMENTO = "3";

	public static final String DATAMATRIX_TIPO_DOCUMENTO = "896";

	public static final int CODELINE_LENGTH = 50;

	public static final String DATAMATRIX_ID_DATAMATRIX = "1";

	public static final String DATAMATRIX_FASE_PAGAMENTO = "P1";

	public static final int DATAMATRIX_ID_UNIVOCO_DIM = 16;

	public static final int DATAMATRIX_ANAGRAFICA_DIM = 40;

	public static final int DATAMATRIX_CAUSALE_DIM = 110;

	public static final int DATAMATRIX_FILLER = 12;

	public static final String DATAMATRIX_VALORE_FINALE_DATAMATRIX = "A";

	public static final int DATAMATRIX_LUNGHEZZA_CONTO = 12;

	public static final String PATH_PAGOPA = "jasperReports/images/logo-pagopa@3x.png";

	public static final String PATH_AVVISO_DI_PAGAMENTO = "jasperReports/images/scritta-avviso-di-pagamento@3x.png";

	public static final String PATH_CANALI_DIGITALI = "jasperReports/images/canali-digitali@3x.png";

	public static final String PATH_CANALI_FISICI = "jasperReports/images/canali-fisici@3x.png";

	public static final String PATH_POSTE_ITALIANE = "jasperReports/images/logo-poste-italiane@3x.png";

	public static final String PATH_BOLLETTINO = "jasperReports/images/logo-bollettino-postale@3x.png";

	public static final String PATH_EURO = "jasperReports/images/logo-euro-bollettino@3x.png";

	public static final String PATH_FORBICI = "jasperReports/images/forbici.png";

	public static final String UNA_UNICA_RATA = "con <b>una unica rata</b>";

	public static final String UNICA_RATA = "Utilizza la porzione di avviso relativa al canale di pagamento che preferisci.";

	public static final String DEL_TUO_ENTE_CREDITORE = "del tuo Ente Creditore ";

	public static final String URL_ENTE_CREDITORE = "(https://servizi.regione.piemonte.it/catalogo/portale-dei-pagamenti), ";

	public static final String DI_POSTE_ITALIANE = "di Poste Italiane, ";

	public static final String DELLA_TUA_BANCA = "della tua Banca o degli altri canali di pagamento. Potrai pagare con carte, conto corrente, CBILL.";

	public static final Double MAX_IMPORTO = 999999999.99;

	//

	public static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA = "ENDPOINT_SERVICE_TASSONOMIA";

	public static final String REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO = "Epayfeapi - Citta' Facile";

	public static final int MAX_LENGTH_DESCRIZIONE_ERRORE = 400;
}
