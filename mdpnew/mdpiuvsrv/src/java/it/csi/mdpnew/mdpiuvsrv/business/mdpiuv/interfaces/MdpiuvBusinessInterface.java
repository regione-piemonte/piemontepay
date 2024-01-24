/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.business.mdpiuv.interfaces;

import javax.xml.soap.SOAPException;

import it.csi.mdpnew.mdpiuvsrv.interfacecsi.mdpiuv.MdpiuvSrv;


public interface MdpiuvBusinessInterface extends MdpiuvSrv{

	
	
	public String getSingleIUV(String idApplication,String dettaglioPagamento) throws SOAPException,it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
	 											 it.csi.csi.wrapper.UnrecoverableException,
 											 	 it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;
	
	public String[] getMultIUV(String idApplication, Integer numeroRichiesto, String dettaglioPagamento)
										throws SOAPException, it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
											   it.csi.csi.wrapper.UnrecoverableException,
											   it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;
	
	/*
	public String getSingleIUV(String idApplication, String dettaglioPagamento) throws SOAPException,it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
			 it.csi.csi.wrapper.UnrecoverableException,
		 	 it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;
	
	public String[] getMultIUV(String idApplication, 
							   Integer numeroRichiesto,
							   String dettaglioPagamento)
		throws SOAPException, it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, 
		  it.csi.csi.wrapper.UnrecoverableException,
		  it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;

     */
}
