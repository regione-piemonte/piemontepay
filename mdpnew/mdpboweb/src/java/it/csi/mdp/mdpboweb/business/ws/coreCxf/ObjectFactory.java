/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws.coreCxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.mdp.mdpboweb.business.ws.coreCxf package. 
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

    private final static QName _GetModalitaPagamentoResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getModalitaPagamentoResponse");
    private final static QName _StartTransazioneResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "startTransazioneResponse");
    private final static QName _GetApplicationResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getApplicationResponse");
    private final static QName _SOAPFaultException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "SOAPFaultException");
    private final static QName _GetComuneProvinciaResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getComuneProvinciaResponse");
    private final static QName _GetComuneProvincia_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getComuneProvincia");
    private final static QName _GetStatoTransazione_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getStatoTransazione");
    private final static QName _CreateException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "CreateException");
    private final static QName _GetStatoTransazioneResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getStatoTransazioneResponse");
    private final static QName _StartTransazione_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "startTransazione");
    private final static QName _InitTransazioneResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "initTransazioneResponse");
    private final static QName _DaoException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "DaoException");
    private final static QName _MissingParameterException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "MissingParameterException");
    private final static QName _GetModalitaPagamento_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getModalitaPagamento");
    private final static QName _InitTransazione_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "initTransazione");
    private final static QName _DatatypeConfigurationException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "DatatypeConfigurationException");
    private final static QName _GetPagonetCustomDataResponse_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getPagonetCustomDataResponse");
    private final static QName _GetPagonetCustomData_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getPagonetCustomData");
    private final static QName _NamingException_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "NamingException");
    private final static QName _GetApplication_QNAME = new QName("http://interfacecxf.core.mdp.csi.it/", "getApplication");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.mdp.mdpboweb.business.ws.coreCxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetComuneProvinciaResponse }
     * 
     */
    public GetComuneProvinciaResponse createGetComuneProvinciaResponse() {
        return new GetComuneProvinciaResponse();
    }

    /**
     * Create an instance of {@link GetPagonetCustomData }
     * 
     */
    public GetPagonetCustomData createGetPagonetCustomData() {
        return new GetPagonetCustomData();
    }

    /**
     * Create an instance of {@link NamingException }
     * 
     */
    public NamingException createNamingException() {
        return new NamingException();
    }

    /**
     * Create an instance of {@link TransazioneExtraAttribute }
     * 
     */
    public TransazioneExtraAttribute createTransazioneExtraAttribute() {
        return new TransazioneExtraAttribute();
    }

    /**
     * Create an instance of {@link GetApplicationResponse }
     * 
     */
    public GetApplicationResponse createGetApplicationResponse() {
        return new GetApplicationResponse();
    }

    /**
     * Create an instance of {@link GetStatoTransazione }
     * 
     */
    public GetStatoTransazione createGetStatoTransazione() {
        return new GetStatoTransazione();
    }

    /**
     * Create an instance of {@link GetComuneProvincia }
     * 
     */
    public GetComuneProvincia createGetComuneProvincia() {
        return new GetComuneProvincia();
    }

    /**
     * Create an instance of {@link StartTransazioneResponse }
     * 
     */
    public StartTransazioneResponse createStartTransazioneResponse() {
        return new StartTransazioneResponse();
    }

    /**
     * Create an instance of {@link GetModalitaPagamentoResponse }
     * 
     */
    public GetModalitaPagamentoResponse createGetModalitaPagamentoResponse() {
        return new GetModalitaPagamentoResponse();
    }

    /**
     * Create an instance of {@link SOAPFaultException }
     * 
     */
    public SOAPFaultException createSOAPFaultException() {
        return new SOAPFaultException();
    }

    /**
     * Create an instance of {@link MissingParameterException }
     * 
     */
    public MissingParameterException createMissingParameterException() {
        return new MissingParameterException();
    }

    /**
     * Create an instance of {@link GetStatoTransazioneResponse }
     * 
     */
    public GetStatoTransazioneResponse createGetStatoTransazioneResponse() {
        return new GetStatoTransazioneResponse();
    }

    /**
     * Create an instance of {@link CreateException }
     * 
     */
    public CreateException createCreateException() {
        return new CreateException();
    }

    /**
     * Create an instance of {@link InitTransazione }
     * 
     */
    public InitTransazione createInitTransazione() {
        return new InitTransazione();
    }

    /**
     * Create an instance of {@link GetPagonetCustomDataResponse }
     * 
     */
    public GetPagonetCustomDataResponse createGetPagonetCustomDataResponse() {
        return new GetPagonetCustomDataResponse();
    }

    /**
     * Create an instance of {@link GetApplication }
     * 
     */
    public GetApplication createGetApplication() {
        return new GetApplication();
    }

    /**
     * Create an instance of {@link Application }
     * 
     */
    public Application createApplication() {
        return new Application();
    }

    /**
     * Create an instance of {@link AppGateway }
     * 
     */
    public AppGateway createAppGateway() {
        return new AppGateway();
    }

    /**
     * Create an instance of {@link PartAnComune }
     * 
     */
    public PartAnComune createPartAnComune() {
        return new PartAnComune();
    }

    /**
     * Create an instance of {@link GetModalitaPagamento }
     * 
     */
    public GetModalitaPagamento createGetModalitaPagamento() {
        return new GetModalitaPagamento();
    }

    /**
     * Create an instance of {@link DaoException }
     * 
     */
    public DaoException createDaoException() {
        return new DaoException();
    }

    /**
     * Create an instance of {@link DatatypeConfigurationException }
     * 
     */
    public DatatypeConfigurationException createDatatypeConfigurationException() {
        return new DatatypeConfigurationException();
    }

    /**
     * Create an instance of {@link Transazione }
     * 
     */
    public Transazione createTransazione() {
        return new Transazione();
    }

    /**
     * Create an instance of {@link Vapplicationcomuni }
     * 
     */
    public Vapplicationcomuni createVapplicationcomuni() {
        return new Vapplicationcomuni();
    }

    /**
     * Create an instance of {@link StartTransazione }
     * 
     */
    public StartTransazione createStartTransazione() {
        return new StartTransazione();
    }

    /**
     * Create an instance of {@link InitTransazioneResponse }
     * 
     */
    public InitTransazioneResponse createInitTransazioneResponse() {
        return new InitTransazioneResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTransazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "startTransazioneResponse")
    public JAXBElement<StartTransazioneResponse> createStartTransazioneResponse(StartTransazioneResponse value) {
        return new JAXBElement<StartTransazioneResponse>(_StartTransazioneResponse_QNAME, StartTransazioneResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SOAPFaultException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "SOAPFaultException")
    public JAXBElement<SOAPFaultException> createSOAPFaultException(SOAPFaultException value) {
        return new JAXBElement<SOAPFaultException>(_SOAPFaultException_QNAME, SOAPFaultException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComuneProvincia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getComuneProvincia")
    public JAXBElement<GetComuneProvincia> createGetComuneProvincia(GetComuneProvincia value) {
        return new JAXBElement<GetComuneProvincia>(_GetComuneProvincia_QNAME, GetComuneProvincia.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "CreateException")
    public JAXBElement<CreateException> createCreateException(CreateException value) {
        return new JAXBElement<CreateException>(_CreateException_QNAME, CreateException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTransazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "startTransazione")
    public JAXBElement<StartTransazione> createStartTransazione(StartTransazione value) {
        return new JAXBElement<StartTransazione>(_StartTransazione_QNAME, StartTransazione.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DaoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "DaoException")
    public JAXBElement<DaoException> createDaoException(DaoException value) {
        return new JAXBElement<DaoException>(_DaoException_QNAME, DaoException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModalitaPagamento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getModalitaPagamento")
    public JAXBElement<GetModalitaPagamento> createGetModalitaPagamento(GetModalitaPagamento value) {
        return new JAXBElement<GetModalitaPagamento>(_GetModalitaPagamento_QNAME, GetModalitaPagamento.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DatatypeConfigurationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "DatatypeConfigurationException")
    public JAXBElement<DatatypeConfigurationException> createDatatypeConfigurationException(DatatypeConfigurationException value) {
        return new JAXBElement<DatatypeConfigurationException>(_DatatypeConfigurationException_QNAME, DatatypeConfigurationException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPagonetCustomData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getPagonetCustomData")
    public JAXBElement<GetPagonetCustomData> createGetPagonetCustomData(GetPagonetCustomData value) {
        return new JAXBElement<GetPagonetCustomData>(_GetPagonetCustomData_QNAME, GetPagonetCustomData.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfacecxf.core.mdp.csi.it/", name = "getApplication")
    public JAXBElement<GetApplication> createGetApplication(GetApplication value) {
        return new JAXBElement<GetApplication>(_GetApplication_QNAME, GetApplication.class, null, value);
    }

}
