/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.common;

public abstract class Messages {

    public final static String ERROR_DURING_SEARCH = "Si e' verificato un errore durante la ricerca: %s";

    public final static String ERROR_DURING_LOCK_REMOVE = "Si e' verificato un errore durante la rimozione dei lock applicativi";

    public final static String SAVED_SUCCESSFULLY = "Modifiche salvate con successo.";

    public final static String ERROR_SAVING_CHANGES = "E' avvenuto un errore durante il salvataggio delle modifiche: %s";

    public final static String UNKNOWN_ERROR = "Errore sconosciuto";

    public final static String ERRORE_DURANTE_RICERCA_ELABORAZIONE = "Si e' verificato un errore durante la ricerca delle elaborazioni precedenti: %s";

    static {
    }
}
