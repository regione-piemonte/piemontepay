/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaVoceEntrataDisponibile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaVoceEntrataDisponibile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaVoceEntrataDisponibileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaVoceEntrataDisponibileInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaVoceEntrataDisponibile", propOrder = {
    "ricercaVoceEntrataDisponibileInput"
})
public class RicercaVoceEntrataDisponibile {

    protected RicercaVoceEntrataDisponibileInput ricercaVoceEntrataDisponibileInput;

    /**
     * Gets the value of the ricercaVoceEntrataDisponibileInput property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaVoceEntrataDisponibileInput }
     *     
     */
    public RicercaVoceEntrataDisponibileInput getRicercaVoceEntrataDisponibileInput() {
        return ricercaVoceEntrataDisponibileInput;
    }

    /**
     * Sets the value of the ricercaVoceEntrataDisponibileInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaVoceEntrataDisponibileInput }
     *     
     */
    public void setRicercaVoceEntrataDisponibileInput(RicercaVoceEntrataDisponibileInput value) {
        this.ricercaVoceEntrataDisponibileInput = value;
    }

}
