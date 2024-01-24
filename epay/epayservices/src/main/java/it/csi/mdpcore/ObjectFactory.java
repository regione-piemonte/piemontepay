/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.mdpcore package. 
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

    private final static QName _GetSceltaWisp_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getSceltaWisp");
    private final static QName _DatatypeConfigurationException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "DatatypeConfigurationException");
    private final static QName _GetListaRTperIUV_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getListaRTperIUV");
    private final static QName _StartTransazioneCarrelloResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "startTransazioneCarrelloResponse");
    private final static QName _StartTransazioneResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "startTransazioneResponse");
    private final static QName _MissingParameterException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "MissingParameterException");
    private final static QName _GetPagonetCustomDataResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getPagonetCustomDataResponse");
    private final static QName _GetApplication_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getApplication");
    private final static QName _GetUrlWisp_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getUrlWisp");
    private final static QName _StartTransazione_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "startTransazione");
    private final static QName _CSIException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "CSIException");
    private final static QName _GetRTperIUVResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getRTperIUVResponse");
    private final static QName _GetRTperIUVTransazioneResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getRTperIUVTransazioneResponse");
    private final static QName _SystemException_QNAME = new QName("http://www.csi.it/mdp/", "SystemException");
    private final static QName _SOAPFaultException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "SOAPFaultException");
    private final static QName _BusinessException_QNAME = new QName("http://www.csi.it/mdp/", "BusinessException");
    private final static QName _GetListaRTperIUVResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getListaRTperIUVResponse");
    private final static QName _InitTransazioneResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "initTransazioneResponse");
    private final static QName _GetModalitaInformativePagamento_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getModalitaInformativePagamento");
    private final static QName _GetComuneProvincia_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getComuneProvincia");
    private final static QName _InitTransazione_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "initTransazione");
    private final static QName _GetComuneProvinciaResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getComuneProvinciaResponse");
    private final static QName _GetModalitaInformativePagamentoResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getModalitaInformativePagamentoResponse");
    private final static QName _CreateException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "CreateException");
    private final static QName _GetStatoTransazione_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getStatoTransazione");
    private final static QName _NamingException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "NamingException");
    private final static QName _StartTransazioneCarrello_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "startTransazioneCarrello");
    private final static QName _GetErroriNodoResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getErroriNodoResponse");
    private final static QName _GetStatoTransazioneResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getStatoTransazioneResponse");
    private final static QName _GetErroriNodo_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getErroriNodo");
    private final static QName _GetRTperIUVTransazione_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getRTperIUVTransazione");
    private final static QName _GetSceltaWispResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getSceltaWispResponse");
    private final static QName _GetModalitaPagamentoResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getModalitaPagamentoResponse");
    private final static QName _DaoException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "DaoException");
    private final static QName _GetModalitaPagamento_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getModalitaPagamento");
    private final static QName _GetPagonetCustomData_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getPagonetCustomData");
    private final static QName _GetRTperIUV_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getRTperIUV");
    private final static QName _GetApplicationResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getApplicationResponse");
    private final static QName _GetUrlWispResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getUrlWispResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.mdpcore
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStatoTransazioneResponse }
     * 
     */
    public GetStatoTransazioneResponse createGetStatoTransazioneResponse() {
        return new GetStatoTransazioneResponse();
    }

    /**
     * Create an instance of {@link GetErroriNodo }
     * 
     */
    public GetErroriNodo createGetErroriNodo() {
        return new GetErroriNodo();
    }

    /**
     * Create an instance of {@link GetErroriNodoResponse }
     * 
     */
    public GetErroriNodoResponse createGetErroriNodoResponse() {
        return new GetErroriNodoResponse();
    }

    /**
     * Create an instance of {@link GetApplicationResponse }
     * 
     */
    public GetApplicationResponse createGetApplicationResponse() {
        return new GetApplicationResponse();
    }

    /**
     * Create an instance of {@link GetUrlWispResponse }
     * 
     */
    public GetUrlWispResponse createGetUrlWispResponse() {
        return new GetUrlWispResponse();
    }

    /**
     * Create an instance of {@link GetRTperIUV }
     * 
     */
    public GetRTperIUV createGetRTperIUV() {
        return new GetRTperIUV();
    }

    /**
     * Create an instance of {@link DaoException }
     * 
     */
    public DaoException createDaoException() {
        return new DaoException();
    }

    /**
     * Create an instance of {@link GetModalitaPagamento }
     * 
     */
    public GetModalitaPagamento createGetModalitaPagamento() {
        return new GetModalitaPagamento();
    }

    /**
     * Create an instance of {@link GetPagonetCustomData }
     * 
     */
    public GetPagonetCustomData createGetPagonetCustomData() {
        return new GetPagonetCustomData();
    }

    /**
     * Create an instance of {@link GetRTperIUVTransazione }
     * 
     */
    public GetRTperIUVTransazione createGetRTperIUVTransazione() {
        return new GetRTperIUVTransazione();
    }

    /**
     * Create an instance of {@link GetSceltaWispResponse }
     * 
     */
    public GetSceltaWispResponse createGetSceltaWispResponse() {
        return new GetSceltaWispResponse();
    }

    /**
     * Create an instance of {@link GetModalitaPagamentoResponse }
     * 
     */
    public GetModalitaPagamentoResponse createGetModalitaPagamentoResponse() {
        return new GetModalitaPagamentoResponse();
    }

    /**
     * Create an instance of {@link GetModalitaInformativePagamento }
     * 
     */
    public GetModalitaInformativePagamento createGetModalitaInformativePagamento() {
        return new GetModalitaInformativePagamento();
    }

    /**
     * Create an instance of {@link InitTransazioneResponse }
     * 
     */
    public InitTransazioneResponse createInitTransazioneResponse() {
        return new InitTransazioneResponse();
    }

    /**
     * Create an instance of {@link GetListaRTperIUVResponse }
     * 
     */
    public GetListaRTperIUVResponse createGetListaRTperIUVResponse() {
        return new GetListaRTperIUVResponse();
    }

    /**
     * Create an instance of {@link StartTransazioneCarrello }
     * 
     */
    public StartTransazioneCarrello createStartTransazioneCarrello() {
        return new StartTransazioneCarrello();
    }

    /**
     * Create an instance of {@link GetStatoTransazione }
     * 
     */
    public GetStatoTransazione createGetStatoTransazione() {
        return new GetStatoTransazione();
    }

    /**
     * Create an instance of {@link NamingException }
     * 
     */
    public NamingException createNamingException() {
        return new NamingException();
    }

    /**
     * Create an instance of {@link GetComuneProvinciaResponse }
     * 
     */
    public GetComuneProvinciaResponse createGetComuneProvinciaResponse() {
        return new GetComuneProvinciaResponse();
    }

    /**
     * Create an instance of {@link GetModalitaInformativePagamentoResponse }
     * 
     */
    public GetModalitaInformativePagamentoResponse createGetModalitaInformativePagamentoResponse() {
        return new GetModalitaInformativePagamentoResponse();
    }

    /**
     * Create an instance of {@link CreateException }
     * 
     */
    public CreateException createCreateException() {
        return new CreateException();
    }

    /**
     * Create an instance of {@link GetComuneProvincia }
     * 
     */
    public GetComuneProvincia createGetComuneProvincia() {
        return new GetComuneProvincia();
    }

    /**
     * Create an instance of {@link InitTransazione }
     * 
     */
    public InitTransazione createInitTransazione() {
        return new InitTransazione();
    }

    /**
     * Create an instance of {@link GetApplication }
     * 
     */
    public GetApplication createGetApplication() {
        return new GetApplication();
    }

    /**
     * Create an instance of {@link GetUrlWisp }
     * 
     */
    public GetUrlWisp createGetUrlWisp() {
        return new GetUrlWisp();
    }

    /**
     * Create an instance of {@link StartTransazione }
     * 
     */
    public StartTransazione createStartTransazione() {
        return new StartTransazione();
    }

    /**
     * Create an instance of {@link CSIException }
     * 
     */
    public CSIException createCSIException() {
        return new CSIException();
    }

    /**
     * Create an instance of {@link GetPagonetCustomDataResponse }
     * 
     */
    public GetPagonetCustomDataResponse createGetPagonetCustomDataResponse() {
        return new GetPagonetCustomDataResponse();
    }

    /**
     * Create an instance of {@link SOAPFaultException }
     * 
     */
    public SOAPFaultException createSOAPFaultException() {
        return new SOAPFaultException();
    }

    /**
     * Create an instance of {@link GetRTperIUVResponse }
     * 
     */
    public GetRTperIUVResponse createGetRTperIUVResponse() {
        return new GetRTperIUVResponse();
    }

    /**
     * Create an instance of {@link GetRTperIUVTransazioneResponse }
     * 
     */
    public GetRTperIUVTransazioneResponse createGetRTperIUVTransazioneResponse() {
        return new GetRTperIUVTransazioneResponse();
    }

    /**
     * Create an instance of {@link DatatypeConfigurationException }
     * 
     */
    public DatatypeConfigurationException createDatatypeConfigurationException() {
        return new DatatypeConfigurationException();
    }

    /**
     * Create an instance of {@link GetSceltaWisp }
     * 
     */
    public GetSceltaWisp createGetSceltaWisp() {
        return new GetSceltaWisp();
    }

    /**
     * Create an instance of {@link StartTransazioneResponse }
     * 
     */
    public StartTransazioneResponse createStartTransazioneResponse() {
        return new StartTransazioneResponse();
    }

    /**
     * Create an instance of {@link MissingParameterException }
     * 
     */
    public MissingParameterException createMissingParameterException() {
        return new MissingParameterException();
    }

    /**
     * Create an instance of {@link StartTransazioneCarrelloResponse }
     * 
     */
    public StartTransazioneCarrelloResponse createStartTransazioneCarrelloResponse() {
        return new StartTransazioneCarrelloResponse();
    }

    /**
     * Create an instance of {@link GetListaRTperIUV }
     * 
     */
    public GetListaRTperIUV createGetListaRTperIUV() {
        return new GetListaRTperIUV();
    }

    /**
     * Create an instance of {@link DatiAccertamentoRPT }
     * 
     */
    public DatiAccertamentoRPT createDatiAccertamentoRPT() {
        return new DatiAccertamentoRPT();
    }

    /**
     * Create an instance of {@link ElencoRPT }
     * 
     */
    public ElencoRPT createElencoRPT() {
        return new ElencoRPT();
    }

    /**
     * Create an instance of {@link RPTData }
     * 
     */
    public RPTData createRPTData() {
        return new RPTData();
    }

    /**
     * Create an instance of {@link TransazioneExtraAttribute }
     * 
     */
    public TransazioneExtraAttribute createTransazioneExtraAttribute() {
        return new TransazioneExtraAttribute();
    }

    /**
     * Create an instance of {@link SoggettoPagatore }
     * 
     */
    public SoggettoPagatore createSoggettoPagatore() {
        return new SoggettoPagatore();
    }

    /**
     * Create an instance of {@link AppGatewayInformativa }
     * 
     */
    public AppGatewayInformativa createAppGatewayInformativa() {
        return new AppGatewayInformativa();
    }

    /**
     * Create an instance of {@link NodoErrore }
     * 
     */
    public NodoErrore createNodoErrore() {
        return new NodoErrore();
    }

    /**
     * Create an instance of {@link DatiMarcaBolloDigitale }
     * 
     */
    public DatiMarcaBolloDigitale createDatiMarcaBolloDigitale() {
        return new DatiMarcaBolloDigitale();
    }

    /**
     * Create an instance of {@link PartAnComune }
     * 
     */
    public PartAnComune createPartAnComune() {
        return new PartAnComune();
    }

    /**
     * Create an instance of {@link DatiSingoloVersamentoRPT }
     * 
     */
    public DatiSingoloVersamentoRPT createDatiSingoloVersamentoRPT() {
        return new DatiSingoloVersamentoRPT();
    }

    /**
     * Create an instance of {@link DatiVersamentoRPT }
     * 
     */
    public DatiVersamentoRPT createDatiVersamentoRPT() {
        return new DatiVersamentoRPT();
    }

    /**
     * Create an instance of {@link InformativePSPScelto }
     * 
     */
    public InformativePSPScelto createInformativePSPScelto() {
        return new InformativePSPScelto();
    }

    /**
     * Create an instance of {@link RicevutaTelematicaNodo }
     * 
     */
    public RicevutaTelematicaNodo createRicevutaTelematicaNodo() {
        return new RicevutaTelematicaNodo();
    }

    /**
     * Create an instance of {@link ChiaveValore }
     * 
     */
    public ChiaveValore createChiaveValore() {
        return new ChiaveValore();
    }

    /**
     * Create an instance of {@link Vapplicationcomuni }
     * 
     */
    public Vapplicationcomuni createVapplicationcomuni() {
        return new Vapplicationcomuni();
    }

    /**
     * Create an instance of {@link Transazione }
     * 
     */
    public Transazione createTransazione() {
        return new Transazione();
    }

    /**
     * Create an instance of {@link AppGateway }
     * 
     */
    public AppGateway createAppGateway() {
        return new AppGateway();
    }

    /**
     * Create an instance of {@link SoggettoVersante }
     * 
     */
    public SoggettoVersante createSoggettoVersante() {
        return new SoggettoVersante();
    }

    /**
     * Create an instance of {@link ParametriUrlWisp }
     * 
     */
    public ParametriUrlWisp createParametriUrlWisp() {
        return new ParametriUrlWisp();
    }

    /**
     * Create an instance of {@link Application }
     * 
     */
    public Application createApplication() {
        return new Application();
    }

    /**
     * Create an instance of {@link BusinessException }
     * 
     */
    public BusinessException createBusinessException() {
        return new BusinessException();
    }

    /**
     * Create an instance of {@link SystemException }
     * 
     */
    public SystemException createSystemException() {
        return new SystemException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSceltaWisp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getSceltaWisp")
    public JAXBElement<GetSceltaWisp> createGetSceltaWisp(GetSceltaWisp value) {
        return new JAXBElement<GetSceltaWisp>(_GetSceltaWisp_QNAME, GetSceltaWisp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatatypeConfigurationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "DatatypeConfigurationException")
    public JAXBElement<DatatypeConfigurationException> createDatatypeConfigurationException(DatatypeConfigurationException value) {
        return new JAXBElement<DatatypeConfigurationException>(_DatatypeConfigurationException_QNAME, DatatypeConfigurationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaRTperIUV }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getListaRTperIUV")
    public JAXBElement<GetListaRTperIUV> createGetListaRTperIUV(GetListaRTperIUV value) {
        return new JAXBElement<GetListaRTperIUV>(_GetListaRTperIUV_QNAME, GetListaRTperIUV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTransazioneCarrelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "startTransazioneCarrelloResponse")
    public JAXBElement<StartTransazioneCarrelloResponse> createStartTransazioneCarrelloResponse(StartTransazioneCarrelloResponse value) {
        return new JAXBElement<StartTransazioneCarrelloResponse>(_StartTransazioneCarrelloResponse_QNAME, StartTransazioneCarrelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTransazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "startTransazioneResponse")
    public JAXBElement<StartTransazioneResponse> createStartTransazioneResponse(StartTransazioneResponse value) {
        return new JAXBElement<StartTransazioneResponse>(_StartTransazioneResponse_QNAME, StartTransazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MissingParameterException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "MissingParameterException")
    public JAXBElement<MissingParameterException> createMissingParameterException(MissingParameterException value) {
        return new JAXBElement<MissingParameterException>(_MissingParameterException_QNAME, MissingParameterException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPagonetCustomDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getPagonetCustomDataResponse")
    public JAXBElement<GetPagonetCustomDataResponse> createGetPagonetCustomDataResponse(GetPagonetCustomDataResponse value) {
        return new JAXBElement<GetPagonetCustomDataResponse>(_GetPagonetCustomDataResponse_QNAME, GetPagonetCustomDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getApplication")
    public JAXBElement<GetApplication> createGetApplication(GetApplication value) {
        return new JAXBElement<GetApplication>(_GetApplication_QNAME, GetApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUrlWisp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getUrlWisp")
    public JAXBElement<GetUrlWisp> createGetUrlWisp(GetUrlWisp value) {
        return new JAXBElement<GetUrlWisp>(_GetUrlWisp_QNAME, GetUrlWisp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTransazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "startTransazione")
    public JAXBElement<StartTransazione> createStartTransazione(StartTransazione value) {
        return new JAXBElement<StartTransazione>(_StartTransazione_QNAME, StartTransazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CSIException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "CSIException")
    public JAXBElement<CSIException> createCSIException(CSIException value) {
        return new JAXBElement<CSIException>(_CSIException_QNAME, CSIException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRTperIUVResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getRTperIUVResponse")
    public JAXBElement<GetRTperIUVResponse> createGetRTperIUVResponse(GetRTperIUVResponse value) {
        return new JAXBElement<GetRTperIUVResponse>(_GetRTperIUVResponse_QNAME, GetRTperIUVResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRTperIUVTransazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getRTperIUVTransazioneResponse")
    public JAXBElement<GetRTperIUVTransazioneResponse> createGetRTperIUVTransazioneResponse(GetRTperIUVTransazioneResponse value) {
        return new JAXBElement<GetRTperIUVTransazioneResponse>(_GetRTperIUVTransazioneResponse_QNAME, GetRTperIUVTransazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/mdp/", name = "SystemException")
    public JAXBElement<SystemException> createSystemException(SystemException value) {
        return new JAXBElement<SystemException>(_SystemException_QNAME, SystemException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SOAPFaultException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "SOAPFaultException")
    public JAXBElement<SOAPFaultException> createSOAPFaultException(SOAPFaultException value) {
        return new JAXBElement<SOAPFaultException>(_SOAPFaultException_QNAME, SOAPFaultException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/mdp/", name = "BusinessException")
    public JAXBElement<BusinessException> createBusinessException(BusinessException value) {
        return new JAXBElement<BusinessException>(_BusinessException_QNAME, BusinessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaRTperIUVResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getListaRTperIUVResponse")
    public JAXBElement<GetListaRTperIUVResponse> createGetListaRTperIUVResponse(GetListaRTperIUVResponse value) {
        return new JAXBElement<GetListaRTperIUVResponse>(_GetListaRTperIUVResponse_QNAME, GetListaRTperIUVResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitTransazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "initTransazioneResponse")
    public JAXBElement<InitTransazioneResponse> createInitTransazioneResponse(InitTransazioneResponse value) {
        return new JAXBElement<InitTransazioneResponse>(_InitTransazioneResponse_QNAME, InitTransazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModalitaInformativePagamento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getModalitaInformativePagamento")
    public JAXBElement<GetModalitaInformativePagamento> createGetModalitaInformativePagamento(GetModalitaInformativePagamento value) {
        return new JAXBElement<GetModalitaInformativePagamento>(_GetModalitaInformativePagamento_QNAME, GetModalitaInformativePagamento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComuneProvincia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getComuneProvincia")
    public JAXBElement<GetComuneProvincia> createGetComuneProvincia(GetComuneProvincia value) {
        return new JAXBElement<GetComuneProvincia>(_GetComuneProvincia_QNAME, GetComuneProvincia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitTransazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "initTransazione")
    public JAXBElement<InitTransazione> createInitTransazione(InitTransazione value) {
        return new JAXBElement<InitTransazione>(_InitTransazione_QNAME, InitTransazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComuneProvinciaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getComuneProvinciaResponse")
    public JAXBElement<GetComuneProvinciaResponse> createGetComuneProvinciaResponse(GetComuneProvinciaResponse value) {
        return new JAXBElement<GetComuneProvinciaResponse>(_GetComuneProvinciaResponse_QNAME, GetComuneProvinciaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModalitaInformativePagamentoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getModalitaInformativePagamentoResponse")
    public JAXBElement<GetModalitaInformativePagamentoResponse> createGetModalitaInformativePagamentoResponse(GetModalitaInformativePagamentoResponse value) {
        return new JAXBElement<GetModalitaInformativePagamentoResponse>(_GetModalitaInformativePagamentoResponse_QNAME, GetModalitaInformativePagamentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "CreateException")
    public JAXBElement<CreateException> createCreateException(CreateException value) {
        return new JAXBElement<CreateException>(_CreateException_QNAME, CreateException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatoTransazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getStatoTransazione")
    public JAXBElement<GetStatoTransazione> createGetStatoTransazione(GetStatoTransazione value) {
        return new JAXBElement<GetStatoTransazione>(_GetStatoTransazione_QNAME, GetStatoTransazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "NamingException")
    public JAXBElement<NamingException> createNamingException(NamingException value) {
        return new JAXBElement<NamingException>(_NamingException_QNAME, NamingException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTransazioneCarrello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "startTransazioneCarrello")
    public JAXBElement<StartTransazioneCarrello> createStartTransazioneCarrello(StartTransazioneCarrello value) {
        return new JAXBElement<StartTransazioneCarrello>(_StartTransazioneCarrello_QNAME, StartTransazioneCarrello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetErroriNodoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getErroriNodoResponse")
    public JAXBElement<GetErroriNodoResponse> createGetErroriNodoResponse(GetErroriNodoResponse value) {
        return new JAXBElement<GetErroriNodoResponse>(_GetErroriNodoResponse_QNAME, GetErroriNodoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatoTransazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getStatoTransazioneResponse")
    public JAXBElement<GetStatoTransazioneResponse> createGetStatoTransazioneResponse(GetStatoTransazioneResponse value) {
        return new JAXBElement<GetStatoTransazioneResponse>(_GetStatoTransazioneResponse_QNAME, GetStatoTransazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetErroriNodo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getErroriNodo")
    public JAXBElement<GetErroriNodo> createGetErroriNodo(GetErroriNodo value) {
        return new JAXBElement<GetErroriNodo>(_GetErroriNodo_QNAME, GetErroriNodo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRTperIUVTransazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getRTperIUVTransazione")
    public JAXBElement<GetRTperIUVTransazione> createGetRTperIUVTransazione(GetRTperIUVTransazione value) {
        return new JAXBElement<GetRTperIUVTransazione>(_GetRTperIUVTransazione_QNAME, GetRTperIUVTransazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSceltaWispResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getSceltaWispResponse")
    public JAXBElement<GetSceltaWispResponse> createGetSceltaWispResponse(GetSceltaWispResponse value) {
        return new JAXBElement<GetSceltaWispResponse>(_GetSceltaWispResponse_QNAME, GetSceltaWispResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModalitaPagamentoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getModalitaPagamentoResponse")
    public JAXBElement<GetModalitaPagamentoResponse> createGetModalitaPagamentoResponse(GetModalitaPagamentoResponse value) {
        return new JAXBElement<GetModalitaPagamentoResponse>(_GetModalitaPagamentoResponse_QNAME, GetModalitaPagamentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DaoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "DaoException")
    public JAXBElement<DaoException> createDaoException(DaoException value) {
        return new JAXBElement<DaoException>(_DaoException_QNAME, DaoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModalitaPagamento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getModalitaPagamento")
    public JAXBElement<GetModalitaPagamento> createGetModalitaPagamento(GetModalitaPagamento value) {
        return new JAXBElement<GetModalitaPagamento>(_GetModalitaPagamento_QNAME, GetModalitaPagamento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPagonetCustomData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getPagonetCustomData")
    public JAXBElement<GetPagonetCustomData> createGetPagonetCustomData(GetPagonetCustomData value) {
        return new JAXBElement<GetPagonetCustomData>(_GetPagonetCustomData_QNAME, GetPagonetCustomData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRTperIUV }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getRTperIUV")
    public JAXBElement<GetRTperIUV> createGetRTperIUV(GetRTperIUV value) {
        return new JAXBElement<GetRTperIUV>(_GetRTperIUV_QNAME, GetRTperIUV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getApplicationResponse")
    public JAXBElement<GetApplicationResponse> createGetApplicationResponse(GetApplicationResponse value) {
        return new JAXBElement<GetApplicationResponse>(_GetApplicationResponse_QNAME, GetApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUrlWispResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getUrlWispResponse")
    public JAXBElement<GetUrlWispResponse> createGetUrlWispResponse(GetUrlWispResponse value) {
        return new JAXBElement<GetUrlWispResponse>(_GetUrlWispResponse_QNAME, GetUrlWispResponse.class, null, value);
    }

}
