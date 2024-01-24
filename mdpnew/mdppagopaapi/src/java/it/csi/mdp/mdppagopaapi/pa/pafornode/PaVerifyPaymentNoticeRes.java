/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Its a response to `paVerifyPaymentNoticeReq` and contains :
 * 
 * - `outcome` and _optional_ `fault` (_see below to details_)
 * - `paymentList` : the list of all available payment options (_see below to details_)
 * - `paymentDescription` : 
 * 
 * If the Public Administration is configured as _OLD_ (i.e. still uses the old primitives) this field must be set with the data `nodeTipoDatiPagamentoPA` of the` nodeVerificaRPTRanspond` specifically:
 * - `causaleVersamento`: represents the extended description of the reason for the payment, or
 * - `spezzoniCausaleVersamento`: structure available to Public Administration to specify the payment reasons.
 * 
 * The size of the current field is such as to allow the concatenation of the old information previously described.
 * 
 * - `fiscalCodePA` : Tax code of the public administration
 * - `companyName` : Public Administration full name
 * - `officeName` : Public Administration Department Name
 * 
 * <p>Java class for paVerifyPaymentNoticeRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paVerifyPaymentNoticeRes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctResponse">
 *       &lt;sequence>
 *         &lt;element name="paymentList" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctPaymentOptionsDescriptionListPA" minOccurs="0"/>
 *         &lt;element name="paymentDescription" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140" minOccurs="0"/>
 *         &lt;element name="fiscalCodePA" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stFiscalCodePA" minOccurs="0"/>
 *         &lt;element name="companyName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140" minOccurs="0"/>
 *         &lt;element name="officeName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paVerifyPaymentNoticeRes", propOrder = {
    "paymentList",
    "paymentDescription",
    "fiscalCodePA",
    "companyName",
    "officeName"
})
public class PaVerifyPaymentNoticeRes
    extends CtResponse
{

    protected CtPaymentOptionsDescriptionListPA paymentList;
    protected String paymentDescription;
    protected String fiscalCodePA;
    protected String companyName;
    protected String officeName;

    /**
     * Gets the value of the paymentList property.
     * 
     * @return
     *     possible object is
     *     {@link CtPaymentOptionsDescriptionListPA }
     *     
     */
    public CtPaymentOptionsDescriptionListPA getPaymentList() {
        return paymentList;
    }

    /**
     * Sets the value of the paymentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtPaymentOptionsDescriptionListPA }
     *     
     */
    public void setPaymentList(CtPaymentOptionsDescriptionListPA value) {
        this.paymentList = value;
    }

    /**
     * Gets the value of the paymentDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentDescription() {
        return paymentDescription;
    }

    /**
     * Sets the value of the paymentDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentDescription(String value) {
        this.paymentDescription = value;
    }

    /**
     * Gets the value of the fiscalCodePA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiscalCodePA() {
        return fiscalCodePA;
    }

    /**
     * Sets the value of the fiscalCodePA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiscalCodePA(String value) {
        this.fiscalCodePA = value;
    }

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the officeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * Sets the value of the officeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeName(String value) {
        this.officeName = value;
    }

}
