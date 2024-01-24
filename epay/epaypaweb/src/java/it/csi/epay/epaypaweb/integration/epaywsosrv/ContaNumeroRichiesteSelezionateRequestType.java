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
 * <p>Java class for ContaNumeroRichiesteSelezionateRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContaNumeroRichiesteSelezionateRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FiltroSelezione" type="{http://www.csi.it/epay/epaywso/businesstypes}FiltroSelezioneRichiesteType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContaNumeroRichiesteSelezionateRequestType", propOrder = {
    "filtroSelezione"
})
public class ContaNumeroRichiesteSelezionateRequestType {

    @XmlElement(name = "FiltroSelezione")
    protected FiltroSelezioneRichiesteType filtroSelezione;

    /**
     * Gets the value of the filtroSelezione property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroSelezioneRichiesteType }
     *     
     */
    public FiltroSelezioneRichiesteType getFiltroSelezione() {
        return filtroSelezione;
    }

    /**
     * Sets the value of the filtroSelezione property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroSelezioneRichiesteType }
     *     
     */
    public void setFiltroSelezione(FiltroSelezioneRichiesteType value) {
        this.filtroSelezione = value;
    }

}
