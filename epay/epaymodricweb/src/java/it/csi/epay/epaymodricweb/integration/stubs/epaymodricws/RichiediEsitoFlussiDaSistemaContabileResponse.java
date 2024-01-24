/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for richiediEsitoFlussiDaSistemaContabileResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="richiediEsitoFlussiDaSistemaContabileResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "richiediEsitoFlussiDaSistemaContabileResponse", propOrder = {
    "result"
})
public class RichiediEsitoFlussiDaSistemaContabileResponse {

    protected DtoOutputWsEsito result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public DtoOutputWsEsito getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public void setResult(DtoOutputWsEsito value) {
        this.result = value;
    }

}
