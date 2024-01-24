/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.business;

import java.util.List;

import it.csi.epay.epaybeapi.dto.common.LoggerDTO;


/**
 * Servizio che gestisce il logging
 */
public interface LoggingService {

    /**
     * Ottiene i logger correntemente configurati
     *
     * @return la lista di logger configurati
     */
    List<LoggerDTO> getLoggers ();

    /**
     * Aggiorna il livello per il logger specificato
     *
     * @param logger il logger da aggiornare ed il relativo livello
     */
    void setLevel ( LoggerDTO logger );
}
