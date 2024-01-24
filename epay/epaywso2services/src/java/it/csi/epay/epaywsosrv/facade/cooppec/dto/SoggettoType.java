/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaywsosrv.facade.cooppec.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SoggettoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SoggettoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="PersonaFisica" type="{http://www.csi.it/epay/epaywso/types}PersonaFisicaType"/>
 *           &lt;element name="PersonaGiuridica" type="{http://www.csi.it/epay/epaywso/types}PersonaGiuridicaType"/>
 *         &lt;/choice>
 *         &lt;element name="IdentificativoUnivocoFiscale" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="Indirizzo" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/>
 *         &lt;element name="Civico" type="{http://www.csi.it/epay/epaywso/types}String16Type" minOccurs="0"/>
 *         &lt;element name="CAP" type="{http://www.csi.it/epay/epaywso/types}String16Type" minOccurs="0"/>
 *         &lt;element name="Localita" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/>
 *         &lt;element name="Provincia" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/>
 *         &lt;element name="Nazione" type="{http://www.csi.it/epay/epaywso/types}NazioneType" minOccurs="0"/>
 *         &lt;element name="EMail" type="{http://www.csi.it/epay/epaywso/types}EMailAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoType", namespace = "http://www.csi.it/epay/epaywso/types", propOrder = {
    "personaFisica",
    "personaGiuridica",
    "identificativoUnivocoFiscale",
    "indirizzo",
    "civico",
    "cap",
    "localita",
    "provincia",
    "nazione",
    "eMail"
})
public class SoggettoType {

    @XmlElement(name = "PersonaFisica")
    protected PersonaFisicaType personaFisica;
    @XmlElement(name = "PersonaGiuridica")
    protected PersonaGiuridicaType personaGiuridica;
    @XmlElement(name = "IdentificativoUnivocoFiscale", required = true)
    protected String identificativoUnivocoFiscale;
    @XmlElement(name = "Indirizzo")
    protected String indirizzo;
    @XmlElement(name = "Civico")
    protected String civico;
    @XmlElement(name = "CAP")
    protected String cap;
    @XmlElement(name = "Localita")
    protected String localita;
    @XmlElement(name = "Provincia")
    protected String provincia;
    @XmlElement(name = "Nazione")
    protected String nazione;
    @XmlElement(name = "EMail")
    protected String eMail;

    /**
     * Gets the value of the personaFisica property.
     * 
     * @return
     *     possible object is
     *     {@link PersonaFisicaType }
     *     
     */
    public PersonaFisicaType getPersonaFisica() {
        return personaFisica;
    }

    /**
     * Sets the value of the personaFisica property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaFisicaType }
     *     
     */
    public void setPersonaFisica(PersonaFisicaType value) {
        this.personaFisica = value;
    }

    /**
     * Gets the value of the personaGiuridica property.
     * 
     * @return
     *     possible object is
     *     {@link PersonaGiuridicaType }
     *     
     */
    public PersonaGiuridicaType getPersonaGiuridica() {
        return personaGiuridica;
    }

    /**
     * Sets the value of the personaGiuridica property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaGiuridicaType }
     *     
     */
    public void setPersonaGiuridica(PersonaGiuridicaType value) {
        this.personaGiuridica = value;
    }

    /**
     * Gets the value of the identificativoUnivocoFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoFiscale() {
        return identificativoUnivocoFiscale;
    }

    /**
     * Sets the value of the identificativoUnivocoFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoFiscale(String value) {
        this.identificativoUnivocoFiscale = value;
    }

    /**
     * Gets the value of the indirizzo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Sets the value of the indirizzo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzo(String value) {
        this.indirizzo = value;
    }

    /**
     * Gets the value of the civico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivico() {
        return civico;
    }

    /**
     * Sets the value of the civico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivico(String value) {
        this.civico = value;
    }

    /**
     * Gets the value of the cap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAP() {
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
    public void setCAP(String value) {
        this.cap = value;
    }

    /**
     * Gets the value of the localita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalita() {
        return localita;
    }

    /**
     * Sets the value of the localita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalita(String value) {
        this.localita = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the nazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * Sets the value of the nazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazione(String value) {
        this.nazione = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

}
