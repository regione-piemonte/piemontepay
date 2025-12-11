/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Column;
import java.io.Serializable;


@RegisterForReflection ()
@SuppressWarnings ( "unused" )
public class EpayTRtPdf implements Serializable {

	private static final long serialVersionUID = -4889194886685724735L;

	@Column ( name = "ricevuta_pdf" )
	private byte[] ricevutaPdf;

	public EpayTRtPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	public byte[] getRicevutaPdf () {
		return ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	@Override
	public String toString () {
		return "{ ricevutaPdf (valore non esposto) }";
	}
}
