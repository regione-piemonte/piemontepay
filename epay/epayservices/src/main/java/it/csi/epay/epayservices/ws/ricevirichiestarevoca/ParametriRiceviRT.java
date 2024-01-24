/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for parametriRiceviRT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parametriRiceviRT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataOraMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="idMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoFirma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iuv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMsgRichiesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rtData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametriRiceviRT", propOrder = {
    "applicationId",
    "transactionId",
    "dataOraMsgRicevuta",
    "idMsgRicevuta",
    "tipoFirma",
    "iuv",
    "codEsitoPagamento",
    "descEsitoPagamento",
    "idMsgRichiesta",
    "rtData",
    "timestamp",
    "mac"
})
public class ParametriRiceviRT {

    @XmlElement(required = true)
    protected String applicationId;
    @XmlElement(required = true)
    protected String transactionId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataOraMsgRicevuta;
    @XmlElement(required = true)
    protected String idMsgRicevuta;
    @XmlElement(required = true)
    protected String tipoFirma;
    @XmlElement(required = true)
    protected String iuv;
    @XmlElement(required = true)
    protected String codEsitoPagamento;
    @XmlElement(required = true)
    protected String descEsitoPagamento;
    @XmlElement(required = true)
    protected String idMsgRichiesta;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler rtData;
    @XmlElement(required = true)
    protected String timestamp;
    @XmlElement(required = true)
    protected String mac;

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
     * Gets the value of the dataOraMsgRicevuta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraMsgRicevuta() {
        return dataOraMsgRicevuta;
    }

    /**
     * Sets the value of the dataOraMsgRicevuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraMsgRicevuta(XMLGregorianCalendar value) {
        this.dataOraMsgRicevuta = value;
    }

    /**
     * Gets the value of the idMsgRicevuta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMsgRicevuta() {
        return idMsgRicevuta;
    }

    /**
     * Sets the value of the idMsgRicevuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMsgRicevuta(String value) {
        this.idMsgRicevuta = value;
    }

    /**
     * Gets the value of the tipoFirma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoFirma() {
        return tipoFirma;
    }

    /**
     * Sets the value of the tipoFirma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoFirma(String value) {
        this.tipoFirma = value;
    }

    /**
     * Gets the value of the iuv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIuv() {
        return iuv;
    }

    /**
     * Sets the value of the iuv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIuv(String value) {
        this.iuv = value;
    }

    /**
     * Gets the value of the codEsitoPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEsitoPagamento() {
        return codEsitoPagamento;
    }

    /**
     * Sets the value of the codEsitoPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEsitoPagamento(String value) {
        this.codEsitoPagamento = value;
    }

    /**
     * Gets the value of the descEsitoPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEsitoPagamento() {
        return descEsitoPagamento;
    }

    /**
     * Sets the value of the descEsitoPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEsitoPagamento(String value) {
        this.descEsitoPagamento = value;
    }

    /**
     * Gets the value of the idMsgRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMsgRichiesta() {
        return idMsgRichiesta;
    }

    /**
     * Sets the value of the idMsgRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMsgRichiesta(String value) {
        this.idMsgRichiesta = value;
    }

    /**
     * Gets the value of the rtData property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getRtData() {
        return rtData;
    }

    /**
     * Sets the value of the rtData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setRtData(DataHandler value) {
        this.rtData = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the mac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMac() {
        return mac;
    }

    /**
     * Sets the value of the mac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMac(String value) {
        this.mac = value;
    }

}
