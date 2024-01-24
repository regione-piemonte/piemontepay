/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.List;

public class PosizioneDebitoriaAutocompleteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<PosizioneDebitoriaAutocompleteElementDto> posizioneDebitoriaAutocompleteElementDtoList;

	public List<PosizioneDebitoriaAutocompleteElementDto> getPosizioneDebitoriaAutocompleteElementDtoList () {
		return posizioneDebitoriaAutocompleteElementDtoList;
	}

	public void
		setPosizioneDebitoriaAutocompleteElementDtoList ( List<PosizioneDebitoriaAutocompleteElementDto> posizioneDebitoriaAutocompleteElementDtoList ) {
		this.posizioneDebitoriaAutocompleteElementDtoList = posizioneDebitoriaAutocompleteElementDtoList;
	}

}
