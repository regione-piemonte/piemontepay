/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpmultiiuv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the it.csi.epay.epayfeapi.soap.client.mdpmultiiuv package.
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

	private final static QName _MissingParameterException_QNAME = new QName ( "http://mdpnew.csi.it/mdpmultiiuv/", "MissingParameterException" );

	private final static QName _SOAPException_QNAME = new QName ( "http://mdpnew.csi.it/mdpmultiiuv/", "SOAPException" );

	private final static QName _ErrorParameterException_QNAME = new QName ( "http://mdpnew.csi.it/mdpmultiiuv/", "ErrorParameterException" );

	private final static QName _MdpMultiIuvSrvException_QNAME = new QName ( "http://mdpnew.csi.it/mdpmultiiuv/", "MdpMultiIuvSrvException" );

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epayfeapi.soap.client.mdpmultiiuv
	 */
	public ObjectFactory () {
	}

	/**
	 * Create an instance of {@link MissingParameterException }
	 */
	public MissingParameterException createMissingParameterException () {
		return new MissingParameterException ();
	}

	/**
	 * Create an instance of {@link SOAPException }
	 */
	public SOAPException createSOAPException () {
		return new SOAPException ();
	}

	/**
	 * Create an instance of {@link ErrorParameterException }
	 */
	public ErrorParameterException createErrorParameterException () {
		return new ErrorParameterException ();
	}

	/**
	 * Create an instance of {@link MdpMultiIuvSrvException }
	 */
	public MdpMultiIuvSrvException createMdpMultiIuvSrvException () {
		return new MdpMultiIuvSrvException ();
	}

	/**
	 * Create an instance of {@link IuvComplexResponse }
	 */
	public IuvComplexResponse createIuvComplexResponse () {
		return new IuvComplexResponse ();
	}

	/**
	 * Create an instance of {@link IuvComplex }
	 */
	public IuvComplex createIuvComplex () {
		return new IuvComplex ();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link MissingParameterException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link MissingParameterException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpmultiiuv/", name = "MissingParameterException" )
	public JAXBElement<MissingParameterException> createMissingParameterException ( MissingParameterException value ) {
		return new JAXBElement<> ( _MissingParameterException_QNAME, MissingParameterException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SOAPException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link SOAPException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpmultiiuv/", name = "SOAPException" )
	public JAXBElement<SOAPException> createSOAPException ( SOAPException value ) {
		return new JAXBElement<> ( _SOAPException_QNAME, SOAPException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ErrorParameterException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link ErrorParameterException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpmultiiuv/", name = "ErrorParameterException" )
	public JAXBElement<ErrorParameterException> createErrorParameterException ( ErrorParameterException value ) {
		return new JAXBElement<> ( _ErrorParameterException_QNAME, ErrorParameterException.class, null, value );
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link MdpMultiIuvSrvException }{@code >}
	 *
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link MdpMultiIuvSrvException }{@code >}
	 */
	@XmlElementDecl ( namespace = "http://mdpnew.csi.it/mdpmultiiuv/", name = "MdpMultiIuvSrvException" )
	public JAXBElement<MdpMultiIuvSrvException> createMdpMultiIuvSrvException ( MdpMultiIuvSrvException value ) {
		return new JAXBElement<> ( _MdpMultiIuvSrvException_QNAME, MdpMultiIuvSrvException.class, null, value );
	}

}
