/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

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
    @XmlSchemaType(name = "string")
    protected ProtocolloAggiornamentoAzioneType protocolloAggiornamentoAzione;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected CodiceVersamentoType codiceVersamento;
    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;

    /**
     * Recupera il valore della proprieta' protocolloAggiornamentoAzione.
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
     * Imposta il valore della proprieta' protocolloAggiornamentoAzione.
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
     * Recupera il valore della proprieta' codiceVersamento.
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
     * Imposta il valore della proprieta' codiceVersamento.
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
     * Recupera il valore della proprieta' idOperazione.
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
     * Imposta il valore della proprieta' idOperazione.
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
