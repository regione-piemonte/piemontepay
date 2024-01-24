/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.xsd.trasmissioneflussi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per FlussiInErroreType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FlussiInErroreType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdFlusso" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="IdPsp" type="{http://www.csi.it/epay/epaywso/types}String70Type"/&gt;
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType" minOccurs="0"/&gt;
 *         &lt;element name="CodiceErrore" type="{http://www.csi.it/epay/epaywso/types}String30Type"/&gt;
 *         &lt;element name="DescrizioneErrore" type="{http://www.csi.it/epay/epaywso/types}String500Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlussiInErroreType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "idFlusso",
    "idPsp",
    "codiceVersamento",
    "codiceErrore",
    "descrizioneErrore"
})
public class FlussiInErroreType {

    @XmlElement(name = "IdFlusso", required = true)
    protected String idFlusso;
    @XmlElement(name = "IdPsp", required = true)
    protected String idPsp;
    @XmlElement(name = "CodiceVersamento")
    protected String codiceVersamento;
    @XmlElement(name = "CodiceErrore", required = true)
    protected String codiceErrore;
    @XmlElement(name = "DescrizioneErrore", required = true)
    protected String descrizioneErrore;

    /**
     * Recupera il valore della proprieta' idFlusso.
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
     * Imposta il valore della proprieta' idFlusso.
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
     * Recupera il valore della proprieta' idPsp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPsp() {
        return idPsp;
    }

    /**
     * Imposta il valore della proprieta' idPsp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPsp(String value) {
        this.idPsp = value;
    }

    /**
     * Recupera il valore della proprieta' codiceVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    /**
     * Imposta il valore della proprieta' codiceVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVersamento(String value) {
        this.codiceVersamento = value;
    }

    /**
     * Recupera il valore della proprieta' codiceErrore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceErrore() {
        return codiceErrore;
    }

    /**
     * Imposta il valore della proprieta' codiceErrore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceErrore(String value) {
        this.codiceErrore = value;
    }

    /**
     * Recupera il valore della proprieta' descrizioneErrore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneErrore() {
        return descrizioneErrore;
    }

    /**
     * Imposta il valore della proprieta' descrizioneErrore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneErrore(String value) {
        this.descrizioneErrore = value;
    }

}
