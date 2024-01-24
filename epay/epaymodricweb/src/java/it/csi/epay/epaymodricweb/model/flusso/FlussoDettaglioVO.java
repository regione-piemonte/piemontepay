/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.flusso;

import java.util.Date;

public class FlussoDettaglioVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;
	protected String anagraficaPagatore;
	protected String causale;
	protected String codiceFiscalePagatore;
	protected String codiceVersamento;
	protected Date dataPagamento;
	protected String datiSpecificiDiRiscossione;
	protected String descrizioneVersamento;
	protected String esitoPagamento;
	protected String idTransazione;
	protected String identificativoUnicoRiscossione;
	protected String identificativoUnicoVersamento;
	protected Double importoSingoloVersamento;
	protected Integer indiceSingoloVersamento;
	protected String statoInvioFruitore;

	public FlussoDettaglioVO() {
		// TODO Auto-generated constructor stub
	}

	public String getIdTransazione() {
		return idTransazione;
	}

	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnagraficaPagatore() {
		return anagraficaPagatore;
	}

	public void setAnagraficaPagatore(String anagraficaPagatore) {
		this.anagraficaPagatore = anagraficaPagatore;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getCodiceFiscalePagatore() {
		return codiceFiscalePagatore;
	}

	public void setCodiceFiscalePagatore(String codiceFiscalePagatore) {
		this.codiceFiscalePagatore = codiceFiscalePagatore;
	}

	public String getCodiceVersamento() {
		return codiceVersamento;
	}

	public void setCodiceVersamento(String codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDatiSpecificiDiRiscossione() {
		return datiSpecificiDiRiscossione;
	}

	public void setDatiSpecificiDiRiscossione(String datiSpecificiDiRiscossione) {
		this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
	}

	public String getDescrizioneVersamento() {
		return descrizioneVersamento;
	}

	public void setDescrizioneVersamento(String descrizioneVersamento) {
		this.descrizioneVersamento = descrizioneVersamento;
	}

	public String getEsitoPagamento() {
		return esitoPagamento;
	}

	public void setEsitoPagamento(String esitoPagamento) {
		this.esitoPagamento = esitoPagamento;
	}

	public String getIdentificativoUnicoRiscossione() {
		return identificativoUnicoRiscossione;
	}

	public void setIdentificativoUnicoRiscossione(String identificativoUnicoRiscossione) {
		this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
	}

	public String getIdentificativoUnicoVersamento() {
		return identificativoUnicoVersamento;
	}

	public void setIdentificativoUnicoVersamento(String identificativoUnicoVersamento) {
		this.identificativoUnicoVersamento = identificativoUnicoVersamento;
	}

	public Double getImportoSingoloVersamento() {
		return importoSingoloVersamento;
	}

	public void setImportoSingoloVersamento(Double importoSingoloVersamento) {
		this.importoSingoloVersamento = importoSingoloVersamento;
	}

	public Integer getIndiceSingoloVersamento() {
		return indiceSingoloVersamento;
	}

	public void setIndiceSingoloVersamento(Integer indiceSingoloVersamento) {
		this.indiceSingoloVersamento = indiceSingoloVersamento;
	}

	public String getStatoInvioFruitore() {
		return statoInvioFruitore;
	}

	public void setStatoInvioFruitore(String statoInvioFruitore) {
		this.statoInvioFruitore = statoInvioFruitore;
	}

}
