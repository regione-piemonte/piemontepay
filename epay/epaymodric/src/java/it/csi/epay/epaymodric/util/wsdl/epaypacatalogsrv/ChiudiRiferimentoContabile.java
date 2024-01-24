/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chiudiRiferimentoContabile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chiudiRiferimentoContabile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chiudiRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}chiudiRiferimentoContabileInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chiudiRiferimentoContabile", propOrder = {
    "chiudiRiferimentoContabileInput"
})
public class ChiudiRiferimentoContabile {

    protected ChiudiRiferimentoContabileInput chiudiRiferimentoContabileInput;

    /**
     * Gets the value of the chiudiRiferimentoContabileInput property.
     * 
     * @return
     *     possible object is
     *     {@link ChiudiRiferimentoContabileInput }
     *     
     */
    public ChiudiRiferimentoContabileInput getChiudiRiferimentoContabileInput() {
        return chiudiRiferimentoContabileInput;
    }

    /**
     * Sets the value of the chiudiRiferimentoContabileInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiudiRiferimentoContabileInput }
     *     
     */
    public void setChiudiRiferimentoContabileInput(ChiudiRiferimentoContabileInput value) {
        this.chiudiRiferimentoContabileInput = value;
    }

}
