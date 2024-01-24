/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProfilazioneUtente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProfilazioneUtente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getProfilazioneUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getProfilazioneUtenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProfilazioneUtente", propOrder = {
    "getProfilazioneUtenteInput"
})
public class GetProfilazioneUtente {

    protected GetProfilazioneUtenteInput getProfilazioneUtenteInput;

    /**
     * Gets the value of the getProfilazioneUtenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetProfilazioneUtenteInput }
     *     
     */
    public GetProfilazioneUtenteInput getGetProfilazioneUtenteInput() {
        return getProfilazioneUtenteInput;
    }

    /**
     * Sets the value of the getProfilazioneUtenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProfilazioneUtenteInput }
     *     
     */
    public void setGetProfilazioneUtenteInput(GetProfilazioneUtenteInput value) {
        this.getProfilazioneUtenteInput = value;
    }

}
