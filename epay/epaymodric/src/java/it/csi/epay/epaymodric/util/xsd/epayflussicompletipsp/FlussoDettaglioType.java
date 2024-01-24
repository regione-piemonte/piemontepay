/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.SoggettoType;


/**
 * <p>Java class for FlussoDettaglioType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FlussoDettaglioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AnagraficaPagatore" type="{http://www.csi.it/epay/epaywso/types}SoggettoType"/>
 *         &lt;element name="DescrizioneCausaleVersamento" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/>
 *         &lt;element name="Causale" type="{http://www.csi.it/epay/epaywso/types}String135Type" minOccurs="0"/>
 *         &lt;element name="DataPagamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EsitoPagamento" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/>
 *         &lt;element name="ImportoSingoloVersamento" type="{http://www.csi.it/epay/epaywso/types}ImportoSignedType" minOccurs="0"/>
 *         &lt;element name="IndiceSingoloVersamento" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType" minOccurs="0"/>
 *         &lt;element name="Transactionid" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/>
 *         &lt;element name="IdentificativoUnivocoVersamento" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/>
 *         &lt;element name="IdentificativoUnivocoRiscossione" type="{http://www.csi.it/epay/epaywso/types}String70Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlussoDettaglioType", propOrder = {
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

    @XmlElement(name = "AnagraficaPagatore", required = true)
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
     * Gets the value of the dataPagamento property.
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
     * Sets the value of the dataPagamento property.
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
     * Gets the value of the importoSingoloVersamento property.
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
     * Sets the value of the importoSingoloVersamento property.
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
     * Gets the value of the indiceSingoloVersamento property.
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
     * Sets the value of the indiceSingoloVersamento property.
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
     * Gets the value of the transactionid property.
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
     * Sets the value of the transactionid property.
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
     * Gets the value of the identificativoUnivocoVersamento property.
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
     * Sets the value of the identificativoUnivocoVersamento property.
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
     * Gets the value of the identificativoUnivocoRiscossione property.
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
     * Sets the value of the identificativoUnivocoRiscossione property.
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
