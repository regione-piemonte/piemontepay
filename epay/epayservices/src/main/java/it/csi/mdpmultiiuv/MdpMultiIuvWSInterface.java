/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpmultiiuv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MdpMultiIuvWSInterface", targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MdpMultiIuvWSInterface {


    /**
     * 
     * @param timeStamp
     * @param idApplicazione
     * @param codicePagamento
     * @param numeroOccorrenzeIUV
     * @param mac
     * @return
     *     returns it.csi.mdpmultiiuv.IuvComplexResponse
     * @throws MissingParameterException_Exception
     * @throws MdpMultiIuvSrvException_Exception
     * @throws ErrorParameterException_Exception
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(name = "ListaIuvComplex", partName = "ListaIuvComplex")
    public IuvComplexResponse getIuvComplex(
        @WebParam(name = "idApplicazione", partName = "idApplicazione")
        String idApplicazione,
        @WebParam(name = "codicePagamento", partName = "codicePagamento")
        String codicePagamento,
        @WebParam(name = "numeroOccorrenzeIUV", partName = "numeroOccorrenzeIUV")
        int numeroOccorrenzeIUV,
        @WebParam(name = "timeStamp", partName = "timeStamp")
        String timeStamp,
        @WebParam(name = "mac", partName = "mac")
        String mac)
        throws ErrorParameterException_Exception, MdpMultiIuvSrvException_Exception, MissingParameterException_Exception, SOAPException_Exception
    ;

}
