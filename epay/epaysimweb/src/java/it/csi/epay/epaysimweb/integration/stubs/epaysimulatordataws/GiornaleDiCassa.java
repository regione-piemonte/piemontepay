/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for giornaleDiCassa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="giornaleDiCassa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}giornaleDiCassaInputDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giornaleDiCassa", propOrder = {
    "param"
})
public class GiornaleDiCassa {

    protected GiornaleDiCassaInputDTO param;

    /**
     * Gets the value of the param property.
     * 
     * @return
     *     possible object is
     *     {@link GiornaleDiCassaInputDTO }
     *     
     */
    public GiornaleDiCassaInputDTO getParam() {
        return param;
    }

    /**
     * Sets the value of the param property.
     * 
     * @param value
     *     allowed object is
     *     {@link GiornaleDiCassaInputDTO }
     *     
     */
    public void setParam(GiornaleDiCassaInputDTO value) {
        this.param = value;
    }

}
