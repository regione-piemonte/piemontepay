/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the epay_t_esiti_ricevuti database table.
 */

public class EsitiRicevuti implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEsito;

	private Integer codEsitoPagamento;

	private Timestamp dataPagamento;

	private String descEsitoPagamento;

	private String idApplicazione;

	private Integer idModalitaRicezione;

	private String idTransazione;

	private String identificativoPsp;

	private BigDecimal importo;

	private String iur;

	private String ragioneSocialePsp;

	private Long idQuietanzaEsito;

	private byte[] ricevutaPdf;

	//MM
	private String iuv;

	private Date dataOraOperazione;
	//MM

	public Date getDataOraOperazione () {
		return dataOraOperazione;
	}

	public void setDataOraOperazione ( Date dataOraOperazione ) {
		this.dataOraOperazione = dataOraOperazione;
	}

	public EsitiRicevuti () {
	}

	public Long getIdEsito () {
		return this.idEsito;
	}

	public void setIdEsito ( Long idEsito ) {
		this.idEsito = idEsito;
	}

	public Integer getCodEsitoPagamento () {
		return this.codEsitoPagamento;
	}

	public void setCodEsitoPagamento ( Integer codEsitoPagamento ) {
		this.codEsitoPagamento = codEsitoPagamento;
	}

	public Timestamp getDataPagamento () {
		return this.dataPagamento;
	}

	public void setDataPagamento ( Timestamp dataPagamento ) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescEsitoPagamento () {
		return this.descEsitoPagamento;
	}

	public void setDescEsitoPagamento ( String descEsitoPagamento ) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIdApplicazione () {
		return this.idApplicazione;
	}

	public void setIdApplicazione ( String idApplicazione ) {
		this.idApplicazione = idApplicazione;
	}

	public Integer getIdModalitaRicezione () {
		return this.idModalitaRicezione;
	}

	public void setIdModalitaRicezione ( Integer idModalitaRicezione ) {
		this.idModalitaRicezione = idModalitaRicezione;
	}

	public String getIdTransazione () {
		return this.idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public String getIdentificativoPsp () {
		return this.identificativoPsp;
	}

	public void setIdentificativoPsp ( String identificativoPsp ) {
		this.identificativoPsp = identificativoPsp;
	}

	public BigDecimal getImporto () {
		return this.importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getIur () {
		return this.iur;
	}

	public void setIur ( String iur ) {
		this.iur = iur;
	}

	public String getRagioneSocialePsp () {
		return this.ragioneSocialePsp;
	}

	public void setRagioneSocialePsp ( String ragioneSocialePsp ) {
		this.ragioneSocialePsp = ragioneSocialePsp;
	}

	@Override
	public String toString () {
		return "EsitiRicevuti [idEsito=" + idEsito + ", codEsitoPagamento=" + codEsitoPagamento + ", dataPagamento=" + dataPagamento + ", descEsitoPagamento="
						+ descEsitoPagamento + ", idApplicazione=" + idApplicazione + ", idModalitaRicezione=" + idModalitaRicezione + ", idTransazione=" + idTransazione
						+ ", identificativoPsp=" + identificativoPsp + ", importo=" + importo + ", iur=" + iur + ", ragioneSocialePsp=" + ragioneSocialePsp + "]";
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Long getIdQuietanzaEsito () {
		return idQuietanzaEsito;
	}

	public void setIdQuietanzaEsito ( Long idQuietanzaEsito ) {
		this.idQuietanzaEsito = idQuietanzaEsito;
	}

	public byte[] getRicevutaPdf () {
		return ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

}
