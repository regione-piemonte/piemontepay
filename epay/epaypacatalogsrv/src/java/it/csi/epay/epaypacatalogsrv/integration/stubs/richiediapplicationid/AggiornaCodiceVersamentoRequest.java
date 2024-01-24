/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid;

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
 *         &lt;element name="ProtocolloAggiornamentoAzione" type="{http://www.csi.it/epay/epaywso/types}ProtocolloAggiornamentoAzioneType"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}CodiceVersamentoType"/>
 *         &lt;element name="IdOperazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
     * Gets the value of the protocolloAggiornamentoAzione property.
     * 
     * @return
     *     possible object is
     *     {@link ProtocolloAggiornamentoAzioneType }
     *     
     */
    public ProtocolloAggiornamentoAzioneType getProtocolloAggiornamentoAzione() {
        return protocolloAggiornamentoAzione;
    }

    /**
     * Sets the value of the protocolloAggiornamentoAzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProtocolloAggiornamentoAzioneType }
     *     
     */
    public void setProtocolloAggiornamentoAzione(ProtocolloAggiornamentoAzioneType value) {
        this.protocolloAggiornamentoAzione = value;
    }

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
