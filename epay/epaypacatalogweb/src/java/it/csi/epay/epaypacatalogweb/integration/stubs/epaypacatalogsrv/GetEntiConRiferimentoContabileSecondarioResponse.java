/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEntiConRiferimentoContabileSecondarioResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEntiConRiferimentoContabileSecondarioResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEntiConRiferimentoContabileSecondarioOutput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntiConRiferimentoContabileSecondarioResponse", propOrder = {
    "result"
})
public class GetEntiConRiferimentoContabileSecondarioResponse {

    protected GetEntiConRiferimentoContabileSecondarioOutput result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link GetEntiConRiferimentoContabileSecondarioOutput }
     *     
     */
    public GetEntiConRiferimentoContabileSecondarioOutput getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEntiConRiferimentoContabileSecondarioOutput }
     *     
     */
    public void setResult(GetEntiConRiferimentoContabileSecondarioOutput value) {
        this.result = value;
    }

}
