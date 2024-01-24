/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AggiornaRichiestaRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggiornaRichiestaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatoRichiesta" type="{http://www.csi.it/epay/epaywso/types}StatoRichiestaType" minOccurs="0"/>
 *         &lt;element name="CodiceEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DettaglioEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataCallback" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CodiceEsitoCallback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DettaglioEsitoCallback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContenutoCallback" type="{http://www.csi.it/epay/epaywso/businesstypes}EmbeddedXMLType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggiornaRichiestaRequestType", propOrder = {
    "messageUUID",
    "statoRichiesta",
    "codiceEsito",
    "dettaglioEsito",
    "dataCallback",
    "codiceEsitoCallback",
    "dettaglioEsitoCallback",
    "contenutoCallback"
})
public class AggiornaRichiestaRequestType {

    @XmlElement(name = "MessageUUID", required = true)
    protected String messageUUID;
    @XmlElement(name = "StatoRichiesta")
    protected StatoRichiestaType statoRichiesta;
    @XmlElement(name = "CodiceEsito")
    protected String codiceEsito;
    @XmlElement(name = "DettaglioEsito")
    protected String dettaglioEsito;
    @XmlElement(name = "DataCallback")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCallback;
    @XmlElement(name = "CodiceEsitoCallback")
    protected String codiceEsitoCallback;
    @XmlElement(name = "DettaglioEsitoCallback")
    protected String dettaglioEsitoCallback;
    @XmlElement(name = "ContenutoCallback")
    protected EmbeddedXMLType contenutoCallback;

    /**
     * Gets the value of the messageUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageUUID() {
        return messageUUID;
    }

    /**
     * Sets the value of the messageUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageUUID(String value) {
        this.messageUUID = value;
    }

    /**
     * Gets the value of the statoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link StatoRichiestaType }
     *     
     */
    public StatoRichiestaType getStatoRichiesta() {
        return statoRichiesta;
    }

    /**
     * Sets the value of the statoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoRichiestaType }
     *     
     */
    public void setStatoRichiesta(StatoRichiestaType value) {
        this.statoRichiesta = value;
    }

    /**
     * Gets the value of the codiceEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Sets the value of the codiceEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsito(String value) {
        this.codiceEsito = value;
    }

    /**
     * Gets the value of the dettaglioEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDettaglioEsito() {
        return dettaglioEsito;
    }

    /**
     * Sets the value of the dettaglioEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDettaglioEsito(String value) {
        this.dettaglioEsito = value;
    }

    /**
     * Gets the value of the dataCallback property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCallback() {
        return dataCallback;
    }

    /**
     * Sets the value of the dataCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCallback(XMLGregorianCalendar value) {
        this.dataCallback = value;
    }

    /**
     * Gets the value of the codiceEsitoCallback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsitoCallback() {
        return codiceEsitoCallback;
    }

    /**
     * Sets the value of the codiceEsitoCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsitoCallback(String value) {
        this.codiceEsitoCallback = value;
    }

    /**
     * Gets the value of the dettaglioEsitoCallback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDettaglioEsitoCallback() {
        return dettaglioEsitoCallback;
    }

    /**
     * Sets the value of the dettaglioEsitoCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDettaglioEsitoCallback(String value) {
        this.dettaglioEsitoCallback = value;
    }

    /**
     * Gets the value of the contenutoCallback property.
     * 
     * @return
     *     possible object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public EmbeddedXMLType getContenutoCallback() {
        return contenutoCallback;
    }

    /**
     * Sets the value of the contenutoCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public void setContenutoCallback(EmbeddedXMLType value) {
        this.contenutoCallback = value;
    }

}
