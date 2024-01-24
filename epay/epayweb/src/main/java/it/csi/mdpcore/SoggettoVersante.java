/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SoggettoVersante complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SoggettoVersante">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificativoUnivocoVersante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoIdentificativoUnivocoVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="anagraficaVersante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="indirizzoVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="civicoVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="capVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localitaVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinciaVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nazioneVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="e-mailVersante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoVersante", propOrder = {
    "identificativoUnivocoVersante",
    "tipoIdentificativoUnivocoVersante",
    "anagraficaVersante",
    "indirizzoVersante",
    "civicoVersante",
    "capVersante",
    "localitaVersante",
    "provinciaVersante",
    "nazioneVersante",
    "eMailVersante"
})
public class SoggettoVersante {

    @XmlElement(required = true)
    protected String identificativoUnivocoVersante;
    protected String tipoIdentificativoUnivocoVersante;
    @XmlElement(required = true)
    protected String anagraficaVersante;
    protected String indirizzoVersante;
    protected String civicoVersante;
    protected String capVersante;
    protected String localitaVersante;
    protected String provinciaVersante;
    protected String nazioneVersante;
    @XmlElement(name = "e-mailVersante")
    protected String eMailVersante;

    /**
     * Recupera il valore della proprieta' identificativoUnivocoVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoVersante() {
        return identificativoUnivocoVersante;
    }

    /**
     * Imposta il valore della proprieta' identificativoUnivocoVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoVersante(String value) {
        this.identificativoUnivocoVersante = value;
    }

    /**
     * Recupera il valore della proprieta' tipoIdentificativoUnivocoVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoIdentificativoUnivocoVersante() {
        return tipoIdentificativoUnivocoVersante;
    }

    /**
     * Imposta il valore della proprieta' tipoIdentificativoUnivocoVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoIdentificativoUnivocoVersante(String value) {
        this.tipoIdentificativoUnivocoVersante = value;
    }

    /**
     * Recupera il valore della proprieta' anagraficaVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnagraficaVersante() {
        return anagraficaVersante;
    }

    /**
     * Imposta il valore della proprieta' anagraficaVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnagraficaVersante(String value) {
        this.anagraficaVersante = value;
    }

    /**
     * Recupera il valore della proprieta' indirizzoVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoVersante() {
        return indirizzoVersante;
    }

    /**
     * Imposta il valore della proprieta' indirizzoVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoVersante(String value) {
        this.indirizzoVersante = value;
    }

    /**
     * Recupera il valore della proprieta' civicoVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivicoVersante() {
        return civicoVersante;
    }

    /**
     * Imposta il valore della proprieta' civicoVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivicoVersante(String value) {
        this.civicoVersante = value;
    }

    /**
     * Recupera il valore della proprieta' capVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapVersante() {
        return capVersante;
    }

    /**
     * Imposta il valore della proprieta' capVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapVersante(String value) {
        this.capVersante = value;
    }

    /**
     * Recupera il valore della proprieta' localitaVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalitaVersante() {
        return localitaVersante;
    }

    /**
     * Imposta il valore della proprieta' localitaVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalitaVersante(String value) {
        this.localitaVersante = value;
    }

    /**
     * Recupera il valore della proprieta' provinciaVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaVersante() {
        return provinciaVersante;
    }

    /**
     * Imposta il valore della proprieta' provinciaVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaVersante(String value) {
        this.provinciaVersante = value;
    }

    /**
     * Recupera il valore della proprieta' nazioneVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazioneVersante() {
        return nazioneVersante;
    }

    /**
     * Imposta il valore della proprieta' nazioneVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazioneVersante(String value) {
        this.nazioneVersante = value;
    }

    /**
     * Recupera il valore della proprieta' eMailVersante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMailVersante() {
        return eMailVersante;
    }

    /**
     * Imposta il valore della proprieta' eMailVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMailVersante(String value) {
        this.eMailVersante = value;
    }

}
