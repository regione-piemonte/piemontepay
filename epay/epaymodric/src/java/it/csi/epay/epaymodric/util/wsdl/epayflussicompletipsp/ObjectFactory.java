/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epay.epaywso.flussi_completi_psp.types package. 
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

    private final static QName _TrasmettiFlussiPagoPAResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/flussi-completi-psp/types", "TrasmettiFlussiPagoPAResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epaywso.flussi_completi_psp.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatiAggiuntiviType }
     * 
     */
    public DatiAggiuntiviType createDatiAggiuntiviType() {
        return new DatiAggiuntiviType();
    }

    /**
     * Create an instance of {@link TrasmettiFlussiPagoPARequest }
     * 
     */
    public TrasmettiFlussiPagoPARequest createTrasmettiFlussiPagoPARequest() {
        return new TrasmettiFlussiPagoPARequest();
    }

    /**
     * Create an instance of {@link TestataTrasmissioneFlussiType }
     * 
     */
    public TestataTrasmissioneFlussiType createTestataTrasmissioneFlussiType() {
        return new TestataTrasmissioneFlussiType();
    }

    /**
     * Create an instance of {@link DatiAggiuntiviType.ElencoCodiciVersamento }
     * 
     */
    public DatiAggiuntiviType.ElencoCodiciVersamento createDatiAggiuntiviTypeElencoCodiciVersamento() {
        return new DatiAggiuntiviType.ElencoCodiciVersamento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/flussi-completi-psp/types", name = "TrasmettiFlussiPagoPAResponse")
    public JAXBElement<ResponseType> createTrasmettiFlussiPagoPAResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_TrasmettiFlussiPagoPAResponse_QNAME, ResponseType.class, null, value);
    }

}
