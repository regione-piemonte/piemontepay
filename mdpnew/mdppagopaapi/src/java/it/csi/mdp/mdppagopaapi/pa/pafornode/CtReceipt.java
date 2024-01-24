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
 * Its contains all receipt information :
 * 
 * **identifier section**
 * - `outcome` : result of receipt **OK** / **KO**
 * - `receiptId` : unique identifier of receipt (assigned by pagoPa) it contains `paymentToken` present in to `activatePaymentNotice` response 
 * - `noticeNumber` : notice number
 * - `fiscalCode` : Tax code of the public administration
 * 
 * **PA data**
 * - `creditorReferenceId` : **IUV** _Identificativo Univoco Versamento_
 * - `paymentAmount` : amount
 * - `description` : 
 * - `companyName` : Public Administration full name
 * - `officeName` Public Administration Department Name
 * - `debtor` : debtor subject identifier
 * - `transferList` : the list of transfers
 * - `metadata` : info received in to `paGetPaymentRes`
 * 
 * **PSP data**
 * - `idPSP` : PSP Identifier, it has been assigned from pagoPA.
 * - `pspFiscalCode` : PSP' fiscal code
 * - `pspPartitaIVA` : PSP' _Partita IVA_
 * - `PSPCompanyName` : PSP full name
 * - `idChannel` : Channel Identifier, it identifies a payment service category and through which the transaction is carried out.
 * - `channelDescription` : Channel Identifier description
 * - `payer` : who made the payment
 * - `paymentMethod` : Method of the payment , i.e. `cash`, `creditCard`, `bancomat` or `other`
 * - `fee` : PSP's fee applied
 * - `paymentDateTime` : payment execution date by the user
 * - `applicationDate` : application date, payment date on the PSP side
 * - `transferDate` : transfer date
 * 
 * <p>Java class for ctReceipt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctReceipt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="receiptId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noticeNumber" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stNoticeNumber"/>
 *         &lt;element name="fiscalCode" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stFiscalCodePA"/>
 *         &lt;element name="outcome" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stOutcome"/>
 *         &lt;element name="creditorReferenceId" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="paymentAmount" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stAmount"/>
 *         &lt;element name="description" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140"/>
 *         &lt;element name="companyName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140"/>
 *         &lt;element name="officeName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140" minOccurs="0"/>
 *         &lt;element name="debtor" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctSubject"/>
 *         &lt;element name="transferList" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctTransferListPA"/>
 *         &lt;element name="idPSP" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="pspFiscalCode" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText70" minOccurs="0"/>
 *         &lt;element name="pspPartitaIVA" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText20" minOccurs="0"/>
 *         &lt;element name="PSPCompanyName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText70"/>
 *         &lt;element name="idChannel" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="channelDescription" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35"/>
 *         &lt;element name="payer" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctSubject" minOccurs="0"/>
 *         &lt;element name="paymentMethod" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35" minOccurs="0"/>
 *         &lt;element name="fee" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stAmount" minOccurs="0"/>
 *         &lt;element name="paymentDateTime" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stISODateTime" minOccurs="0"/>
 *         &lt;element name="applicationDate" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stISODate" minOccurs="0"/>
 *         &lt;element name="transferDate" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stISODate" minOccurs="0"/>
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
@XmlType(name = "ctReceipt", propOrder = {
    "receiptId",
    "noticeNumber",
    "fiscalCode",
    "outcome",
    "creditorReferenceId",
    "paymentAmount",
    "description",
    "companyName",
    "officeName",
    "debtor",
    "transferList",
    "idPSP",
    "pspFiscalCode",
    "pspPartitaIVA",
    "pspCompanyName",
    "idChannel",
    "channelDescription",
    "payer",
    "paymentMethod",
    "fee",
    "paymentDateTime",
    "applicationDate",
    "transferDate",
    "metadata"
})
public class CtReceipt {

    @XmlElement(required = true)
    protected String receiptId;
    @XmlElement(required = true)
    protected String noticeNumber;
    @XmlElement(required = true)
    protected String fiscalCode;
    @XmlElement(required = true)
    protected StOutcome outcome;
    @XmlElement(required = true)
    protected String creditorReferenceId;
    @XmlElement(required = true)
    protected BigDecimal paymentAmount;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String companyName;
    protected String officeName;
    @XmlElement(required = true)
    protected CtSubject debtor;
    @XmlElement(required = true)
    protected CtTransferListPA transferList;
    @XmlElement(required = true)
    protected String idPSP;
    protected String pspFiscalCode;
    protected String pspPartitaIVA;
    @XmlElement(name = "PSPCompanyName", required = true)
    protected String pspCompanyName;
    @XmlElement(required = true)
    protected String idChannel;
    @XmlElement(required = true)
    protected String channelDescription;
    protected CtSubject payer;
    protected String paymentMethod;
    protected BigDecimal fee;
    protected XMLGregorianCalendar paymentDateTime;
    protected XMLGregorianCalendar applicationDate;
    protected XMLGregorianCalendar transferDate;
    protected CtMetadata metadata;

    /**
     * Gets the value of the receiptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptId() {
        return receiptId;
    }

    /**
     * Sets the value of the receiptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptId(String value) {
        this.receiptId = value;
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
     * Gets the value of the outcome property.
     * 
     * @return
     *     possible object is
     *     {@link StOutcome }
     *     
     */
    public StOutcome getOutcome() {
        return outcome;
    }

    /**
     * Sets the value of the outcome property.
     * 
     * @param value
     *     allowed object is
     *     {@link StOutcome }
     *     
     */
    public void setOutcome(StOutcome value) {
        this.outcome = value;
    }

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
     * Gets the value of the idPSP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPSP() {
        return idPSP;
    }

    /**
     * Sets the value of the idPSP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPSP(String value) {
        this.idPSP = value;
    }

    /**
     * Gets the value of the pspFiscalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPspFiscalCode() {
        return pspFiscalCode;
    }

    /**
     * Sets the value of the pspFiscalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPspFiscalCode(String value) {
        this.pspFiscalCode = value;
    }

    /**
     * Gets the value of the pspPartitaIVA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPspPartitaIVA() {
        return pspPartitaIVA;
    }

    /**
     * Sets the value of the pspPartitaIVA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPspPartitaIVA(String value) {
        this.pspPartitaIVA = value;
    }

    /**
     * Gets the value of the pspCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPSPCompanyName() {
        return pspCompanyName;
    }

    /**
     * Sets the value of the pspCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPSPCompanyName(String value) {
        this.pspCompanyName = value;
    }

    /**
     * Gets the value of the idChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdChannel() {
        return idChannel;
    }

    /**
     * Sets the value of the idChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdChannel(String value) {
        this.idChannel = value;
    }

    /**
     * Gets the value of the channelDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelDescription() {
        return channelDescription;
    }

    /**
     * Sets the value of the channelDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelDescription(String value) {
        this.channelDescription = value;
    }

    /**
     * Gets the value of the payer property.
     * 
     * @return
     *     possible object is
     *     {@link CtSubject }
     *     
     */
    public CtSubject getPayer() {
        return payer;
    }

    /**
     * Sets the value of the payer property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtSubject }
     *     
     */
    public void setPayer(CtSubject value) {
        this.payer = value;
    }

    /**
     * Gets the value of the paymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMethod(String value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the fee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Sets the value of the fee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFee(BigDecimal value) {
        this.fee = value;
    }

    /**
     * Gets the value of the paymentDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDateTime() {
        return paymentDateTime;
    }

    /**
     * Sets the value of the paymentDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDateTime(XMLGregorianCalendar value) {
        this.paymentDateTime = value;
    }

    /**
     * Gets the value of the applicationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getApplicationDate() {
        return applicationDate;
    }

    /**
     * Sets the value of the applicationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setApplicationDate(XMLGregorianCalendar value) {
        this.applicationDate = value;
    }

    /**
     * Gets the value of the transferDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransferDate() {
        return transferDate;
    }

    /**
     * Sets the value of the transferDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransferDate(XMLGregorianCalendar value) {
        this.transferDate = value;
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
