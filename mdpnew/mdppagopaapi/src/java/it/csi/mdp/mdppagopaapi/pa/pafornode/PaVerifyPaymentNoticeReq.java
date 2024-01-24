/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The `paVerifyPaymentNotice` request contains :
 * - `idPA` : alphanumeric field containing the tax code of the structure sending the payment request.
 * - `idBrokerPA` : identification of subject that operates as an intermediary for the PA.
 * - `idStation` : identification of the station of the PA into pagoPa system.
 * - `qrCode` : is the union of `fiscalCode` and `noticeNumber`
 * 
 * <p>Java class for paVerifyPaymentNoticeReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paVerifyPaymentNoticeReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPA" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="idBrokerPA" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="idStation" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="qrCode" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctQrCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paVerifyPaymentNoticeReq", propOrder = {
    "idPA",
    "idBrokerPA",
    "idStation",
    "qrCode"
})
public class PaVerifyPaymentNoticeReq {

    @XmlElement(required = true)
    protected String idPA;
    @XmlElement(required = true)
    protected String idBrokerPA;
    @XmlElement(required = true)
    protected String idStation;
    @XmlElement(required = true)
    protected CtQrCode qrCode;

    /**
     * Gets the value of the idPA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPA() {
        return idPA;
    }

    /**
     * Sets the value of the idPA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPA(String value) {
        this.idPA = value;
    }

    /**
     * Gets the value of the idBrokerPA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdBrokerPA() {
        return idBrokerPA;
    }

    /**
     * Sets the value of the idBrokerPA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdBrokerPA(String value) {
        this.idBrokerPA = value;
    }

    /**
     * Gets the value of the idStation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdStation() {
        return idStation;
    }

    /**
     * Sets the value of the idStation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdStation(String value) {
        this.idStation = value;
    }

    /**
     * Gets the value of the qrCode property.
     * 
     * @return
     *     possible object is
     *     {@link CtQrCode }
     *     
     */
    public CtQrCode getQrCode() {
        return qrCode;
    }

    /**
     * Sets the value of the qrCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtQrCode }
     *     
     */
    public void setQrCode(CtQrCode value) {
        this.qrCode = value;
    }

}
