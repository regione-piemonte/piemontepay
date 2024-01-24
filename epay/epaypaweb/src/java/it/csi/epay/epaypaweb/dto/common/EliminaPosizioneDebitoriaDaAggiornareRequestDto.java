/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class EliminaPosizioneDebitoriaDaAggiornareRequestDto extends EliminaPosizioneDebitoriaRequestDto {

	private static final long serialVersionUID = 1L;

	public EliminaPosizioneDebitoriaDaAggiornareRequestDto(String ipAddress,  Long idUtente,
			String codiceFiscaleUtente, String codiceApplicazione, Long idFlusso, Long idPosizioneDebitoria) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione, idFlusso, idPosizioneDebitoria);
	
	}
}
