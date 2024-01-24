/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaLimiteEsportazioneResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaLimiteEsportazioneResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsRicercaLimiteEsportazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaLimiteEsportazioneResponse", propOrder = {
    "result"
})
public class RicercaLimiteEsportazioneResponse {

    protected DtoOutputWsRicercaLimiteEsportazione result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsRicercaLimiteEsportazione }
     *     
     */
    public DtoOutputWsRicercaLimiteEsportazione getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsRicercaLimiteEsportazione }
     *     
     */
    public void setResult(DtoOutputWsRicercaLimiteEsportazione value) {
        this.result = value;
    }

}
