/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RicercaStatiAggregatiWsoRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RicercaStatiAggregatiWsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RicercaStatoAggregatoWsoList" type="{http://www.csi.it/epay/epaywso/businesstypes}RicercaStatiAggregatiWsoTypeList" minOccurs="0"/>
 *         &lt;element name="IdTipoRichiesta" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RicercaStatiAggregatiWsoRequestType", propOrder = {
    "ricercaStatoAggregatoWsoList",
    "idTipoRichiesta"
})
public class RicercaStatiAggregatiWsoRequestType {

    @XmlElement(name = "RicercaStatoAggregatoWsoList")
    protected RicercaStatiAggregatiWsoTypeList ricercaStatoAggregatoWsoList;
    @XmlElement(name = "IdTipoRichiesta", required = true)
    protected BigInteger idTipoRichiesta;

    /**
     * Gets the value of the ricercaStatoAggregatoWsoList property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaStatiAggregatiWsoTypeList }
     *     
     */
    public RicercaStatiAggregatiWsoTypeList getRicercaStatoAggregatoWsoList() {
        return ricercaStatoAggregatoWsoList;
    }

    /**
     * Sets the value of the ricercaStatoAggregatoWsoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaStatiAggregatiWsoTypeList }
     *     
     */
    public void setRicercaStatoAggregatoWsoList(RicercaStatiAggregatiWsoTypeList value) {
        this.ricercaStatoAggregatoWsoList = value;
    }

    /**
     * Gets the value of the idTipoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdTipoRichiesta() {
        return idTipoRichiesta;
    }

    /**
     * Sets the value of the idTipoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdTipoRichiesta(BigInteger value) {
        this.idTipoRichiesta = value;
    }

}
