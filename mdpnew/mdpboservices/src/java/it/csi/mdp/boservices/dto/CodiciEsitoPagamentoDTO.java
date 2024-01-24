/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.util.Date;

public class CodiciEsitoPagamentoDTO extends BaseDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1526664780340072104L;

	private Integer idEsitoPagamento;
	
	private String descrizione;

	/**
	 * @return the id_esito_pagamento
	 */
	public Integer getIdEsitoPagamento() {
		return idEsitoPagamento;
	}

	/**
	 * @param id_esito_pagamento the id_esito_pagamento to set
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
