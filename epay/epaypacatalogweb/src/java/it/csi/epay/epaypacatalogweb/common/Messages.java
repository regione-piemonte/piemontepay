/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.common;

public abstract class Messages {

    public final static String ERROR_DURING_SEARCH = "Errore: %s";

    public final static String SAVED_SUCCESSFULLY = "Modifiche salvate con successo.";

    public final static String ERROR_SAVING_CHANGES = "Errore: %s";

    public final static String ERROR_LOADING_PAGE = "Errore: %s";

    public final static String UNKNOWN_ERROR = "Errore sconosciuto";

    public final static String USER_EXISTS = "Utente esistente";

    public final static String LIMIT_COD_VERS_TIPO_PS
    = "E' stato raggiunto il numero massimo di riferimenti contabili possibili per il codice versamento specificato";

    public final static String ALERT_MODAL_INS_COD_VER
        = "Attenzione, per poter visualizzare e utilizzare anche negli altri moduli il nuovo Codice Versamento e' necessario effettuare il log out e accedere nuovamente all'applicativo.";

	public final static String ALERT_MODAL_INS_COD_VER_2 = "Attenzione: per poter visualizzare e utilizzare anche negli altri moduli il nuovo Codice Versamento e' necessario effettuare il log out e accedere nuovamente all'applicativo. Inoltre, se i pagamenti associati al codice versamento devono essere elaborati da un gestionale esterno a PiemontePAY (sia per la gestione della posizione debitoria che per l'integrazione contabile), aprire una remedy all'assistenza per il completamento della configurazione.";

    public final static String RIFERIMENTO_GENERICO_E_NON_GENERICO_PRESENTI  
    = "Esitono gia' un riferimento generico e uno non generico per  il codice versamento: %s e anno di esercizio: %s.";
    
    public final static String RIFERIMENTO_GENERICO_PRESENTE  
    = "Esite gia' un riferimento generico per il codice versamento: %s e anno di esercizio: %s.";
    
    public final static String PIU_RIFERIMENTI_PRESENTI
    = "Esitono piu' riferimenti generico per il codice versamento: %s e anno di esercizio: %s, non e' possibile inserire un generico.";
    
    public final static String RIFERIMENTO_ESITENTE  
    = "Esite gia' un riferimento per  il codice versamento: %s e anno di esercizio: %s.";
    
    public final static String RIFERIMENTO_CONTABILE_IN_VITA
    = "Esiste gia' un riferimento contabile in vita per il codice versamento specificato, non e' possibile abilitare il codice versamento alla gestione esterna";
    

    static {
    }
}
