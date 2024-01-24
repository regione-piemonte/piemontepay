/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import java.util.List;

import it.csi.mdp.mdppagopaapi.integration.domain.Application;


public interface ApplicationService {

    /**
     * Metodo per ricercare l'id nella tabella Application by Iuv.application_id.
     *
     * @param stringa contenente application_id
     * @return application_id uguale a quello passato in ingresso.
     */
    Application findById ( String applicationId );

    /**
     * 
     * @param applicationId
     * @return
     */
    List<Application> findAllById ( List<String> applicationId );

        /**
     * Metodo per registrare un evento.
     *
     * @param gde
     * @return
     */
    Application isExists ( String applicationId );

}
