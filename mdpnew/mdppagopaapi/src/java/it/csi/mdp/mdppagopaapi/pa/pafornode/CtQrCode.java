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
 * Payment Notice **QR-CODE** data.
 * QR-CODE is ISO/IEC 18004:2015 compliant generated with:
 * 
 * Parameters for generating the QR-CODE
 * 
 * - Symbol Version : 4
 * - Modules : 33x33
 * - Modules width : 3 pixels
 * - ECC Level: M ( max correction error 15%)
 * - Character set : UTF-8
 * 
 * QR-CODE contains a string formatted as :
 * `PAGOPA|002|noticeNumber|fiscalCode|amount`
 * 
 * Where `noticeNumber` is composed by :
 * 
 * `[auxDigit][segregationCode][IUVBase][IUVCheckDigit]`
 * 
 * While `fiscalCode` is the creditor tax code.
 * 
 * <p>Java class for ctQrCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctQrCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fiscalCode" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stFiscalCodePA"/>
 *         &lt;element name="noticeNumber" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stNoticeNumber"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctQrCode", propOrder = {
    "fiscalCode",
    "noticeNumber"
})
public class CtQrCode {

    @XmlElement(required = true)
    protected String fiscalCode;
    @XmlElement(required = true)
    protected String noticeNumber;

    /**
     * Gets the value of the fiscalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiscalCode() {
        return fiscalCode;
    }

    /**
     * Sets the value of the fiscalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiscalCode(String value) {
        this.fiscalCode = value;
    }

    /**
     * Gets the value of the noticeNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoticeNumber() {
        return noticeNumber;
    }

    /**
     * Sets the value of the noticeNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoticeNumber(String value) {
        this.noticeNumber = value;
    }

}
