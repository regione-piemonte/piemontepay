/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ricercaRiferimentoContabileOutputDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabileOutputDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="annoAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="datoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="livelloPdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="storico" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaRiferimentoContabileStoricoOutputDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentoContabileOutputDto", propOrder = {
    "annoAccertamento",
    "annoEsercizio",
    "categoria",
    "codiceCodiceVersamento",
    "codiceMacrotipo",
    "codiceTematica",
    "codiceTipologiaDatoSpecificoRiscossione",
    "codiceVoceEntrata",
    "dataFineValidita",
    "dataInizioValidita",
    "datoSpecificoRiscossione",
    "descrizioneCodiceVersamento",
    "descrizioneDatoSpecificoRiscossione",
    "descrizioneErroreAggiornamento",
    "descrizioneMacrotipo",
    "descrizioneTematica",
    "descrizioneTipologiaDatoSpecificoRiscossione",
    "descrizioneVoceEntrata",
    "id",
    "idCodiceVersamento",
    "livelloPdc",
    "numeroAccertamento",
    "numeroArticolo",
    "numeroCapitolo",
    "storico",
    "tipologia",
    "titolo"
})
public class RicercaRiferimentoContabileOutputDto {

    protected Integer annoAccertamento;
    protected Integer annoEsercizio;
    protected String categoria;
    protected String codiceCodiceVersamento;
    protected String codiceMacrotipo;
    protected String codiceTematica;
    protected String codiceTipologiaDatoSpecificoRiscossione;
    protected String codiceVoceEntrata;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineValidita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioValidita;
    protected String datoSpecificoRiscossione;
    protected String descrizioneCodiceVersamento;
    protected String descrizioneDatoSpecificoRiscossione;
    protected String descrizioneErroreAggiornamento;
    protected String descrizioneMacrotipo;
    protected String descrizioneTematica;
    protected String descrizioneTipologiaDatoSpecificoRiscossione;
    protected String descrizioneVoceEntrata;
    protected Long id;
    protected Long idCodiceVersamento;
    protected String livelloPdc;
    protected Integer numeroAccertamento;
    protected Integer numeroArticolo;
    protected Integer numeroCapitolo;
    @XmlElement(nillable = true)
    protected List<RicercaRiferimentoContabileStoricoOutputDto> storico;
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
     * Gets the value of the codiceCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCodiceVersamento() {
        return codiceCodiceVersamento;
    }

    /**
     * Sets the value of the codiceCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCodiceVersamento(String value) {
        this.codiceCodiceVersamento = value;
    }

    /**
     * Gets the value of the codiceMacrotipo property.
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
     * Sets the value of the codiceMacrotipo property.
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
     * Gets the value of the codiceTematica property.
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
     * Sets the value of the codiceTematica property.
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
     * Gets the value of the codiceVoceEntrata property.
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
     * Sets the value of the codiceVoceEntrata property.
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
     * Gets the value of the descrizioneCodiceVersamento property.
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
     * Sets the value of the descrizioneCodiceVersamento property.
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
     * Gets the value of the descrizioneErroreAggiornamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneErroreAggiornamento() {
        return descrizioneErroreAggiornamento;
    }

    /**
     * Sets the value of the descrizioneErroreAggiornamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneErroreAggiornamento(String value) {
        this.descrizioneErroreAggiornamento = value;
    }

    /**
     * Gets the value of the descrizioneMacrotipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneMacrotipo() {
        return descrizioneMacrotipo;
    }

    /**
     * Sets the value of the descrizioneMacrotipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneMacrotipo(String value) {
        this.descrizioneMacrotipo = value;
    }

    /**
     * Gets the value of the descrizioneTematica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTematica() {
        return descrizioneTematica;
    }

    /**
     * Sets the value of the descrizioneTematica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTematica(String value) {
        this.descrizioneTematica = value;
    }

    /**
     * Gets the value of the descrizioneTipologiaDatoSpecificoRiscossione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTipologiaDatoSpecificoRiscossione() {
        return descrizioneTipologiaDatoSpecificoRiscossione;
    }

    /**
     * Sets the value of the descrizioneTipologiaDatoSpecificoRiscossione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTipologiaDatoSpecificoRiscossione(String value) {
        this.descrizioneTipologiaDatoSpecificoRiscossione = value;
    }

    /**
     * Gets the value of the descrizioneVoceEntrata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneVoceEntrata() {
        return descrizioneVoceEntrata;
    }

    /**
     * Sets the value of the descrizioneVoceEntrata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneVoceEntrata(String value) {
        this.descrizioneVoceEntrata = value;
    }

    /**
     * Gets the value of the id property.
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
     * Sets the value of the id property.
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
     * Gets the value of the storico property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storico property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStorico().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RicercaRiferimentoContabileStoricoOutputDto }
     * 
     * 
     */
    public List<RicercaRiferimentoContabileStoricoOutputDto> getStorico() {
        if (storico == null) {
            storico = new ArrayList<RicercaRiferimentoContabileStoricoOutputDto>();
        }
        return this.storico;
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
