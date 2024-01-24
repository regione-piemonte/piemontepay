/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaysim.business.epaysim.manager.TestResourcesManager;
import it.csi.epay.epaysim.business.epaysim.model.Test;
import it.csi.epay.epaysim.business.epaysim.repository.TestRepository;

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


	public TestRepository getTestRepository() {
		return testRepository;
	}


	public void setTestRepository(TestRepository testRepository) {
		this.testRepository = testRepository;
	}


}
