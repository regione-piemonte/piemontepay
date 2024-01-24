/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for giornaleDiCassaOutputDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="giornaleDiCassaOutputDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="giornaleDiCassaDTO" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}giornaleDiCassaDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giornaleDiCassaOutputDTO", propOrder = {
    "giornaleDiCassaDTO"
})
public class GiornaleDiCassaOutputDTO
    extends ParentOutput
{

    protected GiornaleDiCassaDTO giornaleDiCassaDTO;

    /**
     * Gets the value of the giornaleDiCassaDTO property.
     * 
     * @return
     *     possible object is
     *     {@link GiornaleDiCassaDTO }
     *     
     */
    public GiornaleDiCassaDTO getGiornaleDiCassaDTO() {
        return giornaleDiCassaDTO;
    }

    /**
     * Sets the value of the giornaleDiCassaDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link GiornaleDiCassaDTO }
     *     
     */
    public void setGiornaleDiCassaDTO(GiornaleDiCassaDTO value) {
        this.giornaleDiCassaDTO = value;
    }

}
