/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class NotificaPagamentoRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	
	private Long idNotificaPagamento;

	public NotificaPagamentoRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Long idNotificaPagamento) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idNotificaPagamento = idNotificaPagamento;
	}

	public Long getIdNotificaPagamento() {
		return idNotificaPagamento;
	}

	public void setIdNotificaPagamento(Long idNotificaPagamento) {
		this.idNotificaPagamento = idNotificaPagamento;
	}
}
