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
 * <p>Java class for AggiornaEsitoInvioRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggiornaEsitoInvioRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdApplicativo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EsitoInvio" type="{http://www.csi.it/epay/epaywso/businesstypes}EsitoInvioType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggiornaEsitoInvioRequestType", propOrder = {
    "messageUUID",
    "idApplicativo",
    "esitoInvio"
})
public class AggiornaEsitoInvioRequestType {

    @XmlElement(name = "MessageUUID", required = true)
    protected String messageUUID;
    @XmlElement(name = "IdApplicativo", required = true, type = Integer.class, nillable = true)
    protected Integer idApplicativo;
    @XmlElement(name = "EsitoInvio", required = true)
    protected EsitoInvioType esitoInvio;

    /**
     * Gets the value of the messageUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageUUID() {
        return messageUUID;
    }

    /**
     * Sets the value of the messageUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageUUID(String value) {
        this.messageUUID = value;
    }

    /**
     * Gets the value of the idApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdApplicativo() {
        return idApplicativo;
    }

    /**
     * Sets the value of the idApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdApplicativo(Integer value) {
        this.idApplicativo = value;
    }

    /**
     * Gets the value of the esitoInvio property.
     * 
     * @return
     *     possible object is
     *     {@link EsitoInvioType }
     *     
     */
    public EsitoInvioType getEsitoInvio() {
        return esitoInvio;
    }

    /**
     * Sets the value of the esitoInvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoInvioType }
     *     
     */
    public void setEsitoInvio(EsitoInvioType value) {
        this.esitoInvio = value;
    }

}
