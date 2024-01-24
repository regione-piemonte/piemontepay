/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProfilazioneUtenteCorrente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProfilazioneUtenteCorrente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getProfilazioneUtenteCorrenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getProfilazioneUtenteCorrenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProfilazioneUtenteCorrente", propOrder = {
    "getProfilazioneUtenteCorrenteInput"
})
public class GetProfilazioneUtenteCorrente {

    protected GetProfilazioneUtenteCorrenteInput getProfilazioneUtenteCorrenteInput;

    /**
     * Gets the value of the getProfilazioneUtenteCorrenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetProfilazioneUtenteCorrenteInput }
     *     
     */
    public GetProfilazioneUtenteCorrenteInput getGetProfilazioneUtenteCorrenteInput() {
        return getProfilazioneUtenteCorrenteInput;
    }

    /**
     * Sets the value of the getProfilazioneUtenteCorrenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProfilazioneUtenteCorrenteInput }
     *     
     */
    public void setGetProfilazioneUtenteCorrenteInput(GetProfilazioneUtenteCorrenteInput value) {
        this.getProfilazioneUtenteCorrenteInput = value;
    }

}
