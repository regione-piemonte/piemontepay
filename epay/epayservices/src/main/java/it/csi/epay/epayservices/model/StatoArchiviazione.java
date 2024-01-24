/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;


public class StatoArchiviazione implements Serializable {

	private static final long serialVersionUID = -3907172036287505717L;

	private Long id;

	private Long idEnte;

	private String codiceFiscale;

	private Boolean flagAbilitaArchiviazione;

	private Date dataOraUltimaModifica;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Long getIdEnte () {
		return idEnte;
	}

	public void setIdEnte ( Long idEnte ) {
		this.idEnte = idEnte;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public Boolean getFlagAbilitaArchiviazione () {
		return flagAbilitaArchiviazione;
	}

	public void setFlagAbilitaArchiviazione ( Boolean flagAbilitaArchiviazione ) {
		this.flagAbilitaArchiviazione = flagAbilitaArchiviazione;
	}

	public Date getDataOraUltimaModifica () {
		return dataOraUltimaModifica;
	}

	public void setDataOraUltimaModifica ( Date dataOraUltimaModifica ) {
		this.dataOraUltimaModifica = dataOraUltimaModifica;
	}
}
