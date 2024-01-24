package it.csi.mdpnew.mdpmultiiuvsrv.util;

public interface MdpMultiIuvConstants {

	public static final String LOGGER_PREFIX = "mdpmultiiuvsrv";
	public static final String MDPIUV_ROOT_LOG_CATEGORY = "mdpmultiiuvsrv";
	public static final String MDPIUV_WEBSERVICES_LOG_CATEGORY = MDPIUV_ROOT_LOG_CATEGORY + ".webservices";

	// errori
	public static final String ERRORE_ID_APPLICATION_IS_NECESSARY = "ID Application is mandatory";
	public static final String ERRORE_ID_APPLICATION_NOT_EXIST = "No application found for appid";
	public static final String ERRORE_CONFIGURATION_APPLICATION = "Nessun ente associato all’applicazione appid";
	public static final String ERRORE_IUV_ATTRIBUTE_ASSIGN = "Errore in assegnazione del progressivo";
	public static final String ERRORE_CKDGTS_ASSIGN = "Errore durante il calcolo del check digit";
	public static final String ERRORE_NO_MULTI_IUV = "Inserire la quantita' di IUV che si vuole generare";
	public static final String ERRORE_MAX_MULTI_IUV = "Il valore del parametro occorrenze supera la soglia massima per singola invocazione del servizio";
	public static final String ERRORE_UNIQUE_MULTI_IUV = "Raggiunto numero massimo di IUV della giornata Impossibile creare nuovi IUV";
	public static final String ERRORE_INSERIMENTO_IUV = "Errore in fase di salvataggio IUV";
	public static final String ERRORE_LUNGH_DETTAGLIO_PAGAM = "Lunghezza della stringa ID del dettaglio di pagamento non corretta";
	public static final String ERRORE_CARAT_NO_DETTAGLIO_PAGAM = "Carattere non ammesso nel dettaglio pagamento";
	public static final String ERRORE_NO_MAC = "MAC dato obbligatorio";
	public static final String ERRORE_NO_COD_VERS = "CodVersamento dato obbligatorio";
	public static final String ERRORE_MAC_NON_RICONOSCIUTO = "Errore MAC non riconosciuto”";
	public static final String ERRORE_NO_TIMESTAMP = "timestamp dato obbligatorio";
	public static final String ERRORE_AUX_DIGIT = "AuxDigit non valorizzato correttamente";
	public static final String ERRORE_APPCODE = "ApplicationCode non valorizzato correttamente";
	public static final String ERRORE_PASSHPRASE = "Passphrase non valorizzata correttamente";
	public static final String ERRORE_COD_ENTE = "Codice Identificativo Ente non valorizzata correttamente";
	public static final String ERRORE_COD_SEGREGAZIONE = "Codice Segregazione non valorizzata correttamente";
	public static final String ERRORE_KEYDB = "Il FIle skeydb.txt non è stato trovato";
	public static final String ERRORE_CALCOLO_IUVOTTICO = "Errore nel calcolo dello IUV ottico";
	public static final String ERRORE_GENERICO = "General Error";
	public static final String ERRORE_ACCESSO_DATI = "Errore di accesso ai dati";

}
