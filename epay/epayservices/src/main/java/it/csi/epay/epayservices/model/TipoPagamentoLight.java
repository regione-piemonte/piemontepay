/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class TipoPagamentoLight implements Serializable {

	private static final long serialVersionUID = -1887703443220776726L;

	private Long idTipoPagamento;

	private String descrizionePortale;

	private String compilazioneNote;

	private String idApplicazione;

	private String codiceVersamento;

	private TipologiaPagamento tipologiaPagamento;

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

	public TipologiaPagamento getTipologiaPagamento () {
		return tipologiaPagamento;
	}

	public void setTipologiaPagamento ( TipologiaPagamento tipologiaPagamento ) {
		this.tipologiaPagamento = tipologiaPagamento;
	}

	@Override public String toString () {
		return "TipoPagamentoLight{" +
						"idTipoPagamento=" + idTipoPagamento +
						", descrizionePortale='" + descrizionePortale + '\'' +
						", compilazioneNote='" + compilazioneNote + '\'' +
						", idApplicazione='" + idApplicazione + '\'' +
						", codiceVersamento='" + codiceVersamento + '\'' +
						", tipologiaPagamento=" + tipologiaPagamento +
						'}';
	}
}
