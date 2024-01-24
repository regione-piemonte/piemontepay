/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.xsd.trasmissioneflussi;

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
@XmlType(name = "SoggettoType", propOrder = {
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
     * Recupera il valore della proprieta' personaFisica.
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
     * Imposta il valore della proprieta' personaFisica.
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
     * Recupera il valore della proprieta' personaGiuridica.
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
     * Imposta il valore della proprieta' personaGiuridica.
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
     * Recupera il valore della proprieta' identificativoUnivocoFiscale.
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
     * Imposta il valore della proprieta' identificativoUnivocoFiscale.
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
     * Recupera il valore della proprieta' indirizzo.
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
     * Imposta il valore della proprieta' indirizzo.
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
     * Recupera il valore della proprieta' civico.
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
     * Imposta il valore della proprieta' civico.
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
     * Recupera il valore della proprieta' cap.
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
     * Imposta il valore della proprieta' cap.
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
     * Recupera il valore della proprieta' localita.
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
     * Imposta il valore della proprieta' localita.
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
     * Recupera il valore della proprieta' provincia.
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
     * Imposta il valore della proprieta' provincia.
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
     * Recupera il valore della proprieta' nazione.
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
     * Imposta il valore della proprieta' nazione.
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
     * Recupera il valore della proprieta' eMail.
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
     * Imposta il valore della proprieta' eMail.
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
