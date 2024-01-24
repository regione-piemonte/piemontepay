/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.webservices;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import it.csi.epay.epaymdpservices.business.interfaces.TestResourcesBeanLocal;

@WebService(serviceName = "CounterService")
public class WsCounterService {
	
	@EJB	
	private TestResourcesBeanLocal resourceBean;
	
	@WebMethod(operationName = "getHits")
	public int getHits(){
		return resourceBean.getHits();
	}	

}
