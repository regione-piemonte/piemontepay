/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.dto.epaymdpservices;

import java.io.Serializable;

public enum  EsitoPagamentoAgID implements Serializable{
	
	PAGAMENTO_ESEGUITO(0L),
	PAGAMENTO_NON_ESEGUITO(1L),
	PAGAMENTO_PARZIALMENTE_ESEGUITO(2L),
	DECORRENZA_TERMINI(3L),
	DECORRENZA_TERMINI_PARZIALE(4L);	
	
	private Long esitoPagamento;
	
	EsitoPagamentoAgID(Long esitoPagamento){
		this.setEsitoPagamento(esitoPagamento);
	}

	public Long getEsitoPagamento() {
		return esitoPagamento;
	}

	public void setEsitoPagamento(Long esitoPagamento) {
		this.esitoPagamento = esitoPagamento;
	}



}
