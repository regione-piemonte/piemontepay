/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesInput;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesOutput;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;


@Operation ( consumes = TestResourcesInput.class, produces = TestResourcesOutput.class, skipAuthentication = true )
@Component
public class TestResourcesOperation implements OperationHandler<TestResourcesInput, TestResourcesOutput> {

	@Autowired
	private EnteRepository enteRepository;

    @Autowired
    private InvioMailService invioMailService;

	@Override
	@Transactional
    public TestResourcesOutput execute ( TestResourcesInput input, OperationDispatchingContext<TestResourcesInput, TestResourcesOutput> context ) {
		enteRepository.count();
		return TestResourcesOutput.ok();
	}

}
