/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaFlussoOrigine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussoOrigine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaFlussoOrigine" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaFlussoOrigine" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussoOrigine", propOrder = {
    "ricercaFlussoOrigine"
})
public class RicercaFlussoOrigine {

    protected DtoInputWsRicercaFlussoOrigine ricercaFlussoOrigine;

    /**
     * Gets the value of the ricercaFlussoOrigine property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaFlussoOrigine }
     *     
     */
    public DtoInputWsRicercaFlussoOrigine getRicercaFlussoOrigine() {
        return ricercaFlussoOrigine;
    }

    /**
     * Sets the value of the ricercaFlussoOrigine property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaFlussoOrigine }
     *     
     */
    public void setRicercaFlussoOrigine(DtoInputWsRicercaFlussoOrigine value) {
        this.ricercaFlussoOrigine = value;
    }

}
