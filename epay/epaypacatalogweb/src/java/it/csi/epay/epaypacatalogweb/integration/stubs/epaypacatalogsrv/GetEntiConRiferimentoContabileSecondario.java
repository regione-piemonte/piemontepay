/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEntiConRiferimentoContabileSecondario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEntiConRiferimentoContabileSecondario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getEntiConRiferimentoContabileSecondarioInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEntiConRiferimentoContabileSecondarioInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntiConRiferimentoContabileSecondario", propOrder = {
    "getEntiConRiferimentoContabileSecondarioInput"
})
public class GetEntiConRiferimentoContabileSecondario {

    protected GetEntiConRiferimentoContabileSecondarioInput getEntiConRiferimentoContabileSecondarioInput;

    /**
     * Gets the value of the getEntiConRiferimentoContabileSecondarioInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetEntiConRiferimentoContabileSecondarioInput }
     *     
     */
    public GetEntiConRiferimentoContabileSecondarioInput getGetEntiConRiferimentoContabileSecondarioInput() {
        return getEntiConRiferimentoContabileSecondarioInput;
    }

    /**
     * Sets the value of the getEntiConRiferimentoContabileSecondarioInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEntiConRiferimentoContabileSecondarioInput }
     *     
     */
    public void setGetEntiConRiferimentoContabileSecondarioInput(GetEntiConRiferimentoContabileSecondarioInput value) {
        this.getEntiConRiferimentoContabileSecondarioInput = value;
    }

}
