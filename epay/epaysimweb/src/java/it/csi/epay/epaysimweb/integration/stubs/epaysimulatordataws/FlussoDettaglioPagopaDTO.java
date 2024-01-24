/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per flussoDettaglioPagopaDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="flussoDettaglioPagopaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accertamentoAnno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="accertamentoNumero" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="anagraficaPagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codicefiscalePagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataPagamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="datiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneCausaleVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="esitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnicoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnicoVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="indiceSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="simTFlussoSintesiPagopa" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoSintesiPagopaDTO" minOccurs="0"/&gt;
 *         &lt;element name="statoInvioFruitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoAnagraficaPagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transactionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flussoDettaglioPagopaDTO", propOrder = {
    "accertamentoAnno",
    "accertamentoNumero",
    "anagraficaPagatore",
    "causale",
    "codiceVersamento",
    "codicefiscalePagatore",
    "dataPagamento",
    "datiSpecificiDiRiscossione",
    "descrizioneCausaleVersamento",
    "esitoPagamento",
    "id",
    "identificativoUnicoRiscossione",
    "identificativoUnicoVersamento",
    "importoSingoloVersamento",
    "indiceSingoloVersamento",
    "simTFlussoSintesiPagopa",
    "statoInvioFruitore",
    "tipoAnagraficaPagatore",
    "transactionid"
})
public class FlussoDettaglioPagopaDTO
    extends ParentInput
{

    protected Integer accertamentoAnno;
    protected Integer accertamentoNumero;
    protected String anagraficaPagatore;
    protected String causale;
    protected String codiceVersamento;
    protected String codicefiscalePagatore;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPagamento;
    protected String datiSpecificiDiRiscossione;
    protected String descrizioneCausaleVersamento;
    protected String esitoPagamento;
    protected Long id;
    protected String identificativoUnicoRiscossione;
    protected String identificativoUnicoVersamento;
    protected BigDecimal importoSingoloVersamento;
    protected Integer indiceSingoloVersamento;
    protected FlussoSintesiPagopaDTO simTFlussoSintesiPagopa;
    protected String statoInvioFruitore;
    protected String tipoAnagraficaPagatore;
    protected String transactionid;

    /**
     * Recupera il valore della propriet accertamentoAnno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccertamentoAnno() {
        return accertamentoAnno;
    }

    /**
     * Imposta il valore della propriet accertamentoAnno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccertamentoAnno(Integer value) {
        this.accertamentoAnno = value;
    }

    /**
     * Recupera il valore della propriet accertamentoNumero.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccertamentoNumero() {
        return accertamentoNumero;
    }

    /**
     * Imposta il valore della propriet accertamentoNumero.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccertamentoNumero(Integer value) {
        this.accertamentoNumero = value;
    }

    /**
     * Recupera il valore della propriet anagraficaPagatore.
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
     * Imposta il valore della propriet anagraficaPagatore.
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
     * Recupera il valore della propriet causale.
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
     * Imposta il valore della propriet causale.
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
     * Recupera il valore della propriet codiceVersamento.
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
     * Imposta il valore della propriet codiceVersamento.
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
     * Recupera il valore della propriet codicefiscalePagatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodicefiscalePagatore() {
        return codicefiscalePagatore;
    }

    /**
     * Imposta il valore della propriet codicefiscalePagatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodicefiscalePagatore(String value) {
        this.codicefiscalePagatore = value;
    }

    /**
     * Recupera il valore della propriet dataPagamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Imposta il valore della propriet dataPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPagamento(XMLGregorianCalendar value) {
        this.dataPagamento = value;
    }

    /**
     * Recupera il valore della propriet datiSpecificiDiRiscossione.
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
     * Imposta il valore della propriet datiSpecificiDiRiscossione.
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
     * Recupera il valore della propriet descrizioneCausaleVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCausaleVersamento() {
        return descrizioneCausaleVersamento;
    }

    /**
     * Imposta il valore della propriet descrizioneCausaleVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCausaleVersamento(String value) {
        this.descrizioneCausaleVersamento = value;
    }

    /**
     * Recupera il valore della propriet esitoPagamento.
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
     * Imposta il valore della propriet esitoPagamento.
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
     * Recupera il valore della propriet id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet identificativoUnicoRiscossione.
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
     * Imposta il valore della propriet identificativoUnicoRiscossione.
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
     * Recupera il valore della propriet identificativoUnicoVersamento.
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
     * Imposta il valore della propriet identificativoUnicoVersamento.
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
     * Recupera il valore della propriet importoSingoloVersamento.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    /**
     * Imposta il valore della propriet importoSingoloVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoSingoloVersamento(BigDecimal value) {
        this.importoSingoloVersamento = value;
    }

    /**
     * Recupera il valore della propriet indiceSingoloVersamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndiceSingoloVersamento() {
        return indiceSingoloVersamento;
    }

    /**
     * Imposta il valore della propriet indiceSingoloVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndiceSingoloVersamento(Integer value) {
        this.indiceSingoloVersamento = value;
    }

    /**
     * Recupera il valore della propriet simTFlussoSintesiPagopa.
     * 
     * @return
     *     possible object is
     *     {@link FlussoSintesiPagopaDTO }
     *     
     */
    public FlussoSintesiPagopaDTO getSimTFlussoSintesiPagopa() {
        return simTFlussoSintesiPagopa;
    }

    /**
     * Imposta il valore della propriet simTFlussoSintesiPagopa.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoSintesiPagopaDTO }
     *     
     */
    public void setSimTFlussoSintesiPagopa(FlussoSintesiPagopaDTO value) {
        this.simTFlussoSintesiPagopa = value;
    }

    /**
     * Recupera il valore della propriet statoInvioFruitore.
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
     * Imposta il valore della propriet statoInvioFruitore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoInvioFruitore(String value) {
        this.statoInvioFruitore = value;
    }

    /**
     * Recupera il valore della propriet tipoAnagraficaPagatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAnagraficaPagatore() {
        return tipoAnagraficaPagatore;
    }

    /**
     * Imposta il valore della propriet tipoAnagraficaPagatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAnagraficaPagatore(String value) {
        this.tipoAnagraficaPagatore = value;
    }

    /**
     * Recupera il valore della propriet transactionid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionid() {
        return transactionid;
    }

    /**
     * Imposta il valore della propriet transactionid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionid(String value) {
        this.transactionid = value;
    }

}
