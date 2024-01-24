/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaVoceEntrata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaVoceEntrata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaVoceEntrataInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaVoceEntrataInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaVoceEntrata", propOrder = {
    "ricercaVoceEntrataInput"
})
public class RicercaVoceEntrata {

    protected RicercaVoceEntrataInput ricercaVoceEntrataInput;

    /**
     * Gets the value of the ricercaVoceEntrataInput property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaVoceEntrataInput }
     *     
     */
    public RicercaVoceEntrataInput getRicercaVoceEntrataInput() {
        return ricercaVoceEntrataInput;
    }

    /**
     * Sets the value of the ricercaVoceEntrataInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaVoceEntrataInput }
     *     
     */
    public void setRicercaVoceEntrataInput(RicercaVoceEntrataInput value) {
        this.ricercaVoceEntrataInput = value;
    }

}
