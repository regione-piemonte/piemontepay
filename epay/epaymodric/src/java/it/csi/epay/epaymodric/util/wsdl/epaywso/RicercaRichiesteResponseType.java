/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RicercaRichiesteResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RicercaRichiesteResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="RichiesteEstratte" type="{http://www.csi.it/epay/epaywso/businesstypes}RichiestaLightTypeList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RicercaRichiesteResponseType", propOrder = {
    "richiesteEstratte"
})
public class RicercaRichiesteResponseType
    extends ResponseType
{

    @XmlElement(name = "RichiesteEstratte")
    protected RichiestaLightTypeList richiesteEstratte;

    /**
     * Gets the value of the richiesteEstratte property.
     * 
     * @return
     *     possible object is
     *     {@link RichiestaLightTypeList }
     *     
     */
    public RichiestaLightTypeList getRichiesteEstratte() {
        return richiesteEstratte;
    }

    /**
     * Sets the value of the richiesteEstratte property.
     * 
     * @param value
     *     allowed object is
     *     {@link RichiestaLightTypeList }
     *     
     */
    public void setRichiesteEstratte(RichiestaLightTypeList value) {
        this.richiesteEstratte = value;
    }

}
