/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp package. 
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

    private final static QName _EsitoFlussiPagoPARequest_QNAME = new QName("http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", "EsitoFlussiPagoPARequest");
    private final static QName _EPaywsoServiceResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/types", "EPaywsoServiceResponse");
    private final static QName _TrasmettiFlussiInErrorePagoPAResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", "TrasmettiFlussiInErrorePagoPAResponse");
    private final static QName _TrasmettiFlussiPagoPAResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", "TrasmettiFlussiPagoPAResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RicercaProvvisoriPagoPARequest }
     * 
     */
    public RicercaProvvisoriPagoPARequest createRicercaProvvisoriPagoPARequest() {
        return new RicercaProvvisoriPagoPARequest();
    }

    /**
     * Create an instance of {@link RicercaProvvisoriPagoPAResponse }
     * 
     */
    public RicercaProvvisoriPagoPAResponse createRicercaProvvisoriPagoPAResponse() {
        return new RicercaProvvisoriPagoPAResponse();
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
     * Create an instance of {@link EndpointType }
     * 
     */
    public EndpointType createEndpointType() {
        return new EndpointType();
    }

    /**
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link SoggettoType }
     * 
     */
    public SoggettoType createSoggettoType() {
        return new SoggettoType();
    }

    /**
     * Create an instance of {@link PersonaGiuridicaType }
     * 
     */
    public PersonaGiuridicaType createPersonaGiuridicaType() {
        return new PersonaGiuridicaType();
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
     * Create an instance of {@link PersonaFisicaType }
     * 
     */
    public PersonaFisicaType createPersonaFisicaType() {
        return new PersonaFisicaType();
    }

    /**
     * Create an instance of {@link EsitoFlussiPagoPAResponse }
     * 
     */
    public EsitoFlussiPagoPAResponse createEsitoFlussiPagoPAResponse() {
        return new EsitoFlussiPagoPAResponse();
    }

    /**
     * Create an instance of {@link TestataTrasmissioneFlussiType }
     * 
     */
    public TestataTrasmissioneFlussiType createTestataTrasmissioneFlussiType() {
        return new TestataTrasmissioneFlussiType();
    }

    /**
     * Create an instance of {@link RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti }
     * 
     */
    public RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti createRicercaProvvisoriPagoPARequestElencoCausaliVersamenti() {
        return new RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti();
    }

    /**
     * Create an instance of {@link TrasmettiFlussiInErrorePagoPARequest }
     * 
     */
    public TrasmettiFlussiInErrorePagoPARequest createTrasmettiFlussiInErrorePagoPARequest() {
        return new TrasmettiFlussiInErrorePagoPARequest();
    }

    /**
     * Create an instance of {@link TestataFlussiInErrore }
     * 
     */
    public TestataFlussiInErrore createTestataFlussiInErrore() {
        return new TestataFlussiInErrore();
    }

    /**
     * Create an instance of {@link TrasmettiFlussiPagoPARequest }
     * 
     */
    public TrasmettiFlussiPagoPARequest createTrasmettiFlussiPagoPARequest() {
        return new TrasmettiFlussiPagoPARequest();
    }

    /**
     * Create an instance of {@link RicercaProvvisoriPagoPAResponse.ElencoProvvisori }
     * 
     */
    public RicercaProvvisoriPagoPAResponse.ElencoProvvisori createRicercaProvvisoriPagoPAResponseElencoProvvisori() {
        return new RicercaProvvisoriPagoPAResponse.ElencoProvvisori();
    }

    /**
     * Create an instance of {@link ProvvisorioType }
     * 
     */
    public ProvvisorioType createProvvisorioType() {
        return new ProvvisorioType();
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
     * Create an instance of {@link EndpointType.CredenzialiAutenticazione }
     * 
     */
    public EndpointType.CredenzialiAutenticazione createEndpointTypeCredenzialiAutenticazione() {
        return new EndpointType.CredenzialiAutenticazione();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestataTrasmissioneFlussiType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", name = "EsitoFlussiPagoPARequest")
    public JAXBElement<TestataTrasmissioneFlussiType> createEsitoFlussiPagoPARequest(TestataTrasmissioneFlussiType value) {
        return new JAXBElement<TestataTrasmissioneFlussiType>(_EsitoFlussiPagoPARequest_QNAME, TestataTrasmissioneFlussiType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/types", name = "EPaywsoServiceResponse")
    public JAXBElement<ResponseType> createEPaywsoServiceResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_EPaywsoServiceResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", name = "TrasmettiFlussiInErrorePagoPAResponse")
    public JAXBElement<ResponseType> createTrasmettiFlussiInErrorePagoPAResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_TrasmettiFlussiInErrorePagoPAResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", name = "TrasmettiFlussiPagoPAResponse")
    public JAXBElement<ResponseType> createTrasmettiFlussiPagoPAResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_TrasmettiFlussiPagoPAResponse_QNAME, ResponseType.class, null, value);
    }

}
