/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inserisciRiferimentoContabileInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciRiferimentoContabileInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="annoAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="datoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="livelloPdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciRiferimentoContabileInput", propOrder = {
    "annoAccertamento",
    "annoEsercizio",
    "categoria",
    "codiceTipologiaDatoSpecificoRiscossione",
    "dataFineValidita",
    "dataInizioValidita",
    "datoSpecificoRiscossione",
    "descrizioneDatoSpecificoRiscossione",
    "idCodiceVersamento",
    "livelloPdc",
    "numeroAccertamento",
    "numeroArticolo",
    "numeroCapitolo",
    "tipologia",
    "titolo"
})
public class InserisciRiferimentoContabileInput
    extends ParentInput
{

    protected Integer annoAccertamento;
    protected Integer annoEsercizio;
    protected String categoria;
    protected String codiceTipologiaDatoSpecificoRiscossione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineValidita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioValidita;
    protected String datoSpecificoRiscossione;
    protected String descrizioneDatoSpecificoRiscossione;
    protected Long idCodiceVersamento;
    protected String livelloPdc;
    protected Integer numeroAccertamento;
    protected Integer numeroArticolo;
    protected Integer numeroCapitolo;
    protected String tipologia;
    protected String titolo;

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
     * Gets the value of the annoEsercizio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoEsercizio() {
        return annoEsercizio;
    }

    /**
     * Sets the value of the annoEsercizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoEsercizio(Integer value) {
        this.annoEsercizio = value;
    }

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the codiceTipologiaDatoSpecificoRiscossione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipologiaDatoSpecificoRiscossione() {
        return codiceTipologiaDatoSpecificoRiscossione;
    }

    /**
     * Sets the value of the codiceTipologiaDatoSpecificoRiscossione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipologiaDatoSpecificoRiscossione(String value) {
        this.codiceTipologiaDatoSpecificoRiscossione = value;
    }

    /**
     * Gets the value of the dataFineValidita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Sets the value of the dataFineValidita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Gets the value of the dataInizioValidita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Sets the value of the dataInizioValidita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Gets the value of the datoSpecificoRiscossione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatoSpecificoRiscossione() {
        return datoSpecificoRiscossione;
    }

    /**
     * Sets the value of the datoSpecificoRiscossione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatoSpecificoRiscossione(String value) {
        this.datoSpecificoRiscossione = value;
    }

    /**
     * Gets the value of the descrizioneDatoSpecificoRiscossione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneDatoSpecificoRiscossione() {
        return descrizioneDatoSpecificoRiscossione;
    }

    /**
     * Sets the value of the descrizioneDatoSpecificoRiscossione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneDatoSpecificoRiscossione(String value) {
        this.descrizioneDatoSpecificoRiscossione = value;
    }

    /**
     * Gets the value of the idCodiceVersamento property.
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
     * Sets the value of the idCodiceVersamento property.
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
     * Gets the value of the livelloPdc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivelloPdc() {
        return livelloPdc;
    }

    /**
     * Sets the value of the livelloPdc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivelloPdc(String value) {
        this.livelloPdc = value;
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
     * Gets the value of the numeroArticolo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroArticolo() {
        return numeroArticolo;
    }

    /**
     * Sets the value of the numeroArticolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroArticolo(Integer value) {
        this.numeroArticolo = value;
    }

    /**
     * Gets the value of the numeroCapitolo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroCapitolo() {
        return numeroCapitolo;
    }

    /**
     * Sets the value of the numeroCapitolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroCapitolo(Integer value) {
        this.numeroCapitolo = value;
    }

    /**
     * Gets the value of the tipologia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * Sets the value of the tipologia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologia(String value) {
        this.tipologia = value;
    }

    /**
     * Gets the value of the titolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Sets the value of the titolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

}
