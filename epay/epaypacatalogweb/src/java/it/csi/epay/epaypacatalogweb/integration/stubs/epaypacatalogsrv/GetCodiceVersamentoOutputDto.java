/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCodiceVersamentoOutputDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCodiceVersamentoOutputDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bicAppoggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceModalitaIntegrazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVersamentoPadre" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoOutputDto" minOccurs="0"/>
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiciVersamentoCollegati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoOutputDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fattura" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagInvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbPrimario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbSecondario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagPresenzaBollettinoPostale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ibanAppoggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ibanAppoggioPostale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ibanPostale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamentoSecondarioCollegato" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEnteSecondarioCollegato" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="flagPersonalizzazioneCov" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="descrizioneTextCov" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strPassphrase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCredenzialiPnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlAttualizzazionePnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagVisualizzaDaSportello" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dtFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dtInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCodiceVersamentoOutputDto", propOrder = {
	"bic",
	"bicAppoggio",
	"codice",
	"codiceMacrotipo",
	"codiceModalitaIntegrazione",
	"codiceStatoAggiornamento",
	"codiceTematica",
	"codiceTipoPagamento",
	"codiceVersamentoPadre",
	"codiceVoceEntrata",
	"codiciVersamentoCollegati",
	"descrizione",
	"descrizioneErroreAggiornamento",
	"descrizioneMacrotipo",
	"descrizioneStatoAggiornamento",
	"descrizioneTematica",
	"descrizioneTipoPagamento",
	"descrizioneVoceEntrata",
	"email",
	"fattura",
	"flagInvioFlussi",
	"flagMbPrimario",
	"flagMbSecondario",
	"flagPresenzaBollettinoPostale",
	"iban",
	"ibanAppoggio",
	"ibanAppoggioPostale",
	"ibanPostale",
	"id",
	"idCodiceVersamentoSecondarioCollegato",
	"idEnteSecondarioCollegato",
	"idVoceEntrata",
	"mbModalita",
	"mbEnteSecondario",
	"mbCodiceVersamentoAssociato",
	"flagPersonalizzazioneCov",
	"descrizioneTextCov",
	"strPassphrase",
	"strCredenzialiPnd",
	"urlAttualizzazionePnd",
	"flagVisualizzaDaSportello",
	"dtInizioValidita",
	"dtFineValidita"
	
})

public class GetCodiceVersamentoOutputDto {

	protected String bic;
	protected String bicAppoggio;
	protected String codice;
	protected String codiceMacrotipo;
	protected String codiceModalitaIntegrazione;
	protected String codiceStatoAggiornamento;
	protected String codiceTematica;
	protected String codiceTipoPagamento;
	protected GetCodiceVersamentoOutputDto codiceVersamentoPadre;
	protected String codiceVoceEntrata;
	@XmlElement(nillable = true)
	protected List<GetCodiceVersamentoOutputDto> codiciVersamentoCollegati;
	protected String descrizione;
	protected String descrizioneErroreAggiornamento;
	protected String descrizioneMacrotipo;
	protected String descrizioneStatoAggiornamento;
	protected String descrizioneTematica;
	protected String descrizioneTipoPagamento;
	protected String descrizioneVoceEntrata;
	protected String email;
	protected Boolean fattura;
	protected Boolean flagInvioFlussi;
	protected Boolean flagMbPrimario;
	protected Boolean flagMbSecondario;
	protected Boolean flagPresenzaBollettinoPostale;
	protected String iban;
	protected String ibanAppoggio;
	protected Boolean ibanAppoggioPostale;
	protected Boolean ibanPostale;
	protected Long id;
	protected Long idCodiceVersamentoSecondarioCollegato;
	protected Long idEnteSecondarioCollegato;
	protected Long idVoceEntrata;

	protected String mbModalita;
	protected String mbEnteSecondario;
	protected String mbCodiceVersamentoAssociato;
	
	protected Boolean flagPersonalizzazioneCov;
	protected String descrizioneTextCov;
	protected String strPassphrase;
	
    private String strCredenzialiPnd;
    private String urlAttualizzazionePnd;
    

	protected Boolean flagVisualizzaDaSportello;
	protected Date dtInizioValidita;
	protected Date dtFineValidita;
	
	
	
	

	/**
	 * Gets the value of the bic property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getBic() {
		return bic;
	}

	/**
	 * Sets the value of the bic property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setBic(String value) {
		this.bic = value;
	}

	/**
	 * Gets the value of the bicAppoggio property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getBicAppoggio() {
		return bicAppoggio;
	}

	/**
	 * Sets the value of the bicAppoggio property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setBicAppoggio(String value) {
		this.bicAppoggio = value;
	}

	/**
	 * Gets the value of the codice property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * Sets the value of the codice property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCodice(String value) {
		this.codice = value;
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
	 * Gets the value of the codiceModalitaIntegrazione property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCodiceModalitaIntegrazione() {
		return codiceModalitaIntegrazione;
	}

	/**
	 * Sets the value of the codiceModalitaIntegrazione property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCodiceModalitaIntegrazione(String value) {
		this.codiceModalitaIntegrazione = value;
	}

	/**
	 * Gets the value of the codiceStatoAggiornamento property.
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
	 * Sets the value of the codiceStatoAggiornamento property.
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
	 * Gets the value of the codiceTipoPagamento property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCodiceTipoPagamento() {
		return codiceTipoPagamento;
	}

	/**
	 * Sets the value of the codiceTipoPagamento property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCodiceTipoPagamento(String value) {
		this.codiceTipoPagamento = value;
	}

	/**
	 * Gets the value of the codiceVersamentoPadre property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link GetCodiceVersamentoOutputDto }
	 *     
	 */
	public GetCodiceVersamentoOutputDto getCodiceVersamentoPadre() {
		return codiceVersamentoPadre;
	}

	/**
	 * Sets the value of the codiceVersamentoPadre property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link GetCodiceVersamentoOutputDto }
	 *     
	 */
	public void setCodiceVersamentoPadre(GetCodiceVersamentoOutputDto value) {
		this.codiceVersamentoPadre = value;
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
	 * Gets the value of the codiciVersamentoCollegati property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the codiciVersamentoCollegati property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getCodiciVersamentoCollegati().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link GetCodiceVersamentoOutputDto }
	 * 
	 * 
	 */
	public List<GetCodiceVersamentoOutputDto> getCodiciVersamentoCollegati() {
		if (codiciVersamentoCollegati == null) {
			codiciVersamentoCollegati = new ArrayList<> ();
		}
		return this.codiciVersamentoCollegati;
	}

	/**
	 * Gets the value of the descrizione property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Sets the value of the descrizione property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDescrizione(String value) {
		this.descrizione = value;
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
	 * Gets the value of the descrizioneStatoAggiornamento property.
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
	 * Sets the value of the descrizioneStatoAggiornamento property.
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
	 * Gets the value of the descrizioneTipoPagamento property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getDescrizioneTipoPagamento() {
		return descrizioneTipoPagamento;
	}

	/**
	 * Sets the value of the descrizioneTipoPagamento property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDescrizioneTipoPagamento(String value) {
		this.descrizioneTipoPagamento = value;
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
	 * Gets the value of the email property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the fattura property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *     
	 */
	public Boolean getFattura() {
		return fattura;
	}

	/**
	 * Sets the value of the fattura property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *     
	 */
	public void setFattura(Boolean value) {
		this.fattura = value;
	}

	/**
	 * Gets the value of the flagInvioFlussi property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *     
	 */
	public Boolean getFlagInvioFlussi() {
		return flagInvioFlussi;
	}

	/**
	 * Sets the value of the flagInvioFlussi property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *     
	 */
	public void setFlagInvioFlussi(Boolean value) {
		this.flagInvioFlussi = value;
	}

	/**
	 * Gets the value of the flagMbPrimario property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *     
	 */
	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	/**
	 * Sets the value of the flagMbPrimario property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *     
	 */
	public void setFlagMbPrimario(Boolean value) {
		this.flagMbPrimario = value;
	}

	/**
	 * Gets the value of the flagMbSecondario property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *     
	 */
	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	/**
	 * Sets the value of the flagMbSecondario property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *     
	 */
	public void setFlagMbSecondario(Boolean value) {
		this.flagMbSecondario = value;
	}

	/**
	 * Gets the value of the flagPresenzaBollettinoPostale property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *     
	 */
	public Boolean getFlagPresenzaBollettinoPostale() {
		return flagPresenzaBollettinoPostale;
	}

	/**
	 * Sets the value of the flagPresenzaBollettinoPostale property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *     
	 */
	public void setFlagPresenzaBollettinoPostale(Boolean value) {
		this.flagPresenzaBollettinoPostale = value;
	}

	/**
	 * Gets the value of the iban property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * Sets the value of the iban property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setIban(String value) {
		this.iban = value;
	}

	/**
	 * Gets the value of the ibanAppoggio property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getIbanAppoggio() {
		return ibanAppoggio;
	}

	/**
	 * Sets the value of the ibanAppoggio property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setIbanAppoggio(String value) {
		this.ibanAppoggio = value;
	}

	/**
	 * Gets the value of the ibanAppoggioPostale property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *     
	 */
	public Boolean getIbanAppoggioPostale() {
		return ibanAppoggioPostale;
	}

	/**
	 * Sets the value of the ibanAppoggioPostale property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *     
	 */
	public void setIbanAppoggioPostale(Boolean value) {
		this.ibanAppoggioPostale = value;
	}

	/**
	 * Gets the value of the ibanPostale property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *     
	 */
	public Boolean getIbanPostale() {
		return ibanPostale;
	}

	/**
	 * Sets the value of the ibanPostale property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *     
	 */
	public void setIbanPostale(Boolean value) {
		this.ibanPostale = value;
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
	 * Gets the value of the idCodiceVersamentoSecondarioCollegato property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Long }
	 *     
	 */
	public Long getIdCodiceVersamentoSecondarioCollegato() {
		return idCodiceVersamentoSecondarioCollegato;
	}

	/**
	 * Sets the value of the idCodiceVersamentoSecondarioCollegato property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Long }
	 *     
	 */
	public void setIdCodiceVersamentoSecondarioCollegato(Long value) {
		this.idCodiceVersamentoSecondarioCollegato = value;
	}

	/**
	 * Gets the value of the idEnteSecondarioCollegato property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Long }
	 *     
	 */
	public Long getIdEnteSecondarioCollegato() {
		return idEnteSecondarioCollegato;
	}

	/**
	 * Sets the value of the idEnteSecondarioCollegato property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Long }
	 *     
	 */
	public void setIdEnteSecondarioCollegato(Long value) {
		this.idEnteSecondarioCollegato = value;
	}

	/**
	 * Gets the value of the idVoceEntrata property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Long }
	 *     
	 */
	public Long getIdVoceEntrata() {
		return idVoceEntrata;
	}

	/**
	 * Sets the value of the idVoceEntrata property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Long }
	 *     
	 */
	public void setIdVoceEntrata(Long value) {
		this.idVoceEntrata = value;
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

	public String getMbCodiceVersamentoAssociato () {
		return mbCodiceVersamentoAssociato;
	}

	public void setMbCodiceVersamentoAssociato ( String mbCodiceVersamentoAssociato ) {
		this.mbCodiceVersamentoAssociato = mbCodiceVersamentoAssociato;
	}

	public void setCodiciVersamentoCollegati ( List<GetCodiceVersamentoOutputDto> codiciVersamentoCollegati ) {
		this.codiciVersamentoCollegati = codiciVersamentoCollegati;
	}

    
    /**
     * @return the flagPersonalizzazioneCov
     */
    public Boolean getFlagPersonalizzazioneCov () {
        return flagPersonalizzazioneCov;
    }

    
    
    /**
     * @return the descrizioneTextCov
     */
    public String getDescrizioneTextCov () {
        return descrizioneTextCov;
    }

    
    /**
     * @param descrizioneTextCov the descrizioneTextCov to set
     */
    public void setDescrizioneTextCov ( String descrizioneTextCov ) {
        this.descrizioneTextCov = descrizioneTextCov;
    }

    /**
     * @param flagPersonalizzazioneCov the flagPersonalizzazioneCov to set
     */
    public void setFlagPersonalizzazioneCov ( Boolean flagPersonalizzazioneCov ) {
        this.flagPersonalizzazioneCov = flagPersonalizzazioneCov;
    }

    
    /**
     * @return the strPassphrase
     */
    public String getStrPassphrase () {
        return strPassphrase;
    }

    
    /**
     * @param strPassphrase the strPassphrase to set
     */
    public void setStrPassphrase ( String strPassphrase ) {
        this.strPassphrase = strPassphrase;
    }

    
    /**
     * @return the strCredenzialiPnd
     */
    public String getStrCredenzialiPnd () {
        return strCredenzialiPnd;
    }

    
    /**
     * @param strCredenzialiPnd the strCredenzialiPnd to set
     */
    public void setStrCredenzialiPnd ( String strCredenzialiPnd ) {
        this.strCredenzialiPnd = strCredenzialiPnd;
    }

    
    /**
     * @return the urlAttualizzazionePnd
     */
    public String getUrlAttualizzazionePnd () {
        return urlAttualizzazionePnd;
    }

    
    /**
     * @param urlAttualizzazionePnd the urlAttualizzazionePnd to set
     */
    public void setUrlAttualizzazionePnd ( String urlAttualizzazionePnd ) {
        this.urlAttualizzazionePnd = urlAttualizzazionePnd;
    }
    
       public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }

    
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }

    
    public Date getDtInizioValidita () {
        return dtInizioValidita;
    }

    
    public void setDtInizioValidita ( Date dtInizioValidita ) {
        this.dtInizioValidita = dtInizioValidita;
    }

    
    public Date getDtFineValidita () {
        return dtFineValidita;
    }

    
    public void setDtFineValidita ( Date dtFineValidita ) {
        this.dtFineValidita = dtFineValidita;
    }
	
	

}
