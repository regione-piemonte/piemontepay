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
 * <p>Java class for RicercaRichiesteRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RicercaRichiesteRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FiltroSelezione" type="{http://www.csi.it/epay/epaywso/businesstypes}FiltroSelezioneRichiesteType" minOccurs="0"/>
 *         &lt;element name="CriteriOrdinamento" type="{http://www.csi.it/epay/epaywso/businesstypes}CriterioOrdinamentoRichiestaTypeList" minOccurs="0"/>
 *         &lt;element name="Paginazione" type="{http://www.csi.it/epay/epaywso/businesstypes}PaginazioneType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RicercaRichiesteRequestType", propOrder = {
    "filtroSelezione",
    "criteriOrdinamento",
    "paginazione"
})
public class RicercaRichiesteRequestType {

    @XmlElement(name = "FiltroSelezione")
    protected FiltroSelezioneRichiesteType filtroSelezione;
    @XmlElement(name = "CriteriOrdinamento")
    protected CriterioOrdinamentoRichiestaTypeList criteriOrdinamento;
    @XmlElement(name = "Paginazione", required = true)
    protected PaginazioneType paginazione;

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

    /**
     * Gets the value of the criteriOrdinamento property.
     * 
     * @return
     *     possible object is
     *     {@link CriterioOrdinamentoRichiestaTypeList }
     *     
     */
    public CriterioOrdinamentoRichiestaTypeList getCriteriOrdinamento() {
        return criteriOrdinamento;
    }

    /**
     * Sets the value of the criteriOrdinamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CriterioOrdinamentoRichiestaTypeList }
     *     
     */
    public void setCriteriOrdinamento(CriterioOrdinamentoRichiestaTypeList value) {
        this.criteriOrdinamento = value;
    }

    /**
     * Gets the value of the paginazione property.
     * 
     * @return
     *     possible object is
     *     {@link PaginazioneType }
     *     
     */
    public PaginazioneType getPaginazione() {
        return paginazione;
    }

    /**
     * Sets the value of the paginazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaginazioneType }
     *     
     */
    public void setPaginazione(PaginazioneType value) {
        this.paginazione = value;
    }

}
