/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.0
 * 2023-03-15T20:45:31.048+01:00
 * Generated source version: 3.3.0
 */

@WebFault ( name = "DatatypeConfigurationException", targetNamespace = "http://interfacecxf.core.mdp.csi.it/" )
@SuppressWarnings ( "unused" )
public class DatatypeConfigurationException_Exception extends Exception {

	public static final long serialVersionUID = 1L;

	private DatatypeConfigurationException datatypeConfigurationException;

	public DatatypeConfigurationException_Exception () {
		super ();
	}

	public DatatypeConfigurationException_Exception ( String message ) {
		super ( message );
	}

	public DatatypeConfigurationException_Exception ( String message, Throwable cause ) {
		super ( message, cause );
	}

	public DatatypeConfigurationException_Exception ( String message, DatatypeConfigurationException datatypeConfigurationException ) {
		super ( message );
		this.datatypeConfigurationException = datatypeConfigurationException;
	}

	public DatatypeConfigurationException_Exception ( String message, DatatypeConfigurationException datatypeConfigurationException, Throwable cause ) {
		super ( message, cause );
		this.datatypeConfigurationException = datatypeConfigurationException;
	}

	public DatatypeConfigurationException getFaultInfo () {
		return this.datatypeConfigurationException;
	}
}
