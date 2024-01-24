/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.ConfigService;
import it.csi.mdp.mdppagopaapi.dto.exception.ManagedException;
import it.csi.mdp.mdppagopaapi.integration.domain.Config;
import it.csi.mdp.mdppagopaapi.integration.dto.ConfigDTO;
import it.csi.mdp.mdppagopaapi.integration.mapper.ConfigMapper;
import it.csi.mdp.mdppagopaapi.integration.repository.ConfigRepository;

@Service
public class ConfigServiceImpl implements ConfigService {
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Autowired
	private ConfigMapper configMapper;

	@Override
	public ConfigDTO getConfig(String key) {
		if (StringUtils.isBlank(key)) {
			throw new ManagedException("Chiave di configurazione vuota o null");
		}
		Config config = configRepository.findOne(key);
		ConfigDTO configDTO = null;
		if (null != config) {
			configDTO = configMapper.toDTO(config);
		}
		
		return configDTO;
	}

}
