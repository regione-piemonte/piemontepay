/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

import java.io.Serializable;

public class BusinessPosizioneDebitoriaRipetutaException extends BusinessException implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idFlusso;
	private Long idPosizioneDebitoria;
	private String idPosizioneDebitoriaEst;

	public BusinessPosizioneDebitoriaRipetutaException(Long idFlusso, long idPosizioneDebitoria) {
		super();
		this.idFlusso = idFlusso;
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public BusinessPosizioneDebitoriaRipetutaException(Long idFlusso, String idPosizioneDebitoriaEst) {
		super();
		this.idFlusso = idFlusso;
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}

	public long getIdFlusso() {
		return idFlusso;
	}

	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public String getIdPosizioneDebitoriaEst() {
		return idPosizioneDebitoriaEst;
	}

}
