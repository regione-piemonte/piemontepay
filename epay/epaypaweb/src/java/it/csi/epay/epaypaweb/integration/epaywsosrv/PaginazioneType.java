/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaginazioneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaginazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroDiPagina" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumeroRighePerPagina" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaginazioneType", propOrder = {
    "numeroDiPagina",
    "numeroRighePerPagina"
})
public class PaginazioneType {

    @XmlElement(name = "NumeroDiPagina", required = true, type = Integer.class, nillable = true)
    protected Integer numeroDiPagina;
    @XmlElement(name = "NumeroRighePerPagina", required = true, type = Integer.class, nillable = true)
    protected Integer numeroRighePerPagina;

    /**
     * Gets the value of the numeroDiPagina property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroDiPagina() {
        return numeroDiPagina;
    }

    /**
     * Sets the value of the numeroDiPagina property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroDiPagina(Integer value) {
        this.numeroDiPagina = value;
    }

    /**
     * Gets the value of the numeroRighePerPagina property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroRighePerPagina() {
        return numeroRighePerPagina;
    }

    /**
     * Sets the value of the numeroRighePerPagina property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroRighePerPagina(Integer value) {
        this.numeroRighePerPagina = value;
    }

}
