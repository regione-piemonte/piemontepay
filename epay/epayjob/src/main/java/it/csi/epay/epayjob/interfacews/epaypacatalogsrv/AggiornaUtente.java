/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaUtente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaUtente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaUtenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaUtente", propOrder = {
    "aggiornaUtenteInput"
})
public class AggiornaUtente {

    protected AggiornaUtenteInput aggiornaUtenteInput;

    /**
     * Gets the value of the aggiornaUtenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaUtenteInput }
     *     
     */
    public AggiornaUtenteInput getAggiornaUtenteInput() {
        return aggiornaUtenteInput;
    }

    /**
     * Sets the value of the aggiornaUtenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaUtenteInput }
     *     
     */
    public void setAggiornaUtenteInput(AggiornaUtenteInput value) {
        this.aggiornaUtenteInput = value;
    }

}
