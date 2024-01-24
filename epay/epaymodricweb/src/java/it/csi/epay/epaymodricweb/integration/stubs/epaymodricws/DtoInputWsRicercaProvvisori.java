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
 * <p>Java class for dtoInputWsRicercaProvvisori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaProvvisori">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dataA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataDa" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idStatoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsRicercaProvvisori", propOrder = {
    "id",
    "dataA",
    "dataDa",
    "idStatoFlusso",
    "identificativoFlusso"
})
public class DtoInputWsRicercaProvvisori
    extends DtoInputBase
{

    protected Long id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataA;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDa;
    protected String idStatoFlusso;
    protected String identificativoFlusso;

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
     * Gets the value of the dataA property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataA() {
        return dataA;
    }

    /**
     * Sets the value of the dataA property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataA(XMLGregorianCalendar value) {
        this.dataA = value;
    }

    /**
     * Gets the value of the dataDa property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDa() {
        return dataDa;
    }

    /**
     * Sets the value of the dataDa property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDa(XMLGregorianCalendar value) {
        this.dataDa = value;
    }

    /**
     * Gets the value of the idStatoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdStatoFlusso() {
        return idStatoFlusso;
    }

    /**
     * Sets the value of the idStatoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdStatoFlusso(String value) {
        this.idStatoFlusso = value;
    }

    /**
     * Gets the value of the identificativoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    /**
     * Sets the value of the identificativoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoFlusso(String value) {
        this.identificativoFlusso = value;
    }

}
