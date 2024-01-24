/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtDatiEsitoRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtRichiestaRevocaRequest;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 *
 */
@WebService(name = "EpayFruitoreRiceviRr", targetNamespace = "http://www.csi.it/epay/epaywso/ricevirichiestarevoca")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EpayFruitoreRiceviRrService {


    /**
     *
     * @param parameters
     * @return
     *     returns it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtDatiEsitoRevoca
     */
    @WebMethod(operationName = "riceviRichiestaRevoca", action = "http://www.csi.it/epay/epaywso/ricevirichiestarevoca/riceviRichiestaRevoca") //  action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AggiornaEnte") 
    @WebResult(name = "ctDatiEsitoRevoca", targetNamespace = "http://www.csi.it/epay/epaywso/ricevirichiestarevoca", partName = "parameters")
    public CtDatiEsitoRevoca riceviRichiestaRevoca(
        @WebParam(name = "CtRichiestaRevocaRequest", targetNamespace = "http://www.csi.it/epay/epaywso/ricevirichiestarevoca", partName = "parameters")
        CtRichiestaRevocaRequest parameters);





 
}
