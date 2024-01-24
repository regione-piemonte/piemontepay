/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProtocolloAggiornamentoAzione" type="{http://www.csi.it/epay/epaywso/types}ProtocolloAggiornamentoAzioneType"/&gt;
 *         &lt;element name="RiferimentoContabile" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}RiferimentoContabileType"/&gt;
 *         &lt;element name="IdOperazione" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    @XmlSchemaType(name = "string")
    protected ProtocolloAggiornamentoAzioneType protocolloAggiornamentoAzione;
    @XmlElement(name = "RiferimentoContabile", required = true)
    protected RiferimentoContabileType riferimentoContabile;
    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;

    /**
     * Recupera il valore della proprietaazione.
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
     * Imposta il valore della proprietaazione.
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
     * Recupera il valore della proprietariferimentoContabile.
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
     * Imposta il valore della proprietariferimentoContabile.
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
     * Recupera il valore della proprietaidOperazione.
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
     * Imposta il valore della proprietaidOperazione.
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
