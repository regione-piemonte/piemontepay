/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaRiferimentoContabileInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabileInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="soloRiferimentiInVita" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentoContabileInput", propOrder = {
    "codiceMacrotipo",
    "codiceTematica",
    "codiceVoceEntrata",
    "descrizioneCodiceVersamento",
    "idCodiceVersamento",
    "soloRiferimentiInVita"
})
public class RicercaRiferimentoContabileInput
    extends ParentInput
{

    protected String codiceMacrotipo;
    protected String codiceTematica;
    protected String codiceVoceEntrata;
    protected String descrizioneCodiceVersamento;
    protected Long idCodiceVersamento;
    protected Boolean soloRiferimentiInVita;

    /**
     * Recupera il valore della propriet codiceMacrotipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceMacrotipo() {
        return codiceMacrotipo;
    }

    /**
     * Imposta il valore della propriet codiceMacrotipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceMacrotipo(String value) {
        this.codiceMacrotipo = value;
    }

    /**
     * Recupera il valore della propriet codiceTematica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTematica() {
        return codiceTematica;
    }

    /**
     * Imposta il valore della propriet codiceTematica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTematica(String value) {
        this.codiceTematica = value;
    }

    /**
     * Recupera il valore della propriet codiceVoceEntrata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVoceEntrata() {
        return codiceVoceEntrata;
    }

    /**
     * Imposta il valore della propriet codiceVoceEntrata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVoceEntrata(String value) {
        this.codiceVoceEntrata = value;
    }

    /**
     * Recupera il valore della propriet descrizioneCodiceVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    /**
     * Imposta il valore della propriet descrizioneCodiceVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCodiceVersamento(String value) {
        this.descrizioneCodiceVersamento = value;
    }

    /**
     * Recupera il valore della propriet idCodiceVersamento.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCodiceVersamento() {
        return idCodiceVersamento;
    }

    /**
     * Imposta il valore della propriet idCodiceVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCodiceVersamento(Long value) {
        this.idCodiceVersamento = value;
    }

    /**
     * Recupera il valore della propriet soloRiferimentiInVita.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSoloRiferimentiInVita() {
        return soloRiferimentiInVita;
    }

    /**
     * Imposta il valore della propriet soloRiferimentiInVita.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSoloRiferimentiInVita(Boolean value) {
        this.soloRiferimentiInVita = value;
    }

}
