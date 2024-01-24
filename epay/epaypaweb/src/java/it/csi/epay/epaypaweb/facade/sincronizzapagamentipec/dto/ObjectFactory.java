/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _EPaypaServiceResponse_QNAME = new QName ( "http://www.csi.it/epay/epaypa/types", "EPaypaServiceResponse" );

    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseType }
     *
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link TestResourcesRequest }
     *
     */
    public TestResourcesRequest createTestResourcesRequest() {
        return new TestResourcesRequest();
    }


    /**
     * Create an instance of {@link TestResourcesResponse }
     *
     */
    public TestResourcesResponse createTestResourcesResponse() {
        return new TestResourcesResponse();
    }


    /**
     * Create an instance of {@link ResultType }
     *
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    public SincronizzaPagamentiRequest createSincronizzaPagamentiRequest () {
        return new SincronizzaPagamentiRequest ();
    }

    public SincronizzaPagamentiResponse createSincronizzaPagamentiResponse () {
        return new SincronizzaPagamentiResponse ();
    }

    public SincronizzaPagamento createSincronizzaPagamento () {
        return new SincronizzaPagamento ();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     *
     */
    @XmlElementDecl ( namespace = "http://www.csi.it/epay/epaypa/types", name = "EPaypaServiceResponse" )
    public JAXBElement<ResponseType> createEPaypaServiceResponse ( ResponseType value ) {
        return new JAXBElement<> ( _EPaypaServiceResponse_QNAME, ResponseType.class, null, value );
    }

}
