/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.List;

/** dto facade <-> business <-> persistence */
public class TotalSizeAndLightListDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Long totalSize;
	private final List<T> lightList;

	public TotalSizeAndLightListDto(Long totalSize, List<T> lightList) {
		this.totalSize = totalSize;
		this.lightList = lightList;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public List<T> getLightList() {
		return lightList;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("totalSize:").append(totalSize).append(COMMA);
		s.append("lightList:").append(lightList);
		s.append(" }");
		return s.toString();
	}

}
