/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per getRiferimentoContabileOutputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getRiferimentoContabileOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="annoAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="datoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datoSpecificoRiscossioneTassonomia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="livelloPdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="storico" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getRiferimentoContabileStoricoOutputDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFineValiditaCodiceTassonomico" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioValiditaCodiceTassonomico" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="mbEnteSecondario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mbCodiceVersamentoAssociato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mbRiferimentoContabileAssociato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoServizioTassonomia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         

 *         
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRiferimentoContabileOutputDto", propOrder = {
	"annoAccertamento",
	"annoEsercizio",
	"categoria",
	"codiceCodiceVersamento",
	"codiceMacrotipo",
	"codiceStatoAggiornamento",
	"codiceTematica",
	"codiceTipologiaDatoSpecificoRiscossione",
	"codiceVoceEntrata",
	"dataFineValidita",
	"dataInizioValidita",
	"datoSpecificoRiscossione",
	"datoSpecificoRiscossioneTassonomia",
	"descrizioneCodiceVersamento",
	"descrizioneDatoSpecificoRiscossione",
	"descrizioneErroreAggiornamento",
	"descrizioneMacrotipo",
	"descrizioneStatoAggiornamento",
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
	"titolo",
	"flagElementiMultibeneficiario",
	"mbModalita",
	"dataFineValiditaCodiceTassonomico",
	"dataInizioValiditaCodiceTassonomico",
	"mbEnteSecondario",
	"mbCodiceVersamentoAssociato",
	"mbRiferimentoContabileAssociato",
	"tipoServizioTassonomia"
	
})
public class GetRiferimentoContabileOutputDto {

	protected Integer annoAccertamento;
	protected Integer annoEsercizio;
	protected String categoria;
	protected String codiceCodiceVersamento;
	protected String codiceMacrotipo;
	protected String codiceStatoAggiornamento;
	protected String codiceTematica;
	protected String codiceTipologiaDatoSpecificoRiscossione;
	protected String codiceVoceEntrata;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataFineValidita;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataInizioValidita;
	protected String datoSpecificoRiscossione;
	protected String datoSpecificoRiscossioneTassonomia;
	protected String descrizioneCodiceVersamento;
	protected String descrizioneDatoSpecificoRiscossione;
	protected String descrizioneErroreAggiornamento;
	protected String descrizioneMacrotipo;
	protected String descrizioneStatoAggiornamento;
	protected String descrizioneTematica;
	protected String descrizioneTipologiaDatoSpecificoRiscossione;
	protected String descrizioneVoceEntrata;
	protected Long id;
	protected Long idCodiceVersamento;
	protected String livelloPdc;
	protected Integer numeroAccertamento;
	protected Integer numeroArticolo;
	protected String numeroCapitolo;
	@XmlElement(nillable = true)
	protected List<GetRiferimentoContabileStoricoOutputDto> storico;
	protected String tipologia;
	protected String titolo;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataFineValiditaCodiceTassonomico;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataInizioValiditaCodiceTassonomico;

	protected Boolean flagElementiMultibeneficiario;

	protected String mbModalita;

	protected String mbEnteSecondario;

	protected String mbCodiceVersamentoAssociato;
	
	protected String mbRiferimentoContabileAssociato;
	
	protected String tipoServizioTassonomia;


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
	 * Recupera il valore della propriet codiceCodiceVersamento.
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
	 * Imposta il valore della propriet codiceCodiceVersamento.
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
	 * Recupera il valore della propriet codiceMacrotipo.
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
	 * Imposta il valore della propriet codiceMacrotipo.
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
	 * Recupera il valore della propriet codiceTematica.
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
	 * Imposta il valore della propriet codiceTematica.
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
	 * Recupera il valore della propriet codiceVoceEntrata.
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
	 * Imposta il valore della propriet codiceVoceEntrata.
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
	 * Recupera il valore della propriet descrizioneCodiceVersamento.
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
	 * Imposta il valore della propriet descrizioneCodiceVersamento.
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
	 * Recupera il valore della propriet descrizioneMacrotipo.
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
	 * Imposta il valore della propriet descrizioneMacrotipo.
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
	 * Recupera il valore della propriet descrizioneTematica.
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
	 * Imposta il valore della propriet descrizioneTematica.
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
	 * Recupera il valore della propriet descrizioneVoceEntrata.
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
	 * Imposta il valore della propriet descrizioneVoceEntrata.
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
	 * Recupera il valore della propriet idCodiceVersamento.
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
	 * Imposta il valore della propriet idCodiceVersamento.
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
	 * {@link GetRiferimentoContabileStoricoOutputDto }
	 * 
	 * 
	 */
	public List<GetRiferimentoContabileStoricoOutputDto> getStorico() {
		if (storico == null) {
			storico = new ArrayList<>();
		}
		return this.storico;
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

	public Boolean getFlagElementiMultibeneficiario () {
		return flagElementiMultibeneficiario;
	}

	public void setFlagElementiMultibeneficiario ( Boolean flagElementiMultibeneficiario ) {
		this.flagElementiMultibeneficiario = flagElementiMultibeneficiario;
	}

	public void setStorico ( List<GetRiferimentoContabileStoricoOutputDto> storico ) {
		this.storico = storico;
	}

	public String getMbModalita () {
		return mbModalita;
	}

	public void setMbModalita ( String mbModalita ) {
		this.mbModalita = mbModalita;
	}

	public String getMbEnteSecondario () {
		return mbEnteSecondario;
	}

	public void setMbEnteSecondario ( String mbEnteSecondario ) {
		this.mbEnteSecondario = mbEnteSecondario;
	}
	/**
	 * @return the dataFineValiditaCodiceTassonomico
	 */
	public XMLGregorianCalendar getDataFineValiditaCodiceTassonomico() {
		return dataFineValiditaCodiceTassonomico;
	}

	/**
	 * @param dataFineValiditaCodiceTassonomico the dataFineValiditaCodiceTassonomico to set
	 */
	public void setDataFineValiditaCodiceTassonomico(XMLGregorianCalendar dataFineValiditaCodiceTassonomico) {
		this.dataFineValiditaCodiceTassonomico = dataFineValiditaCodiceTassonomico;
	}

	/**
	 * @return the dataInizioValiditaCodiceTassonomico
	 */
	public XMLGregorianCalendar getDataInizioValiditaCodiceTassonomico() {
		return dataInizioValiditaCodiceTassonomico;
	}

	/**
	 * @param dataInizioValiditaCodiceTassonomico the dataInizioValiditaCodiceTassonomico to set
	 */
	public void setDataInizioValiditaCodiceTassonomico(XMLGregorianCalendar dataInizioValiditaCodiceTassonomico) {
		this.dataInizioValiditaCodiceTassonomico = dataInizioValiditaCodiceTassonomico;
	}

	public String getMbCodiceVersamentoAssociato () {
		return mbCodiceVersamentoAssociato;
	}

	public void setMbCodiceVersamentoAssociato ( String mbCodiceVersamentoAssociato ) {
		this.mbCodiceVersamentoAssociato = mbCodiceVersamentoAssociato;
	}

    
    /**
     * @return the mbRiferimentoContabileAssociato
     */
    public String getMbRiferimentoContabileAssociato () {
        return mbRiferimentoContabileAssociato;
    }

    
    /**
     * @param mbRiferimentoContabileAssociato the mbRiferimentoContabileAssociato to set
     */
    public void setMbRiferimentoContabileAssociato ( String mbRiferimentoContabileAssociato ) {
        this.mbRiferimentoContabileAssociato = mbRiferimentoContabileAssociato;
    }

    
    public String getDatoSpecificoRiscossioneTassonomia () {
        return datoSpecificoRiscossioneTassonomia;
    }

    
    public void setDatoSpecificoRiscossioneTassonomia ( String datoSpecificoRiscossioneTassonomia ) {
        this.datoSpecificoRiscossioneTassonomia = datoSpecificoRiscossioneTassonomia;
    }

    
    /**
     * @return the tipoServizioTassonomia
     */
    public String getTipoServizioTassonomia () {
        return tipoServizioTassonomia;
    }

    
    /**
     * @param tipoServizioTassonomia the tipoServizioTassonomia to set
     */
    public void setTipoServizioTassonomia ( String tipoServizioTassonomia ) {
        this.tipoServizioTassonomia = tipoServizioTassonomia;
    }
	
	

}
