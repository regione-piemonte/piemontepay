/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaRiferimentoContabileResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabileResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaRiferimentoContabileOutput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentoContabileResponse", propOrder = {
    "result"
})
public class RicercaRiferimentoContabileResponse {

    protected RicercaRiferimentoContabileOutput result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaRiferimentoContabileOutput }
     *     
     */
    public RicercaRiferimentoContabileOutput getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaRiferimentoContabileOutput }
     *     
     */
    public void setResult(RicercaRiferimentoContabileOutput value) {
        this.result = value;
    }

}
