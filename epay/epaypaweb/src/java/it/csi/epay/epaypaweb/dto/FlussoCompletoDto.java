/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.List;

/** dto facade <-> business <-> persistence */
public class FlussoCompletoDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private FlussoDto flusso;
	private List<T> itemList;

	public FlussoDto getFlusso() {
		return flusso;
	}

	public void setFlusso(FlussoDto flusso) {
		this.flusso = flusso;
	}

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("flusso:").append(flusso).append(COMMA);
		s.append("itemList:").append(itemList);
		s.append(" }");
		return s.toString();
	}

}
