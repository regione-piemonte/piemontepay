/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUtente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUtente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getUtenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUtente", propOrder = {
    "getUtenteInput"
})
public class GetUtente {

    protected GetUtenteInput getUtenteInput;

    /**
     * Gets the value of the getUtenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetUtenteInput }
     *     
     */
    public GetUtenteInput getGetUtenteInput() {
        return getUtenteInput;
    }

    /**
     * Sets the value of the getUtenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetUtenteInput }
     *     
     */
    public void setGetUtenteInput(GetUtenteInput value) {
        this.getUtenteInput = value;
    }

}
