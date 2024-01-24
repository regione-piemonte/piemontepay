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
 * <p>Classe Java per flussoDettaglioPagopaOutputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="flussoDettaglioPagopaOutputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="anagraficaPagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataPagamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneCausaleVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="esitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnicoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnicoVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="indiceSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="transactionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flussoSintesiPagopaOutputDTO" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoSintesiPagopaOutputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flussoDettaglioPagopaOutputDTO", propOrder = {
    "id",
    "anagraficaPagatore",
    "causale",
    "dataPagamento",
    "descrizioneCausaleVersamento",
    "esitoPagamento",
    "identificativoUnicoRiscossione",
    "identificativoUnicoVersamento",
    "importoSingoloVersamento",
    "indiceSingoloVersamento",
    "transactionid",
    "flussoSintesiPagopaOutputDTO"
})
public class FlussoDettaglioPagopaOutputDTO
    extends ParentOutput
{

    protected Long id;
    protected String anagraficaPagatore;
    protected String causale;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPagamento;
    protected String descrizioneCausaleVersamento;
    protected String esitoPagamento;
    protected String identificativoUnicoRiscossione;
    protected String identificativoUnicoVersamento;
    protected BigDecimal importoSingoloVersamento;
    protected Integer indiceSingoloVersamento;
    protected String transactionid;
    protected FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO;

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
     * Recupera il valore della propriet flussoSintesiPagopaOutputDTO.
     * 
     * @return
     *     possible object is
     *     {@link FlussoSintesiPagopaOutputDTO }
     *     
     */
    public FlussoSintesiPagopaOutputDTO getFlussoSintesiPagopaOutputDTO() {
        return flussoSintesiPagopaOutputDTO;
    }

    /**
     * Imposta il valore della propriet flussoSintesiPagopaOutputDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoSintesiPagopaOutputDTO }
     *     
     */
    public void setFlussoSintesiPagopaOutputDTO(FlussoSintesiPagopaOutputDTO value) {
        this.flussoSintesiPagopaOutputDTO = value;
    }

}
