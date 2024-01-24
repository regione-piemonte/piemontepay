/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AggiornaRichiestaESingoloEsitoInvioRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggiornaRichiestaESingoloEsitoInvioRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatoRichiesta" type="{http://www.csi.it/epay/epaywso/types}StatoRichiestaType" minOccurs="0"/>
 *         &lt;element name="IdApplicativo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DescrizioneApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageStore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodiceEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DettaglioEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataInvioAlDestinatario" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "AggiornaRichiestaESingoloEsitoInvioRequestType", propOrder = {
    "messageUUID",
    "statoRichiesta",
    "idApplicativo",
    "descrizioneApplicativo",
    "messageStore",
    "codiceEsito",
    "dettaglioEsito",
    "dataInvioAlDestinatario",
    "dataCallback",
    "codiceEsitoCallback",
    "dettaglioEsitoCallback",
    "contenutoCallback"
})
public class AggiornaRichiestaESingoloEsitoInvioRequestType {

    @XmlElement(name = "MessageUUID", required = true)
    protected String messageUUID;
    @XmlElement(name = "StatoRichiesta")
    protected StatoRichiestaType statoRichiesta;
    @XmlElementRef(name = "IdApplicativo", namespace = "http://www.csi.it/epay/epaywso/businesstypes", type = JAXBElement.class)
    protected JAXBElement<Integer> idApplicativo;
    @XmlElement(name = "DescrizioneApplicativo")
    protected String descrizioneApplicativo;
    @XmlElement(name = "MessageStore")
    protected String messageStore;
    @XmlElement(name = "CodiceEsito")
    protected String codiceEsito;
    @XmlElement(name = "DettaglioEsito")
    protected String dettaglioEsito;
    @XmlElement(name = "DataInvioAlDestinatario")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInvioAlDestinatario;
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
     * Gets the value of the idApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIdApplicativo() {
        return idApplicativo;
    }

    /**
     * Sets the value of the idApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIdApplicativo(JAXBElement<Integer> value) {
        this.idApplicativo = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the descrizioneApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneApplicativo() {
        return descrizioneApplicativo;
    }

    /**
     * Sets the value of the descrizioneApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneApplicativo(String value) {
        this.descrizioneApplicativo = value;
    }

    /**
     * Gets the value of the messageStore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageStore() {
        return messageStore;
    }

    /**
     * Sets the value of the messageStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageStore(String value) {
        this.messageStore = value;
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
     * Gets the value of the dataInvioAlDestinatario property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInvioAlDestinatario() {
        return dataInvioAlDestinatario;
    }

    /**
     * Sets the value of the dataInvioAlDestinatario property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInvioAlDestinatario(XMLGregorianCalendar value) {
        this.dataInvioAlDestinatario = value;
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
