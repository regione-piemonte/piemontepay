/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResponseType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="StatoElaborazioneFlusso" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}StatoElaborazioneFlussoType"/>
 *         &lt;element name="DettaglioEsitoElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statoElaborazioneFlusso",
    "dettaglioEsitoElaborazione"
})
@XmlRootElement(name = "EsitoFlussiPagoPAResponse", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
public class EsitoFlussiPagoPAResponse
extends ResponseType
{

    @XmlElement(name = "StatoElaborazioneFlusso", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
    protected StatoElaborazioneFlussoType statoElaborazioneFlusso;
    @XmlElement(name = "DettaglioEsitoElaborazione", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    protected String dettaglioEsitoElaborazione;

    /**
     * Gets the value of the statoElaborazioneFlusso property.
     *
     * @return
     *     possible object is
     *     {@link StatoElaborazioneFlussoType }
     *
     */
    public StatoElaborazioneFlussoType getStatoElaborazioneFlusso() {
        return statoElaborazioneFlusso;
    }

    /**
     * Sets the value of the statoElaborazioneFlusso property.
     *
     * @param value
     *     allowed object is
     *     {@link StatoElaborazioneFlussoType }
     *
     */
    public void setStatoElaborazioneFlusso(StatoElaborazioneFlussoType value) {
        this.statoElaborazioneFlusso = value;
    }

    /**
     * Gets the value of the dettaglioEsitoElaborazione property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDettaglioEsitoElaborazione() {
        return dettaglioEsitoElaborazione;
    }

    /**
     * Sets the value of the dettaglioEsitoElaborazione property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDettaglioEsitoElaborazione(String value) {
        this.dettaglioEsitoElaborazione = value;
    }

}
