/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.mdp.mdppagopaapi.util.clientws package. 
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

    private final static QName _RiceviRTException_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "RiceviRTException");
    private final static QName _RiceviRT_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "riceviRT");
    private final static QName _RiceviEsitoResponse_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "riceviEsitoResponse");
    private final static QName _UnrecoverableException_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "UnrecoverableException");
    private final static QName _VerificaDatiPagamento_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "verificaDatiPagamento");
    private final static QName _ChiediDatiPagamentoException_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "ChiediDatiPagamentoException");
    private final static QName _VerificaDatiPagamentoException_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "VerificaDatiPagamentoException");
    private final static QName _ChiediDatiPagamentoResponse_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "chiediDatiPagamentoResponse");
    private final static QName _VerificaDatiPagamentoResponse_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "verificaDatiPagamentoResponse");
    private final static QName _RiceviEsito_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "riceviEsito");
    private final static QName _ChiediDatiPagamento_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "chiediDatiPagamento");
    private final static QName _RiceviRTResponse_QNAME = new QName("http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", "riceviRTResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.mdp.mdppagopaapi.util.clientws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RiceviRTException }
     * 
     */
    public RiceviRTException createRiceviRTException() {
        return new RiceviRTException();
    }

    /**
     * Create an instance of {@link RiceviEsito }
     * 
     */
    public RiceviEsito createRiceviEsito() {
        return new RiceviEsito();
    }

    /**
     * Create an instance of {@link ChiaveValore }
     * 
     */
    public ChiaveValore createChiaveValore() {
        return new ChiaveValore();
    }

    /**
     * Create an instance of {@link RiceviRT }
     * 
     */
    public RiceviRT createRiceviRT() {
        return new RiceviRT();
    }

    /**
     * Create an instance of {@link UnrecoverableException }
     * 
     */
    public UnrecoverableException createUnrecoverableException() {
        return new UnrecoverableException();
    }

    /**
     * Create an instance of {@link VerificaDatiPagamentoResponse }
     * 
     */
    public VerificaDatiPagamentoResponse createVerificaDatiPagamentoResponse() {
        return new VerificaDatiPagamentoResponse();
    }

    /**
     * Create an instance of {@link TransazioneExtraAttribute }
     * 
     */
    public TransazioneExtraAttribute createTransazioneExtraAttribute() {
        return new TransazioneExtraAttribute();
    }

    /**
     * Create an instance of {@link VerificaDatiPagamentoException }
     * 
     */
    public VerificaDatiPagamentoException createVerificaDatiPagamentoException() {
        return new VerificaDatiPagamentoException();
    }

    /**
     * Create an instance of {@link EsitoRiceviEsito }
     * 
     */
    public EsitoRiceviEsito createEsitoRiceviEsito() {
        return new EsitoRiceviEsito();
    }

    /**
     * Create an instance of {@link ParametriChiediDatiPagamento }
     * 
     */
    public ParametriChiediDatiPagamento createParametriChiediDatiPagamento() {
        return new ParametriChiediDatiPagamento();
    }

    /**
     * Create an instance of {@link ParametriVerificaDatiPagamento }
     * 
     */
    public ParametriVerificaDatiPagamento createParametriVerificaDatiPagamento() {
        return new ParametriVerificaDatiPagamento();
    }

    /**
     * Create an instance of {@link ParametriRiceviEsito }
     * 
     */
    public ParametriRiceviEsito createParametriRiceviEsito() {
        return new ParametriRiceviEsito();
    }

    /**
     * Create an instance of {@link EsitoChiediDatiPagamento }
     * 
     */
    public EsitoChiediDatiPagamento createEsitoChiediDatiPagamento() {
        return new EsitoChiediDatiPagamento();
    }

    /**
     * Create an instance of {@link VerificaDatiPagamento }
     * 
     */
    public VerificaDatiPagamento createVerificaDatiPagamento() {
        return new VerificaDatiPagamento();
    }

    /**
     * Create an instance of {@link ChiediDatiPagamentoResponse }
     * 
     */
    public ChiediDatiPagamentoResponse createChiediDatiPagamentoResponse() {
        return new ChiediDatiPagamentoResponse();
    }

    /**
     * Create an instance of {@link ChiediDatiPagamentoException }
     * 
     */
    public ChiediDatiPagamentoException createChiediDatiPagamentoException() {
        return new ChiediDatiPagamentoException();
    }

    /**
     * Create an instance of {@link RiceviEsitoResponse }
     * 
     */
    public RiceviEsitoResponse createRiceviEsitoResponse() {
        return new RiceviEsitoResponse();
    }

    /**
     * Create an instance of {@link ChiediDatiPagamento }
     * 
     */
    public ChiediDatiPagamento createChiediDatiPagamento() {
        return new ChiediDatiPagamento();
    }

    /**
     * Create an instance of {@link EsitoRiceviRT }
     * 
     */
    public EsitoRiceviRT createEsitoRiceviRT() {
        return new EsitoRiceviRT();
    }

    /**
     * Create an instance of {@link RiceviRTResponse }
     * 
     */
    public RiceviRTResponse createRiceviRTResponse() {
        return new RiceviRTResponse();
    }

    /**
     * Create an instance of {@link ParametriRiceviRT }
     * 
     */
    public ParametriRiceviRT createParametriRiceviRT() {
        return new ParametriRiceviRT();
    }

    /**
     * Create an instance of {@link EsitoVerificaDatiPagamento }
     * 
     */
    public EsitoVerificaDatiPagamento createEsitoVerificaDatiPagamento() {
        return new EsitoVerificaDatiPagamento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RiceviRTException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "RiceviRTException")
    public JAXBElement<RiceviRTException> createRiceviRTException(RiceviRTException value) {
        return new JAXBElement<RiceviRTException>(_RiceviRTException_QNAME, RiceviRTException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RiceviRT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "riceviRT")
    public JAXBElement<RiceviRT> createRiceviRT(RiceviRT value) {
        return new JAXBElement<RiceviRT>(_RiceviRT_QNAME, RiceviRT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RiceviEsitoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "riceviEsitoResponse")
    public JAXBElement<RiceviEsitoResponse> createRiceviEsitoResponse(RiceviEsitoResponse value) {
        return new JAXBElement<RiceviEsitoResponse>(_RiceviEsitoResponse_QNAME, RiceviEsitoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnrecoverableException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "UnrecoverableException")
    public JAXBElement<UnrecoverableException> createUnrecoverableException(UnrecoverableException value) {
        return new JAXBElement<UnrecoverableException>(_UnrecoverableException_QNAME, UnrecoverableException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificaDatiPagamento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "verificaDatiPagamento")
    public JAXBElement<VerificaDatiPagamento> createVerificaDatiPagamento(VerificaDatiPagamento value) {
        return new JAXBElement<VerificaDatiPagamento>(_VerificaDatiPagamento_QNAME, VerificaDatiPagamento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChiediDatiPagamentoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "ChiediDatiPagamentoException")
    public JAXBElement<ChiediDatiPagamentoException> createChiediDatiPagamentoException(ChiediDatiPagamentoException value) {
        return new JAXBElement<ChiediDatiPagamentoException>(_ChiediDatiPagamentoException_QNAME, ChiediDatiPagamentoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificaDatiPagamentoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "VerificaDatiPagamentoException")
    public JAXBElement<VerificaDatiPagamentoException> createVerificaDatiPagamentoException(VerificaDatiPagamentoException value) {
        return new JAXBElement<VerificaDatiPagamentoException>(_VerificaDatiPagamentoException_QNAME, VerificaDatiPagamentoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChiediDatiPagamentoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "chiediDatiPagamentoResponse")
    public JAXBElement<ChiediDatiPagamentoResponse> createChiediDatiPagamentoResponse(ChiediDatiPagamentoResponse value) {
        return new JAXBElement<ChiediDatiPagamentoResponse>(_ChiediDatiPagamentoResponse_QNAME, ChiediDatiPagamentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificaDatiPagamentoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "verificaDatiPagamentoResponse")
    public JAXBElement<VerificaDatiPagamentoResponse> createVerificaDatiPagamentoResponse(VerificaDatiPagamentoResponse value) {
        return new JAXBElement<VerificaDatiPagamentoResponse>(_VerificaDatiPagamentoResponse_QNAME, VerificaDatiPagamentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RiceviEsito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "riceviEsito")
    public JAXBElement<RiceviEsito> createRiceviEsito(RiceviEsito value) {
        return new JAXBElement<RiceviEsito>(_RiceviEsito_QNAME, RiceviEsito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChiediDatiPagamento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "chiediDatiPagamento")
    public JAXBElement<ChiediDatiPagamento> createChiediDatiPagamento(ChiediDatiPagamento value) {
        return new JAXBElement<ChiediDatiPagamento>(_ChiediDatiPagamento_QNAME, ChiediDatiPagamento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RiceviRTResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", name = "riceviRTResponse")
    public JAXBElement<RiceviRTResponse> createRiceviRTResponse(RiceviRTResponse value) {
        return new JAXBElement<RiceviRTResponse>(_RiceviRTResponse_QNAME, RiceviRTResponse.class, null, value);
    }

}
