/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.25 alle 11:56:43 AM CEST 
//


package it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp package. 
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

    private final static QName _ReportFlussiErrati_QNAME = new QName("http://www.csi.it/epay/epaywso/rendicontazione", "ReportFlussiErrati");
    private final static QName _EPaywsoServiceResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/types", "EPaywsoServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EndpointType }
     * 
     */
    public EndpointType createEndpointType() {
        return new EndpointType();
    }

    /**
     * Create an instance of {@link EsitoAggiornamentoType }
     * 
     */
    public EsitoAggiornamentoType createEsitoAggiornamentoType() {
        return new EsitoAggiornamentoType();
    }

    /**
     * Create an instance of {@link EsitoInserimentoType }
     * 
     */
    public EsitoInserimentoType createEsitoInserimentoType() {
        return new EsitoInserimentoType();
    }

    /**
     * Create an instance of {@link ElencoFlussiInErroreType }
     * 
     */
    public ElencoFlussiInErroreType createElencoFlussiInErroreType() {
        return new ElencoFlussiInErroreType();
    }

    /**
     * Create an instance of {@link TestataFlussiErroreType }
     * 
     */
    public TestataFlussiErroreType createTestataFlussiErroreType() {
        return new TestataFlussiErroreType();
    }

    /**
     * Create an instance of {@link FlussiInErroreType }
     * 
     */
    public FlussiInErroreType createFlussiInErroreType() {
        return new FlussiInErroreType();
    }

    /**
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link ResultType }
     * 
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    /**
     * Create an instance of {@link PosizioneDebitoriaType }
     * 
     */
    public PosizioneDebitoriaType createPosizioneDebitoriaType() {
        return new PosizioneDebitoriaType();
    }

    /**
     * Create an instance of {@link SoggettoType }
     * 
     */
    public SoggettoType createSoggettoType() {
        return new SoggettoType();
    }

    /**
     * Create an instance of {@link PersonaFisicaType }
     * 
     */
    public PersonaFisicaType createPersonaFisicaType() {
        return new PersonaFisicaType();
    }

    /**
     * Create an instance of {@link PersonaGiuridicaType }
     * 
     */
    public PersonaGiuridicaType createPersonaGiuridicaType() {
        return new PersonaGiuridicaType();
    }

    /**
     * Create an instance of {@link EndpointType.CredenzialiAutenticazione }
     * 
     */
    public EndpointType.CredenzialiAutenticazione createEndpointTypeCredenzialiAutenticazione() {
        return new EndpointType.CredenzialiAutenticazione();
    }

    /**
     * Create an instance of {@link EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate }
     * 
     */
    public EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate createEsitoAggiornamentoTypeElencoPosizioniDebitorieAggiornate() {
        return new EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate();
    }

    /**
     * Create an instance of {@link EsitoInserimentoType.ElencoPosizioniDebitorieInserite }
     * 
     */
    public EsitoInserimentoType.ElencoPosizioniDebitorieInserite createEsitoInserimentoTypeElencoPosizioniDebitorieInserite() {
        return new EsitoInserimentoType.ElencoPosizioniDebitorieInserite();
    }

    /**
     * Create an instance of {@link ElencoFlussiInErroreType.RigheFlussi }
     * 
     */
    public ElencoFlussiInErroreType.RigheFlussi createElencoFlussiInErroreTypeRigheFlussi() {
        return new ElencoFlussiInErroreType.RigheFlussi();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElencoFlussiInErroreType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElencoFlussiInErroreType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/rendicontazione", name = "ReportFlussiErrati")
    public JAXBElement<ElencoFlussiInErroreType> createReportFlussiErrati(ElencoFlussiInErroreType value) {
        return new JAXBElement<ElencoFlussiInErroreType>(_ReportFlussiErrati_QNAME, ElencoFlussiInErroreType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/types", name = "EPaywsoServiceResponse")
    public JAXBElement<ResponseType> createEPaywsoServiceResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_EPaywsoServiceResponse_QNAME, ResponseType.class, null, value);
    }

}
