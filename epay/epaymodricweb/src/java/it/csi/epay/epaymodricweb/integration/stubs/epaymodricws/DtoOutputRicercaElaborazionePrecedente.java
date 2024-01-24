/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtoOutputRicercaElaborazionePrecedente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputRicercaElaborazionePrecedente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataElaborazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idErrore" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="listaFlussi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="utenteIns" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputRicercaElaborazionePrecedente", propOrder = {
    "dataElaborazione",
    "dataFine",
    "dataInizio",
    "esito",
    "id",
    "idEnte",
    "idErrore",
    "listaFlussi",
    "msgErrore",
    "statoElaborazione",
    "utenteIns"
})
public class DtoOutputRicercaElaborazionePrecedente {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataElaborazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizio;
    protected DtoOutputWsEsito esito;
    protected Long id;
    protected String idEnte;
    protected Long idErrore;
    protected String listaFlussi;
    protected String msgErrore;
    protected String statoElaborazione;
    protected String utenteIns;

    /**
     * Gets the value of the dataElaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataElaborazione() {
        return dataElaborazione;
    }

    /**
     * Sets the value of the dataElaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataElaborazione(XMLGregorianCalendar value) {
        this.dataElaborazione = value;
    }

    /**
     * Gets the value of the dataFine property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Sets the value of the dataFine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

    /**
     * Gets the value of the dataInizio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Sets the value of the dataInizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Gets the value of the esito property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public DtoOutputWsEsito getEsito() {
        return esito;
    }

    /**
     * Sets the value of the esito property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public void setEsito(DtoOutputWsEsito value) {
        this.esito = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEnte() {
        return idEnte;
    }

    /**
     * Sets the value of the idEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEnte(String value) {
        this.idEnte = value;
    }

    /**
     * Gets the value of the idErrore property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdErrore() {
        return idErrore;
    }

    /**
     * Sets the value of the idErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdErrore(Long value) {
        this.idErrore = value;
    }

    /**
     * Gets the value of the listaFlussi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListaFlussi() {
        return listaFlussi;
    }

    /**
     * Sets the value of the listaFlussi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListaFlussi(String value) {
        this.listaFlussi = value;
    }

    /**
     * Gets the value of the msgErrore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgErrore() {
        return msgErrore;
    }

    /**
     * Sets the value of the msgErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgErrore(String value) {
        this.msgErrore = value;
    }

    /**
     * Gets the value of the statoElaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoElaborazione() {
        return statoElaborazione;
    }

    /**
     * Sets the value of the statoElaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoElaborazione(String value) {
        this.statoElaborazione = value;
    }

    /**
     * Gets the value of the utenteIns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteIns() {
        return utenteIns;
    }

    /**
     * Sets the value of the utenteIns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteIns(String value) {
        this.utenteIns = value;
    }

}
