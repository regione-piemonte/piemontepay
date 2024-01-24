/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.GetProfilazioneUtenteCorrenteService;
import it.csi.epay.epaypacatalogsrv.business.service.TestResourcesService;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesInput;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesOutput;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


/**
 * 
 */
@Service
@Transactional
public class TestResourcesServiceImpl implements TestResourcesService {

	private static final Logger LOG = LogManager.getLogger(GetProfilazioneUtenteCorrenteService.class);

	@Autowired
	private EnteRepository enteRepository;


	@Override
	public TestResourcesOutput testResources (TestResourcesInput input ) {

		try {
			enteRepository.count();
			return TestResourcesOutput.ok();
		}
		catch (Exception e) {
			LOG.error ( "Errore ", e );
			TestResourcesOutput output = TestResourcesOutput.fail ( e, TestResourcesOutput.class );
			output.setCodiceEsito ( Constants.RESULT_CODES.BAD_REQUEST );
			return output;
		}
	}

	


}
