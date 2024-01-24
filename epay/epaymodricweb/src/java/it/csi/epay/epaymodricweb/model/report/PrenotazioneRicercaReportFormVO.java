/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.report;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import it.csi.epay.epaymodricweb.util.SecurityUtils;

public class PrenotazioneRicercaReportFormVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String identificativoFlusso;
	
	private String iuv;
	
	private String idCodVersamento; //idCodVersamento (select)
	
	private String statoFlusso;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataElaborazioneDa;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataElaborazioneA;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRicezioneDa;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRicezioneA;
	
	private String psp;
	
	// campo finto per mostrare messaggi non relativi ad un campo specifico
    private String validitaGenerica;
	
    private String reportContabile;
    
    private String reportFlussiCompleti;
    
    private String tipoReport;
    
    private String tipoFile;

	public String getTipoReport() {
		return tipoReport;
	}

	public void setTipoReport(String tipoReport) {
		this.tipoReport = tipoReport;
	}

	private String nomeReport;
	
	private String idTipoPagamento; //idTipoPagamento (radio)
	
	private String cognome;
	
	private String codiceFiscale;
	
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamentoInizio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamentoFine;
	
	public PrenotazioneRicercaReportFormVO() {
		codiceFiscale = SecurityUtils.getUser().getEnte().getCodiceFiscale();
	}

	public String getNomeReport() {
		return nomeReport;
	}

	public void setNomeReport(String nomeReport) {
		this.nomeReport = nomeReport;
	}

	public String getIdTipoPagamento() {
		return idTipoPagamento;
	}

	public void setIdTipoPagamento(String idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}

	public String getIdCodVersamento() {
		return idCodVersamento;
	}

	public void setIdCodVersamento(String idCodVersamento) {
		this.idCodVersamento = idCodVersamento;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public Date getDataPagamentoInizio() {
		return dataPagamentoInizio;
	}

	public void setDataPagamentoInizio(Date dataPagamentoInizio) {
		this.dataPagamentoInizio = dataPagamentoInizio;
	}

	public Date getDataPagamentoFine() {
		return dataPagamentoFine;
	}

	public void setDataPagamentoFine(Date dataPagamentoFine) {
		this.dataPagamentoFine = dataPagamentoFine;
	}

	public String getIdentificativoFlusso() {
		return identificativoFlusso;
	}

	public void setIdentificativoFlusso(String identificativoFlusso) {
		this.identificativoFlusso = identificativoFlusso;
	}

	public String getStatoFlusso() {
		return statoFlusso;
	}

	public void setStatoFlusso(String statoFlusso) {
		this.statoFlusso = statoFlusso;
	}

	public Date getDataElaborazioneDa() {
		return dataElaborazioneDa;
	}

	public void setDataElaborazioneDa(Date dataElaborazioneDa) {
		this.dataElaborazioneDa = dataElaborazioneDa;
	}

	public Date getDataElaborazioneA() {
		return dataElaborazioneA;
	}

	public void setDataElaborazioneA(Date dataElaborazioneA) {
		this.dataElaborazioneA = dataElaborazioneA;
	}

	public Date getDataRicezioneDa() {
		return dataRicezioneDa;
	}

	public void setDataRicezioneDa(Date dataRicezioneDa) {
		this.dataRicezioneDa = dataRicezioneDa;
	}

	public Date getDataRicezioneA() {
		return dataRicezioneA;
	}

	public void setDataRicezioneA(Date dataRicezioneA) {
		this.dataRicezioneA = dataRicezioneA;
	}

	public String getPsp() {
		return psp;
	}

	public void setPsp(String psp) {
		this.psp = psp;
	}
	
	public String getValiditaGenerica() {
		return validitaGenerica;
	}

	public void setValiditaGenerica(String validitaGenerica) {
		this.validitaGenerica = validitaGenerica;
	}
	
	 public String getReportContabile() {
		return reportContabile;
	}

	public void setReportContabile(String reportContabile) {
		this.reportContabile = reportContabile;
	}
	

	public String getReportFlussiCompleti() {
		return reportFlussiCompleti;
	}

	public void setReportFlussiCompleti(String reportFlussiCompleti) {
		this.reportFlussiCompleti = reportFlussiCompleti;
	}

	public String getTipoFile() {
		return tipoFile;
	}

	public void setTipoFile(String tipoFile) {
		this.tipoFile = tipoFile;
	}

	public boolean isEmpty() {
	        return ( StringUtils.isBlank(statoFlusso) &&
	                        StringUtils.isBlank(identificativoFlusso) &&
	                        StringUtils.isBlank(iuv) &&
	                        StringUtils.isBlank(idCodVersamento) &&
	                        dataElaborazioneDa == null &&
	            dataElaborazioneA == null && StringUtils.isBlank ( psp ) && dataRicezioneA == null && dataRicezioneDa == null );
	    }
	
}
