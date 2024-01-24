/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoEnte complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoEnte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="denominazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entePlurintermediato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagAccertamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagRicezioneErrori" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagRiconciliazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="giornoSchedulazione" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ibanTesoreria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodicitaSchedulazione" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoEnte", propOrder = {
    "codiceFiscale",
    "denominazione",
    "emailEnte",
    "entePlurintermediato",
    "flagAccertamento",
    "flagRicezioneErrori",
    "flagRiconciliazione",
    "giornoSchedulazione",
    "ibanTesoreria",
    "id",
    "idEnte",
    "periodicitaSchedulazione"
})
public class DtoEnte {

    protected String codiceFiscale;
    protected String denominazione;
    protected String emailEnte;
    protected Boolean entePlurintermediato;
    protected Boolean flagAccertamento;
    protected Boolean flagRicezioneErrori;
    protected Boolean flagRiconciliazione;
    protected int giornoSchedulazione;
    protected String ibanTesoreria;
    protected Long id;
    protected String idEnte;
    protected int periodicitaSchedulazione;

    /**
     * Gets the value of the codiceFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Sets the value of the codiceFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Gets the value of the denominazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Sets the value of the denominazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazione(String value) {
        this.denominazione = value;
    }

    /**
     * Gets the value of the emailEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailEnte() {
        return emailEnte;
    }

    /**
     * Sets the value of the emailEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailEnte(String value) {
        this.emailEnte = value;
    }

    /**
     * Gets the value of the entePlurintermediato property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEntePlurintermediato() {
        return entePlurintermediato;
    }

    /**
     * Sets the value of the entePlurintermediato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEntePlurintermediato(Boolean value) {
        this.entePlurintermediato = value;
    }

    /**
     * Gets the value of the flagAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagAccertamento() {
        return flagAccertamento;
    }

    /**
     * Sets the value of the flagAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagAccertamento(Boolean value) {
        this.flagAccertamento = value;
    }

    /**
     * Gets the value of the flagRicezioneErrori property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagRicezioneErrori() {
        return flagRicezioneErrori;
    }

    /**
     * Sets the value of the flagRicezioneErrori property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagRicezioneErrori(Boolean value) {
        this.flagRicezioneErrori = value;
    }

    /**
     * Gets the value of the flagRiconciliazione property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagRiconciliazione() {
        return flagRiconciliazione;
    }

    /**
     * Sets the value of the flagRiconciliazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagRiconciliazione(Boolean value) {
        this.flagRiconciliazione = value;
    }

    /**
     * Gets the value of the giornoSchedulazione property.
     * 
     */
    public int getGiornoSchedulazione() {
        return giornoSchedulazione;
    }

    /**
     * Sets the value of the giornoSchedulazione property.
     * 
     */
    public void setGiornoSchedulazione(int value) {
        this.giornoSchedulazione = value;
    }

    /**
     * Gets the value of the ibanTesoreria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanTesoreria() {
        return ibanTesoreria;
    }

    /**
     * Sets the value of the ibanTesoreria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanTesoreria(String value) {
        this.ibanTesoreria = value;
    }

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
     * Gets the value of the periodicitaSchedulazione property.
     * 
     */
    public int getPeriodicitaSchedulazione() {
        return periodicitaSchedulazione;
    }

    /**
     * Sets the value of the periodicitaSchedulazione property.
     * 
     */
    public void setPeriodicitaSchedulazione(int value) {
        this.periodicitaSchedulazione = value;
    }

}
