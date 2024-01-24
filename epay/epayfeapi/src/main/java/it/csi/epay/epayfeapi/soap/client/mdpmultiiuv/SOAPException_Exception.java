/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpmultiiuv;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.0
 * 2023-03-16T17:53:25.610+01:00
 * Generated source version: 3.3.0
 */

@WebFault ( name = "SOAPException", targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/" )
@SuppressWarnings ( "unused" )
public class SOAPException_Exception extends Exception {

	public static final long serialVersionUID = 1L;

	private it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.SOAPException soapException;

	public SOAPException_Exception () {
		super ();
	}

	public SOAPException_Exception ( String message ) {
		super ( message );
	}

	public SOAPException_Exception ( String message, java.lang.Throwable cause ) {
		super ( message, cause );
	}

	public SOAPException_Exception ( String message, it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.SOAPException soapException ) {
		super ( message );
		this.soapException = soapException;
	}

	public SOAPException_Exception ( String message, it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.SOAPException soapException, java.lang.Throwable cause ) {
		super ( message, cause );
		this.soapException = soapException;
	}

	public it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.SOAPException getFaultInfo () {
		return this.soapException;
	}
}
