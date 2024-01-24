/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings ( "unused" )
public class FlagAbilitaArchiviazioneDTO implements Serializable {

	private static final long serialVersionUID = -6189219308260651669L;

	private boolean abilitato;

	private Date dataOraUltimaModifica;

	public boolean isAbilitato () {
		return abilitato;
	}

	public void setAbilitato ( boolean abilitato ) {
		this.abilitato = abilitato;
	}

	public Date getDataOraUltimaModifica () {
		return dataOraUltimaModifica;
	}

	public void setDataOraUltimaModifica ( Date dataOraUltimaModifica ) {
		this.dataOraUltimaModifica = dataOraUltimaModifica;
	}

	@Override
	public String toString () {
		return "{ " +
			"abilitato:" + abilitato +
			", dataOraUltimaModifica:" + dataOraUltimaModifica +
			" }";
	}
}
