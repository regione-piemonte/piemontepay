/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.xsd.trasmissioneflussi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epay.epaymodric.util.xsd.trasmissioneflussi package. 
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

    private final static QName _TrasmettiFlussiInErroreRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/rendicontazione", "TrasmettiFlussiInErroreRequest");
    private final static QName _TrasmettiFlussoRiconciliazioneRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/rendicontazione", "TrasmettiFlussoRiconciliazioneRequest");
    private final static QName _EPaywsoServiceResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/types", "EPaywsoServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epaymodric.util.xsd.trasmissioneflussi
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
     * Create an instance of {@link TrasmettiFlussiInErroreRequest }
     * 
     */
    public TrasmettiFlussiInErroreRequest createTrasmettiFlussiInErroreRequest() {
        return new TrasmettiFlussiInErroreRequest();
    }

    /**
     * Create an instance of {@link TrasmettiFlussoRiconciliazioneRequest }
     * 
     */
    public TrasmettiFlussoRiconciliazioneRequest createTrasmettiFlussoRiconciliazioneRequest() {
        return new TrasmettiFlussoRiconciliazioneRequest();
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
     * Create an instance of {@link TestataFlussoRiconciliazioneType }
     * 
     */
    public TestataFlussoRiconciliazioneType createTestataFlussoRiconciliazioneType() {
        return new TestataFlussoRiconciliazioneType();
    }

    /**
     * Create an instance of {@link FlussoSintesiType }
     * 
     */
    public FlussoSintesiType createFlussoSintesiType() {
        return new FlussoSintesiType();
    }

    /**
     * Create an instance of {@link FlussoDettaglioType }
     * 
     */
    public FlussoDettaglioType createFlussoDettaglioType() {
        return new FlussoDettaglioType();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link TrasmettiFlussiInErroreRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/rendicontazione", name = "TrasmettiFlussiInErroreRequest")
    public JAXBElement<TrasmettiFlussiInErroreRequest> createTrasmettiFlussiInErroreRequest(TrasmettiFlussiInErroreRequest value) {
        return new JAXBElement<TrasmettiFlussiInErroreRequest>(_TrasmettiFlussiInErroreRequest_QNAME, TrasmettiFlussiInErroreRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrasmettiFlussoRiconciliazioneRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/rendicontazione", name = "TrasmettiFlussoRiconciliazioneRequest")
    public JAXBElement<TrasmettiFlussoRiconciliazioneRequest> createTrasmettiFlussoRiconciliazioneRequest(TrasmettiFlussoRiconciliazioneRequest value) {
        return new JAXBElement<TrasmettiFlussoRiconciliazioneRequest>(_TrasmettiFlussoRiconciliazioneRequest_QNAME, TrasmettiFlussoRiconciliazioneRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/types", name = "EPaywsoServiceResponse")
    public JAXBElement<ResponseType> createEPaywsoServiceResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_EPaywsoServiceResponse_QNAME, ResponseType.class, null, value);
    }

}
