/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.flussorendicontazione;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DatiSingoloPagamentoType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DatiSingoloPagamentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IUV" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="IUR" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="IndiceDatiPagamento">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CodiceEsitoPagamento">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]{1,1}"/>
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="3"/>
 *               &lt;enumeration value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SingoloImportoPagato" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/>
 *         &lt;element name="DataEsitoSingoloPagamento" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType"/>
 *         &lt;element name="DatiSpecificiRiscossione" type="{http://www.csi.it/epay/epaywso/types}String140Type"/>
 *         &lt;element name="DescrizioneCausaleVersamento" type="{http://www.csi.it/epay/epaywso/types}String140Type"/>
 *         &lt;element name="TransactionId" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="AnagraficaPagatore" type="{http://www.csi.it/epay/epaywso/types}SoggettoType"/>
 *         &lt;element name="AnagraficaVersante" type="{http://www.csi.it/epay/epaywso/types}SoggettoType"/>
 *         &lt;element name="AnnoAccertamento" type="{http://www.csi.it/epay/epaywso/types}AnnoType"/>
 *         &lt;element name="NumeroAccertamento" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType"/>
 *         &lt;element name="TipoRicevuta" type="{http://www.w3.org/2001/XMLSchema}TipoRicevuta" form="qualified"/>
 *         &lt;element name="CategoriaIUV" type="{http://www.w3.org/2001/XMLSchema}CategoriaIUV" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiSingoloPagamentoType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", propOrder = {
    "iuv",
    "iur",
    "indiceDatiPagamento",
    "codiceEsitoPagamento",
    "singoloImportoPagato",
    "dataEsitoSingoloPagamento",
    "codiceVersamento",
    "datiSpecificiRiscossione",
    "descrizioneCausaleVersamento",
    "transactionId",
    "anagraficaPagatore",
    "anagraficaVersante",
    "annoAccertamento",
    "numeroAccertamento",
    "tipoRicevuta",
    "categoriaIUV"
})
public class DatiSingoloPagamentoType {

    @XmlElement(name = "IUV", required = true)
    protected String iuv;
    @XmlElement(name = "IUR", required = true)
    protected String iur;
    @XmlElement(name = "IndiceDatiPagamento")
    protected int indiceDatiPagamento;
    @XmlElement(name = "CodiceEsitoPagamento", required = true)
    protected String codiceEsitoPagamento;
    @XmlElement(name = "SingoloImportoPagato", required = true)
    protected BigDecimal singoloImportoPagato;
    @XmlElement(name = "DataEsitoSingoloPagamento", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataEsitoSingoloPagamento;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;
    @XmlElement(name = "DatiSpecificiRiscossione", required = true)
    protected String datiSpecificiRiscossione;
    @XmlElement(name = "DescrizioneCausaleVersamento", required = true)
    protected String descrizioneCausaleVersamento;
    @XmlElement(name = "TransactionId", required = true)
    protected String transactionId;
    @XmlElement(name = "AnagraficaPagatore", required = true)
    protected SoggettoType anagraficaPagatore;
    @XmlElement(name = "AnagraficaVersante", required = true)
    protected SoggettoType anagraficaVersante;
    @XmlElement ( name = "AnnoAccertamento", type = Integer.class )
    protected Integer annoAccertamento;
    @XmlElement ( name = "NumeroAccertamento", type = Integer.class )
    protected Integer numeroAccertamento;
    @XmlElement(name = "tipoRicevuta")
    protected TipoRicevuta tipoRicevuta;
    @XmlElement(name = "categoriaIUV")
    protected CategoriaIUV categoriaIUV;
    
    /**
     * Gets the value of the iuv property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIUV() {
        return iuv;
    }

    /**
     * Sets the value of the iuv property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIUV(String value) {
        this.iuv = value;
    }

    /**
     * Gets the value of the iur property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIUR() {
        return iur;
    }

    /**
     * Sets the value of the iur property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIUR(String value) {
        this.iur = value;
    }

    /**
     * Gets the value of the indiceDatiPagamento property.
     *
     */
    public int getIndiceDatiPagamento() {
        return indiceDatiPagamento;
    }

    /**
     * Sets the value of the indiceDatiPagamento property.
     *
     */
    public void setIndiceDatiPagamento(int value) {
        this.indiceDatiPagamento = value;
    }

    /**
     * Gets the value of the codiceEsitoPagamento property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodiceEsitoPagamento() {
        return codiceEsitoPagamento;
    }

    /**
     * Sets the value of the codiceEsitoPagamento property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodiceEsitoPagamento(String value) {
        this.codiceEsitoPagamento = value;
    }

    /**
     * Gets the value of the singoloImportoPagato property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getSingoloImportoPagato() {
        return singoloImportoPagato;
    }

    /**
     * Sets the value of the singoloImportoPagato property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setSingoloImportoPagato(BigDecimal value) {
        this.singoloImportoPagato = value;
    }

    /**
     * Gets the value of the dataEsitoSingoloPagamento property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataEsitoSingoloPagamento() {
        return dataEsitoSingoloPagamento;
    }

    /**
     * Sets the value of the dataEsitoSingoloPagamento property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDataEsitoSingoloPagamento(XMLGregorianCalendar value) {
        this.dataEsitoSingoloPagamento = value;
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
     * Gets the value of the datiSpecificiRiscossione property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    /**
     * Sets the value of the datiSpecificiRiscossione property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDatiSpecificiRiscossione(String value) {
        this.datiSpecificiRiscossione = value;
    }

    /**
     * Gets the value of the descrizioneCausaleVersamento property.
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
     * Sets the value of the descrizioneCausaleVersamento property.
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
     * Gets the value of the transactionId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the anagraficaPagatore property.
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
     * Sets the value of the anagraficaPagatore property.
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
     * Gets the value of the anagraficaVersante property.
     *
     * @return
     *     possible object is
     *     {@link SoggettoType }
     *
     */
    public SoggettoType getAnagraficaVersante() {
        return anagraficaVersante;
    }

    /**
     * Sets the value of the anagraficaVersante property.
     *
     * @param value
     *     allowed object is
     *     {@link SoggettoType }
     *
     */
    public void setAnagraficaVersante(SoggettoType value) {
        this.anagraficaVersante = value;
    }

    /**
     * Gets the value of the annoAccertamento property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAnnoAccertamento() {
        return annoAccertamento;
    }

    /**
     * Sets the value of the annoAccertamento property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAnnoAccertamento(Integer value) {
        this.annoAccertamento = value;
    }

    /**
     * Gets the value of the numeroAccertamento property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getNumeroAccertamento() {
        return numeroAccertamento;
    }

    /**
     * Sets the value of the numeroAccertamento property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setNumeroAccertamento(Integer value) {
        this.numeroAccertamento = value;
    }

    /**
     * @return the tipoRicevuta
     */
    public TipoRicevuta getTipoRicevuta () {
        return tipoRicevuta;
    }

    /**
     * @param tipoRicevuta the tipoRicevuta to set
     */
    public void setTipoRicevuta ( TipoRicevuta tipoRicevuta ) {
        this.tipoRicevuta = tipoRicevuta;
    }

    /**
     * @return the categoriaIUV
     */
    public CategoriaIUV getCategoriaIUV () {
        return categoriaIUV;
    }

    /**
     * @param categoriaIUV the categoriaIUV to set
     */
    public void setCategoriaIUV ( CategoriaIUV categoriaIUV ) {
        this.categoriaIUV = categoriaIUV;
    }

}
