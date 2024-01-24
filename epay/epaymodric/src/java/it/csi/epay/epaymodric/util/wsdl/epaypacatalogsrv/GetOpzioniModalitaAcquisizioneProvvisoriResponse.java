/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOpzioniModalitaAcquisizioneProvvisoriResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniModalitaAcquisizioneProvvisoriResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniModalitaAcquisizioneProvvisoriOutput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniModalitaAcquisizioneProvvisoriResponse", propOrder = {
    "result"
})
public class GetOpzioniModalitaAcquisizioneProvvisoriResponse {

    protected GetOpzioniModalitaAcquisizioneProvvisoriOutput result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniModalitaAcquisizioneProvvisoriOutput }
     *     
     */
    public GetOpzioniModalitaAcquisizioneProvvisoriOutput getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniModalitaAcquisizioneProvvisoriOutput }
     *     
     */
    public void setResult(GetOpzioniModalitaAcquisizioneProvvisoriOutput value) {
        this.result = value;
    }

}
