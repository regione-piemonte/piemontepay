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
 * <p>Java class for dtoFileReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoFileReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenuto" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="dataInizioElaborazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataModifica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nomeFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoFileReport", propOrder = {
    "contenuto",
    "dataInizioElaborazione",
    "dataInserimento",
    "dataModifica",
    "id",
    "nomeFile"
})
public class DtoFileReport {

    protected byte[] contenuto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioElaborazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataModifica;
    protected Long id;
    protected String nomeFile;

    /**
     * Gets the value of the contenuto property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContenuto() {
        return contenuto;
    }

    /**
     * Sets the value of the contenuto property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContenuto(byte[] value) {
        this.contenuto = ((byte[]) value);
    }

    /**
     * Gets the value of the dataInizioElaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioElaborazione() {
        return dataInizioElaborazione;
    }

    /**
     * Sets the value of the dataInizioElaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioElaborazione(XMLGregorianCalendar value) {
        this.dataInizioElaborazione = value;
    }

    /**
     * Gets the value of the dataInserimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimento() {
        return dataInserimento;
    }

    /**
     * Sets the value of the dataInserimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimento(XMLGregorianCalendar value) {
        this.dataInserimento = value;
    }

    /**
     * Gets the value of the dataModifica property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataModifica() {
        return dataModifica;
    }

    /**
     * Sets the value of the dataModifica property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataModifica(XMLGregorianCalendar value) {
        this.dataModifica = value;
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
     * Gets the value of the nomeFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFile() {
        return nomeFile;
    }

    /**
     * Sets the value of the nomeFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFile(String value) {
        this.nomeFile = value;
    }

}
