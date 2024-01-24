/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.coopapplicativapec;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import it.csi.epay.epayservices.integration.db.manager.EnteTempManager;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.coopapplicativapec.TestResourcesRequest;


@Singleton ( name = "TestResourcesOperation", mappedName = "TestResourcesOperation" )
public class TestResourcesOperation {

	protected LogUtil log = new LogUtil(this.getClass());

	@EJB
    EnteTempManager enteTempManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public CodiciEsito execute ( TestResourcesRequest parameters ) {

        return CodiciEsito.ESECUZIONE_OK;
	}

}
