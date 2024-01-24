/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEnteOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEnteOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="ente" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEnteOutputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEnteOutput", propOrder = {
    "ente"
})
public class GetEnteOutput
    extends ParentOutput
{

    protected GetEnteOutputDto ente;

    /**
     * Gets the value of the ente property.
     * 
     * @return
     *     possible object is
     *     {@link GetEnteOutputDto }
     *     
     */
    public GetEnteOutputDto getEnte() {
        return ente;
    }

    /**
     * Sets the value of the ente property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEnteOutputDto }
     *     
     */
    public void setEnte(GetEnteOutputDto value) {
        this.ente = value;
    }

}
