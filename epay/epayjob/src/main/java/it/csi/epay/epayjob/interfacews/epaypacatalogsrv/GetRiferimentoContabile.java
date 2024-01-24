/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRiferimentoContabile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRiferimentoContabile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getRiferimentoContabileInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRiferimentoContabile", propOrder = {
    "getRiferimentoContabileInput"
})
public class GetRiferimentoContabile {

    protected GetRiferimentoContabileInput getRiferimentoContabileInput;

    /**
     * Gets the value of the getRiferimentoContabileInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetRiferimentoContabileInput }
     *     
     */
    public GetRiferimentoContabileInput getGetRiferimentoContabileInput() {
        return getRiferimentoContabileInput;
    }

    /**
     * Sets the value of the getRiferimentoContabileInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRiferimentoContabileInput }
     *     
     */
    public void setGetRiferimentoContabileInput(GetRiferimentoContabileInput value) {
        this.getRiferimentoContabileInput = value;
    }

}
