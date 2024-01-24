/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoFlussoDettaglio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoFlussoDettaglio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anagraficaPagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceFiscalePagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="esitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flussoSintesi" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoSintesi" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idTransaction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnicoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnicoVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="indiceSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statoInvioFruitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoFlussoDettaglio", propOrder = {
    "anagraficaPagatore",
    "causale",
    "codiceFiscalePagatore",
    "codiceVersamento",
    "dataPagamento",
    "datiSpecificiDiRiscossione",
    "descrizioneVersamento",
    "esitoPagamento",
    "flussoSintesi",
    "id",
    "idTransaction",
    "identificativoUnicoRiscossione",
    "identificativoUnicoVersamento",
    "importoSingoloVersamento",
    "indiceSingoloVersamento",
    "statoInvioFruitore"
})
public class DtoFlussoDettaglio {

    protected String anagraficaPagatore;
    protected String causale;
    protected String codiceFiscalePagatore;
    protected String codiceVersamento;
    protected String dataPagamento;
    protected String datiSpecificiDiRiscossione;
    protected String descrizioneVersamento;
    protected String esitoPagamento;
    protected DtoFlussoSintesi flussoSintesi;
    protected String id;
    protected String idTransaction;
    protected String identificativoUnicoRiscossione;
    protected String identificativoUnicoVersamento;
    protected String importoSingoloVersamento;
    protected String indiceSingoloVersamento;
    protected String statoInvioFruitore;

    /**
     * Recupera il valore della proprieta anagraficaPagatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnagraficaPagatore() {
        return anagraficaPagatore;
    }

    /**
     * Imposta il valore della proprieta anagraficaPagatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnagraficaPagatore(String value) {
        this.anagraficaPagatore = value;
    }

    /**
     * Recupera il valore della proprieta causale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausale() {
        return causale;
    }

    /**
     * Imposta il valore della proprieta causale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausale(String value) {
        this.causale = value;
    }

    /**
     * Recupera il valore della proprieta codiceFiscalePagatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscalePagatore() {
        return codiceFiscalePagatore;
    }

    /**
     * Imposta il valore della proprieta codiceFiscalePagatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscalePagatore(String value) {
        this.codiceFiscalePagatore = value;
    }

    /**
     * Recupera il valore della proprieta codiceVersamento.
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
     * Imposta il valore della proprieta codiceVersamento.
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
     * Recupera il valore della proprieta dataPagamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Imposta il valore della proprieta dataPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataPagamento(String value) {
        this.dataPagamento = value;
    }

    /**
     * Recupera il valore della proprieta datiSpecificiDiRiscossione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatiSpecificiDiRiscossione() {
        return datiSpecificiDiRiscossione;
    }

    /**
     * Imposta il valore della proprieta datiSpecificiDiRiscossione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatiSpecificiDiRiscossione(String value) {
        this.datiSpecificiDiRiscossione = value;
    }

    /**
     * Recupera il valore della proprieta descrizioneVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneVersamento() {
        return descrizioneVersamento;
    }

    /**
     * Imposta il valore della proprieta descrizioneVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneVersamento(String value) {
        this.descrizioneVersamento = value;
    }

    /**
     * Recupera il valore della proprieta esitoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsitoPagamento() {
        return esitoPagamento;
    }

    /**
     * Imposta il valore della proprieta esitoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsitoPagamento(String value) {
        this.esitoPagamento = value;
    }

    /**
     * Recupera il valore della proprieta flussoSintesi.
     * 
     * @return
     *     possible object is
     *     {@link DtoFlussoSintesi }
     *     
     */
    public DtoFlussoSintesi getFlussoSintesi() {
        return flussoSintesi;
    }

    /**
     * Imposta il valore della proprieta flussoSintesi.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoFlussoSintesi }
     *     
     */
    public void setFlussoSintesi(DtoFlussoSintesi value) {
        this.flussoSintesi = value;
    }

    /**
     * Recupera il valore della proprieta id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprieta id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprieta idTransaction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTransaction() {
        return idTransaction;
    }

    /**
     * Imposta il valore della proprieta idTransaction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTransaction(String value) {
        this.idTransaction = value;
    }

    /**
     * Recupera il valore della proprieta identificativoUnicoRiscossione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnicoRiscossione() {
        return identificativoUnicoRiscossione;
    }

    /**
     * Imposta il valore della proprieta identificativoUnicoRiscossione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnicoRiscossione(String value) {
        this.identificativoUnicoRiscossione = value;
    }

    /**
     * Recupera il valore della proprieta identificativoUnicoVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnicoVersamento() {
        return identificativoUnicoVersamento;
    }

    /**
     * Imposta il valore della proprieta identificativoUnicoVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnicoVersamento(String value) {
        this.identificativoUnicoVersamento = value;
    }

    /**
     * Recupera il valore della proprieta importoSingoloVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    /**
     * Imposta il valore della proprieta importoSingoloVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportoSingoloVersamento(String value) {
        this.importoSingoloVersamento = value;
    }

    /**
     * Recupera il valore della proprieta indiceSingoloVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiceSingoloVersamento() {
        return indiceSingoloVersamento;
    }

    /**
     * Imposta il valore della proprieta indiceSingoloVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiceSingoloVersamento(String value) {
        this.indiceSingoloVersamento = value;
    }

    /**
     * Recupera il valore della proprieta statoInvioFruitore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoInvioFruitore() {
        return statoInvioFruitore;
    }

    /**
     * Imposta il valore della proprieta statoInvioFruitore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoInvioFruitore(String value) {
        this.statoInvioFruitore = value;
    }

}
