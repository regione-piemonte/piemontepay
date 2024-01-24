/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * The `paGetPayment` request contains :
 * - `idPA` : alphanumeric field containing the tax code of the structure sending the payment request.
 * - `idBrokerPA` : identification of subject that operates as an intermediary for the PA.
 * - `idStation` : identification of the station of the PA into pagoPa system.
 * - `qrCode` : is the union of `fiscalCode` and `noticeNumber`
 * - `amount` : amount of the payment
 * - `paymentNote` : details description of the payment
 * - `transferType` : _specific only for POSTE Italiane_
 * - `dueDate` : indicates the expiration payment date according to the ISO 8601 format `[YYYY]-[MM]-[DD]`.
 * 
 * <p>Java class for paGetPaymentReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paGetPaymentReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPA" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="idBrokerPA" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="idStation" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="qrCode" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctQrCode"/>
 *         &lt;element name="amount" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stAmount" minOccurs="0"/>
 *         &lt;element name="paymentNote" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText210" minOccurs="0"/>
 *         &lt;element name="transferType" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stTransferType" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stISODate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paGetPaymentReq", propOrder = {
    "idPA",
    "idBrokerPA",
    "idStation",
    "qrCode",
    "amount",
    "paymentNote",
    "transferType",
    "dueDate"
})
public class PaGetPaymentReq {

    @XmlElement(required = true)
    protected String idPA;
    @XmlElement(required = true)
    protected String idBrokerPA;
    @XmlElement(required = true)
    protected String idStation;
    @XmlElement(required = true)
    protected CtQrCode qrCode;
    protected BigDecimal amount;
    protected String paymentNote;
    protected StTransferType transferType;
    protected XMLGregorianCalendar dueDate;

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

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the paymentNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentNote() {
        return paymentNote;
    }

    /**
     * Sets the value of the paymentNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentNote(String value) {
        this.paymentNote = value;
    }

    /**
     * Gets the value of the transferType property.
     * 
     * @return
     *     possible object is
     *     {@link StTransferType }
     *     
     */
    public StTransferType getTransferType() {
        return transferType;
    }

    /**
     * Sets the value of the transferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link StTransferType }
     *     
     */
    public void setTransferType(StTransferType value) {
        this.transferType = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

}
