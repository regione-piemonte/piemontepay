/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epaypa.sincronizzapagamentipec;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epaypa.sincronizzapagamentipec package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EPaypaServiceResponse_QNAME = new QName("http://www.csi.it/epay/epaypa/types", "EPaypaServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epaypa.sincronizzapagamentipec
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SincronizzaPagamentiRequest }
     * 
     */
    public SincronizzaPagamentiRequest createSincronizzaPagamentiRequest() {
        return new SincronizzaPagamentiRequest();
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
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link SincronizzaPagamentiRequest.Pagamento }
     * 
     */
    public SincronizzaPagamentiRequest.Pagamento createSincronizzaPagamentiRequestPagamento() {
        return new SincronizzaPagamentiRequest.Pagamento();
    }

    /**
     * Create an instance of {@link SincronizzaPagamento }
     * 
     */
    public SincronizzaPagamento createSincronizzaPagamento() {
        return new SincronizzaPagamento();
    }

    /**
     * Create an instance of {@link SincronizzaPagamentiResponse }
     * 
     */
    public SincronizzaPagamentiResponse createSincronizzaPagamentiResponse() {
        return new SincronizzaPagamentiResponse();
    }

    /**
     * Create an instance of {@link ResultType }
     * 
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaypa/types", name = "EPaypaServiceResponse")
    public JAXBElement<ResponseType> createEPaypaServiceResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_EPaypaServiceResponse_QNAME, ResponseType.class, null, value);
    }

}
