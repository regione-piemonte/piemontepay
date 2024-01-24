/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per Transazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * Recupera il valore della proprieta' amount.
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
     * Imposta il valore della proprieta' amount.
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
     * Recupera il valore della proprieta' applicationId.
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
     * Imposta il valore della proprieta' applicationId.
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
     * Recupera il valore della proprieta' authornumber.
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
     * Imposta il valore della proprieta' authornumber.
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
     * Recupera il valore della proprieta' basketId.
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
     * Imposta il valore della proprieta' basketId.
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
     * Recupera il valore della proprieta' buyerEmail.
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
     * Imposta il valore della proprieta' buyerEmail.
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
     * Recupera il valore della proprieta' buyercodfisc.
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
     * Imposta il valore della proprieta' buyercodfisc.
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
     * Recupera il valore della proprieta' buyername.
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
     * Imposta il valore della proprieta' buyername.
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
     * Recupera il valore della proprieta' ccy.
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
     * Imposta il valore della proprieta' ccy.
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
     * Recupera il valore della proprieta' changestatedate.
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
     * Imposta il valore della proprieta' changestatedate.
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
     * Recupera il valore della proprieta' clientipaddress.
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
     * Imposta il valore della proprieta' clientipaddress.
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
     * Recupera il valore della proprieta' codStato.
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
     * Imposta il valore della proprieta' codStato.
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
     * Recupera il valore della proprieta' commissioniApplicate.
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
     * Imposta il valore della proprieta' commissioniApplicate.
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
     * Recupera il valore della proprieta' commissioniApplicateNull.
     * 
     */
    public boolean isCommissioniApplicateNull() {
        return commissioniApplicateNull;
    }

    /**
     * Imposta il valore della proprieta' commissioniApplicateNull.
     * 
     */
    public void setCommissioniApplicateNull(boolean value) {
        this.commissioniApplicateNull = value;
    }

    /**
     * Recupera il valore della proprieta' errcode.
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
     * Imposta il valore della proprieta' errcode.
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
     * Recupera il valore della proprieta' finishDate.
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
     * Imposta il valore della proprieta' finishDate.
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
     * Recupera il valore della proprieta' gatewayId.
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
     * Imposta il valore della proprieta' gatewayId.
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
     * Recupera il valore della proprieta' gatewaypaymodeid.
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
     * Imposta il valore della proprieta' gatewaypaymodeid.
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
     * Recupera il valore della proprieta' incassokoerrormessage.
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
     * Imposta il valore della proprieta' incassokoerrormessage.
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
     * Recupera il valore della proprieta' initDate.
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
     * Imposta il valore della proprieta' initDate.
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
     * Recupera il valore della proprieta' intestatariocc.
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
     * Imposta il valore della proprieta' intestatariocc.
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
     * Recupera il valore della proprieta' language.
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
     * Imposta il valore della proprieta' language.
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
     * Recupera il valore della proprieta' merchantId.
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
     * Imposta il valore della proprieta' merchantId.
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
     * Recupera il valore della proprieta' mscsorderid.
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
     * Imposta il valore della proprieta' mscsorderid.
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
     * Recupera il valore della proprieta' mscsorderidNull.
     * 
     */
    public boolean isMscsorderidNull() {
        return mscsorderidNull;
    }

    /**
     * Imposta il valore della proprieta' mscsorderidNull.
     * 
     */
    public void setMscsorderidNull(boolean value) {
        this.mscsorderidNull = value;
    }

    /**
     * Recupera il valore della proprieta' oldstate.
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
     * Imposta il valore della proprieta' oldstate.
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
     * Recupera il valore della proprieta' opernumber.
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
     * Imposta il valore della proprieta' opernumber.
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
     * Recupera il valore della proprieta' paymentid.
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
     * Imposta il valore della proprieta' paymentid.
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
     * Recupera il valore della proprieta' payurl.
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
     * Imposta il valore della proprieta' payurl.
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
     * Recupera il valore della proprieta' pgresultcode.
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
     * Imposta il valore della proprieta' pgresultcode.
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
     * Recupera il valore della proprieta' providertimestamp.
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
     * Imposta il valore della proprieta' providertimestamp.
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
     * Recupera il valore della proprieta' rispcomp.
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
     * Imposta il valore della proprieta' rispcomp.
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
     * Recupera il valore della proprieta' startDate.
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
     * Imposta il valore della proprieta' startDate.
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
     * Recupera il valore della proprieta' transactionId.
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
     * Imposta il valore della proprieta' transactionId.
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
     * Recupera il valore della proprieta' userhaschange.
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
     * Imposta il valore della proprieta' userhaschange.
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
