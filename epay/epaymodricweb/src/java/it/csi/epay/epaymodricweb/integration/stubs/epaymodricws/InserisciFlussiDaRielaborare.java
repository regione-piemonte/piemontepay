/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciFlussiDaRielaborare complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciFlussiDaRielaborare">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsInserisciFlussiDaRielaborare" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciFlussiDaRielaborare", propOrder = {
    "arg0"
})
public class InserisciFlussiDaRielaborare {

    protected DtoInputWsInserisciFlussiDaRielaborare arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsInserisciFlussiDaRielaborare }
     *     
     */
    public DtoInputWsInserisciFlussiDaRielaborare getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsInserisciFlussiDaRielaborare }
     *     
     */
    public void setArg0(DtoInputWsInserisciFlussiDaRielaborare value) {
        this.arg0 = value;
    }

}
