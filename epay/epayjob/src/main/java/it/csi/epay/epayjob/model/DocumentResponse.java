/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;


public class DocumentResponse implements Serializable {

	private static final long serialVersionUID = -4406410708894536878L;

	private String uuid;

	public String getUuid () {
		return uuid;
	}

	public void setUuid ( String uuid ) {
		this.uuid = uuid;
	}
}
