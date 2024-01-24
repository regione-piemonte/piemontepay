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
 * <p>Java class for EsitoRicercaStatiAggregatiWsoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EsitoRicercaStatiAggregatiWsoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdFlusso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatoFlusso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DatoAggiuntivoCodEsito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DatoAggiuntivoDescEsito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoRicercaStatiAggregatiWsoType", propOrder = {
    "idFlusso",
    "codFiscaleEnte",
    "statoFlusso",
    "datoAggiuntivoCodEsito",
    "datoAggiuntivoDescEsito"
})
public class EsitoRicercaStatiAggregatiWsoType {

    @XmlElement(name = "IdFlusso", required = true)
    protected String idFlusso;
    @XmlElement(name = "CodFiscaleEnte", required = true)
    protected String codFiscaleEnte;
    @XmlElement(name = "StatoFlusso", required = true)
    protected String statoFlusso;
    @XmlElement(name = "DatoAggiuntivoCodEsito", required = true)
    protected String datoAggiuntivoCodEsito;
    @XmlElement(name = "DatoAggiuntivoDescEsito", required = true)
    protected String datoAggiuntivoDescEsito;

    /**
     * Gets the value of the idFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlusso() {
        return idFlusso;
    }

    /**
     * Sets the value of the idFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlusso(String value) {
        this.idFlusso = value;
    }

    /**
     * Gets the value of the codFiscaleEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodFiscaleEnte() {
        return codFiscaleEnte;
    }

    /**
     * Sets the value of the codFiscaleEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodFiscaleEnte(String value) {
        this.codFiscaleEnte = value;
    }

    /**
     * Gets the value of the statoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoFlusso() {
        return statoFlusso;
    }

    /**
     * Sets the value of the statoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoFlusso(String value) {
        this.statoFlusso = value;
    }

    /**
     * Gets the value of the datoAggiuntivoCodEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatoAggiuntivoCodEsito() {
        return datoAggiuntivoCodEsito;
    }

    /**
     * Sets the value of the datoAggiuntivoCodEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatoAggiuntivoCodEsito(String value) {
        this.datoAggiuntivoCodEsito = value;
    }

    /**
     * Gets the value of the datoAggiuntivoDescEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatoAggiuntivoDescEsito() {
        return datoAggiuntivoDescEsito;
    }

    /**
     * Sets the value of the datoAggiuntivoDescEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatoAggiuntivoDescEsito(String value) {
        this.datoAggiuntivoDescEsito = value;
    }

}
