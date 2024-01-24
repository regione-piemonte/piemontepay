/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.gov.pagopa.pagopa_api.pa.pafornode package. 
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

    private final static QName _PaGetPaymentRes_QNAME = new QName("http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", "paGetPaymentRes");
    private final static QName _PaGetPaymentReq_QNAME = new QName("http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", "paGetPaymentReq");
    private final static QName _PaSendRTReq_QNAME = new QName("http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", "paSendRTReq");
    private final static QName _PaSendRTRes_QNAME = new QName("http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", "paSendRTRes");
    private final static QName _PaVerifyPaymentNoticeRes_QNAME = new QName("http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", "paVerifyPaymentNoticeRes");
    private final static QName _PaVerifyPaymentNoticeReq_QNAME = new QName("http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", "paVerifyPaymentNoticeReq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.gov.pagopa.pagopa_api.pa.pafornode
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PaVerifyPaymentNoticeRes }
     * 
     */
    public PaVerifyPaymentNoticeRes createPaVerifyPaymentNoticeRes() {
        return new PaVerifyPaymentNoticeRes();
    }

    /**
     * Create an instance of {@link CtPaymentOptionDescriptionPA }
     * 
     */
    public CtPaymentOptionDescriptionPA createCtPaymentOptionDescriptionPA() {
        return new CtPaymentOptionDescriptionPA();
    }

    /**
     * Create an instance of {@link PaVerifyPaymentNoticeReq }
     * 
     */
    public PaVerifyPaymentNoticeReq createPaVerifyPaymentNoticeReq() {
        return new PaVerifyPaymentNoticeReq();
    }

    /**
     * Create an instance of {@link CtEntityUniqueIdentifier }
     * 
     */
    public CtEntityUniqueIdentifier createCtEntityUniqueIdentifier() {
        return new CtEntityUniqueIdentifier();
    }

    /**
     * Create an instance of {@link CtFaultBean }
     * 
     */
    public CtFaultBean createCtFaultBean() {
        return new CtFaultBean();
    }

    /**
     * Create an instance of {@link CtTransferListPA }
     * 
     */
    public CtTransferListPA createCtTransferListPA() {
        return new CtTransferListPA();
    }

    /**
     * Create an instance of {@link CtResponse }
     * 
     */
    public CtResponse createCtResponse() {
        return new CtResponse();
    }

    /**
     * Create an instance of {@link CtReceipt }
     * 
     */
    public CtReceipt createCtReceipt() {
        return new CtReceipt();
    }

    /**
     * Create an instance of {@link CtMapEntry }
     * 
     */
    public CtMapEntry createCtMapEntry() {
        return new CtMapEntry();
    }

    /**
     * Create an instance of {@link PaGetPaymentReq }
     * 
     */
    public PaGetPaymentReq createPaGetPaymentReq() {
        return new PaGetPaymentReq();
    }

    /**
     * Create an instance of {@link PaGetPaymentRes }
     * 
     */
    public PaGetPaymentRes createPaGetPaymentRes() {
        return new PaGetPaymentRes();
    }

    /**
     * Create an instance of {@link CtQrCode }
     * 
     */
    public CtQrCode createCtQrCode() {
        return new CtQrCode();
    }

    /**
     * Create an instance of {@link CtPaymentOptionsDescriptionListPA }
     * 
     */
    public CtPaymentOptionsDescriptionListPA createCtPaymentOptionsDescriptionListPA() {
        return new CtPaymentOptionsDescriptionListPA();
    }

    /**
     * Create an instance of {@link CtSubject }
     * 
     */
    public CtSubject createCtSubject() {
        return new CtSubject();
    }

    /**
     * Create an instance of {@link PaSendRTReq }
     * 
     */
    public PaSendRTReq createPaSendRTReq() {
        return new PaSendRTReq();
    }

    /**
     * Create an instance of {@link CtTransferPA }
     * 
     */
    public CtTransferPA createCtTransferPA() {
        return new CtTransferPA();
    }

    /**
     * Create an instance of {@link PaSendRTRes }
     * 
     */
    public PaSendRTRes createPaSendRTRes() {
        return new PaSendRTRes();
    }

    /**
     * Create an instance of {@link CtMetadata }
     * 
     */
    public CtMetadata createCtMetadata() {
        return new CtMetadata();
    }

    /**
     * Create an instance of {@link CtPaymentPA }
     * 
     */
    public CtPaymentPA createCtPaymentPA() {
        return new CtPaymentPA();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaGetPaymentRes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", name = "paGetPaymentRes")
    public JAXBElement<PaGetPaymentRes> createPaGetPaymentRes(PaGetPaymentRes value) {
        return new JAXBElement<PaGetPaymentRes>(_PaGetPaymentRes_QNAME, PaGetPaymentRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaGetPaymentReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", name = "paGetPaymentReq")
    public JAXBElement<PaGetPaymentReq> createPaGetPaymentReq(PaGetPaymentReq value) {
        return new JAXBElement<PaGetPaymentReq>(_PaGetPaymentReq_QNAME, PaGetPaymentReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaSendRTReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", name = "paSendRTReq")
    public JAXBElement<PaSendRTReq> createPaSendRTReq(PaSendRTReq value) {
        return new JAXBElement<PaSendRTReq>(_PaSendRTReq_QNAME, PaSendRTReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaSendRTRes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", name = "paSendRTRes")
    public JAXBElement<PaSendRTRes> createPaSendRTRes(PaSendRTRes value) {
        return new JAXBElement<PaSendRTRes>(_PaSendRTRes_QNAME, PaSendRTRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaVerifyPaymentNoticeRes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", name = "paVerifyPaymentNoticeRes")
    public JAXBElement<PaVerifyPaymentNoticeRes> createPaVerifyPaymentNoticeRes(PaVerifyPaymentNoticeRes value) {
        return new JAXBElement<PaVerifyPaymentNoticeRes>(_PaVerifyPaymentNoticeRes_QNAME, PaVerifyPaymentNoticeRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaVerifyPaymentNoticeReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd", name = "paVerifyPaymentNoticeReq")
    public JAXBElement<PaVerifyPaymentNoticeReq> createPaVerifyPaymentNoticeReq(PaVerifyPaymentNoticeReq value) {
        return new JAXBElement<PaVerifyPaymentNoticeReq>(_PaVerifyPaymentNoticeReq_QNAME, PaVerifyPaymentNoticeReq.class, null, value);
    }

}
