/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaUtente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaUtente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaUtenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaUtente", propOrder = {
    "ricercaUtenteInput"
})
public class RicercaUtente {

    protected RicercaUtenteInput ricercaUtenteInput;

    /**
     * Gets the value of the ricercaUtenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaUtenteInput }
     *     
     */
    public RicercaUtenteInput getRicercaUtenteInput() {
        return ricercaUtenteInput;
    }

    /**
     * Sets the value of the ricercaUtenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaUtenteInput }
     *     
     */
    public void setRicercaUtenteInput(RicercaUtenteInput value) {
        this.ricercaUtenteInput = value;
    }

}
