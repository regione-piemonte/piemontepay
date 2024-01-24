/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.epay.epayjob.interfacews.epaymodric package. 
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

    private final static QName _Exception_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "Exception");
    private final static QName _AttivaElaborazioneConStatiDaEscludere_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "attivaElaborazioneConStatiDaEscludere");
    private final static QName _AttivaElaborazioneConStatiDaEscludereResponse_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "attivaElaborazioneConStatiDaEscludereResponse");
    private final static QName _AttivaElaborazione_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "attivaElaborazione");
    private final static QName _AttivaElaborazioneResponse_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "attivaElaborazioneResponse");
    private final static QName _EseguiElaborazioneResponse_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "eseguiElaborazioneResponse");
    private final static QName _EpaymodricException_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "EpaymodricException");
    private final static QName _UnrecoverableException_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "UnrecoverableException");
    private final static QName _EseguiElaborazione_QNAME = new QName("http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", "eseguiElaborazione");
    private final static QName _EpaymodricExceptionErrorCode_QNAME = new QName("", "errorCode");
    private final static QName _EpaymodricExceptionMessage_QNAME = new QName("", "message");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.epay.epayjob.interfacews.epaymodric
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AttivaElaborazioneResponse }
     * 
     */
    public AttivaElaborazioneResponse createAttivaElaborazioneResponse() {
        return new AttivaElaborazioneResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link EseguiElaborazione }
     * 
     */
    public EseguiElaborazione createEseguiElaborazione() {
        return new EseguiElaborazione();
    }

    /**
     * Create an instance of {@link DtoOutputWsEsito }
     * 
     */
    public DtoOutputWsEsito createDtoOutputWsEsito() {
        return new DtoOutputWsEsito();
    }

    /**
     * Create an instance of {@link DtoErroreFlusso }
     * 
     */
    public DtoErroreFlusso createDtoErroreFlusso() {
        return new DtoErroreFlusso();
    }

    /**
     * Create an instance of {@link UnrecoverableException }
     * 
     */
    public UnrecoverableException createUnrecoverableException() {
        return new UnrecoverableException();
    }

    /**
     * Create an instance of {@link AttivaElaborazioneConStatiDaEscludere }
     * 
     */
    public AttivaElaborazioneConStatiDaEscludere createAttivaElaborazioneConStatiDaEscludere() {
        return new AttivaElaborazioneConStatiDaEscludere();
    }

    /**
     * Create an instance of {@link DtoInputBase }
     * 
     */
    public DtoInputBase createDtoInputBase() {
        return new DtoInputBase();
    }

    /**
     * Create an instance of {@link EseguiElaborazioneResponse }
     * 
     */
    public EseguiElaborazioneResponse createEseguiElaborazioneResponse() {
        return new EseguiElaborazioneResponse();
    }

    /**
     * Create an instance of {@link DtoInputWsAttivaElaborazioneConStatiDaEscludere }
     * 
     */
    public DtoInputWsAttivaElaborazioneConStatiDaEscludere createDtoInputWsAttivaElaborazioneConStatiDaEscludere() {
        return new DtoInputWsAttivaElaborazioneConStatiDaEscludere();
    }

    /**
     * Create an instance of {@link DtoInputWsEseguiElaborazione }
     * 
     */
    public DtoInputWsEseguiElaborazione createDtoInputWsEseguiElaborazione() {
        return new DtoInputWsEseguiElaborazione();
    }

    /**
     * Create an instance of {@link AttivaElaborazione }
     * 
     */
    public AttivaElaborazione createAttivaElaborazione() {
        return new AttivaElaborazione();
    }

    /**
     * Create an instance of {@link DtoInputVuoto }
     * 
     */
    public DtoInputVuoto createDtoInputVuoto() {
        return new DtoInputVuoto();
    }

    /**
     * Create an instance of {@link DtoPrincipal }
     * 
     */
    public DtoPrincipal createDtoPrincipal() {
        return new DtoPrincipal();
    }

    /**
     * Create an instance of {@link DtoOutputWsMotoreDiRiconciliazione }
     * 
     */
    public DtoOutputWsMotoreDiRiconciliazione createDtoOutputWsMotoreDiRiconciliazione() {
        return new DtoOutputWsMotoreDiRiconciliazione();
    }

    /**
     * Create an instance of {@link AttivaElaborazioneConStatiDaEscludereResponse }
     * 
     */
    public AttivaElaborazioneConStatiDaEscludereResponse createAttivaElaborazioneConStatiDaEscludereResponse() {
        return new AttivaElaborazioneConStatiDaEscludereResponse();
    }

    /**
     * Create an instance of {@link EpaymodricException }
     * 
     */
    public EpaymodricException createEpaymodricException() {
        return new EpaymodricException();
    }

    /**
     * Create an instance of {@link DtoCaller }
     * 
     */
    public DtoCaller createDtoCaller() {
        return new DtoCaller();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttivaElaborazioneConStatiDaEscludere }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "attivaElaborazioneConStatiDaEscludere")
    public JAXBElement<AttivaElaborazioneConStatiDaEscludere> createAttivaElaborazioneConStatiDaEscludere(AttivaElaborazioneConStatiDaEscludere value) {
        return new JAXBElement<AttivaElaborazioneConStatiDaEscludere>(_AttivaElaborazioneConStatiDaEscludere_QNAME, AttivaElaborazioneConStatiDaEscludere.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttivaElaborazioneConStatiDaEscludereResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "attivaElaborazioneConStatiDaEscludereResponse")
    public JAXBElement<AttivaElaborazioneConStatiDaEscludereResponse> createAttivaElaborazioneConStatiDaEscludereResponse(AttivaElaborazioneConStatiDaEscludereResponse value) {
        return new JAXBElement<AttivaElaborazioneConStatiDaEscludereResponse>(_AttivaElaborazioneConStatiDaEscludereResponse_QNAME, AttivaElaborazioneConStatiDaEscludereResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttivaElaborazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "attivaElaborazione")
    public JAXBElement<AttivaElaborazione> createAttivaElaborazione(AttivaElaborazione value) {
        return new JAXBElement<AttivaElaborazione>(_AttivaElaborazione_QNAME, AttivaElaborazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttivaElaborazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "attivaElaborazioneResponse")
    public JAXBElement<AttivaElaborazioneResponse> createAttivaElaborazioneResponse(AttivaElaborazioneResponse value) {
        return new JAXBElement<AttivaElaborazioneResponse>(_AttivaElaborazioneResponse_QNAME, AttivaElaborazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EseguiElaborazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "eseguiElaborazioneResponse")
    public JAXBElement<EseguiElaborazioneResponse> createEseguiElaborazioneResponse(EseguiElaborazioneResponse value) {
        return new JAXBElement<EseguiElaborazioneResponse>(_EseguiElaborazioneResponse_QNAME, EseguiElaborazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EpaymodricException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "EpaymodricException")
    public JAXBElement<EpaymodricException> createEpaymodricException(EpaymodricException value) {
        return new JAXBElement<EpaymodricException>(_EpaymodricException_QNAME, EpaymodricException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnrecoverableException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "UnrecoverableException")
    public JAXBElement<UnrecoverableException> createUnrecoverableException(UnrecoverableException value) {
        return new JAXBElement<UnrecoverableException>(_UnrecoverableException_QNAME, UnrecoverableException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EseguiElaborazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/", name = "eseguiElaborazione")
    public JAXBElement<EseguiElaborazione> createEseguiElaborazione(EseguiElaborazione value) {
        return new JAXBElement<EseguiElaborazione>(_EseguiElaborazione_QNAME, EseguiElaborazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "errorCode", scope = EpaymodricException.class)
    public JAXBElement<Integer> createEpaymodricExceptionErrorCode(Integer value) {
        return new JAXBElement<Integer>(_EpaymodricExceptionErrorCode_QNAME, Integer.class, EpaymodricException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "message", scope = EpaymodricException.class)
    public JAXBElement<String> createEpaymodricExceptionMessage(String value) {
        return new JAXBElement<String>(_EpaymodricExceptionMessage_QNAME, String.class, EpaymodricException.class, value);
    }

}
