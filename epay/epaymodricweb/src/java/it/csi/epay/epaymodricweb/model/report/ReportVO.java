/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.report;

import java.io.Serializable;
import java.util.Date;


public class ReportVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codiceFiscale;
	private String cognome;
	private Date dataInserimento;
	private Date dataModifica;
	private Date dataPagamentoFine;
	private Date dataPagamentoInizio;
	private Integer idCodiceVersamento;
	private Integer idEnte;
	private Long idUtente;
	private String iuv;
	private String nominativoExport;
	private Integer tipoPagamento;
	private String codiceFiscaleEnte;
	private String codiceFiscaleUtente;
	private Integer epaymodricDStatoReport;
	private Integer epaymodricDTipoFileReport;
	private Integer epaymodricTFileReport;
	
	
	
	public ReportVO(Long id, String codiceFiscale, String cognome, Date dataInserimento, Date dataModifica,
			Date dataPagamentoFine, Date dataPagamentoInizio, Integer idCodiceVersamento, Integer idEnte, Long idUtente,
			String iuv, String nominativoExport, Integer tipoPagamento, String codiceFiscaleEnte,
			String codiceFiscaleUtente, Integer epaymodricDStatoReport, Integer epaymodricDTipoFileReport,
			Integer epaymodricTFileReport) {
		this.id = id;
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.dataInserimento = dataInserimento;
		this.dataModifica = dataModifica;
		this.dataPagamentoFine = dataPagamentoFine;
		this.dataPagamentoInizio = dataPagamentoInizio;
		this.idCodiceVersamento = idCodiceVersamento;
		this.idEnte = idEnte;
		this.idUtente = idUtente;
		this.iuv = iuv;
		this.nominativoExport = nominativoExport;
		this.tipoPagamento = tipoPagamento;
		this.codiceFiscaleEnte = codiceFiscaleEnte;
		this.codiceFiscaleUtente = codiceFiscaleUtente;
		this.epaymodricDStatoReport = epaymodricDStatoReport;
		this.epaymodricDTipoFileReport = epaymodricDTipoFileReport;
		this.epaymodricTFileReport = epaymodricTFileReport;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public Date getDataModifica() {
		return dataModifica;
	}
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}
	public Date getDataPagamentoFine() {
		return dataPagamentoFine;
	}
	public void setDataPagamentoFine(Date dataPagamentoFine) {
		this.dataPagamentoFine = dataPagamentoFine;
	}
	public Date getDataPagamentoInizio() {
		return dataPagamentoInizio;
	}
	public void setDataPagamentoInizio(Date dataPagamentoInizio) {
		this.dataPagamentoInizio = dataPagamentoInizio;
	}
	public Integer getIdCodiceVersamento() {
		return idCodiceVersamento;
	}
	public void setIdCodiceVersamento(Integer idCodiceVersamento) {
		this.idCodiceVersamento = idCodiceVersamento;
	}
	public Integer getIdEnte() {
		return idEnte;
	}
	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}
	public Long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public String getNominativoExport() {
		return nominativoExport;
	}
	public void setNominativoExport(String nominativoExport) {
		this.nominativoExport = nominativoExport;
	}
	public Integer getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}
	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}
	public String getCodiceFiscaleUtente() {
		return codiceFiscaleUtente;
	}
	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
	}
	public Integer getEpaymodricDStatoReport() {
		return epaymodricDStatoReport;
	}
	public void setEpaymodricDStatoReport(Integer epaymodricDStatoReport) {
		this.epaymodricDStatoReport = epaymodricDStatoReport;
	}
	public Integer getEpaymodricDTipoFileReport() {
		return epaymodricDTipoFileReport;
	}
	public void setEpaymodricDTipoFileReport(Integer epaymodricDTipoFileReport) {
		this.epaymodricDTipoFileReport = epaymodricDTipoFileReport;
	}
	public Integer getEpaymodricTFileReport() {
		return epaymodricTFileReport;
	}
	public void setEpaymodricTFileReport(Integer epaymodricTFileReport) {
		this.epaymodricTFileReport = epaymodricTFileReport;
	}
	
	
	
}
