/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpiuv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the it.csi.soap.client.mdpiuv.mdpiuv package.
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

	private final static QName _SystemException_QNAME = new QName ( "http://mdpnew.csi.it/mdpiuv/", "SystemException" );

	private final static QName _UnrecoverableException_QNAME = new QName ( "http://mdpnew.csi.it/mdpiuv/", "UnrecoverableException" );

	private final static QName _MdpIuvSrvException_QNAME = new QName ( "http://mdpnew.csi.it/mdpiuv/", "MdpIuvSrvException" );

	private final static QName _CSIException_QNAME = new QName ( "http://mdpnew.csi.it/mdpiuv/", "CSIException" );

	private final static QName _SOAPException_QNAME = new QName ( "http://mdpnew.csi.it/mdpiuv/", "SOAPException" );

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.soap.client.mdpiuv.mdpiuv
	 */
	public ObjectFactory () {
	}

	/**
	 * Create an instance of {@link SystemException }
	 */
	public SystemException createSystemException () {
		return new SystemException ();
	}

	/**
	 * Create an instance of {@link UnrecoverableException }
	 */
	public UnrecoverableException createUnrecoverableException () {
		return new UnrecoverableException ();
	}

	/**
	 * Create an instance of {@link MdpIuvSrvException }
	 */
	public MdpIuvSrvException createMdpIuvSrvException () {
		return new MdpIuvSrvException ();
	}

	/**
	 * Create an instance of {@link CSIException }
	 */
	public CSIException createCSIException () {
		return new CSIException ();
	}

	/**
	 * Create an instance of {@link SOAPException }
	 */
	public SOAPException createSOAPException () {
		return new SOAPException ();
	}

	/**
	 * Create an instance of {@link IuvComplex }
	 */
	public IuvComplex createIuvComplex () {
		return new IuvComplex ();
	}

	/**
	 * Create an instance of {@link IuvComplexArray }
	 */
	public IuvComplexArray createIuvComplexArray () {
		return new IuvComplexArray ();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SystemException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link SystemException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpiuv/", name = "SystemException" )
	public JAXBElement<SystemException> createSystemException ( SystemException value ) {
		return new JAXBElement<> ( _SystemException_QNAME, SystemException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link UnrecoverableException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link UnrecoverableException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpiuv/", name = "UnrecoverableException" )
	public JAXBElement<UnrecoverableException> createUnrecoverableException ( UnrecoverableException value ) {
		return new JAXBElement<> ( _UnrecoverableException_QNAME, UnrecoverableException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link MdpIuvSrvException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link MdpIuvSrvException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpiuv/", name = "MdpIuvSrvException" )
	public JAXBElement<MdpIuvSrvException> createMdpIuvSrvException ( MdpIuvSrvException value ) {
		return new JAXBElement<> ( _MdpIuvSrvException_QNAME, MdpIuvSrvException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CSIException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link CSIException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpiuv/", name = "CSIException" )
	public JAXBElement<CSIException> createCSIException ( CSIException value ) {
		return new JAXBElement<> ( _CSIException_QNAME, CSIException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SOAPException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link SOAPException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpiuv/", name = "SOAPException" )
	public JAXBElement<SOAPException> createSOAPException ( SOAPException value ) {
		return new JAXBElement<> ( _SOAPException_QNAME, SOAPException.class, null, value );
	}

}
