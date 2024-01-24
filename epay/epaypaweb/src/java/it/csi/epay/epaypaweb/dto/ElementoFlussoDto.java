/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

public class ElementoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public ElementoFlussoDto() {
	}

	public ElementoFlussoDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String toSuperString() {
		return "id:" + id;
	}

}
