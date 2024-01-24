/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEntiAssociati complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEntiAssociati">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getEntiAssociatiInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEntiAssociatiInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntiAssociati", propOrder = {
    "getEntiAssociatiInput"
})
public class GetEntiAssociati {

    protected GetEntiAssociatiInput getEntiAssociatiInput;

    /**
     * Gets the value of the getEntiAssociatiInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetEntiAssociatiInput }
     *     
     */
    public GetEntiAssociatiInput getGetEntiAssociatiInput() {
        return getEntiAssociatiInput;
    }

    /**
     * Sets the value of the getEntiAssociatiInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEntiAssociatiInput }
     *     
     */
    public void setGetEntiAssociatiInput(GetEntiAssociatiInput value) {
        this.getEntiAssociatiInput = value;
    }

}
