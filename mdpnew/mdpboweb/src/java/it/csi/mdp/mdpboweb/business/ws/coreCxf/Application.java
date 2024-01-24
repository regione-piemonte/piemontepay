/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws.coreCxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for application complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="application">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esercemail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroverde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="progetto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redirectNewmdp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="referentecsi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validoAl" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="validoDal" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "application", propOrder = {
    "applicationname",
    "cliente",
    "esercemail",
    "id",
    "note",
    "numeroverde",
    "progetto",
    "redirectNewmdp",
    "referentecsi",
    "validoAl",
    "validoDal"
})
public class Application {

    protected String applicationname;
    protected String cliente;
    protected String esercemail;
    protected String id;
    protected String note;
    protected String numeroverde;
    protected String progetto;
    protected long redirectNewmdp;
    protected String referentecsi;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validoAl;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validoDal;

    /**
     * Gets the value of the applicationname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationname() {
        return applicationname;
    }

    /**
     * Sets the value of the applicationname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationname(String value) {
        this.applicationname = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCliente(String value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the esercemail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsercemail() {
        return esercemail;
    }

    /**
     * Sets the value of the esercemail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsercemail(String value) {
        this.esercemail = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the numeroverde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroverde() {
        return numeroverde;
    }

    /**
     * Sets the value of the numeroverde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroverde(String value) {
        this.numeroverde = value;
    }

    /**
     * Gets the value of the progetto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgetto() {
        return progetto;
    }

    /**
     * Sets the value of the progetto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgetto(String value) {
        this.progetto = value;
    }

    /**
     * Gets the value of the redirectNewmdp property.
     * 
     */
    public long getRedirectNewmdp() {
        return redirectNewmdp;
    }

    /**
     * Sets the value of the redirectNewmdp property.
     * 
     */
    public void setRedirectNewmdp(long value) {
        this.redirectNewmdp = value;
    }

    /**
     * Gets the value of the referentecsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferentecsi() {
        return referentecsi;
    }

    /**
     * Sets the value of the referentecsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferentecsi(String value) {
        this.referentecsi = value;
    }

    /**
     * Gets the value of the validoAl property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidoAl() {
        return validoAl;
    }

    /**
     * Sets the value of the validoAl property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidoAl(XMLGregorianCalendar value) {
        this.validoAl = value;
    }

    /**
     * Gets the value of the validoDal property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidoDal() {
        return validoDal;
    }

    /**
     * Sets the value of the validoDal property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidoDal(XMLGregorianCalendar value) {
        this.validoDal = value;
    }

}
