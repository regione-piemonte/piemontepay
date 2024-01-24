/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws.coreCxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for appGateway complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="appGateway">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatewayDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatewayId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatewayServiceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchantId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentmodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentmodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sogliaMax" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sogliaMin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tipoCommissione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valoreComm" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="valoreMax" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="valoreMin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appGateway", propOrder = {
    "applicationId",
    "gatewayDescription",
    "gatewayId",
    "gatewayServiceName",
    "merchantId",
    "paymentmodeDescription",
    "paymentmodeId",
    "sogliaMax",
    "sogliaMin",
    "tipoCommissione",
    "valoreComm",
    "valoreMax",
    "valoreMin"
})
public class AppGateway {

    protected String applicationId;
    protected String gatewayDescription;
    protected String gatewayId;
    protected String gatewayServiceName;
    protected String merchantId;
    protected String paymentmodeDescription;
    protected String paymentmodeId;
    protected double sogliaMax;
    protected double sogliaMin;
    protected String tipoCommissione;
    protected double valoreComm;
    protected double valoreMax;
    protected double valoreMin;

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
     * Gets the value of the gatewayDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayDescription() {
        return gatewayDescription;
    }

    /**
     * Sets the value of the gatewayDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayDescription(String value) {
        this.gatewayDescription = value;
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
     * Gets the value of the gatewayServiceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayServiceName() {
        return gatewayServiceName;
    }

    /**
     * Sets the value of the gatewayServiceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayServiceName(String value) {
        this.gatewayServiceName = value;
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
     * Gets the value of the paymentmodeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentmodeDescription() {
        return paymentmodeDescription;
    }

    /**
     * Sets the value of the paymentmodeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentmodeDescription(String value) {
        this.paymentmodeDescription = value;
    }

    /**
     * Gets the value of the paymentmodeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentmodeId() {
        return paymentmodeId;
    }

    /**
     * Sets the value of the paymentmodeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentmodeId(String value) {
        this.paymentmodeId = value;
    }

    /**
     * Gets the value of the sogliaMax property.
     * 
     */
    public double getSogliaMax() {
        return sogliaMax;
    }

    /**
     * Sets the value of the sogliaMax property.
     * 
     */
    public void setSogliaMax(double value) {
        this.sogliaMax = value;
    }

    /**
     * Gets the value of the sogliaMin property.
     * 
     */
    public double getSogliaMin() {
        return sogliaMin;
    }

    /**
     * Sets the value of the sogliaMin property.
     * 
     */
    public void setSogliaMin(double value) {
        this.sogliaMin = value;
    }

    /**
     * Gets the value of the tipoCommissione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCommissione() {
        return tipoCommissione;
    }

    /**
     * Sets the value of the tipoCommissione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCommissione(String value) {
        this.tipoCommissione = value;
    }

    /**
     * Gets the value of the valoreComm property.
     * 
     */
    public double getValoreComm() {
        return valoreComm;
    }

    /**
     * Sets the value of the valoreComm property.
     * 
     */
    public void setValoreComm(double value) {
        this.valoreComm = value;
    }

    /**
     * Gets the value of the valoreMax property.
     * 
     */
    public double getValoreMax() {
        return valoreMax;
    }

    /**
     * Sets the value of the valoreMax property.
     * 
     */
    public void setValoreMax(double value) {
        this.valoreMax = value;
    }

    /**
     * Gets the value of the valoreMin property.
     * 
     */
    public double getValoreMin() {
        return valoreMin;
    }

    /**
     * Sets the value of the valoreMin property.
     * 
     */
    public void setValoreMin(double value) {
        this.valoreMin = value;
    }

}
