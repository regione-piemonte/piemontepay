/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class CodiciEsitoPagamento implements Serializable {
	
	private static final long serialVersionUID = -2756855824903299524L;
	private Integer idEsitoPagamento;
	private String descrizione;
	
	
	/**
	 * @return the idEsitoPagamento
	 */
	public Integer getIdEsitoPagamento() {
		return idEsitoPagamento;
	}

	/**
	 * @param idEsitoPagamento the idEsitoPagamento to set
	 */
	public void setIdEsitoPagamento(Integer idEsitoPagamento) {
		this.idEsitoPagamento = idEsitoPagamento;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
