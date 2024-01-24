/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtoInputDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputDate">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="dataElaborazioneA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataElaborazioneDa" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRicezioneA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRicezioneDa" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputDate", propOrder = {
    "dataElaborazioneA",
    "dataElaborazioneDa",
    "dataRicezioneA",
    "dataRicezioneDa"
})
@XmlSeeAlso({
    DtoInputWsRicercaPrenotazioneReport.class,
    DtoInputWsRicercaFlussoOrigine.class,
    DtoInputWsInserisciPrenotazioneReport.class
})
public class DtoInputDate
    extends DtoInputBase
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataElaborazioneA;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataElaborazioneDa;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRicezioneA;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRicezioneDa;

    /**
     * Gets the value of the dataElaborazioneA property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataElaborazioneA() {
        return dataElaborazioneA;
    }

    /**
     * Sets the value of the dataElaborazioneA property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataElaborazioneA(XMLGregorianCalendar value) {
        this.dataElaborazioneA = value;
    }

    /**
     * Gets the value of the dataElaborazioneDa property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataElaborazioneDa() {
        return dataElaborazioneDa;
    }

    /**
     * Sets the value of the dataElaborazioneDa property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataElaborazioneDa(XMLGregorianCalendar value) {
        this.dataElaborazioneDa = value;
    }

    /**
     * Gets the value of the dataRicezioneA property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRicezioneA() {
        return dataRicezioneA;
    }

    /**
     * Sets the value of the dataRicezioneA property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRicezioneA(XMLGregorianCalendar value) {
        this.dataRicezioneA = value;
    }

    /**
     * Gets the value of the dataRicezioneDa property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRicezioneDa() {
        return dataRicezioneDa;
    }

    /**
     * Sets the value of the dataRicezioneDa property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRicezioneDa(XMLGregorianCalendar value) {
        this.dataRicezioneDa = value;
    }

}
