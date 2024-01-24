/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ricevutaTelematicaNodo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricevutaTelematicaNodo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idMsgRichiesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="insertDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="iuv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rtData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="tipoFirma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricevutaTelematicaNodo", propOrder = {
    "applicationId",
    "dataMsgRicevuta",
    "descEsitoPagamento",
    "id",
    "idEsitoPagamento",
    "idMsgRicevuta",
    "idMsgRichiesta",
    "insertDate",
    "iuv",
    "lastUpdate",
    "rtData",
    "tipoFirma",
    "transactionId"
})
public class RicevutaTelematicaNodo {

    protected String applicationId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMsgRicevuta;
    protected String descEsitoPagamento;
    protected Integer id;
    protected Integer idEsitoPagamento;
    protected String idMsgRicevuta;
    protected String idMsgRichiesta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar insertDate;
    protected String iuv;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    protected byte[] rtData;
    protected String tipoFirma;
    protected String transactionId;

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
     * Recupera il valore della proprieta' dataMsgRicevuta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMsgRicevuta() {
        return dataMsgRicevuta;
    }

    /**
     * Imposta il valore della proprieta' dataMsgRicevuta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMsgRicevuta(XMLGregorianCalendar value) {
        this.dataMsgRicevuta = value;
    }

    /**
     * Recupera il valore della proprieta' descEsitoPagamento.
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
     * Imposta il valore della proprieta' descEsitoPagamento.
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
     * Recupera il valore della proprieta' id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta il valore della proprieta' id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprieta' idEsitoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdEsitoPagamento() {
        return idEsitoPagamento;
    }

    /**
     * Imposta il valore della proprieta' idEsitoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdEsitoPagamento(Integer value) {
        this.idEsitoPagamento = value;
    }

    /**
     * Recupera il valore della proprieta' idMsgRicevuta.
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
     * Imposta il valore della proprieta' idMsgRicevuta.
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
     * Recupera il valore della proprieta' idMsgRichiesta.
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
     * Imposta il valore della proprieta' idMsgRichiesta.
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
     * Recupera il valore della proprieta' insertDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsertDate() {
        return insertDate;
    }

    /**
     * Imposta il valore della proprieta' insertDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsertDate(XMLGregorianCalendar value) {
        this.insertDate = value;
    }

    /**
     * Recupera il valore della proprieta' iuv.
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
     * Imposta il valore della proprieta' iuv.
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
     * Recupera il valore della proprieta' lastUpdate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Imposta il valore della proprieta' lastUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    /**
     * Recupera il valore della proprieta' rtData.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getRtData() {
        return rtData;
    }

    /**
     * Imposta il valore della proprieta' rtData.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setRtData(byte[] value) {
        this.rtData = value;
    }

    /**
     * Recupera il valore della proprieta' tipoFirma.
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
     * Imposta il valore della proprieta' tipoFirma.
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

}
