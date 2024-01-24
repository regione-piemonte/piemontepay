/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaCduUtente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCduUtente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaCduUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaCduUtenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCduUtente", propOrder = {
    "aggiornaCduUtenteInput"
})
public class AggiornaCduUtente {

    protected AggiornaCduUtenteInput aggiornaCduUtenteInput;

    /**
     * Gets the value of the aggiornaCduUtenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCduUtenteInput }
     *     
     */
    public AggiornaCduUtenteInput getAggiornaCduUtenteInput() {
        return aggiornaCduUtenteInput;
    }

    /**
     * Sets the value of the aggiornaCduUtenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCduUtenteInput }
     *     
     */
    public void setAggiornaCduUtenteInput(AggiornaCduUtenteInput value) {
        this.aggiornaCduUtenteInput = value;
    }

}
