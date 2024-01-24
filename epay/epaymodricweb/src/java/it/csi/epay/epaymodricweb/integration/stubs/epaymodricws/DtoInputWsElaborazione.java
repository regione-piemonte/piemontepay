/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsElaborazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsElaborazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flussiDaElaborare" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="idElaborazionePrecedente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riesecuzione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="statoDaImpostare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="utenteElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsElaborazione", propOrder = {
    "codiceFiscaleEnte",
    "flussiDaElaborare",
    "idElaborazionePrecedente",
    "riesecuzione",
    "statoDaImpostare",
    "utenteElaborazione"
})
public class DtoInputWsElaborazione
    extends DtoInputBase
{

    protected String codiceFiscaleEnte;
    @XmlElement(nillable = true)
    protected List<String> flussiDaElaborare;
    protected String idElaborazionePrecedente;
    protected boolean riesecuzione;
    protected String statoDaImpostare;
    protected String utenteElaborazione;

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
     * Gets the value of the flussiDaElaborare property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flussiDaElaborare property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlussiDaElaborare().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFlussiDaElaborare() {
        if (flussiDaElaborare == null) {
            flussiDaElaborare = new ArrayList<String>();
        }
        return this.flussiDaElaborare;
    }

    /**
     * Gets the value of the idElaborazionePrecedente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdElaborazionePrecedente() {
        return idElaborazionePrecedente;
    }

    /**
     * Sets the value of the idElaborazionePrecedente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdElaborazionePrecedente(String value) {
        this.idElaborazionePrecedente = value;
    }

    /**
     * Gets the value of the riesecuzione property.
     * 
     */
    public boolean isRiesecuzione() {
        return riesecuzione;
    }

    /**
     * Sets the value of the riesecuzione property.
     * 
     */
    public void setRiesecuzione(boolean value) {
        this.riesecuzione = value;
    }

    /**
     * Gets the value of the statoDaImpostare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoDaImpostare() {
        return statoDaImpostare;
    }

    /**
     * Sets the value of the statoDaImpostare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoDaImpostare(String value) {
        this.statoDaImpostare = value;
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

}
