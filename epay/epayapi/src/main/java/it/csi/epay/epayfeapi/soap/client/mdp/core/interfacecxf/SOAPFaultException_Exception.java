/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.0
 * 2023-03-15T20:45:31.052+01:00
 * Generated source version: 3.3.0
 */

@WebFault ( name = "SOAPFaultException", targetNamespace = "http://interfacecxf.core.mdp.csi.it/" )
@SuppressWarnings ( "unused" )
public class SOAPFaultException_Exception extends Exception {

	public static final long serialVersionUID = 1L;

	private SOAPFaultException soapFaultException;

	public SOAPFaultException_Exception () {
		super ();
	}

	public SOAPFaultException_Exception ( String message ) {
		super ( message );
	}

	public SOAPFaultException_Exception ( String message, Throwable cause ) {
		super ( message, cause );
	}

	public SOAPFaultException_Exception ( String message, SOAPFaultException soapFaultException ) {
		super ( message );
		this.soapFaultException = soapFaultException;
	}

	public SOAPFaultException_Exception ( String message, SOAPFaultException soapFaultException, Throwable cause ) {
		super ( message, cause );
		this.soapFaultException = soapFaultException;
	}

	public SOAPFaultException getFaultInfo () {
		return this.soapFaultException;
	}
}
