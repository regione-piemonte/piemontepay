/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.Application;


/**
 * Spring data Jpa repository for "ApplicationRepository" <br>
 *
 * @author Silvia.Balsamini
 * @author Marco.Mezzolla
 */

@Repository
public interface ApplicationRepository extends IRepository<Application, Integer> {

    Application findTopById ( String applicationid );

    Application findById ( String applicationId );

    List<Application> findAllByIdIn ( List<String> applicationId );
}
