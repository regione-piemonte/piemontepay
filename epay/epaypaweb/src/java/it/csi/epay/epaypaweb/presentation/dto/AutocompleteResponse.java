/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.dto;

import java.io.Serializable;
import java.util.List;

public class AutocompleteResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<String> result;

	private List<Long> resultIds;

	public List<String> getResult () {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

	public List<Long> getResultIds () {
		return resultIds;
	}

	public void setResultIds ( List<Long> resultIds ) {
		this.resultIds = resultIds;
	}

}
