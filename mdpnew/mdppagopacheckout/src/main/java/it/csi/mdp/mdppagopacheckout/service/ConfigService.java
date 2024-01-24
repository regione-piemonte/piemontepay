/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service;

import it.csi.mdp.mdppagopacheckout.entity.Config;
import it.csi.mdp.mdppagopacheckout.repository.ConfigRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@SuppressWarnings ( "ALL" )
@ApplicationScoped
@Transactional
public class ConfigService {

	@Inject
	ConfigRepository configRepository;

	public Config getConfigByKey ( String key ) {
		return configRepository.findByKey ( key );
	}
}
