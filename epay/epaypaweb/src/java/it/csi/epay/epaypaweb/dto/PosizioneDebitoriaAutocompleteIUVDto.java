/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.List;


public class PosizioneDebitoriaAutocompleteIUVDto implements Serializable {

	private static final long serialVersionUID = 5765167576456332586L;

	private List<PosizioneDebitoriaAutocompleteIUVElementDto> posizioneDebitoriaAutocompleteIUVElementDtoList;

	public List<PosizioneDebitoriaAutocompleteIUVElementDto> getPosizioneDebitoriaAutocompleteIUVElementDtoList () {
		return posizioneDebitoriaAutocompleteIUVElementDtoList;
	}

	public void setPosizioneDebitoriaAutocompleteIUVElementDtoList (
					List<PosizioneDebitoriaAutocompleteIUVElementDto> posizioneDebitoriaAutocompleteIUVElementDtoList ) {
		this.posizioneDebitoriaAutocompleteIUVElementDtoList = posizioneDebitoriaAutocompleteIUVElementDtoList;
	}
}
