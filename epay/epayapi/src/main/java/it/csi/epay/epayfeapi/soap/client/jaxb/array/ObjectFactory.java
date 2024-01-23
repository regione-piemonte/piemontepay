/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.jaxb.array;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the it.csi.soap.client.jaxb.array package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
@SuppressWarnings ( "unused" )
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.soap.client.jaxb.array
	 */
	public ObjectFactory () {
	}

	/**
	 * Create an instance of {@link StringArray }
	 */
	public StringArray createStringArray () {
		return new StringArray ();
	}

}
