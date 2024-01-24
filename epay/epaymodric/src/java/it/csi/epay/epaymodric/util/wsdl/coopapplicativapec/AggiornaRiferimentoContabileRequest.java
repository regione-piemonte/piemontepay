/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.coopapplicativapec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProtocolloAggiornamentoAzione" type="{http://www.csi.it/epay/epaywso/types}AzioneType"/>
 *         &lt;element name="RiferimentoContabile" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}RiferimentoContabileType"/>
 *         &lt;element name="IdOperazione" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "protocolloAggiornamentoAzione",
    "riferimentoContabile",
    "idOperazione"
})
@XmlRootElement(name = "AggiornaRiferimentoContabileRequest")
public class AggiornaRiferimentoContabileRequest {

    @XmlElement(name = "ProtocolloAggiornamentoAzione", required = true)
    protected ProtocolloAggiornamentoAzioneType protocolloAggiornamentoAzione;
    @XmlElement(name = "RiferimentoContabile", required = true)
    protected RiferimentoContabileType riferimentoContabile;
    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;

    /**
     * Gets the value of the riferimentoContabile property.
     * 
     * @return
     *     possible object is
     *     {@link RiferimentoContabileType }
     *     
     */
    public RiferimentoContabileType getRiferimentoContabile() {
        return riferimentoContabile;
    }

    
    /**
     * Gets the value of the azione property.
     * 
     * @return
     *     possible object is
     *     {@link ProtocolloAggiornamentoAzioneType }
     *     
     */
    public ProtocolloAggiornamentoAzioneType getProtocolloAggiornamentoAzione () {
        return protocolloAggiornamentoAzione;
    }

    
    /**
     * Sets the value of the azione property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProtocolloAggiornamentoAzioneType }
     *     
     */
    public void setProtocolloAggiornamentoAzione ( ProtocolloAggiornamentoAzioneType protocolloAggiornamentoAzione ) {
        this.protocolloAggiornamentoAzione = protocolloAggiornamentoAzione;
    }

    /**
     * Sets the value of the riferimentoContabile property.
     * 
     * @param value
     *     allowed object is
     *     {@link RiferimentoContabileType }
     *     
     */
    public void setRiferimentoContabile(RiferimentoContabileType value) {
        this.riferimentoContabile = value;
    }

    /**
     * Gets the value of the idOperazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperazione() {
        return idOperazione;
    }

    /**
     * Sets the value of the idOperazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperazione(String value) {
        this.idOperazione = value;
    }

}
