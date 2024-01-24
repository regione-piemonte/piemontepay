/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRiferimentoContabileOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRiferimentoContabileOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="riferimentoContabile" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getRiferimentoContabileOutputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRiferimentoContabileOutput", propOrder = {
    "riferimentoContabile"
})
public class GetRiferimentoContabileOutput
    extends ParentOutput
{

    protected GetRiferimentoContabileOutputDto riferimentoContabile;

    /**
     * Gets the value of the riferimentoContabile property.
     * 
     * @return
     *     possible object is
     *     {@link GetRiferimentoContabileOutputDto }
     *     
     */
    public GetRiferimentoContabileOutputDto getRiferimentoContabile() {
        return riferimentoContabile;
    }

    /**
     * Sets the value of the riferimentoContabile property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRiferimentoContabileOutputDto }
     *     
     */
    public void setRiferimentoContabile(GetRiferimentoContabileOutputDto value) {
        this.riferimentoContabile = value;
    }

}
