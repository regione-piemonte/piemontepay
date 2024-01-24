/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eseguiMigrazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eseguiMigrazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eseguiMigrazioneInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eseguiMigrazioneInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eseguiMigrazione", propOrder = {
    "eseguiMigrazioneInput"
})
public class EseguiMigrazione {

    protected EseguiMigrazioneInput eseguiMigrazioneInput;

    /**
     * Gets the value of the eseguiMigrazioneInput property.
     * 
     * @return
     *     possible object is
     *     {@link EseguiMigrazioneInput }
     *     
     */
    public EseguiMigrazioneInput getEseguiMigrazioneInput() {
        return eseguiMigrazioneInput;
    }

    /**
     * Sets the value of the eseguiMigrazioneInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link EseguiMigrazioneInput }
     *     
     */
    public void setEseguiMigrazioneInput(EseguiMigrazioneInput value) {
        this.eseguiMigrazioneInput = value;
    }

}
