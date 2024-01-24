/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import java.util.Collection;
import java.util.List;

import it.csi.mdp.mdppagopaapi.integration.domain.Application;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationcustomfield;


public interface ApplicationCustomFieldService {

    Applicationcustomfield findTopByApplicationAndFieldnameIs ( Application application, String fieldName );

    List<Applicationcustomfield> findAllByApplicationInAndFieldnameIs ( Collection<Application> applications, String fieldName );

    Applicationcustomfield findById ( int id );

    List<Applicationcustomfield> findAllByApplicationEnabled ( String applicationId );

}
