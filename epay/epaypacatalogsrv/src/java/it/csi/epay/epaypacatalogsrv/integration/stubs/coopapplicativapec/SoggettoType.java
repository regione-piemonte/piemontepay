/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SoggettoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SoggettoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="PersonaFisica" type="{http://www.csi.it/epay/epaywso/types}PersonaFisicaType"/&gt;
 *           &lt;element name="PersonaGiuridica" type="{http://www.csi.it/epay/epaywso/types}PersonaGiuridicaType"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="IdentificativoUnivocoFiscale" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="Indirizzo" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/&gt;
 *         &lt;element name="Civico" type="{http://www.csi.it/epay/epaywso/types}String16Type" minOccurs="0"/&gt;
 *         &lt;element name="CAP" type="{http://www.csi.it/epay/epaywso/types}String16Type" minOccurs="0"/&gt;
 *         &lt;element name="Localita" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/&gt;
 *         &lt;element name="Provincia" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/&gt;
 *         &lt;element name="Nazione" type="{http://www.csi.it/epay/epaywso/types}NazioneType" minOccurs="0"/&gt;
 *         &lt;element name="EMail" type="{http://www.csi.it/epay/epaywso/types}EMailAddress" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Recupera il valore della proprietapersonaFisica.
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
     * Imposta il valore della proprietapersonaFisica.
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
     * Recupera il valore della proprietapersonaGiuridica.
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
     * Imposta il valore della proprietapersonaGiuridica.
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
     * Recupera il valore della proprietaidentificativoUnivocoFiscale.
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
     * Imposta il valore della proprietaidentificativoUnivocoFiscale.
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
     * Recupera il valore della proprietaindirizzo.
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
     * Imposta il valore della proprietaindirizzo.
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
     * Recupera il valore della proprietacivico.
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
     * Imposta il valore della proprietacivico.
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
     * Recupera il valore della proprietacap.
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
     * Imposta il valore della proprietacap.
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
     * Recupera il valore della proprietalocalita.
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
     * Imposta il valore della proprietalocalita.
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
     * Recupera il valore della proprietaprovincia.
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
     * Imposta il valore della proprietaprovincia.
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
     * Recupera il valore della proprietanazione.
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
     * Imposta il valore della proprietanazione.
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
     * Recupera il valore della proprietaeMail.
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
     * Imposta il valore della proprietaeMail.
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
