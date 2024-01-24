/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaFlussiDaEsportareResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussiDaEsportareResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsRicercaFlussiDaEsportare" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussiDaEsportareResponse", propOrder = {
    "result"
})
public class RicercaFlussiDaEsportareResponse {

    protected DtoOutputWsRicercaFlussiDaEsportare result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsRicercaFlussiDaEsportare }
     *     
     */
    public DtoOutputWsRicercaFlussiDaEsportare getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsRicercaFlussiDaEsportare }
     *     
     */
    public void setResult(DtoOutputWsRicercaFlussiDaEsportare value) {
        this.result = value;
    }

}
