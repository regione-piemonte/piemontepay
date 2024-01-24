/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for salvaListaDiCarico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="salvaListaDiCarico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsSalvaListaDiCarico" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salvaListaDiCarico", propOrder = {
    "arg0"
})
public class SalvaListaDiCarico {

    protected DtoInputWsSalvaListaDiCarico arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsSalvaListaDiCarico }
     *     
     */
    public DtoInputWsSalvaListaDiCarico getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsSalvaListaDiCarico }
     *     
     */
    public void setArg0(DtoInputWsSalvaListaDiCarico value) {
        this.arg0 = value;
    }

}
