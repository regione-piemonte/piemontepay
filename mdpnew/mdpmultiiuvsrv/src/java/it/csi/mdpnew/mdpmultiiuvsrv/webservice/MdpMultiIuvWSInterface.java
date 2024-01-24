/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.webservice;

import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.ErrorParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MdpMultiIuvSrvException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MissingParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv.IuvComplexResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.soap.SOAPException;

@WebService(targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public interface MdpMultiIuvWSInterface {

	@WebMethod(operationName = "getIuvComplex")
	@WebResult(targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/", name = "ListaIuvComplex")
	public IuvComplexResponse getIuvComplex(@WebParam(name = "idApplicazione") String idApplication,
			@WebParam(name = "codicePagamento") String codicePagamento, @WebParam(name = "numeroOccorrenzeIUV") Integer numeroOccorrenzeIUV,
			@WebParam(name = "timeStamp") String timeStamp, @WebParam(name = "mac") String mac) throws SOAPException, MdpMultiIuvSrvException,
			MissingParameterException, ErrorParameterException;

}
