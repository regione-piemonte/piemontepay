/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.cooppec.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="protocolloAggiornamentoAzione" type="{http://www.csi.it/epay/epaywso/types}AzioneType"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}CodiceVersamentoType"/>
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
    "codiceVersamento",
    "idOperazione"
})
@XmlRootElement(name = "AggiornaCodiceVersamentoRequest")
public class AggiornaCodiceVersamentoRequest {

    @XmlElement(name = "ProtocolloAggiornamentoAzione", required = true)
    protected ProtocolloAggiornamentoAzioneType protocolloAggiornamentoAzione;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected CodiceVersamentoType codiceVersamento;
    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;

    /**
     * Gets the value of the codiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link CodiceVersamentoType }
     *     
     */
    public CodiceVersamentoType getCodiceVersamento() {
        return codiceVersamento;
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
     * Sets the value of the codiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceVersamentoType }
     *     
     */
    public void setCodiceVersamento(CodiceVersamentoType value) {
        this.codiceVersamento = value;
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
}
