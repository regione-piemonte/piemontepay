/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.io.Serializable;
import java.util.Date;


public class GetRiferimentoContabileStoricoOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer annoAccertamento;

	private Integer annoEsercizio;

	private Date dataFineValidita;

	private Date dataInizioValidita;

	private String livelloPdc;

	private Integer numeroAccertamento;

	private Integer numeroArticolo;

	private String numeroCapitolo;

	private String titolo;

	private String codiceTipologiaDatoSpecificoRiscossione;

	private String descrizioneTipologiaDatoSpecificoRiscossione;

	private String datoSpecificoRiscossione;

	private String descrizioneDatoSpecificoRiscossione;

	private String categoria;

	private String tipologia;

	private String codiceStatoAggiornamento;

	private String descrizioneStatoAggiornamento;

	private String descrizioneErroreAggiornamento;

	private Boolean flagElementiMultibeneficiario;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public Integer getAnnoEsercizio () {
		return annoEsercizio;
	}

	public void setAnnoEsercizio ( Integer annoEsercizio ) {
		this.annoEsercizio = annoEsercizio;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public Date getDataInizioValidita () {
		return dataInizioValidita;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getLivelloPdc () {
		return livelloPdc;
	}

	public void setLivelloPdc ( String livelloPdc ) {
		this.livelloPdc = livelloPdc;
	}

	public Integer getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( Integer numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public Integer getNumeroArticolo () {
		return numeroArticolo;
	}

	public void setNumeroArticolo ( Integer numeroArticolo ) {
		this.numeroArticolo = numeroArticolo;
	}

	public String getNumeroCapitolo () {
		return numeroCapitolo;
	}

	public void setNumeroCapitolo ( String numeroCapitolo ) {
		this.numeroCapitolo = numeroCapitolo;
	}

	public String getTitolo () {
		return titolo;
	}

	public void setTitolo ( String titolo ) {
		this.titolo = titolo;
	}

	public String getCodiceTipologiaDatoSpecificoRiscossione () {
		return codiceTipologiaDatoSpecificoRiscossione;
	}

	public void setCodiceTipologiaDatoSpecificoRiscossione ( String codiceTipologiaDatoSpecificoRiscossione ) {
		this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
	}

	public String getDescrizioneTipologiaDatoSpecificoRiscossione () {
		return descrizioneTipologiaDatoSpecificoRiscossione;
	}

	public void setDescrizioneTipologiaDatoSpecificoRiscossione ( String descrizioneTipologiaDatoSpecificoRiscossione ) {
		this.descrizioneTipologiaDatoSpecificoRiscossione = descrizioneTipologiaDatoSpecificoRiscossione;
	}

	public String getDatoSpecificoRiscossione () {
		return datoSpecificoRiscossione;
	}

	public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
		this.datoSpecificoRiscossione = datoSpecificoRiscossione;
	}

	public String getDescrizioneDatoSpecificoRiscossione () {
		return descrizioneDatoSpecificoRiscossione;
	}

	public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
		this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
	}

	public String getCategoria () {
		return categoria;
	}

	public void setCategoria ( String categoria ) {
		this.categoria = categoria;
	}

	public String getTipologia () {
		return tipologia;
	}

	public void setTipologia ( String tipologia ) {
		this.tipologia = tipologia;
	}

	public String getCodiceStatoAggiornamento () {
		return codiceStatoAggiornamento;
	}

	public void setCodiceStatoAggiornamento ( String codiceStatoAggiornamento ) {
		this.codiceStatoAggiornamento = codiceStatoAggiornamento;
	}

	public String getDescrizioneStatoAggiornamento () {
		return descrizioneStatoAggiornamento;
	}

	public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
		this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
	}

	public String getDescrizioneErroreAggiornamento () {
		return descrizioneErroreAggiornamento;
	}

	public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
		this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
	}

	@Override
	public String toString () {
		return "GetRiferimentoContabileStoricoOutputDto [id=" + id + ", annoAccertamento=" + annoAccertamento + ", annoEsercizio=" + annoEsercizio
						+ ", dataFineValidita=" + dataFineValidita + ", dataInizioValidita=" + dataInizioValidita + ", livelloPdc="
										+ livelloPdc + ", numeroAccertamento=" + numeroAccertamento + ", numeroArticolo=" + numeroArticolo + ", numeroCapitolo=" + numeroCapitolo
										+ ", titolo=" + titolo + ", codiceTipologiaDatoSpecificoRiscossione=" + codiceTipologiaDatoSpecificoRiscossione
										+ ", descrizioneTipologiaDatoSpecificoRiscossione=" + descrizioneTipologiaDatoSpecificoRiscossione + ", datoSpecificoRiscossione="
										+ datoSpecificoRiscossione + ", descrizioneDatoSpecificoRiscossione=" + descrizioneDatoSpecificoRiscossione + ", categoria=" + categoria
										+ ", tipologia=" + tipologia + ", codiceStatoAggiornamento=" + codiceStatoAggiornamento + ", descrizioneStatoAggiornamento="
										+ descrizioneStatoAggiornamento + ", descrizioneErroreAggiornamento=" + descrizioneErroreAggiornamento + "]";
	}

	public Boolean getFlagElementiMultibeneficiario () {
		return flagElementiMultibeneficiario;
	}

	public void setFlagElementiMultibeneficiario ( Boolean flagElementiMultibeneficiario ) {
		this.flagElementiMultibeneficiario = flagElementiMultibeneficiario;
	}

}
