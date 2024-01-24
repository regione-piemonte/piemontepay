/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoErroreFlusso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoErroreFlusso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAggiornamentoStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneAggiuntivaErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoErroreFlusso", propOrder = {
    "codiceErrore",
    "dataAggiornamentoStato",
    "descrizioneAggiuntivaErrore",
    "descrizioneErrore",
    "identificativoFlusso",
    "tipologia"
})
public class DtoErroreFlusso {

    protected String codiceErrore;
    protected String dataAggiornamentoStato;
    protected String descrizioneAggiuntivaErrore;
    protected String descrizioneErrore;
    protected String identificativoFlusso;
    protected String tipologia;

    /**
     * Gets the value of the codiceErrore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceErrore() {
        return codiceErrore;
    }

    /**
     * Sets the value of the codiceErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceErrore(String value) {
        this.codiceErrore = value;
    }

    /**
     * Gets the value of the dataAggiornamentoStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataAggiornamentoStato() {
        return dataAggiornamentoStato;
    }

    /**
     * Sets the value of the dataAggiornamentoStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAggiornamentoStato(String value) {
        this.dataAggiornamentoStato = value;
    }

    /**
     * Gets the value of the descrizioneAggiuntivaErrore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneAggiuntivaErrore() {
        return descrizioneAggiuntivaErrore;
    }

    /**
     * Sets the value of the descrizioneAggiuntivaErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneAggiuntivaErrore(String value) {
        this.descrizioneAggiuntivaErrore = value;
    }

    /**
     * Gets the value of the descrizioneErrore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneErrore() {
        return descrizioneErrore;
    }

    /**
     * Sets the value of the descrizioneErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneErrore(String value) {
        this.descrizioneErrore = value;
    }

    /**
     * Gets the value of the identificativoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    /**
     * Sets the value of the identificativoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoFlusso(String value) {
        this.identificativoFlusso = value;
    }

    /**
     * Gets the value of the tipologia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * Sets the value of the tipologia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologia(String value) {
        this.tipologia = value;
    }

}
