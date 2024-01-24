/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class EliminaPosizioneDebitoriaRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;

	private Long idFlusso; 
	private Long idPosizioneDebitoria;
	
	public EliminaPosizioneDebitoriaRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Long idFlusso, Long idPosizioneDebitoria) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idFlusso = idFlusso;
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}


	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}


	public void setIdPosizioneDebitoria(Long idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}
}
