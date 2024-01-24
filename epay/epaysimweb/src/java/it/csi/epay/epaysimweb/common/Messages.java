/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.common;

public interface Messages {

    final static String SAVED_SUCCESSFULLY = "Modifiche salvate con successo.";

    final static String LOAD_SUCCESSFULLY = "Il giornale di cassa relativo all'identificativo flusso BT %s e' stato caricato con successo";

    // ERRORS
    final static String ERROR_DURING_SEARCH = "Si e' verificato un errore durante la ricerca: %s";

    final static String ERROR_SAVING_CHANGES = "E' avvenuto un errore durante il salvataggio delle modifiche: %s";

    final static String UNKNOWN_ERROR = "Errore sconosciuto";

    final static String ELAB_PROVVISORIO_UNSUCCESS = "Nessun provvisorio caricato.";

    final static String LOAD_PROVVISORIO_EXCPETION = "Non e' stato possibile completare il caricamento del file: ";

    final static String LOAD_PROVVISORIO_UNSUCCESS
    = "Non e' stato possibile completare il caricamento del file relativo all'identificativo flusso BT %s. Selezionalo nuovamente.";

    final static String POPUP_UPDATE_GIORNALE = "Vuoi proseguire con il caricamento del giornale cassa [ %s ] sovrascrivendo i dati?";
}
