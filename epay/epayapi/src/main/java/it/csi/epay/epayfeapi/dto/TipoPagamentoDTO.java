/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings ( "unused" )
public class TipoPagamentoDTO implements Serializable {

	private static final long serialVersionUID = 1207096817459572874L;

	private Long idTipoPagamento;

	private String descrizionePortale;

	private String compilazioneNote;

	private String idApplicazione;

	private String codiceVersamento;

	private Date fineValidita;

	private Date inizioValidita;

	private String datiSpecificiRiscossione;

	private Boolean flagInvioPagamenti;

	private EnteDTO epayTEnti;

	private String numeroAccertamento;

	private Long annoAccertamento;

	private String chiaveIntersistema;

	private Long contatoreCompilazioni;

	private Long contatorePagamenti;

	private Long contatoreSelezioni;

	private Boolean pagamentoSpontaneo;

	private TipologiaPagamentoDTO tipologiaPagamento;

	private Boolean flagPresenzaBollettinoPostale;

	private Boolean flagInvioRT;

	private Boolean flagMultibeneficiario;

	public Long getIdTipoPagamento () {
		return idTipoPagamento;
	}

	public void setIdTipoPagamento ( Long idTipoPagamento ) {
		this.idTipoPagamento = idTipoPagamento;
	}

	public String getDescrizionePortale () {
		return descrizionePortale;
	}

	public void setDescrizionePortale ( String descrizionePortale ) {
		this.descrizionePortale = descrizionePortale;
	}

	public String getCompilazioneNote () {
		return compilazioneNote;
	}

	public void setCompilazioneNote ( String compilazioneNote ) {
		this.compilazioneNote = compilazioneNote;
	}

	public String getIdApplicazione () {
		return idApplicazione;
	}

	public void setIdApplicazione ( String idApplicazione ) {
		this.idApplicazione = idApplicazione;
	}

	public String getCodiceVersamento () {
		return codiceVersamento;
	}

	public void setCodiceVersamento ( String codiceVersamento ) {
		this.codiceVersamento = codiceVersamento;
	}

	public Date getFineValidita () {
		return fineValidita;
	}

	public void setFineValidita ( Date fineValidita ) {
		this.fineValidita = fineValidita;
	}

	public Date getInizioValidita () {
		return inizioValidita;
	}

	public void setInizioValidita ( Date inizioValidita ) {
		this.inizioValidita = inizioValidita;
	}

	public String getDatiSpecificiRiscossione () {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public Boolean getFlagInvioPagamenti () {
		return flagInvioPagamenti;
	}

	public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
		this.flagInvioPagamenti = flagInvioPagamenti;
	}

	public EnteDTO getEpayTEnti () {
		return epayTEnti;
	}

	public void setEpayTEnti ( EnteDTO epayTEnti ) {
		this.epayTEnti = epayTEnti;
	}

	public String getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( String numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public Long getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Long annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getChiaveIntersistema () {
		return chiaveIntersistema;
	}

	public void setChiaveIntersistema ( String chiaveIntersistema ) {
		this.chiaveIntersistema = chiaveIntersistema;
	}

	public Long getContatoreCompilazioni () {
		return contatoreCompilazioni;
	}

	public void setContatoreCompilazioni ( Long contatoreCompilazioni ) {
		this.contatoreCompilazioni = contatoreCompilazioni;
	}

	public Long getContatorePagamenti () {
		return contatorePagamenti;
	}

	public void setContatorePagamenti ( Long contatorePagamenti ) {
		this.contatorePagamenti = contatorePagamenti;
	}

	public Long getContatoreSelezioni () {
		return contatoreSelezioni;
	}

	public void setContatoreSelezioni ( Long contatoreSelezioni ) {
		this.contatoreSelezioni = contatoreSelezioni;
	}

	public Boolean getPagamentoSpontaneo () {
		return pagamentoSpontaneo;
	}

	public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	public TipologiaPagamentoDTO getTipologiaPagamento () {
		return tipologiaPagamento;
	}

	public void setTipologiaPagamento ( TipologiaPagamentoDTO tipologiaPagamento ) {
		this.tipologiaPagamento = tipologiaPagamento;
	}

	public Boolean getFlagPresenzaBollettinoPostale () {
		return flagPresenzaBollettinoPostale;
	}

	public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
		this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	}

	public Boolean getFlagInvioRT () {
		return flagInvioRT;
	}

	public void setFlagInvioRT ( Boolean flagInvioRT ) {
		this.flagInvioRT = flagInvioRT;
	}

	public Boolean getFlagMultibeneficiario () {
		return flagMultibeneficiario;
	}

	public void setFlagMultibeneficiario ( Boolean flagMultibeneficiario ) {
		this.flagMultibeneficiario = flagMultibeneficiario;
	}
}
