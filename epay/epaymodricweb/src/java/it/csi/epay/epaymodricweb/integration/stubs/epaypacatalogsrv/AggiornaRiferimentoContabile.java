/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaRiferimentoContabile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaRiferimentoContabile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaRiferimentoContabileInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaRiferimentoContabile", propOrder = {
    "aggiornaRiferimentoContabileInput"
})
public class AggiornaRiferimentoContabile {

    protected AggiornaRiferimentoContabileInput aggiornaRiferimentoContabileInput;

    /**
     * Gets the value of the aggiornaRiferimentoContabileInput property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaRiferimentoContabileInput }
     *     
     */
    public AggiornaRiferimentoContabileInput getAggiornaRiferimentoContabileInput() {
        return aggiornaRiferimentoContabileInput;
    }

    /**
     * Sets the value of the aggiornaRiferimentoContabileInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaRiferimentoContabileInput }
     *     
     */
    public void setAggiornaRiferimentoContabileInput(AggiornaRiferimentoContabileInput value) {
        this.aggiornaRiferimentoContabileInput = value;
    }

}
