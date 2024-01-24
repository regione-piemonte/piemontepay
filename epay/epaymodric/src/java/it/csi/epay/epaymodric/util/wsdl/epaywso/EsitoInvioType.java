/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EsitoInvioType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EsitoInvioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DescrizioneApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageStore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodiceEsitoInvio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DettaglioEsitoInvio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataEsitoInvio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoInvioType", propOrder = {
    "descrizioneApplicativo",
    "messageStore",
    "codiceEsitoInvio",
    "dettaglioEsitoInvio",
    "dataEsitoInvio"
})
public class EsitoInvioType {

    @XmlElement(name = "DescrizioneApplicativo")
    protected String descrizioneApplicativo;
    @XmlElement(name = "MessageStore")
    protected String messageStore;
    @XmlElement(name = "CodiceEsitoInvio")
    protected String codiceEsitoInvio;
    @XmlElement(name = "DettaglioEsitoInvio")
    protected String dettaglioEsitoInvio;
    @XmlElement(name = "DataEsitoInvio")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEsitoInvio;

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
     * Gets the value of the codiceEsitoInvio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsitoInvio() {
        return codiceEsitoInvio;
    }

    /**
     * Sets the value of the codiceEsitoInvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsitoInvio(String value) {
        this.codiceEsitoInvio = value;
    }

    /**
     * Gets the value of the dettaglioEsitoInvio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDettaglioEsitoInvio() {
        return dettaglioEsitoInvio;
    }

    /**
     * Sets the value of the dettaglioEsitoInvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDettaglioEsitoInvio(String value) {
        this.dettaglioEsitoInvio = value;
    }

    /**
     * Gets the value of the dataEsitoInvio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEsitoInvio() {
        return dataEsitoInvio;
    }

    /**
     * Sets the value of the dataEsitoInvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEsitoInvio(XMLGregorianCalendar value) {
        this.dataEsitoInvio = value;
    }

}
