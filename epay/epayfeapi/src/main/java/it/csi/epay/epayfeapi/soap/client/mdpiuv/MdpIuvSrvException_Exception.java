/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpiuv;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.0
 * 2023-03-09T15:15:50.318+01:00
 * Generated source version: 3.3.0
 */

@WebFault ( name = "MdpIuvSrvException", targetNamespace = "http://mdpnew.csi.it/mdpiuv/" )
@SuppressWarnings ( "unused" )
public class MdpIuvSrvException_Exception extends Exception {

	public static final long serialVersionUID = 1L;

	private MdpIuvSrvException mdpIuvSrvException;

	public MdpIuvSrvException_Exception () {
		super ();
	}

	public MdpIuvSrvException_Exception ( String message ) {
		super ( message );
	}

	public MdpIuvSrvException_Exception ( String message, Throwable cause ) {
		super ( message, cause );
	}

	public MdpIuvSrvException_Exception ( String message, MdpIuvSrvException mdpIuvSrvException ) {
		super ( message );
		this.mdpIuvSrvException = mdpIuvSrvException;
	}

	public MdpIuvSrvException_Exception ( String message, MdpIuvSrvException mdpIuvSrvException, Throwable cause ) {
		super ( message, cause );
		this.mdpIuvSrvException = mdpIuvSrvException;
	}

	public MdpIuvSrvException getFaultInfo () {
		return this.mdpIuvSrvException;
	}
}
