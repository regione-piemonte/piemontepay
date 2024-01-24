/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Ente" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}EnteType"/&gt;
 *         &lt;element name="CodiciVersamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}CodiciVersamentoType"/&gt;
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
     * Recupera il valore della proprietaente.
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
     * Imposta il valore della proprietaente.
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
     * Recupera il valore della proprietacodiciVersamento.
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
     * Imposta il valore della proprietacodiciVersamento.
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
