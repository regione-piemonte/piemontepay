/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciPrenotazioneReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciPrenotazioneReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsInserisciPrenotazioneReport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciPrenotazioneReport", propOrder = {
    "param"
})
public class InserisciPrenotazioneReport {

    protected DtoInputWsInserisciPrenotazioneReport param;

    /**
     * Gets the value of the param property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsInserisciPrenotazioneReport }
     *     
     */
    public DtoInputWsInserisciPrenotazioneReport getParam() {
        return param;
    }

    /**
     * Sets the value of the param property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsInserisciPrenotazioneReport }
     *     
     */
    public void setParam(DtoInputWsInserisciPrenotazioneReport value) {
        this.param = value;
    }

}
