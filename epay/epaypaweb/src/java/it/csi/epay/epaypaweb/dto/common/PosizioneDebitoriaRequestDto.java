/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class PosizioneDebitoriaRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	private Long idPosizioneDebitoria;

	public PosizioneDebitoriaRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Long idPosizioneDebitoria) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(Long idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}
}
