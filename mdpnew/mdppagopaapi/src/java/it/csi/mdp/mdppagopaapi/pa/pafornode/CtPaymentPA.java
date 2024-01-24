/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Its contains all payment information :
 * 
 * - `creditorReferenceId` : its equal to **IUV** _Identificativo Univoco Versamento_ 
 * - `paymentAmount` : amount, it must be equal to the sums of `transferAmount` present in the `transferList`
 * - `dueDate` : indicates the expiration payment date according to the ISO 8601 format `[YYYY]-[MM]-[DD]`.
 * - `retentionDate` : indicates the retention payment date according to the ISO 8601 format `[YYYY]-[MM]-[DD]`.
 * - `lastPayment` : boolean flag used for in installment payments 
 * - `description` : free text available to describe the payment reasons
 * - `companyName` : Public Administration full name
 * - `officeName` : Public Admninistration Department Name
 * - `debtor` : identifies the debtor to whom the debt position refers
 * - `transferList` : the list of all available transfer information (_see below to details_)
 * - `metadata` : (_see below to details_)
 * 
 * <p>Java class for ctPaymentPA complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctPaymentPA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditorReferenceId" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="paymentAmount" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stAmountNotZero"/>
 *         &lt;element name="dueDate" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stISODate"/>
 *         &lt;element name="retentionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lastPayment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="description" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140"/>
 *         &lt;element name="companyName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140" minOccurs="0"/>
 *         &lt;element name="officeName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140" minOccurs="0"/>
 *         &lt;element name="debtor" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctSubject"/>
 *         &lt;element name="transferList" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctTransferListPA"/>
 *         &lt;element name="metadata" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctMetadata" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctPaymentPA", propOrder = {
    "creditorReferenceId",
    "paymentAmount",
    "dueDate",
    "retentionDate",
    "lastPayment",
    "description",
    "companyName",
    "officeName",
    "debtor",
    "transferList",
    "metadata"
})
public class CtPaymentPA {

    @XmlElement(required = true)
    protected String creditorReferenceId;
    @XmlElement(required = true)
    protected BigDecimal paymentAmount;
    @XmlElement(required = true)
    protected XMLGregorianCalendar dueDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar retentionDate;
    protected Boolean lastPayment;
    @XmlElement(required = true)
    protected String description;
    protected String companyName;
    protected String officeName;
    @XmlElement(required = true)
    protected CtSubject debtor;
    @XmlElement(required = true)
    protected CtTransferListPA transferList;
    protected CtMetadata metadata;

    /**
     * Gets the value of the creditorReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorReferenceId() {
        return creditorReferenceId;
    }

    /**
     * Sets the value of the creditorReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorReferenceId(String value) {
        this.creditorReferenceId = value;
    }

    /**
     * Gets the value of the paymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the paymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentAmount(BigDecimal value) {
        this.paymentAmount = value;
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

    /**
     * Gets the value of the retentionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRetentionDate() {
        return retentionDate;
    }

    /**
     * Sets the value of the retentionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRetentionDate(XMLGregorianCalendar value) {
        this.retentionDate = value;
    }

    /**
     * Gets the value of the lastPayment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getLastPayment() {
        return lastPayment;
    }

    /**
     * Sets the value of the lastPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLastPayment(Boolean value) {
        this.lastPayment = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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

    /**
     * Gets the value of the debtor property.
     * 
     * @return
     *     possible object is
     *     {@link CtSubject }
     *     
     */
    public CtSubject getDebtor() {
        return debtor;
    }

    /**
     * Sets the value of the debtor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtSubject }
     *     
     */
    public void setDebtor(CtSubject value) {
        this.debtor = value;
    }

    /**
     * Gets the value of the transferList property.
     * 
     * @return
     *     possible object is
     *     {@link CtTransferListPA }
     *     
     */
    public CtTransferListPA getTransferList() {
        return transferList;
    }

    /**
     * Sets the value of the transferList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtTransferListPA }
     *     
     */
    public void setTransferList(CtTransferListPA value) {
        this.transferList = value;
    }

    /**
     * Gets the value of the metadata property.
     * 
     * @return
     *     possible object is
     *     {@link CtMetadata }
     *     
     */
    public CtMetadata getMetadata() {
        return metadata;
    }

    /**
     * Sets the value of the metadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtMetadata }
     *     
     */
    public void setMetadata(CtMetadata value) {
        this.metadata = value;
    }

}
