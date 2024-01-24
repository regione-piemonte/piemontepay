/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOpzioniTipologiaDatoSpecificoRiscossione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniTipologiaDatoSpecificoRiscossione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOpzioniInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniTipologiaDatoSpecificoRiscossione", propOrder = {
    "getOpzioniInput"
})
public class GetOpzioniTipologiaDatoSpecificoRiscossione {

    protected GetOpzioniInput getOpzioniInput;

    /**
     * Gets the value of the getOpzioniInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniInput }
     *     
     */
    public GetOpzioniInput getGetOpzioniInput() {
        return getOpzioniInput;
    }

    /**
     * Sets the value of the getOpzioniInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniInput }
     *     
     */
    public void setGetOpzioniInput(GetOpzioniInput value) {
        this.getOpzioniInput = value;
    }

}
