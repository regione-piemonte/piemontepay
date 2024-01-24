/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCodiciVersamentoPerCodiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCodiciVersamentoPerCodiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsCodiciVersamento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCodiciVersamentoPerCodiceResponse", propOrder = {
    "result"
})
public class RicercaCodiciVersamentoPerCodiceResponse {

    protected DtoOutputWsCodiciVersamento result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsCodiciVersamento }
     *     
     */
    public DtoOutputWsCodiciVersamento getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsCodiciVersamento }
     *     
     */
    public void setResult(DtoOutputWsCodiciVersamento value) {
        this.result = value;
    }

}
