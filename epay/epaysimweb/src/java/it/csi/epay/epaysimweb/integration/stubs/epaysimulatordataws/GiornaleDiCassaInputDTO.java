/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for giornaleDiCassaInputDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="giornaleDiCassaInputDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="identificativoFlussoBT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giornaleDiCassaInputDTO", propOrder = {
    "identificativoFlussoBT"
})
public class GiornaleDiCassaInputDTO
    extends ParentInput
{

    protected String identificativoFlussoBT;

    /**
     * Gets the value of the identificativoFlussoBT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoFlussoBT() {
        return identificativoFlussoBT;
    }

    /**
     * Sets the value of the identificativoFlussoBT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoFlussoBT(String value) {
        this.identificativoFlussoBT = value;
    }

}
