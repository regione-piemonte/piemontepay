/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;

import io.quarkus.runtime.annotations.RegisterForReflection;


@RegisterForReflection ( )
@SuppressWarnings ( "unused" )
public class EpayTRegistroVersamentiReflection implements Serializable {

	private static final long serialVersionUID = 1702655545510094023L;

	private Long idRegistro;

	public EpayTRegistroVersamentiReflection ( Long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	public Long getIdRegistro () {
		return idRegistro;
	}

	public void setIdRegistro ( Long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	@Override
	public String toString () {
		return "{ idRegistro:" + idRegistro + " }";
	}
}
