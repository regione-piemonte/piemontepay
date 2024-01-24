/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epay.epaypaweb.integration.epaywsosrv package. 
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

    private final static QName _DeterminaProssimoEndpointRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "DeterminaProssimoEndpointRequest");
    private final static QName _AggiornaRichiestaESingoloEsitoInvioRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaRichiestaESingoloEsitoInvioRequest");
    private final static QName _RicercaEnteResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaEnteResponse");
    private final static QName _DeterminaProssimoEndpointResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "DeterminaProssimoEndpointResponse");
    private final static QName _AggiornaEsitoComplessivoResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaEsitoComplessivoResponse");
    private final static QName _ContaNumeroRichiesteSelezionateRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "ContaNumeroRichiesteSelezionateRequest");
    private final static QName _RicercaConfigurazioneApplicativoRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaConfigurazioneApplicativoRequest");
    private final static QName _RicercaListaConfigurazioniApplicativiResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaListaConfigurazioniApplicativiResponse");
    private final static QName _AggiornaEsitoInvioRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaEsitoInvioRequest");
    private final static QName _AggiornaEsitoComplessivoRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaEsitoComplessivoRequest");
    private final static QName _AggiornaRichiestaRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaRichiestaRequest");
    private final static QName _RicercaStatiAggregatiWsoRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaStatiAggregatiWsoRequest");
    private final static QName _RicercaRichiesteRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaRichiesteRequest");
    private final static QName _RicercaListaConfigurazioniApplicativiRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaListaConfigurazioniApplicativiRequest");
    private final static QName _AggiornaEsitoInvioResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaEsitoInvioResponse");
    private final static QName _LeggiRichiestaResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "LeggiRichiestaResponse");
    private final static QName _AggiornaRichiestaESingoloEsitoInvioResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaRichiestaESingoloEsitoInvioResponse");
    private final static QName _AggiornaRichiestaResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "AggiornaRichiestaResponse");
    private final static QName _EPaywsoServiceResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/types", "EPaywsoServiceResponse");
    private final static QName _InserisciNuovaRichiestaRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "InserisciNuovaRichiestaRequest");
    private final static QName _InserisciNuovaRichiestaResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "InserisciNuovaRichiestaResponse");
    private final static QName _ContaNumeroRichiesteSelezionateResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "ContaNumeroRichiesteSelezionateResponse");
    private final static QName _LeggiRichiestaRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "LeggiRichiestaRequest");
    private final static QName _RicercaConfigurazioneApplicativoResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaConfigurazioneApplicativoResponse");
    private final static QName _RicercaStatiAggregatiWsoResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaStatiAggregatiWsoResponse");
    private final static QName _RicercaRichiesteResponse_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaRichiesteResponse");
    private final static QName _RicercaEnteRequest_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "RicercaEnteRequest");
    private final static QName _AggiornaRichiestaESingoloEsitoInvioRequestTypeIdApplicativo_QNAME = new QName("http://www.csi.it/epay/epaywso/businesstypes", "IdApplicativo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epaypaweb.integration.epaywsosrv
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CriterioOrdinamentoRichiestaType }
     * 
     */
    public CriterioOrdinamentoRichiestaType createCriterioOrdinamentoRichiestaType() {
        return new CriterioOrdinamentoRichiestaType();
    }

    /**
     * Create an instance of {@link AggiornaRichiestaESingoloEsitoInvioRequestType }
     * 
     */
    public AggiornaRichiestaESingoloEsitoInvioRequestType createAggiornaRichiestaESingoloEsitoInvioRequestType() {
        return new AggiornaRichiestaESingoloEsitoInvioRequestType();
    }

    /**
     * Create an instance of {@link PersonaGiuridicaType }
     * 
     */
    public PersonaGiuridicaType createPersonaGiuridicaType() {
        return new PersonaGiuridicaType();
    }

    /**
     * Create an instance of {@link EsitoInserimentoType.ElencoPosizioniDebitorieInserite }
     * 
     */
    public EsitoInserimentoType.ElencoPosizioniDebitorieInserite createEsitoInserimentoTypeElencoPosizioniDebitorieInserite() {
        return new EsitoInserimentoType.ElencoPosizioniDebitorieInserite();
    }

    /**
     * Create an instance of {@link ConfigurazioneApplicativoTypeList }
     * 
     */
    public ConfigurazioneApplicativoTypeList createConfigurazioneApplicativoTypeList() {
        return new ConfigurazioneApplicativoTypeList();
    }

    /**
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link EndpointType }
     * 
     */
    public EndpointType createEndpointType() {
        return new EndpointType();
    }

    /**
     * Create an instance of {@link RichiestaLightType }
     * 
     */
    public RichiestaLightType createRichiestaLightType() {
        return new RichiestaLightType();
    }

    /**
     * Create an instance of {@link ConfigurazioneApplicativoType }
     * 
     */
    public ConfigurazioneApplicativoType createConfigurazioneApplicativoType() {
        return new ConfigurazioneApplicativoType();
    }

    /**
     * Create an instance of {@link PersonaFisicaType }
     * 
     */
    public PersonaFisicaType createPersonaFisicaType() {
        return new PersonaFisicaType();
    }

    /**
     * Create an instance of {@link RicercaEnteRequestType }
     * 
     */
    public RicercaEnteRequestType createRicercaEnteRequestType() {
        return new RicercaEnteRequestType();
    }

    /**
     * Create an instance of {@link SoggettoType }
     * 
     */
    public SoggettoType createSoggettoType() {
        return new SoggettoType();
    }

    /**
     * Create an instance of {@link RicercaStatiAggregatiWsoRequestType }
     * 
     */
    public RicercaStatiAggregatiWsoRequestType createRicercaStatiAggregatiWsoRequestType() {
        return new RicercaStatiAggregatiWsoRequestType();
    }

    /**
     * Create an instance of {@link RicercaStatoAggregatoWsoType }
     * 
     */
    public RicercaStatoAggregatoWsoType createRicercaStatoAggregatoWsoType() {
        return new RicercaStatoAggregatoWsoType();
    }

    /**
     * Create an instance of {@link RichiestaType }
     * 
     */
    public RichiestaType createRichiestaType() {
        return new RichiestaType();
    }

    /**
     * Create an instance of {@link EsitoInvioTypeList }
     * 
     */
    public EsitoInvioTypeList createEsitoInvioTypeList() {
        return new EsitoInvioTypeList();
    }

    /**
     * Create an instance of {@link DeterminaProssimoEndpointRequestType }
     * 
     */
    public DeterminaProssimoEndpointRequestType createDeterminaProssimoEndpointRequestType() {
        return new DeterminaProssimoEndpointRequestType();
    }

    /**
     * Create an instance of {@link InserisciNuovaRichiestaResponseType }
     * 
     */
    public InserisciNuovaRichiestaResponseType createInserisciNuovaRichiestaResponseType() {
        return new InserisciNuovaRichiestaResponseType();
    }

    /**
     * Create an instance of {@link AggiornaRichiestaRequestType }
     * 
     */
    public AggiornaRichiestaRequestType createAggiornaRichiestaRequestType() {
        return new AggiornaRichiestaRequestType();
    }

    /**
     * Create an instance of {@link EmbeddedXMLType }
     * 
     */
    public EmbeddedXMLType createEmbeddedXMLType() {
        return new EmbeddedXMLType();
    }

    /**
     * Create an instance of {@link EsitoRicercaStatiAggregatiWsoListTypeList }
     * 
     */
    public EsitoRicercaStatiAggregatiWsoListTypeList createEsitoRicercaStatiAggregatiWsoListTypeList() {
        return new EsitoRicercaStatiAggregatiWsoListTypeList();
    }

    /**
     * Create an instance of {@link EsitoAggiornamentoType }
     * 
     */
    public EsitoAggiornamentoType createEsitoAggiornamentoType() {
        return new EsitoAggiornamentoType();
    }

    /**
     * Create an instance of {@link RicercaListaConfigurazioniApplicativiRequestType }
     * 
     */
    public RicercaListaConfigurazioniApplicativiRequestType createRicercaListaConfigurazioniApplicativiRequestType() {
        return new RicercaListaConfigurazioniApplicativiRequestType();
    }

    /**
     * Create an instance of {@link RicercaStatiAggregatiWsoResponseType }
     * 
     */
    public RicercaStatiAggregatiWsoResponseType createRicercaStatiAggregatiWsoResponseType() {
        return new RicercaStatiAggregatiWsoResponseType();
    }

    /**
     * Create an instance of {@link ResultType }
     * 
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    /**
     * Create an instance of {@link AggiornaEsitoComplessivoRequestType }
     * 
     */
    public AggiornaEsitoComplessivoRequestType createAggiornaEsitoComplessivoRequestType() {
        return new AggiornaEsitoComplessivoRequestType();
    }

    /**
     * Create an instance of {@link EsitoRicercaStatiAggregatiWsoType }
     * 
     */
    public EsitoRicercaStatiAggregatiWsoType createEsitoRicercaStatiAggregatiWsoType() {
        return new EsitoRicercaStatiAggregatiWsoType();
    }

    /**
     * Create an instance of {@link RicercaStatiAggregatiWsoTypeList }
     * 
     */
    public RicercaStatiAggregatiWsoTypeList createRicercaStatiAggregatiWsoTypeList() {
        return new RicercaStatiAggregatiWsoTypeList();
    }

    /**
     * Create an instance of {@link FiltroSelezioneRichiesteType }
     * 
     */
    public FiltroSelezioneRichiesteType createFiltroSelezioneRichiesteType() {
        return new FiltroSelezioneRichiesteType();
    }

    /**
     * Create an instance of {@link CriterioOrdinamentoRichiestaTypeList }
     * 
     */
    public CriterioOrdinamentoRichiestaTypeList createCriterioOrdinamentoRichiestaTypeList() {
        return new CriterioOrdinamentoRichiestaTypeList();
    }

    /**
     * Create an instance of {@link PaginazioneType }
     * 
     */
    public PaginazioneType createPaginazioneType() {
        return new PaginazioneType();
    }

    /**
     * Create an instance of {@link RicercaConfigurazioneApplicativoResponseType }
     * 
     */
    public RicercaConfigurazioneApplicativoResponseType createRicercaConfigurazioneApplicativoResponseType() {
        return new RicercaConfigurazioneApplicativoResponseType();
    }

    /**
     * Create an instance of {@link EsitoInserimentoType }
     * 
     */
    public EsitoInserimentoType createEsitoInserimentoType() {
        return new EsitoInserimentoType();
    }

    /**
     * Create an instance of {@link PosizioneDebitoriaType }
     * 
     */
    public PosizioneDebitoriaType createPosizioneDebitoriaType() {
        return new PosizioneDebitoriaType();
    }

    /**
     * Create an instance of {@link RicercaEnteResponseType }
     * 
     */
    public RicercaEnteResponseType createRicercaEnteResponseType() {
        return new RicercaEnteResponseType();
    }

    /**
     * Create an instance of {@link LeggiRichiestaRequestType }
     * 
     */
    public LeggiRichiestaRequestType createLeggiRichiestaRequestType() {
        return new LeggiRichiestaRequestType();
    }

    /**
     * Create an instance of {@link RicercaListaConfigurazioniApplicativiResponseType }
     * 
     */
    public RicercaListaConfigurazioniApplicativiResponseType createRicercaListaConfigurazioniApplicativiResponseType() {
        return new RicercaListaConfigurazioniApplicativiResponseType();
    }

    /**
     * Create an instance of {@link EnteType }
     * 
     */
    public EnteType createEnteType() {
        return new EnteType();
    }

    /**
     * Create an instance of {@link DeterminaProssimoEndpointResponseType }
     * 
     */
    public DeterminaProssimoEndpointResponseType createDeterminaProssimoEndpointResponseType() {
        return new DeterminaProssimoEndpointResponseType();
    }

    /**
     * Create an instance of {@link EndpointType.CredenzialiAutenticazione }
     * 
     */
    public EndpointType.CredenzialiAutenticazione createEndpointTypeCredenzialiAutenticazione() {
        return new EndpointType.CredenzialiAutenticazione();
    }

    /**
     * Create an instance of {@link LeggiRichiestaResponseType }
     * 
     */
    public LeggiRichiestaResponseType createLeggiRichiestaResponseType() {
        return new LeggiRichiestaResponseType();
    }

    /**
     * Create an instance of {@link EsitoInvioType }
     * 
     */
    public EsitoInvioType createEsitoInvioType() {
        return new EsitoInvioType();
    }

    /**
     * Create an instance of {@link RichiestaLightTypeList }
     * 
     */
    public RichiestaLightTypeList createRichiestaLightTypeList() {
        return new RichiestaLightTypeList();
    }

    /**
     * Create an instance of {@link RicercaConfigurazioneApplicativoRequestType }
     * 
     */
    public RicercaConfigurazioneApplicativoRequestType createRicercaConfigurazioneApplicativoRequestType() {
        return new RicercaConfigurazioneApplicativoRequestType();
    }

    /**
     * Create an instance of {@link ContaNumeroRichiesteSelezionateResponseType }
     * 
     */
    public ContaNumeroRichiesteSelezionateResponseType createContaNumeroRichiesteSelezionateResponseType() {
        return new ContaNumeroRichiesteSelezionateResponseType();
    }

    /**
     * Create an instance of {@link StatoRichiestaTypeList }
     * 
     */
    public StatoRichiestaTypeList createStatoRichiestaTypeList() {
        return new StatoRichiestaTypeList();
    }

    /**
     * Create an instance of {@link InserisciNuovaRichiestaRequestType }
     * 
     */
    public InserisciNuovaRichiestaRequestType createInserisciNuovaRichiestaRequestType() {
        return new InserisciNuovaRichiestaRequestType();
    }

    /**
     * Create an instance of {@link EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate }
     * 
     */
    public EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate createEsitoAggiornamentoTypeElencoPosizioniDebitorieAggiornate() {
        return new EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate();
    }

    /**
     * Create an instance of {@link ContaNumeroRichiesteSelezionateRequestType }
     * 
     */
    public ContaNumeroRichiesteSelezionateRequestType createContaNumeroRichiesteSelezionateRequestType() {
        return new ContaNumeroRichiesteSelezionateRequestType();
    }

    /**
     * Create an instance of {@link CodiceVersamentoTypeList }
     * 
     */
    public CodiceVersamentoTypeList createCodiceVersamentoTypeList() {
        return new CodiceVersamentoTypeList();
    }

    /**
     * Create an instance of {@link RicercaRichiesteRequestType }
     * 
     */
    public RicercaRichiesteRequestType createRicercaRichiesteRequestType() {
        return new RicercaRichiesteRequestType();
    }

    /**
     * Create an instance of {@link AggiornaEsitoInvioRequestType }
     * 
     */
    public AggiornaEsitoInvioRequestType createAggiornaEsitoInvioRequestType() {
        return new AggiornaEsitoInvioRequestType();
    }

    /**
     * Create an instance of {@link RicercaRichiesteResponseType }
     * 
     */
    public RicercaRichiesteResponseType createRicercaRichiesteResponseType() {
        return new RicercaRichiesteResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeterminaProssimoEndpointRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "DeterminaProssimoEndpointRequest")
    public JAXBElement<DeterminaProssimoEndpointRequestType> createDeterminaProssimoEndpointRequest(DeterminaProssimoEndpointRequestType value) {
        return new JAXBElement<DeterminaProssimoEndpointRequestType>(_DeterminaProssimoEndpointRequest_QNAME, DeterminaProssimoEndpointRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaRichiestaESingoloEsitoInvioRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaRichiestaESingoloEsitoInvioRequest")
    public JAXBElement<AggiornaRichiestaESingoloEsitoInvioRequestType> createAggiornaRichiestaESingoloEsitoInvioRequest(AggiornaRichiestaESingoloEsitoInvioRequestType value) {
        return new JAXBElement<AggiornaRichiestaESingoloEsitoInvioRequestType>(_AggiornaRichiestaESingoloEsitoInvioRequest_QNAME, AggiornaRichiestaESingoloEsitoInvioRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaEnteResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaEnteResponse")
    public JAXBElement<RicercaEnteResponseType> createRicercaEnteResponse(RicercaEnteResponseType value) {
        return new JAXBElement<RicercaEnteResponseType>(_RicercaEnteResponse_QNAME, RicercaEnteResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeterminaProssimoEndpointResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "DeterminaProssimoEndpointResponse")
    public JAXBElement<DeterminaProssimoEndpointResponseType> createDeterminaProssimoEndpointResponse(DeterminaProssimoEndpointResponseType value) {
        return new JAXBElement<DeterminaProssimoEndpointResponseType>(_DeterminaProssimoEndpointResponse_QNAME, DeterminaProssimoEndpointResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaEsitoComplessivoResponse")
    public JAXBElement<ResponseType> createAggiornaEsitoComplessivoResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AggiornaEsitoComplessivoResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContaNumeroRichiesteSelezionateRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "ContaNumeroRichiesteSelezionateRequest")
    public JAXBElement<ContaNumeroRichiesteSelezionateRequestType> createContaNumeroRichiesteSelezionateRequest(ContaNumeroRichiesteSelezionateRequestType value) {
        return new JAXBElement<ContaNumeroRichiesteSelezionateRequestType>(_ContaNumeroRichiesteSelezionateRequest_QNAME, ContaNumeroRichiesteSelezionateRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaConfigurazioneApplicativoRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaConfigurazioneApplicativoRequest")
    public JAXBElement<RicercaConfigurazioneApplicativoRequestType> createRicercaConfigurazioneApplicativoRequest(RicercaConfigurazioneApplicativoRequestType value) {
        return new JAXBElement<RicercaConfigurazioneApplicativoRequestType>(_RicercaConfigurazioneApplicativoRequest_QNAME, RicercaConfigurazioneApplicativoRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaListaConfigurazioniApplicativiResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaListaConfigurazioniApplicativiResponse")
    public JAXBElement<RicercaListaConfigurazioniApplicativiResponseType> createRicercaListaConfigurazioniApplicativiResponse(RicercaListaConfigurazioniApplicativiResponseType value) {
        return new JAXBElement<RicercaListaConfigurazioniApplicativiResponseType>(_RicercaListaConfigurazioniApplicativiResponse_QNAME, RicercaListaConfigurazioniApplicativiResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaEsitoInvioRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaEsitoInvioRequest")
    public JAXBElement<AggiornaEsitoInvioRequestType> createAggiornaEsitoInvioRequest(AggiornaEsitoInvioRequestType value) {
        return new JAXBElement<AggiornaEsitoInvioRequestType>(_AggiornaEsitoInvioRequest_QNAME, AggiornaEsitoInvioRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaEsitoComplessivoRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaEsitoComplessivoRequest")
    public JAXBElement<AggiornaEsitoComplessivoRequestType> createAggiornaEsitoComplessivoRequest(AggiornaEsitoComplessivoRequestType value) {
        return new JAXBElement<AggiornaEsitoComplessivoRequestType>(_AggiornaEsitoComplessivoRequest_QNAME, AggiornaEsitoComplessivoRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaRichiestaRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaRichiestaRequest")
    public JAXBElement<AggiornaRichiestaRequestType> createAggiornaRichiestaRequest(AggiornaRichiestaRequestType value) {
        return new JAXBElement<AggiornaRichiestaRequestType>(_AggiornaRichiestaRequest_QNAME, AggiornaRichiestaRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaStatiAggregatiWsoRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaStatiAggregatiWsoRequest")
    public JAXBElement<RicercaStatiAggregatiWsoRequestType> createRicercaStatiAggregatiWsoRequest(RicercaStatiAggregatiWsoRequestType value) {
        return new JAXBElement<RicercaStatiAggregatiWsoRequestType>(_RicercaStatiAggregatiWsoRequest_QNAME, RicercaStatiAggregatiWsoRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaRichiesteRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaRichiesteRequest")
    public JAXBElement<RicercaRichiesteRequestType> createRicercaRichiesteRequest(RicercaRichiesteRequestType value) {
        return new JAXBElement<RicercaRichiesteRequestType>(_RicercaRichiesteRequest_QNAME, RicercaRichiesteRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaListaConfigurazioniApplicativiRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaListaConfigurazioniApplicativiRequest")
    public JAXBElement<RicercaListaConfigurazioniApplicativiRequestType> createRicercaListaConfigurazioniApplicativiRequest(RicercaListaConfigurazioniApplicativiRequestType value) {
        return new JAXBElement<RicercaListaConfigurazioniApplicativiRequestType>(_RicercaListaConfigurazioniApplicativiRequest_QNAME, RicercaListaConfigurazioniApplicativiRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaEsitoInvioResponse")
    public JAXBElement<ResponseType> createAggiornaEsitoInvioResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AggiornaEsitoInvioResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeggiRichiestaResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "LeggiRichiestaResponse")
    public JAXBElement<LeggiRichiestaResponseType> createLeggiRichiestaResponse(LeggiRichiestaResponseType value) {
        return new JAXBElement<LeggiRichiestaResponseType>(_LeggiRichiestaResponse_QNAME, LeggiRichiestaResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaRichiestaESingoloEsitoInvioResponse")
    public JAXBElement<ResponseType> createAggiornaRichiestaESingoloEsitoInvioResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AggiornaRichiestaESingoloEsitoInvioResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "AggiornaRichiestaResponse")
    public JAXBElement<ResponseType> createAggiornaRichiestaResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AggiornaRichiestaResponse_QNAME, ResponseType.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciNuovaRichiestaRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "InserisciNuovaRichiestaRequest")
    public JAXBElement<InserisciNuovaRichiestaRequestType> createInserisciNuovaRichiestaRequest(InserisciNuovaRichiestaRequestType value) {
        return new JAXBElement<InserisciNuovaRichiestaRequestType>(_InserisciNuovaRichiestaRequest_QNAME, InserisciNuovaRichiestaRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciNuovaRichiestaResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "InserisciNuovaRichiestaResponse")
    public JAXBElement<InserisciNuovaRichiestaResponseType> createInserisciNuovaRichiestaResponse(InserisciNuovaRichiestaResponseType value) {
        return new JAXBElement<InserisciNuovaRichiestaResponseType>(_InserisciNuovaRichiestaResponse_QNAME, InserisciNuovaRichiestaResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContaNumeroRichiesteSelezionateResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "ContaNumeroRichiesteSelezionateResponse")
    public JAXBElement<ContaNumeroRichiesteSelezionateResponseType> createContaNumeroRichiesteSelezionateResponse(ContaNumeroRichiesteSelezionateResponseType value) {
        return new JAXBElement<ContaNumeroRichiesteSelezionateResponseType>(_ContaNumeroRichiesteSelezionateResponse_QNAME, ContaNumeroRichiesteSelezionateResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeggiRichiestaRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "LeggiRichiestaRequest")
    public JAXBElement<LeggiRichiestaRequestType> createLeggiRichiestaRequest(LeggiRichiestaRequestType value) {
        return new JAXBElement<LeggiRichiestaRequestType>(_LeggiRichiestaRequest_QNAME, LeggiRichiestaRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaConfigurazioneApplicativoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaConfigurazioneApplicativoResponse")
    public JAXBElement<RicercaConfigurazioneApplicativoResponseType> createRicercaConfigurazioneApplicativoResponse(RicercaConfigurazioneApplicativoResponseType value) {
        return new JAXBElement<RicercaConfigurazioneApplicativoResponseType>(_RicercaConfigurazioneApplicativoResponse_QNAME, RicercaConfigurazioneApplicativoResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaStatiAggregatiWsoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaStatiAggregatiWsoResponse")
    public JAXBElement<RicercaStatiAggregatiWsoResponseType> createRicercaStatiAggregatiWsoResponse(RicercaStatiAggregatiWsoResponseType value) {
        return new JAXBElement<RicercaStatiAggregatiWsoResponseType>(_RicercaStatiAggregatiWsoResponse_QNAME, RicercaStatiAggregatiWsoResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaRichiesteResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaRichiesteResponse")
    public JAXBElement<RicercaRichiesteResponseType> createRicercaRichiesteResponse(RicercaRichiesteResponseType value) {
        return new JAXBElement<RicercaRichiesteResponseType>(_RicercaRichiesteResponse_QNAME, RicercaRichiesteResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaEnteRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "RicercaEnteRequest")
    public JAXBElement<RicercaEnteRequestType> createRicercaEnteRequest(RicercaEnteRequestType value) {
        return new JAXBElement<RicercaEnteRequestType>(_RicercaEnteRequest_QNAME, RicercaEnteRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csi.it/epay/epaywso/businesstypes", name = "IdApplicativo", scope = AggiornaRichiestaESingoloEsitoInvioRequestType.class)
    public JAXBElement<Integer> createAggiornaRichiestaESingoloEsitoInvioRequestTypeIdApplicativo(Integer value) {
        return new JAXBElement<Integer>(_AggiornaRichiestaESingoloEsitoInvioRequestTypeIdApplicativo_QNAME, Integer.class, AggiornaRichiestaESingoloEsitoInvioRequestType.class, value);
    }

}
