/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the epay_t_rt database table.
 */
@Entity
@Table ( name = "epay_t_rt" )
public class EpayTRtPdf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column ( name = "id_rt", unique = true, nullable = false )
	private Long idRt;

	@Column ( name = "cod_esito_pagamento" )
	private Integer codEsitoPagamento;

	@Column ( name = "desc_esito_pagamento", length = 255 )
	private String descEsitoPagamento;

	@Column ( name = "iuv", length = 35 )
	private String iuv;

	@Column ( name = "ricevuta_pdf" )
	private byte[] ricevutaPdf;

	//bi-directional many-to-one association to EpayTRegistroVersamenti
	@ManyToOne
	@JoinColumn ( name = "id_registro", nullable = false )
	private EpayTRegistroVersamenti epayTRegistroVersamenti;

	@Column ( name = "identificativo_dominio", length = 35 )
	private String identificativoDominio;

	public EpayTRtPdf () {
	}

	public Long getIdRt () {
		return idRt;
	}

	public void setIdRt ( Long idRt ) {
		this.idRt = idRt;
	}

	public Integer getCodEsitoPagamento () {
		return codEsitoPagamento;
	}

	public void setCodEsitoPagamento ( Integer codEsitoPagamento ) {
		this.codEsitoPagamento = codEsitoPagamento;
	}

	public String getDescEsitoPagamento () {
		return descEsitoPagamento;
	}

	public void setDescEsitoPagamento ( String descEsitoPagamento ) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public byte[] getRicevutaPdf () {
		return ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	public EpayTRegistroVersamenti getEpayTRegistroVersamenti () {
		return epayTRegistroVersamenti;
	}

	public void setEpayTRegistroVersamenti ( EpayTRegistroVersamenti epayTRegistroVersamenti ) {
		this.epayTRegistroVersamenti = epayTRegistroVersamenti;
	}

	public String getIdentificativoDominio () {
		return identificativoDominio;
	}

	public void setIdentificativoDominio ( String identificativoDominio ) {
		this.identificativoDominio = identificativoDominio;
	}
}
