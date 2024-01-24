/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class BusinessEsitoGiaRicevutoException extends BusinessException {
	private static final long serialVersionUID = 1L;

	private Long id;

	public BusinessEsitoGiaRicevutoException(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
