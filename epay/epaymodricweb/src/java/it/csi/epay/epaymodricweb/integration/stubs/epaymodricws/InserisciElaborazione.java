/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciElaborazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciElaborazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciElaborazioneInput" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsElaborazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciElaborazione", propOrder = {
    "inserisciElaborazioneInput"
})
public class InserisciElaborazione {

    protected DtoInputWsElaborazione inserisciElaborazioneInput;

    /**
     * Gets the value of the inserisciElaborazioneInput property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsElaborazione }
     *     
     */
    public DtoInputWsElaborazione getInserisciElaborazioneInput() {
        return inserisciElaborazioneInput;
    }

    /**
     * Sets the value of the inserisciElaborazioneInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsElaborazione }
     *     
     */
    public void setInserisciElaborazioneInput(DtoInputWsElaborazione value) {
        this.inserisciElaborazioneInput = value;
    }

}
