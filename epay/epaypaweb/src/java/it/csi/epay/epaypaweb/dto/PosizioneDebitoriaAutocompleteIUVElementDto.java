/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;


public class PosizioneDebitoriaAutocompleteIUVElementDto implements Serializable {

	private static final long serialVersionUID = 2695271642659509474L;

	private Long idPosizioneDebitoria;

	private String iuv;

	public Long getIdPosizioneDebitoria () {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria ( Long idPosizioneDebitoria ) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}
}
