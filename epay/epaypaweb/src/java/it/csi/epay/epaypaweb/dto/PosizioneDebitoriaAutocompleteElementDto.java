/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

public class PosizioneDebitoriaAutocompleteElementDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idPosizioneDebitoria;
	private String idPosizioneDebitoriaEst;

	public Long getIdPosizioneDebitoria () {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria ( Long idPosizioneDebitoria ) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public String getIdPosizioneDebitoriaEst () {
		return idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst ( String idPosizioneDebitoriaEst ) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}

}
