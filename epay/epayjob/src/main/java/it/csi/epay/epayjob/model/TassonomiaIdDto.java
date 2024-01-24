/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class TassonomiaIdDto implements Serializable {

	private static final long serialVersionUID = 1003763897141395014L;

	@JsonProperty ( "id" )
	private long id;

	public long getId () {
		return id;
	}

	public void setId ( long id ) {
		this.id = id;
	}

	public TassonomiaIdDto ( long id ) {
		this.id = id;
	}
}
