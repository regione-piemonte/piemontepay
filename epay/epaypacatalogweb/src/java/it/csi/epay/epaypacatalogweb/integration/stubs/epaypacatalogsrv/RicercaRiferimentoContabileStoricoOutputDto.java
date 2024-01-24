/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ricercaRiferimentoContabileStoricoOutputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabileStoricoOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="annoAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="datoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="livelloPdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentoContabileStoricoOutputDto", propOrder = {
	"annoAccertamento",
	"annoEsercizio",
	"categoria",
	"codiceStatoAggiornamento",
	"codiceTipologiaDatoSpecificoRiscossione",
	"dataFineValidita",
	"dataInizioValidita",
	"datoSpecificoRiscossione",
	"descrizioneDatoSpecificoRiscossione",
	"descrizioneErroreAggiornamento",
	"descrizioneStatoAggiornamento",
	"descrizioneTipologiaDatoSpecificoRiscossione",
	"id",
	"livelloPdc",
	"numeroAccertamento",
	"numeroArticolo",
	"numeroCapitolo",
	"tipologia",
	"titolo",
	"flagMbPrimario",
	"flagMbSecondario"
})
public class RicercaRiferimentoContabileStoricoOutputDto {

	protected Integer annoAccertamento;
	protected Integer annoEsercizio;
	protected String categoria;
	protected String codiceStatoAggiornamento;
	protected String codiceTipologiaDatoSpecificoRiscossione;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataFineValidita;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataInizioValidita;
	protected String datoSpecificoRiscossione;
	protected String descrizioneDatoSpecificoRiscossione;
	protected String descrizioneErroreAggiornamento;
	protected String descrizioneStatoAggiornamento;
	protected String descrizioneTipologiaDatoSpecificoRiscossione;
	protected Long id;
	protected String livelloPdc;
	protected Integer numeroAccertamento;
	protected Integer numeroArticolo;
	protected String numeroCapitolo;
	protected String tipologia;
	protected String titolo;

	protected Boolean flagMbPrimario;

	protected Boolean flagMbSecondario;

	/**
	 * Recupera il valore della propriet annoAccertamento.
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
	 * Imposta il valore della propriet annoAccertamento.
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
	 * Recupera il valore della propriet annoEsercizio.
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
	 * Imposta il valore della propriet annoEsercizio.
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
	 * Recupera il valore della propriet categoria.
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
	 * Imposta il valore della propriet categoria.
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
	 * Recupera il valore della propriet codiceStatoAggiornamento.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCodiceStatoAggiornamento() {
		return codiceStatoAggiornamento;
	}

	/**
	 * Imposta il valore della propriet codiceStatoAggiornamento.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCodiceStatoAggiornamento(String value) {
		this.codiceStatoAggiornamento = value;
	}

	/**
	 * Recupera il valore della propriet codiceTipologiaDatoSpecificoRiscossione.
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
	 * Imposta il valore della propriet codiceTipologiaDatoSpecificoRiscossione.
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
	 * Recupera il valore della propriet dataFineValidita.
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
	 * Imposta il valore della propriet dataFineValidita.
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
	 * Recupera il valore della propriet dataInizioValidita.
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
	 * Imposta il valore della propriet dataInizioValidita.
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
	 * Recupera il valore della propriet datoSpecificoRiscossione.
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
	 * Imposta il valore della propriet datoSpecificoRiscossione.
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
	 * Recupera il valore della propriet descrizioneDatoSpecificoRiscossione.
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
	 * Imposta il valore della propriet descrizioneDatoSpecificoRiscossione.
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
	 * Recupera il valore della propriet descrizioneErroreAggiornamento.
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
	 * Imposta il valore della propriet descrizioneErroreAggiornamento.
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
	 * Recupera il valore della propriet descrizioneStatoAggiornamento.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getDescrizioneStatoAggiornamento() {
		return descrizioneStatoAggiornamento;
	}

	/**
	 * Imposta il valore della propriet descrizioneStatoAggiornamento.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDescrizioneStatoAggiornamento(String value) {
		this.descrizioneStatoAggiornamento = value;
	}

	/**
	 * Recupera il valore della propriet descrizioneTipologiaDatoSpecificoRiscossione.
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
	 * Imposta il valore della propriet descrizioneTipologiaDatoSpecificoRiscossione.
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
	 * Recupera il valore della propriet livelloPdc.
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
	 * Imposta il valore della propriet livelloPdc.
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
	 * Recupera il valore della propriet numeroAccertamento.
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
	 * Imposta il valore della propriet numeroAccertamento.
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
	 * Recupera il valore della propriet numeroArticolo.
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
	 * Imposta il valore della propriet numeroArticolo.
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
	 * Recupera il valore della propriet numeroCapitolo.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Integer }
	 *     
	 */
	public String getNumeroCapitolo() {
		return numeroCapitolo;
	}

	/**
	 * Imposta il valore della propriet numeroCapitolo.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Integer }
	 *     
	 */
	public void setNumeroCapitolo(String value) {
		this.numeroCapitolo = value;
	}

	/**
	 * Recupera il valore della propriet tipologia.
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
	 * Imposta il valore della propriet tipologia.
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
	 * Recupera il valore della propriet titolo.
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
	 * Imposta il valore della propriet titolo.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTitolo(String value) {
		this.titolo = value;
	}

	public Boolean getFlagMbPrimario () {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Boolean getFlagMbSecondario () {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
		this.flagMbSecondario = flagMbSecondario;
	}

}
