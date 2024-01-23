/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the it.csi.mdp package.
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

	private final static QName _BusinessException_QNAME = new QName ( "http://www.csi.it/mdp/", "BusinessException" );

	private final static QName _SystemException_QNAME = new QName ( "http://www.csi.it/mdp/", "SystemException" );

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.mdp
	 */
	public ObjectFactory () {
	}

	/**
	 * Create an instance of {@link BusinessException }
	 */
	public BusinessException createBusinessException () {
		return new BusinessException ();
	}

	/**
	 * Create an instance of {@link SystemException }
	 */
	public SystemException createSystemException () {
		return new SystemException ();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BusinessException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link BusinessException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://www.csi.it/mdp/", name = "BusinessException" )
	public JAXBElement<BusinessException> createBusinessException ( BusinessException value ) {
		return new JAXBElement<> ( _BusinessException_QNAME, BusinessException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SystemException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link SystemException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://www.csi.it/mdp/", name = "SystemException" )
	public JAXBElement<SystemException> createSystemException ( SystemException value ) {
		return new JAXBElement<> ( _SystemException_QNAME, SystemException.class, null, value );
	}

}
