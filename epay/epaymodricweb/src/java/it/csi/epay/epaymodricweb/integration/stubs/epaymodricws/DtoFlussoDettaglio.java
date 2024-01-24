/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoFlussoDettaglio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoFlussoDettaglio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="anagraficaPagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscalePagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flussoSintesi" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoSintesi" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idTransaction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoUnicoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoUnicoVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indiceSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoInvioFruitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the anagraficaPagatore property.
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
     * Sets the value of the anagraficaPagatore property.
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
     * Gets the value of the causale property.
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
     * Sets the value of the causale property.
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
     * Gets the value of the codiceFiscalePagatore property.
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
     * Sets the value of the codiceFiscalePagatore property.
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
     * Gets the value of the codiceVersamento property.
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
     * Sets the value of the codiceVersamento property.
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
     * Gets the value of the dataPagamento property.
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
     * Sets the value of the dataPagamento property.
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
     * Gets the value of the datiSpecificiDiRiscossione property.
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
     * Sets the value of the datiSpecificiDiRiscossione property.
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
     * Gets the value of the descrizioneVersamento property.
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
     * Sets the value of the descrizioneVersamento property.
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
     * Gets the value of the esitoPagamento property.
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
     * Sets the value of the esitoPagamento property.
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
     * Gets the value of the flussoSintesi property.
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
     * Sets the value of the flussoSintesi property.
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
     * Gets the value of the id property.
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
     * Sets the value of the id property.
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
     * Gets the value of the idTransaction property.
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
     * Sets the value of the idTransaction property.
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
     * Gets the value of the identificativoUnicoRiscossione property.
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
     * Sets the value of the identificativoUnicoRiscossione property.
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
     * Gets the value of the identificativoUnicoVersamento property.
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
     * Sets the value of the identificativoUnicoVersamento property.
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
     * Gets the value of the importoSingoloVersamento property.
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
     * Sets the value of the importoSingoloVersamento property.
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
     * Gets the value of the indiceSingoloVersamento property.
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
     * Sets the value of the indiceSingoloVersamento property.
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
     * Gets the value of the statoInvioFruitore property.
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
     * Sets the value of the statoInvioFruitore property.
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
