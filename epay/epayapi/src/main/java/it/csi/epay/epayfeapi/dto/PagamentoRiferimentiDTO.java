/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class PagamentoRiferimentiDTO implements Serializable {

	private static final long serialVersionUID = 4158316753915056990L;

	private Long idRiferimento;

	private Integer progressivo;

	private String nome;

	private String valore;

	private String utenteUltimaModifica;

	public Long getIdRiferimento () {
		return idRiferimento;
	}

	public void setIdRiferimento ( Long idRiferimento ) {
		this.idRiferimento = idRiferimento;
	}

	public Integer getProgressivo () {
		return progressivo;
	}

	public void setProgressivo ( Integer progressivo ) {
		this.progressivo = progressivo;
	}

	public String getNome () {
		return nome;
	}

	public void setNome ( String nome ) {
		this.nome = nome;
	}

	public String getValore () {
		return valore;
	}

	public void setValore ( String valore ) {
		this.valore = valore;
	}

	public String getUtenteUltimaModifica () {
		return utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica ( String utenteUltimaModifica ) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}
}
