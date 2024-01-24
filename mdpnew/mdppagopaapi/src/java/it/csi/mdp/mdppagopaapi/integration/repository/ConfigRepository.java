/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.Config;
/**
 * Spring data Jpa repository for "Config" <br>
 *
 * @author lorenzo.fantini
 */
@SuppressWarnings ( "unused" )
@Repository
public interface ConfigRepository extends IReadOnlyRepository<Config, String> {
}
