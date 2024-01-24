/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RicercaStatiAggregatiWsoResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RicercaStatiAggregatiWsoResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="EsitoRicercaStatiAggregatiWsoList" type="{http://www.csi.it/epay/epaywso/businesstypes}EsitoRicercaStatiAggregatiWsoListTypeList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RicercaStatiAggregatiWsoResponseType", propOrder = {
    "esitoRicercaStatiAggregatiWsoList"
})
public class RicercaStatiAggregatiWsoResponseType
    extends ResponseType
{

    @XmlElement(name = "EsitoRicercaStatiAggregatiWsoList")
    protected EsitoRicercaStatiAggregatiWsoListTypeList esitoRicercaStatiAggregatiWsoList;

    /**
     * Gets the value of the esitoRicercaStatiAggregatiWsoList property.
     * 
     * @return
     *     possible object is
     *     {@link EsitoRicercaStatiAggregatiWsoListTypeList }
     *     
     */
    public EsitoRicercaStatiAggregatiWsoListTypeList getEsitoRicercaStatiAggregatiWsoList() {
        return esitoRicercaStatiAggregatiWsoList;
    }

    /**
     * Sets the value of the esitoRicercaStatiAggregatiWsoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoRicercaStatiAggregatiWsoListTypeList }
     *     
     */
    public void setEsitoRicercaStatiAggregatiWsoList(EsitoRicercaStatiAggregatiWsoListTypeList value) {
        this.esitoRicercaStatiAggregatiWsoList = value;
    }

}
