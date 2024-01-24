/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.utils;

/**
 *
 */
public abstract class Messaggi {

    public static final String OK = "OK";

    public static final String FIELD_REQUIRED = "Campo %1$s obbligatorio";

    public static final String INVALID_FIELD = "Campo non valido: %1$s=%2$s ";

    public static final String ITEM_NOT_FOUND = "Item %1$s non trovato";

    public static final String INTERNAL_ERROR = "Errore imprevisto durante l'esecuzione dell'operazione %$1s";

    public static final String ELABORAZIONE_TERMINATA_CON_ERRORI = "Elaborazione terminata con errori\n$s";

    public static final String ERRORE_INSERIMENTO_PROVVISORIO = "Errore in fase di salvataggio del provvisorio %s";

    public static final String PROVVISORIO_PRESENTE_IN_BASE_DATI = "Provvisorio gia' acquisito per il flusso: %s.";

    public static final String GIORNALE_DI_CASSA_PRESENTE = "Giornale di cassa con identificativo flusso BT: %s esistente";
}
