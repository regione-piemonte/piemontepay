/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.25 alle 10:35:00 AM CEST 
//


package it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per FlussoDettaglioType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FlussoDettaglioType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AnagraficaPagatore" type="{http://www.csi.it/epay/epaywso/types}SoggettoType" minOccurs="0"/&gt;
 *         &lt;element name="DescrizioneCausaleVersamento" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="Causale" type="{http://www.csi.it/epay/epaywso/types}String135Type" minOccurs="0"/&gt;
 *         &lt;element name="DataPagamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="EsitoPagamento" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/&gt;
 *         &lt;element name="ImportoSingoloVersamento" type="{http://www.csi.it/epay/epaywso/types}ImportoSignedType" minOccurs="0"/&gt;
 *         &lt;element name="IndiceSingoloVersamento" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType" minOccurs="0"/&gt;
 *         &lt;element name="Transactionid" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoUnivocoVersamento" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoUnivocoRiscossione" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlussoDettaglioType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "anagraficaPagatore",
    "descrizioneCausaleVersamento",
    "causale",
    "dataPagamento",
    "esitoPagamento",
    "importoSingoloVersamento",
    "indiceSingoloVersamento",
    "transactionid",
    "identificativoUnivocoVersamento",
    "identificativoUnivocoRiscossione"
})
public class FlussoDettaglioType {

    @XmlElement(name = "AnagraficaPagatore")
    protected SoggettoType anagraficaPagatore;
    @XmlElement(name = "DescrizioneCausaleVersamento")
    protected String descrizioneCausaleVersamento;
    @XmlElement(name = "Causale")
    protected String causale;
    @XmlElement(name = "DataPagamento")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPagamento;
    @XmlElement(name = "EsitoPagamento")
    protected String esitoPagamento;
    @XmlElement(name = "ImportoSingoloVersamento")
    protected BigDecimal importoSingoloVersamento;
    @XmlElement(name = "IndiceSingoloVersamento")
    @XmlSchemaType(name = "integer")
    protected Integer indiceSingoloVersamento;
    @XmlElement(name = "Transactionid")
    protected String transactionid;
    @XmlElement(name = "IdentificativoUnivocoVersamento")
    protected String identificativoUnivocoVersamento;
    @XmlElement(name = "IdentificativoUnivocoRiscossione")
    protected String identificativoUnivocoRiscossione;

    /**
     * Recupera il valore della propriet anagraficaPagatore.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoType }
     *     
     */
    public SoggettoType getAnagraficaPagatore() {
        return anagraficaPagatore;
    }

    /**
     * Imposta il valore della propriet anagraficaPagatore.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoType }
     *     
     */
    public void setAnagraficaPagatore(SoggettoType value) {
        this.anagraficaPagatore = value;
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

    /**
     * Recupera il valore della propriet identificativoUnivocoVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoVersamento() {
        return identificativoUnivocoVersamento;
    }

    /**
     * Imposta il valore della propriet identificativoUnivocoVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoVersamento(String value) {
        this.identificativoUnivocoVersamento = value;
    }

    /**
     * Recupera il valore della propriet identificativoUnivocoRiscossione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoRiscossione() {
        return identificativoUnivocoRiscossione;
    }

    /**
     * Imposta il valore della propriet identificativoUnivocoRiscossione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoRiscossione(String value) {
        this.identificativoUnivocoRiscossione = value;
    }

}
