/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per appGatewayInformativa complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="appGatewayInformativa"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}appGateway"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="condizioniEconomicheMassime" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioValidita" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="dataPubblicazione" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="datainserimento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneServizio" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="disponibilitaServizio" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="idInformativaPSP" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *         &lt;element name="identificativoCanale" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoIntermediario" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoPSP" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="modelloPagamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *         &lt;element name="ordinamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *         &lt;element name="origine" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="priorita" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *         &lt;element name="ragioneSociale" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="statoinserimento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="stornoPagamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *         &lt;element name="tipoVersamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="urlInformazioniPSP" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "appGatewayInformativa", propOrder = {
				"condizioniEconomicheMassime",
				"dataInizioValidita",
				"dataPubblicazione",
				"datainserimento",
				"descrizioneServizio",
				"disponibilitaServizio",
				"idInformativaPSP",
				"identificativoCanale",
				"identificativoFlusso",
				"identificativoIntermediario",
				"identificativoPSP",
				"modelloPagamento",
				"ordinamento",
				"origine",
				"priorita",
				"ragioneSociale",
				"statoinserimento",
				"stornoPagamento",
				"tipoVersamento",
				"urlInformazioniPSP"
} )
@SuppressWarnings ( "unused" )
public class AppGatewayInformativa
				extends AppGateway {

	protected String condizioniEconomicheMassime;

	protected String dataInizioValidita;

	protected String dataPubblicazione;

	@XmlSchemaType ( name = "dateTime" )
	protected XMLGregorianCalendar datainserimento;

	protected String descrizioneServizio;

	protected String disponibilitaServizio;

	protected Integer idInformativaPSP;

	protected String identificativoCanale;

	protected String identificativoFlusso;

	protected String identificativoIntermediario;

	protected String identificativoPSP;

	protected Integer modelloPagamento;

	protected Integer ordinamento;

	protected String origine;

	protected Integer priorita;

	protected String ragioneSociale;

	protected String statoinserimento;

	protected Integer stornoPagamento;

	protected String tipoVersamento;

	protected String urlInformazioniPSP;

	/**
	 * Recupera il valore della proprieta' condizioniEconomicheMassime.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCondizioniEconomicheMassime () {
		return condizioniEconomicheMassime;
	}

	/**
	 * Imposta il valore della proprieta' condizioniEconomicheMassime.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCondizioniEconomicheMassime ( String value ) {
		this.condizioniEconomicheMassime = value;
	}

	/**
	 * Recupera il valore della proprieta' dataInizioValidita.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDataInizioValidita () {
		return dataInizioValidita;
	}

	/**
	 * Imposta il valore della proprieta' dataInizioValidita.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDataInizioValidita ( String value ) {
		this.dataInizioValidita = value;
	}

	/**
	 * Recupera il valore della proprieta' dataPubblicazione.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDataPubblicazione () {
		return dataPubblicazione;
	}

	/**
	 * Imposta il valore della proprieta' dataPubblicazione.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDataPubblicazione ( String value ) {
		this.dataPubblicazione = value;
	}

	/**
	 * Recupera il valore della proprieta' datainserimento.
	 *
	 * @return possible object is
	 * {@link XMLGregorianCalendar }
	 */
	public XMLGregorianCalendar getDatainserimento () {
		return datainserimento;
	}

	/**
	 * Imposta il valore della proprieta' datainserimento.
	 *
	 * @param value allowed object is
	 *              {@link XMLGregorianCalendar }
	 */
	public void setDatainserimento ( XMLGregorianCalendar value ) {
		this.datainserimento = value;
	}

	/**
	 * Recupera il valore della proprieta' descrizioneServizio.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDescrizioneServizio () {
		return descrizioneServizio;
	}

	/**
	 * Imposta il valore della proprieta' descrizioneServizio.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDescrizioneServizio ( String value ) {
		this.descrizioneServizio = value;
	}

	/**
	 * Recupera il valore della proprieta' disponibilitaServizio.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDisponibilitaServizio () {
		return disponibilitaServizio;
	}

	/**
	 * Imposta il valore della proprieta' disponibilitaServizio.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDisponibilitaServizio ( String value ) {
		this.disponibilitaServizio = value;
	}

	/**
	 * Recupera il valore della proprieta' idInformativaPSP.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getIdInformativaPSP () {
		return idInformativaPSP;
	}

	/**
	 * Imposta il valore della proprieta' idInformativaPSP.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setIdInformativaPSP ( Integer value ) {
		this.idInformativaPSP = value;
	}

	/**
	 * Recupera il valore della proprieta' identificativoCanale.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIdentificativoCanale () {
		return identificativoCanale;
	}

	/**
	 * Imposta il valore della proprieta' identificativoCanale.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIdentificativoCanale ( String value ) {
		this.identificativoCanale = value;
	}

	/**
	 * Recupera il valore della proprieta' identificativoFlusso.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIdentificativoFlusso () {
		return identificativoFlusso;
	}

	/**
	 * Imposta il valore della proprieta' identificativoFlusso.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIdentificativoFlusso ( String value ) {
		this.identificativoFlusso = value;
	}

	/**
	 * Recupera il valore della proprieta' identificativoIntermediario.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIdentificativoIntermediario () {
		return identificativoIntermediario;
	}

	/**
	 * Imposta il valore della proprieta' identificativoIntermediario.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIdentificativoIntermediario ( String value ) {
		this.identificativoIntermediario = value;
	}

	/**
	 * Recupera il valore della proprieta' identificativoPSP.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIdentificativoPSP () {
		return identificativoPSP;
	}

	/**
	 * Imposta il valore della proprieta' identificativoPSP.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIdentificativoPSP ( String value ) {
		this.identificativoPSP = value;
	}

	/**
	 * Recupera il valore della proprieta' modelloPagamento.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getModelloPagamento () {
		return modelloPagamento;
	}

	/**
	 * Imposta il valore della proprieta' modelloPagamento.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setModelloPagamento ( Integer value ) {
		this.modelloPagamento = value;
	}

	/**
	 * Recupera il valore della proprieta' ordinamento.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getOrdinamento () {
		return ordinamento;
	}

	/**
	 * Imposta il valore della proprieta' ordinamento.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setOrdinamento ( Integer value ) {
		this.ordinamento = value;
	}

	/**
	 * Recupera il valore della proprieta' origine.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getOrigine () {
		return origine;
	}

	/**
	 * Imposta il valore della proprieta' origine.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setOrigine ( String value ) {
		this.origine = value;
	}

	/**
	 * Recupera il valore della proprieta' priorita.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getPriorita () {
		return priorita;
	}

	/**
	 * Imposta il valore della proprieta' priorita.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setPriorita ( Integer value ) {
		this.priorita = value;
	}

	/**
	 * Recupera il valore della proprieta' ragioneSociale.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getRagioneSociale () {
		return ragioneSociale;
	}

	/**
	 * Imposta il valore della proprieta' ragioneSociale.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setRagioneSociale ( String value ) {
		this.ragioneSociale = value;
	}

	/**
	 * Recupera il valore della proprieta' statoinserimento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getStatoinserimento () {
		return statoinserimento;
	}

	/**
	 * Imposta il valore della proprieta' statoinserimento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setStatoinserimento ( String value ) {
		this.statoinserimento = value;
	}

	/**
	 * Recupera il valore della proprieta' stornoPagamento.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getStornoPagamento () {
		return stornoPagamento;
	}

	/**
	 * Imposta il valore della proprieta' stornoPagamento.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setStornoPagamento ( Integer value ) {
		this.stornoPagamento = value;
	}

	/**
	 * Recupera il valore della proprieta' tipoVersamento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTipoVersamento () {
		return tipoVersamento;
	}

	/**
	 * Imposta il valore della proprieta' tipoVersamento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTipoVersamento ( String value ) {
		this.tipoVersamento = value;
	}

	/**
	 * Recupera il valore della proprieta' urlInformazioniPSP.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getUrlInformazioniPSP () {
		return urlInformazioniPSP;
	}

	/**
	 * Imposta il valore della proprieta' urlInformazioniPSP.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setUrlInformazioniPSP ( String value ) {
		this.urlInformazioniPSP = value;
	}

}
