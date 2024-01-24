/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContaNumeroRichiesteSelezionateResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContaNumeroRichiesteSelezionateResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="NumeroRichiesteSelezionate" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContaNumeroRichiesteSelezionateResponseType", propOrder = {
    "numeroRichiesteSelezionate"
})
public class ContaNumeroRichiesteSelezionateResponseType
    extends ResponseType
{

    @XmlElement(name = "NumeroRichiesteSelezionate", required = true)
    protected BigInteger numeroRichiesteSelezionate;

    /**
     * Gets the value of the numeroRichiesteSelezionate property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroRichiesteSelezionate() {
        return numeroRichiesteSelezionate;
    }

    /**
     * Sets the value of the numeroRichiesteSelezionate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroRichiesteSelezionate(BigInteger value) {
        this.numeroRichiesteSelezionate = value;
    }

}
