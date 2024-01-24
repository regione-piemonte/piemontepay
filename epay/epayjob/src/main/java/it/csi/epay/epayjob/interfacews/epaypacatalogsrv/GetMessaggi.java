/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessaggi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessaggi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getMessaggiInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getMessaggiInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessaggi", propOrder = {
    "getMessaggiInput"
})
public class GetMessaggi {

    protected GetMessaggiInput getMessaggiInput;

    /**
     * Gets the value of the getMessaggiInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetMessaggiInput }
     *     
     */
    public GetMessaggiInput getGetMessaggiInput() {
        return getMessaggiInput;
    }

    /**
     * Sets the value of the getMessaggiInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMessaggiInput }
     *     
     */
    public void setGetMessaggiInput(GetMessaggiInput value) {
        this.getMessaggiInput = value;
    }

}
