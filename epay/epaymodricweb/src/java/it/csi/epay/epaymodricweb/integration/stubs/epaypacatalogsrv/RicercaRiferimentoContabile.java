/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaRiferimentoContabile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaRiferimentoContabileInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentoContabile", propOrder = {
    "ricercaRiferimentoContabileInput"
})
public class RicercaRiferimentoContabile {

    protected RicercaRiferimentoContabileInput ricercaRiferimentoContabileInput;

    /**
     * Gets the value of the ricercaRiferimentoContabileInput property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaRiferimentoContabileInput }
     *     
     */
    public RicercaRiferimentoContabileInput getRicercaRiferimentoContabileInput() {
        return ricercaRiferimentoContabileInput;
    }

    /**
     * Sets the value of the ricercaRiferimentoContabileInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaRiferimentoContabileInput }
     *     
     */
    public void setRicercaRiferimentoContabileInput(RicercaRiferimentoContabileInput value) {
        this.ricercaRiferimentoContabileInput = value;
    }

}
