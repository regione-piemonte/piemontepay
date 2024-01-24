/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for riceviRTResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="riceviRTResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://serviziofruitore.interfacews.mdp.nodospc.csi.it/}esitoRiceviRT" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "riceviRTResponse", propOrder = {
    "result"
})
public class RiceviRTResponse {

    protected EsitoRiceviRT result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link EsitoRiceviRT }
     *     
     */
    public EsitoRiceviRT getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoRiceviRT }
     *     
     */
    public void setResult(EsitoRiceviRT value) {
        this.result = value;
    }

}
