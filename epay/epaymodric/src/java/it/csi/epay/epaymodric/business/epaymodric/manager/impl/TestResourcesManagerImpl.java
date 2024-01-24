/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.TestResourcesManager;
import it.csi.epay.epaymodric.business.epaymodric.model.Test;
import it.csi.epay.epaymodric.business.epaymodric.repository.TestRepository;

@Service
@Transactional
public class TestResourcesManagerImpl implements TestResourcesManager {
	
	 @Autowired
	 private TestRepository testRepository;
	

	@Override
	public String testResources() {
		Test test = testRepository.findOne(1L);
		return test.getDescrizione();
	}

}
