/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.webservice;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa.interfaces.MdpcoopapplicativaBusinessInterface;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.MdpcoopapplicativaConstants;


@WebService ( name = "RichiediApplicationId", portName = "RichiediApplicationId", serviceName = "RichiediApplicationId",
                targetNamespace = "http://www.csi.it/epay/epaywso/richiediapplicationid" )
public class MdpRichiediApplicationIdWSImpl implements MdpRichiediApplicationIdWSInterface {

    protected static Logger log = Logger.getLogger ( MdpcoopapplicativaConstants.MDPCOOPAPPLICATIVA_ROOT_LOG_CATEGORY + ".ws" );

    MdpcoopapplicativaBusinessInterface mdpcoopapplicativaBusinessInterface;

    public MdpcoopapplicativaBusinessInterface getMdpcoopapplicativaBusinessInterface () {
        return mdpcoopapplicativaBusinessInterface;
    }

    public void setMdpcoopapplicativaBusinessInterface (
        MdpcoopapplicativaBusinessInterface mdpcoopapplicativaBusinessInterface ) {
        this.mdpcoopapplicativaBusinessInterface = mdpcoopapplicativaBusinessInterface;
    }

	public RichiediApplicationIdResponse richiediApplicationId(RichiediApplicationIdRequest parameters) {
		return mdpcoopapplicativaBusinessInterface.richiediApplicationId(parameters);
	}



    

    
   
}

