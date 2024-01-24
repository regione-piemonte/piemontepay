/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEntiAssociatiInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEntiAssociatiInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="codiceFiscaleUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntiAssociatiInput", propOrder = {
    "codiceFiscaleUtente"
})
public class GetEntiAssociatiInput
    extends ParentInput
{

    protected String codiceFiscaleUtente;

    /**
     * Gets the value of the codiceFiscaleUtente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleUtente() {
        return codiceFiscaleUtente;
    }

    /**
     * Sets the value of the codiceFiscaleUtente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleUtente(String value) {
        this.codiceFiscaleUtente = value;
    }

}
