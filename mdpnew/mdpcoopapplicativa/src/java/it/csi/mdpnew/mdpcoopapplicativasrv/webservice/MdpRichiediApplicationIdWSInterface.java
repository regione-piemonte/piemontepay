/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdResponse;


@WebService ( targetNamespace = "http://www.csi.it/epay/epaywso/richiediapplicationid", name = "RichiediApplicationId" )
@SOAPBinding ( parameterStyle = SOAPBinding.ParameterStyle.BARE )
@XmlSeeAlso ( {
    it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ObjectFactory.class
} )
public interface MdpRichiediApplicationIdWSInterface {

    @WebMethod ( operationName = "RichiediApplicationId", action = "http://www.csi.it/epay/epaywso/richiediapplicationid/RichiediApplicationId" )
    @WebResult ( name = "RichiediApplicationIdResponse", targetNamespace = "http://www.csi.it/epay/epaywso/richiediapplicationid", partName = "parameters" )
    public RichiediApplicationIdResponse richiediApplicationId (
        @WebParam ( partName = "parameters", name = "RichiediApplicationIdRequest",
                        targetNamespace = "http://www.csi.it/epay/epaywso/richiediapplicationid" ) RichiediApplicationIdRequest parameters );

}
