/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEnte complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEnte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getEnteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEnteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEnte", propOrder = {
    "getEnteInput"
})
public class GetEnte {

    protected GetEnteInput getEnteInput;

    /**
     * Gets the value of the getEnteInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetEnteInput }
     *     
     */
    public GetEnteInput getGetEnteInput() {
        return getEnteInput;
    }

    /**
     * Sets the value of the getEnteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEnteInput }
     *     
     */
    public void setGetEnteInput(GetEnteInput value) {
        this.getEnteInput = value;
    }

}
