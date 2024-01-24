/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business;

import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import it.csi.epay.epaymdpservices.business.interfaces.TestResourcesBeanLocal;
import it.csi.epay.epayservices.interfaces.ejb.EnteFacade;

@Singleton
public class TestResourcesBean implements TestResourcesBeanLocal{
		
	@Resource(lookup = "java:global/epayservices/epayservices-ejb/EnteFacade")	
	private EnteFacade enteFacade;

	private int hits = 1;

	@Lock(LockType.READ)
	public int getHits() {
		return hits++;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

}
