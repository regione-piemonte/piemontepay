/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws.coreCxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for partAnComune complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partAnComune">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DStop" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descComune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descRegione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idComune" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idComuneNext" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idComuneNextNull" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idComunePrev" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idComunePrevNull" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="istatComune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="istatProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="istatRegione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siglaProv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partAnComune", propOrder = {
    "cap",
    "dStart",
    "dStop",
    "descComune",
    "descProvincia",
    "descRegione",
    "idComune",
    "idComuneNext",
    "idComuneNextNull",
    "idComunePrev",
    "idComunePrevNull",
    "istatComune",
    "istatProvincia",
    "istatRegione",
    "rStatus",
    "siglaProv"
})
public class PartAnComune {

    protected String cap;
    @XmlElement(name = "DStart")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dStart;
    @XmlElement(name = "DStop")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dStop;
    protected String descComune;
    protected String descProvincia;
    protected String descRegione;
    protected long idComune;
    protected long idComuneNext;
    protected boolean idComuneNextNull;
    protected long idComunePrev;
    protected boolean idComunePrevNull;
    protected String istatComune;
    protected String istatProvincia;
    protected String istatRegione;
    @XmlElement(name = "RStatus")
    protected String rStatus;
    protected String siglaProv;

    /**
     * Gets the value of the cap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCap() {
        return cap;
    }

    /**
     * Sets the value of the cap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCap(String value) {
        this.cap = value;
    }

    /**
     * Gets the value of the dStart property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDStart() {
        return dStart;
    }

    /**
     * Sets the value of the dStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDStart(XMLGregorianCalendar value) {
        this.dStart = value;
    }

    /**
     * Gets the value of the dStop property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDStop() {
        return dStop;
    }

    /**
     * Sets the value of the dStop property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDStop(XMLGregorianCalendar value) {
        this.dStop = value;
    }

    /**
     * Gets the value of the descComune property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescComune() {
        return descComune;
    }

    /**
     * Sets the value of the descComune property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescComune(String value) {
        this.descComune = value;
    }

    /**
     * Gets the value of the descProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescProvincia() {
        return descProvincia;
    }

    /**
     * Sets the value of the descProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescProvincia(String value) {
        this.descProvincia = value;
    }

    /**
     * Gets the value of the descRegione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRegione() {
        return descRegione;
    }

    /**
     * Sets the value of the descRegione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRegione(String value) {
        this.descRegione = value;
    }

    /**
     * Gets the value of the idComune property.
     * 
     */
    public long getIdComune() {
        return idComune;
    }

    /**
     * Sets the value of the idComune property.
     * 
     */
    public void setIdComune(long value) {
        this.idComune = value;
    }

    /**
     * Gets the value of the idComuneNext property.
     * 
     */
    public long getIdComuneNext() {
        return idComuneNext;
    }

    /**
     * Sets the value of the idComuneNext property.
     * 
     */
    public void setIdComuneNext(long value) {
        this.idComuneNext = value;
    }

    /**
     * Gets the value of the idComuneNextNull property.
     * 
     */
    public boolean isIdComuneNextNull() {
        return idComuneNextNull;
    }

    /**
     * Sets the value of the idComuneNextNull property.
     * 
     */
    public void setIdComuneNextNull(boolean value) {
        this.idComuneNextNull = value;
    }

    /**
     * Gets the value of the idComunePrev property.
     * 
     */
    public long getIdComunePrev() {
        return idComunePrev;
    }

    /**
     * Sets the value of the idComunePrev property.
     * 
     */
    public void setIdComunePrev(long value) {
        this.idComunePrev = value;
    }

    /**
     * Gets the value of the idComunePrevNull property.
     * 
     */
    public boolean isIdComunePrevNull() {
        return idComunePrevNull;
    }

    /**
     * Sets the value of the idComunePrevNull property.
     * 
     */
    public void setIdComunePrevNull(boolean value) {
        this.idComunePrevNull = value;
    }

    /**
     * Gets the value of the istatComune property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIstatComune() {
        return istatComune;
    }

    /**
     * Sets the value of the istatComune property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIstatComune(String value) {
        this.istatComune = value;
    }

    /**
     * Gets the value of the istatProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIstatProvincia() {
        return istatProvincia;
    }

    /**
     * Sets the value of the istatProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIstatProvincia(String value) {
        this.istatProvincia = value;
    }

    /**
     * Gets the value of the istatRegione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIstatRegione() {
        return istatRegione;
    }

    /**
     * Sets the value of the istatRegione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIstatRegione(String value) {
        this.istatRegione = value;
    }

    /**
     * Gets the value of the rStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRStatus() {
        return rStatus;
    }

    /**
     * Sets the value of the rStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRStatus(String value) {
        this.rStatus = value;
    }

    /**
     * Gets the value of the siglaProv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaProv() {
        return siglaProv;
    }

    /**
     * Sets the value of the siglaProv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaProv(String value) {
        this.siglaProv = value;
    }

}
