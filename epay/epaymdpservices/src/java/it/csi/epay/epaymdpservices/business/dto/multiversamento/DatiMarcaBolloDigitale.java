/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.24 at 11:40:10 AM CEST 
//


package it.csi.epay.epaymdpservices.business.dto.multiversamento;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatiMarcaBolloDigitale complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiMarcaBolloDigitale">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoBollo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hashDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provinciaResidenza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiMarcaBolloDigitale", propOrder = {
    "tipoBollo",
    "hashDocumento",
    "provinciaResidenza"
})
public class DatiMarcaBolloDigitale implements Serializable {

    private static final long serialVersionUID = 2906849832735320L;
   
    @XmlElement(required = true)
    protected String tipoBollo;
    @XmlElement(required = true)
    protected String hashDocumento;
    @XmlElement(required = true)
    protected String provinciaResidenza;

    public DatiMarcaBolloDigitale()
    {
    }   
    
    /**
     * Gets the value of the tipoBollo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoBollo() {
        return tipoBollo;
    }

    /**
     * Sets the value of the tipoBollo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoBollo(String value) {
        this.tipoBollo = value;
    }

    /**
     * Gets the value of the hashDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashDocumento() {
        return hashDocumento;
    }

    /**
     * Sets the value of the hashDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashDocumento(String value) {
        this.hashDocumento = value;
    }

    /**
     * Gets the value of the provinciaResidenza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }

    /**
     * Sets the value of the provinciaResidenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaResidenza(String value) {
        this.provinciaResidenza = value;
    }

}
