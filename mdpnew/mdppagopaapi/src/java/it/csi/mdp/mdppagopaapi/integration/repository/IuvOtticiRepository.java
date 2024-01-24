/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.IuvOttici;


/**
 * Spring data Jpa repository for "IuvOtticiRepository" <br>
 *
 * @author Enrico.LaMalfa
 */
@Repository
public interface IuvOtticiRepository extends IRepository<IuvOttici, Integer> {

    IuvOttici findTopByIuvOttico ( String iuvOttico );
}
