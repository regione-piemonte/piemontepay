/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eseguiElaborazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eseguiElaborazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="input" type="{http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsEseguiElaborazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eseguiElaborazione", propOrder = {
    "input"
})
public class EseguiElaborazione {

    protected DtoInputWsEseguiElaborazione input;

    /**
     * Gets the value of the input property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsEseguiElaborazione }
     *     
     */
    public DtoInputWsEseguiElaborazione getInput() {
        return input;
    }

    /**
     * Sets the value of the input property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsEseguiElaborazione }
     *     
     */
    public void setInput(DtoInputWsEseguiElaborazione value) {
        this.input = value;
    }

}
