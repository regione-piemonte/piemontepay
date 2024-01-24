/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class PosizioneDebitoriaDaAggiornareRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	private Long idPosizioneDebitoriaDaAggiornare;

	public PosizioneDebitoriaDaAggiornareRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Long idPosizioneDebitoria) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idPosizioneDebitoriaDaAggiornare = idPosizioneDebitoria;
	}

	public Long getIdPosizioneDebitoriaDaAggiornare() {
		return idPosizioneDebitoriaDaAggiornare;
	}

	public void setIdPosizioneDebitoriaDaAggiornare(Long idPosizioneDebitoriaDaAggiornare) {
		this.idPosizioneDebitoriaDaAggiornare = idPosizioneDebitoriaDaAggiornare;
	}

	
}
