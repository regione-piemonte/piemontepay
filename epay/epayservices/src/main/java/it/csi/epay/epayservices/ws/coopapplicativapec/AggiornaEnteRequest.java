/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.coopapplicativapec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="Ente" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}EnteType"/>
 *         &lt;element name="CodiciVersamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}CodiciVersamentoType"/>
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
    "ente",
    "codiciVersamento",
    "idOperazione"
})
@XmlRootElement(name = "AggiornaEnteRequest")
public class AggiornaEnteRequest {

    @XmlElement(name = "Ente", required = true)
    protected EnteType ente;
    @XmlElement(name = "CodiciVersamento", required = true)
    protected CodiciVersamentoType codiciVersamento;
    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;

    /**
     * Recupera il valore della proprieta' ente.
     * 
     * @return
     *     possible object is
     *     {@link EnteType }
     *     
     */
    public EnteType getEnte() {
        return ente;
    }

    /**
     * Imposta il valore della proprieta' ente.
     * 
     * @param value
     *     allowed object is
     *     {@link EnteType }
     *     
     */
    public void setEnte(EnteType value) {
        this.ente = value;
    }

    /**
     * Recupera il valore della proprieta' codiciVersamento.
     * 
     * @return
     *     possible object is
     *     {@link CodiciVersamentoType }
     *     
     */
    public CodiciVersamentoType getCodiciVersamento() {
        return codiciVersamento;
    }

    /**
     * Imposta il valore della proprieta' codiciVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiciVersamentoType }
     *     
     */
    public void setCodiciVersamento(CodiciVersamentoType value) {
        this.codiciVersamento = value;
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
