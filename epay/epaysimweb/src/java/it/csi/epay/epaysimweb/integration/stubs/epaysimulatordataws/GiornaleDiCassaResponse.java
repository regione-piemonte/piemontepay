/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for giornaleDiCassaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="giornaleDiCassaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}giornaleDiCassaOutputDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giornaleDiCassaResponse", propOrder = {
    "result"
})
public class GiornaleDiCassaResponse {

    protected GiornaleDiCassaOutputDTO result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link GiornaleDiCassaOutputDTO }
     *     
     */
    public GiornaleDiCassaOutputDTO getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link GiornaleDiCassaOutputDTO }
     *     
     */
    public void setResult(GiornaleDiCassaOutputDTO value) {
        this.result = value;
    }

}
