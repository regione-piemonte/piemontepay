/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws.coreCxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Transazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Transazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authornumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="basketId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyerEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyercodfisc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ccy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changestatedate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="clientipaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codStato" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="commissioniApplicate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="commissioniApplicateNull" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="finishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gatewayId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatewaypaymodeid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incassokoerrormessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="initDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="intestatariocc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchantId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mscsorderid" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mscsorderidNull" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="oldstate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="opernumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payurl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgresultcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="providertimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rispcomp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userhaschange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transazione", propOrder = {

})
public class Transazione {

    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double amount;
    protected String applicationId;
    protected String authornumber;
    protected String basketId;
    protected String buyerEmail;
    protected String buyercodfisc;
    protected String buyername;
    protected String ccy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar changestatedate;
    protected String clientipaddress;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long codStato;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double commissioniApplicate;
    protected boolean commissioniApplicateNull;
    protected String errcode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar finishDate;
    protected String gatewayId;
    protected String gatewaypaymodeid;
    protected String incassokoerrormessage;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar initDate;
    protected String intestatariocc;
    protected String language;
    protected String merchantId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long mscsorderid;
    protected boolean mscsorderidNull;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long oldstate;
    protected String opernumber;
    protected String paymentid;
    protected String payurl;
    protected String pgresultcode;
    protected String providertimestamp;
    protected String rispcomp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected String transactionId;
    protected String userhaschange;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the applicationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the value of the applicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * Gets the value of the authornumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthornumber() {
        return authornumber;
    }

    /**
     * Sets the value of the authornumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthornumber(String value) {
        this.authornumber = value;
    }

    /**
     * Gets the value of the basketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasketId() {
        return basketId;
    }

    /**
     * Sets the value of the basketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasketId(String value) {
        this.basketId = value;
    }

    /**
     * Gets the value of the buyerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * Sets the value of the buyerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerEmail(String value) {
        this.buyerEmail = value;
    }

    /**
     * Gets the value of the buyercodfisc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyercodfisc() {
        return buyercodfisc;
    }

    /**
     * Sets the value of the buyercodfisc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyercodfisc(String value) {
        this.buyercodfisc = value;
    }

    /**
     * Gets the value of the buyername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyername() {
        return buyername;
    }

    /**
     * Sets the value of the buyername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyername(String value) {
        this.buyername = value;
    }

    /**
     * Gets the value of the ccy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * Sets the value of the ccy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcy(String value) {
        this.ccy = value;
    }

    /**
     * Gets the value of the changestatedate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChangestatedate() {
        return changestatedate;
    }

    /**
     * Sets the value of the changestatedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChangestatedate(XMLGregorianCalendar value) {
        this.changestatedate = value;
    }

    /**
     * Gets the value of the clientipaddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientipaddress() {
        return clientipaddress;
    }

    /**
     * Sets the value of the clientipaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientipaddress(String value) {
        this.clientipaddress = value;
    }

    /**
     * Gets the value of the codStato property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodStato() {
        return codStato;
    }

    /**
     * Sets the value of the codStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodStato(Long value) {
        this.codStato = value;
    }

    /**
     * Gets the value of the commissioniApplicate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCommissioniApplicate() {
        return commissioniApplicate;
    }

    /**
     * Sets the value of the commissioniApplicate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCommissioniApplicate(Double value) {
        this.commissioniApplicate = value;
    }

    /**
     * Gets the value of the commissioniApplicateNull property.
     * 
     */
    public boolean isCommissioniApplicateNull() {
        return commissioniApplicateNull;
    }

    /**
     * Sets the value of the commissioniApplicateNull property.
     * 
     */
    public void setCommissioniApplicateNull(boolean value) {
        this.commissioniApplicateNull = value;
    }

    /**
     * Gets the value of the errcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrcode() {
        return errcode;
    }

    /**
     * Sets the value of the errcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrcode(String value) {
        this.errcode = value;
    }

    /**
     * Gets the value of the finishDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFinishDate() {
        return finishDate;
    }

    /**
     * Sets the value of the finishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinishDate(XMLGregorianCalendar value) {
        this.finishDate = value;
    }

    /**
     * Gets the value of the gatewayId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayId() {
        return gatewayId;
    }

    /**
     * Sets the value of the gatewayId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayId(String value) {
        this.gatewayId = value;
    }

    /**
     * Gets the value of the gatewaypaymodeid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewaypaymodeid() {
        return gatewaypaymodeid;
    }

    /**
     * Sets the value of the gatewaypaymodeid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewaypaymodeid(String value) {
        this.gatewaypaymodeid = value;
    }

    /**
     * Gets the value of the incassokoerrormessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncassokoerrormessage() {
        return incassokoerrormessage;
    }

    /**
     * Sets the value of the incassokoerrormessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncassokoerrormessage(String value) {
        this.incassokoerrormessage = value;
    }

    /**
     * Gets the value of the initDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInitDate() {
        return initDate;
    }

    /**
     * Sets the value of the initDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInitDate(XMLGregorianCalendar value) {
        this.initDate = value;
    }

    /**
     * Gets the value of the intestatariocc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntestatariocc() {
        return intestatariocc;
    }

    /**
     * Sets the value of the intestatariocc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntestatariocc(String value) {
        this.intestatariocc = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the merchantId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * Sets the value of the merchantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantId(String value) {
        this.merchantId = value;
    }

    /**
     * Gets the value of the mscsorderid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMscsorderid() {
        return mscsorderid;
    }

    /**
     * Sets the value of the mscsorderid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMscsorderid(Long value) {
        this.mscsorderid = value;
    }

    /**
     * Gets the value of the mscsorderidNull property.
     * 
     */
    public boolean isMscsorderidNull() {
        return mscsorderidNull;
    }

    /**
     * Sets the value of the mscsorderidNull property.
     * 
     */
    public void setMscsorderidNull(boolean value) {
        this.mscsorderidNull = value;
    }

    /**
     * Gets the value of the oldstate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOldstate() {
        return oldstate;
    }

    /**
     * Sets the value of the oldstate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOldstate(Long value) {
        this.oldstate = value;
    }

    /**
     * Gets the value of the opernumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpernumber() {
        return opernumber;
    }

    /**
     * Sets the value of the opernumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpernumber(String value) {
        this.opernumber = value;
    }

    /**
     * Gets the value of the paymentid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentid() {
        return paymentid;
    }

    /**
     * Sets the value of the paymentid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentid(String value) {
        this.paymentid = value;
    }

    /**
     * Gets the value of the payurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayurl() {
        return payurl;
    }

    /**
     * Sets the value of the payurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayurl(String value) {
        this.payurl = value;
    }

    /**
     * Gets the value of the pgresultcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgresultcode() {
        return pgresultcode;
    }

    /**
     * Sets the value of the pgresultcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgresultcode(String value) {
        this.pgresultcode = value;
    }

    /**
     * Gets the value of the providertimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvidertimestamp() {
        return providertimestamp;
    }

    /**
     * Sets the value of the providertimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvidertimestamp(String value) {
        this.providertimestamp = value;
    }

    /**
     * Gets the value of the rispcomp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRispcomp() {
        return rispcomp;
    }

    /**
     * Sets the value of the rispcomp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRispcomp(String value) {
        this.rispcomp = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the userhaschange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserhaschange() {
        return userhaschange;
    }

    /**
     * Sets the value of the userhaschange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserhaschange(String value) {
        this.userhaschange = value;
    }

}
