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
 * <p>Java class for dtoInputWsRicercaElaborazionePrecedente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaElaborazionePrecedente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="utenteElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validitaGenerica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsRicercaElaborazionePrecedente", propOrder = {
    "codiceFiscaleEnte",
    "dataFine",
    "dataInizio",
    "idEnte",
    "statoElaborazione",
    "utenteElaborazione",
    "validitaGenerica"
})
public class DtoInputWsRicercaElaborazionePrecedente
    extends DtoInputBase
{

    protected String codiceFiscaleEnte;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizio;
    protected String idEnte;
    protected String statoElaborazione;
    protected String utenteElaborazione;
    protected String validitaGenerica;

    /**
     * Gets the value of the codiceFiscaleEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    /**
     * Sets the value of the codiceFiscaleEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleEnte(String value) {
        this.codiceFiscaleEnte = value;
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
     * Gets the value of the utenteElaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteElaborazione() {
        return utenteElaborazione;
    }

    /**
     * Sets the value of the utenteElaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteElaborazione(String value) {
        this.utenteElaborazione = value;
    }

    /**
     * Gets the value of the validitaGenerica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValiditaGenerica() {
        return validitaGenerica;
    }

    /**
     * Sets the value of the validitaGenerica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValiditaGenerica(String value) {
        this.validitaGenerica = value;
    }

}
