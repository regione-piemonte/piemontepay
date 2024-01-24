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
 * <p>
 * Classe Java per anonymous complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="protocolloAggiornamentoAzione" type="{http://www.csi.it/epay/epaywso/types}ProtocolloAggiornamentoAzioneType"/&gt;
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}CodiceVersamentoType"/&gt;
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
     * Recupera il valore della proprietacodiceVersamento.
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
     * Imposta il valore della proprietacodiceVersamento.
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

}
