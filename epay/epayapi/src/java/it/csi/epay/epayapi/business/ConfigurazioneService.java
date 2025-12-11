/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business;

import java.util.List;
import java.util.Map;

import it.csi.epay.epayapi.dto.common.ConfigurazioneDTO;
import it.csi.epay.epayapi.util.ParametriApplicativo;


/**
 * Servizio per la gestione della configurazione
 */
public interface ConfigurazioneService {

    /**
     * Ritorna il valore del parametro di configurazione corrispondente al codice specificato. ATTENZIONE: utilizzo non consigliato. Utilizzare l'overload che
     * accetta in input un ParametroApplicativo
     *
     * @param raw il codice del parametro desiderato
     * @return la configurazione corrispondente al codice, oppure null
     */
    ConfigurazioneDTO getConfig ( String raw );

    /**
     * Ritorna il valore del parametro di configurazione corrispondente al parametro.
     *
     * @param parametro il parametro desiderato
     * @return la configurazione corrispondente al codice, oppure null
     */
    ConfigurazioneDTO getConfig ( ParametriApplicativo parametro );

    /**
     * Ritorna il valore del parametro di configurazione corrispondente al parametro. Solleva eccezione in caso di parametro mancante
     *
     * @param parametro il parametro desiderato
     * @return la configurazione corrispondente al codice
     */
    ConfigurazioneDTO requireConfig ( ParametriApplicativo parametro );

    /**
     * Ritorna informazioni sulla build dell'applicativo
     * 
     * @return informazioni sulla build dell'applicativo
     */
    Map<String, Object> getBuildProperties ();

    /**
     * Ritorna tutti i parametri di configurazione che possono essere esposti all'esterno dell'applicativo in modo sicuro
     * 
     * @return lista di parametri di configurazione
     */
    List<ConfigurazioneDTO> getExternalConfiguration ();
}
