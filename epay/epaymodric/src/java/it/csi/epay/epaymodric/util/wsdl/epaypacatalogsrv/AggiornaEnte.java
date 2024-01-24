/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaEnte complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaEnte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modificaEnteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaEnteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaEnte", propOrder = {
    "modificaEnteInput"
})
public class AggiornaEnte {

    protected AggiornaEnteInput modificaEnteInput;

    /**
     * Gets the value of the modificaEnteInput property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaEnteInput }
     *     
     */
    public AggiornaEnteInput getModificaEnteInput() {
        return modificaEnteInput;
    }

    /**
     * Sets the value of the modificaEnteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaEnteInput }
     *     
     */
    public void setModificaEnteInput(AggiornaEnteInput value) {
        this.modificaEnteInput = value;
    }

}
