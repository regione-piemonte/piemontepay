/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for parametriRiceviEsito complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="parametriRiceviEsito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataOraMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataEsitoSingoloPagamento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="idMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoFirma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iuv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idMsgRichiesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rtData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identificativoUnivocoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importoPagato" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="elencoParametriAggiuntivi" type="{http://serviziofruitore.interfacews.mdp.nodospc.csi.it/}ChiaveValore" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rtPresente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="identificativoPSP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="denominazionePSP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametriRiceviEsito", propOrder = {
    "applicationId",
    "transactionId",
    "dataOraMsgRicevuta",
    "dataEsitoSingoloPagamento",
    "idMsgRicevuta",
    "tipoFirma",
    "iuv",
    "codEsitoPagamento",
    "descEsitoPagamento",
    "idMsgRichiesta",
    "rtData",
    "timestamp",
    "mac",
    "identificativoUnivocoRiscossione",
    "importoPagato",
    "elencoParametriAggiuntivi",
    "rtPresente",
    "identificativoPSP",
    "denominazionePSP"
})
public class ParametriRiceviEsito {

    @XmlElement(required = true)
    protected String applicationId;
    protected String transactionId;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataOraMsgRicevuta;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataEsitoSingoloPagamento;
    @XmlElement(required = true)
    protected String idMsgRicevuta;
    protected String tipoFirma;
    @XmlElement(required = true)
    protected String iuv;
    @XmlElement(required = true)
    protected String codEsitoPagamento;
    protected String descEsitoPagamento;
    protected String idMsgRichiesta;
    @XmlMimeType("application/octet-stream")
    protected DataHandler rtData;
    @XmlElement(required = true)
    protected String timestamp;
    @XmlElement(required = true)
    protected String mac;
    @XmlElement(required = true)
    protected String identificativoUnivocoRiscossione;
    @XmlElement(required = true)
    protected BigDecimal importoPagato;
    protected List<ChiaveValore> elencoParametriAggiuntivi;
    protected boolean rtPresente;
    @XmlElement(required = true)
    protected String identificativoPSP;
    @XmlElement(required = true)
    protected String denominazionePSP;

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
     * Gets the value of the dataEsitoSingoloPagamento property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataEsitoSingoloPagamento() {
        return dataEsitoSingoloPagamento;
    }

    /**
     * Sets the value of the dataEsitoSingoloPagamento property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDataEsitoSingoloPagamento(XMLGregorianCalendar value) {
        this.dataEsitoSingoloPagamento = value;
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

    public void setElencoParametriAggiuntivi ( List<ChiaveValore> elencoParametriAggiuntivi ) {
        this.elencoParametriAggiuntivi = elencoParametriAggiuntivi;
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

    /**
     * Gets the value of the identificativoUnivocoRiscossione property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdentificativoUnivocoRiscossione() {
        return identificativoUnivocoRiscossione;
    }

    /**
     * Sets the value of the identificativoUnivocoRiscossione property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdentificativoUnivocoRiscossione(String value) {
        this.identificativoUnivocoRiscossione = value;
    }

    /**
     * Gets the value of the importoPagato property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getImportoPagato() {
        return importoPagato;
    }

    /**
     * Sets the value of the importoPagato property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setImportoPagato(BigDecimal value) {
        this.importoPagato = value;
    }

    /**
     * Gets the value of the elencoParametriAggiuntivi property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elencoParametriAggiuntivi property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElencoParametriAggiuntivi().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChiaveValore }
     *
     *
     */
    public List<ChiaveValore> getElencoParametriAggiuntivi() {
        if (elencoParametriAggiuntivi == null) {
            elencoParametriAggiuntivi = new ArrayList<ChiaveValore>();
        }
        return this.elencoParametriAggiuntivi;
    }

    /**
     * Gets the value of the rtPresente property.
     *
     */
    public boolean isRtPresente() {
        return rtPresente;
    }

    /**
     * Sets the value of the rtPresente property.
     *
     */
    public void setRtPresente(boolean value) {
        this.rtPresente = value;
    }

    /**
     * Gets the value of the identificativoPSP property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdentificativoPSP() {
        return identificativoPSP;
    }

    /**
     * Sets the value of the identificativoPSP property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdentificativoPSP(String value) {
        this.identificativoPSP = value;
    }

    /**
     * Gets the value of the denominazionePSP property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDenominazionePSP() {
        return denominazionePSP;
    }

    /**
     * Sets the value of the denominazionePSP property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDenominazionePSP(String value) {
        this.denominazionePSP = value;
    }

}
