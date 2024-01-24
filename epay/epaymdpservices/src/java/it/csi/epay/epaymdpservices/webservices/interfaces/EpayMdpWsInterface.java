/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.webservices.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoRiceviRT;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.EsitoVerificaDatiPagamento;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviEsito;
import it.csi.epay.epaymdpservices.dto.epaymdpservices.ParametriRiceviRT;
import it.csi.epay.epaymdpservices.exception.EpayMdpServicesException;
import it.csi.epay.epaymdpservices.exception.ErrorParameterException;
import it.csi.epay.epaymdpservices.exception.MissingParameterException;

@WebService(targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public interface EpayMdpWsInterface {

	@WebMethod(operationName = "riceviRT")
	@WebResult(name = "result", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
	//@WebResult(targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "esitoRiceviRT")
    @RequestWrapper(localName = "riceviRT", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.RiceviRT")
    @ResponseWrapper(localName = "riceviRTResponse", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.RiceviRTResponse")
	public EsitoRiceviRT riceviRT(@WebParam(name = "parametri") ParametriRiceviRT parametriRiceviRT) throws MissingParameterException, ErrorParameterException, EpayMdpServicesException;

	@WebMethod(operationName = "riceviEsito")
	@RequestWrapper(localName = "riceviEsito", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.RiceviEsito")
    @ResponseWrapper(localName = "riceviEsitoResponse", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.RiceviEsitoResponse")
	@WebResult(name = "result", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
	//@WebResult(targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "esitoRiceviEsito")
	public EsitoRiceviEsito riceviEsito(@WebParam(name = "parametri", targetNamespace = "") ParametriRiceviEsito parametri)throws RiceviEsitoException, UnrecoverableException ;

    @WebResult(name = "result", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
    @RequestWrapper(localName = "chiediDatiPagamento", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.ChiediDatiPagamento")
    @ResponseWrapper(localName = "chiediDatiPagamentoResponse", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.ChiediDatiPagamentoResponse")
    @WebMethod
    public EsitoChiediDatiPagamento chiediDatiPagamento(
        @WebParam(name = "parametri", targetNamespace = "")
        ParametriChiediDatiPagamento parametri
    ) throws ChiediDatiPagamentoException, UnrecoverableException;
    
    
    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "verificaDatiPagamento", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.VerificaDatiPagamento")
    @ResponseWrapper(localName = "verificaDatiPagamentoResponse", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", className = "it.csi.mdp.clientmod3.VerificaDatiPagamentoResponse")
    @WebMethod
    public EsitoVerificaDatiPagamento verificaDatiPagamento(
        @WebParam(name = "parametri", targetNamespace = "")
        ParametriVerificaDatiPagamento parametri
    ) throws VerificaDatiPagamentoException, UnrecoverableException;

}
