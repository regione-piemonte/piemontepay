/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.webservice;

import it.csi.csi.wrapper.CSIException;
import it.csi.csi.wrapper.SystemException;
import it.csi.csi.wrapper.UnrecoverableException;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;

import java.rmi.RemoteException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.soap.SOAPException;



@WebService (targetNamespace = "http://mdpnew.csi.it/mdpiuv/")
@SOAPBinding(style=Style.RPC, use=Use.LITERAL)
public interface MdpiuvWSInterface {

	@WebMethod(operationName="getSingleIUV")
	@WebResult(targetNamespace="http://mdpnew.csi.it/mdpiuv/",
     name="iuv")
	public String getSingleIUV( @WebParam(name = "idApplicazione") String idApplication, @WebParam(name = "codicePagamento") String codicePagamento) throws SOAPException, RemoteException, SystemException, UnrecoverableException, MdpIuvSrvException, CSIException;
	
	@WebMethod(operationName="getMultiIUV")
	@WebResult(targetNamespace="http://mdpnew.csi.it/mdpiuv/",
     name="listaIuv")
	public String[] getMultiIUV( @WebParam(name = "idApplicazione")String idApplication, @WebParam(name = "numeroOccorrenzeIUV") Integer numeroOccorrenzeIUV, @WebParam(name = "codicePagamento") String codicePagamento) throws SOAPException, RemoteException, SystemException, UnrecoverableException, MdpIuvSrvException, CSIException;

	@WebMethod(operationName="getSingleIUVComplex")
	@WebResult(targetNamespace="http://mdpnew.csi.it/mdpiuv/", name="iuvComplexResponse")
	public IuvComplex getSingleIUVComplex( @WebParam(name = "idApplicazione") String idApplication, @WebParam(name = "codicePagamento") String codicePagamento) throws SOAPException, RemoteException, SystemException, UnrecoverableException, MdpIuvSrvException, CSIException;
	
	
	@WebMethod(operationName="getMultiIUVComplex")
	@WebResult(targetNamespace="http://mdpnew.csi.it/mdpiuv/", name="iuvComplexResponse")
	public List<IuvComplex> getMultiIUVComplex( @WebParam(name = "idApplicazione") String idApplication, @WebParam(name = "codicePagamento") String codicePagamento, @WebParam(name = "numeroOccorrenzeIUV") Integer numeroOccorrenzeIUV) throws SOAPException, RemoteException, SystemException, UnrecoverableException, MdpIuvSrvException, CSIException;
	
}
