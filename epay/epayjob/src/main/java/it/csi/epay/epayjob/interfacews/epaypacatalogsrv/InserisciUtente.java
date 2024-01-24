/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciUtente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciUtente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciUtenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciUtente", propOrder = {
    "inserisciUtenteInput"
})
public class InserisciUtente {

    protected InserisciUtenteInput inserisciUtenteInput;

    /**
     * Gets the value of the inserisciUtenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link InserisciUtenteInput }
     *     
     */
    public InserisciUtenteInput getInserisciUtenteInput() {
        return inserisciUtenteInput;
    }

    /**
     * Sets the value of the inserisciUtenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciUtenteInput }
     *     
     */
    public void setInserisciUtenteInput(InserisciUtenteInput value) {
        this.inserisciUtenteInput = value;
    }

}
