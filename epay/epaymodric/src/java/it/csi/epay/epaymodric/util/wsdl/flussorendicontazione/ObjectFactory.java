/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.flussorendicontazione;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epay.epaymodric.util.wsdl.flussorendicontazione package. 
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

    private final static QName _EPaywsoServiceResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/types", "EPaywsoServiceResponse");
    private final static QName _TrasmettiRTExtRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/rendicontazione-ext", "TrasmettiRTExtRequest");
    private final static QName _TrasmettiFlussoRendicontazioneExtRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/rendicontazione-ext", "TrasmettiFlussoRendicontazioneExtRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epaymodric.util.wsdl.flussorendicontazione
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestataFlussoRendicontazioneExtType }
     * 
     */
    public TestataFlussoRendicontazioneExtType createTestataFlussoRendicontazioneExtType() {
        return new TestataFlussoRendicontazioneExtType();
    }

    /**
     * Create an instance of {@link PagamentoIntermediatoType }
     * 
     */
    public PagamentoIntermediatoType createPagamentoIntermediatoType() {
        return new PagamentoIntermediatoType();
    }

    /**
     * Create an instance of {@link TrasmettiRTExtRequestType }
     * 
     */
    public TrasmettiRTExtRequestType createTrasmettiRTExtRequestType() {
        return new TrasmettiRTExtRequestType();
    }

    /**
     * Create an instance of {@link TrasmettiFlussoRendicontazioneExtRequestType }
     * 
     */
    public TrasmettiFlussoRendicontazioneExtRequestType createTrasmettiFlussoRendicontazioneExtRequestType() {
        return new TrasmettiFlussoRendicontazioneExtRequestType();
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
     * Create an instance of {@link ResultType }
     * 
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    /**
     * Create an instance of {@link SoggettoType }
     * 
     */
    public SoggettoType createSoggettoType() {
        return new SoggettoType();
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
     * Create an instance of {@link PersonaGiuridicaType }
     * 
     */
    public PersonaGiuridicaType createPersonaGiuridicaType() {
        return new PersonaGiuridicaType();
    }

    /**
     * Create an instance of {@link DatiSingoloPagamentoType }
     * 
     */
    public DatiSingoloPagamentoType createDatiSingoloPagamentoType() {
        return new DatiSingoloPagamentoType();
    }

    /**
     * Create an instance of {@link TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento }
     * 
     */
    public TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento createTestataFlussoRendicontazioneExtTypeElencoCodiciVersamento() {
        return new TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento();
    }

    /**
     * Create an instance of {@link PagamentoIntermediatoType.DatiSingoliPagamenti }
     * 
     */
    public PagamentoIntermediatoType.DatiSingoliPagamenti createPagamentoIntermediatoTypeDatiSingoliPagamenti() {
        return new PagamentoIntermediatoType.DatiSingoliPagamenti();
    }

    /**
     * Create an instance of {@link TrasmettiRTExtRequestType.PagamentiIntermediati }
     * 
     */
    public TrasmettiRTExtRequestType.PagamentiIntermediati createTrasmettiRTExtRequestTypePagamentiIntermediati() {
        return new TrasmettiRTExtRequestType.PagamentiIntermediati();
    }

    /**
     * Create an instance of {@link TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati }
     * 
     */
    public TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati createTrasmettiFlussoRendicontazioneExtRequestTypePagamentiIntermediati() {
        return new TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/types", name = "EPaywsoServiceResponse")
    public JAXBElement<ResponseType> createEPaywsoServiceResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_EPaywsoServiceResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrasmettiRTExtRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", name = "TrasmettiRTExtRequest")
    public JAXBElement<TrasmettiRTExtRequestType> createTrasmettiRTExtRequest(TrasmettiRTExtRequestType value) {
        return new JAXBElement<TrasmettiRTExtRequestType>(_TrasmettiRTExtRequest_QNAME, TrasmettiRTExtRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrasmettiFlussoRendicontazioneExtRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", name = "TrasmettiFlussoRendicontazioneExtRequest")
    public JAXBElement<TrasmettiFlussoRendicontazioneExtRequestType> createTrasmettiFlussoRendicontazioneExtRequest(TrasmettiFlussoRendicontazioneExtRequestType value) {
        return new JAXBElement<TrasmettiFlussoRendicontazioneExtRequestType>(_TrasmettiFlussoRendicontazioneExtRequest_QNAME, TrasmettiFlussoRendicontazioneExtRequestType.class, null, value);
    }

}
