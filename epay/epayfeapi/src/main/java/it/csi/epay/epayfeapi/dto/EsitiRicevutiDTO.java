/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;


@SuppressWarnings ( "unused" )
public class EsitiRicevutiDTO implements Serializable {

	private static final long serialVersionUID = -7986499418024974982L;

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

	private String iuv;

	private Date dataOraOperazione;

	public Long getIdEsito () {
		return idEsito;
	}

	public void setIdEsito ( Long idEsito ) {
		this.idEsito = idEsito;
	}

	public Integer getCodEsitoPagamento () {
		return codEsitoPagamento;
	}

	public void setCodEsitoPagamento ( Integer codEsitoPagamento ) {
		this.codEsitoPagamento = codEsitoPagamento;
	}

	public Timestamp getDataPagamento () {
		return dataPagamento;
	}

	public void setDataPagamento ( Timestamp dataPagamento ) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescEsitoPagamento () {
		return descEsitoPagamento;
	}

	public void setDescEsitoPagamento ( String descEsitoPagamento ) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIdApplicazione () {
		return idApplicazione;
	}

	public void setIdApplicazione ( String idApplicazione ) {
		this.idApplicazione = idApplicazione;
	}

	public Integer getIdModalitaRicezione () {
		return idModalitaRicezione;
	}

	public void setIdModalitaRicezione ( Integer idModalitaRicezione ) {
		this.idModalitaRicezione = idModalitaRicezione;
	}

	public String getIdTransazione () {
		return idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public String getIdentificativoPsp () {
		return identificativoPsp;
	}

	public void setIdentificativoPsp ( String identificativoPsp ) {
		this.identificativoPsp = identificativoPsp;
	}

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getIur () {
		return iur;
	}

	public void setIur ( String iur ) {
		this.iur = iur;
	}

	public String getRagioneSocialePsp () {
		return ragioneSocialePsp;
	}

	public void setRagioneSocialePsp ( String ragioneSocialePsp ) {
		this.ragioneSocialePsp = ragioneSocialePsp;
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

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Date getDataOraOperazione () {
		return dataOraOperazione;
	}

	public void setDataOraOperazione ( Date dataOraOperazione ) {
		this.dataOraOperazione = dataOraOperazione;
	}

	@Override public String toString () {
		return "EsitiRicevutiDTO{" +
						"idEsito=" + idEsito +
						", codEsitoPagamento=" + codEsitoPagamento +
						", dataPagamento=" + dataPagamento +
						", descEsitoPagamento='" + descEsitoPagamento + '\'' +
						", idApplicazione='" + idApplicazione + '\'' +
						", idModalitaRicezione=" + idModalitaRicezione +
						", idTransazione='" + idTransazione + '\'' +
						", identificativoPsp='" + identificativoPsp + '\'' +
						", importo=" + importo +
						", iur='" + iur + '\'' +
						", ragioneSocialePsp='" + ragioneSocialePsp + '\'' +
						", idQuietanzaEsito=" + idQuietanzaEsito +
						", ricevutaPdf=" + Arrays.toString ( ricevutaPdf ) +
						", iuv='" + iuv + '\'' +
						", dataOraOperazione=" + dataOraOperazione +
						'}';
	}
}
