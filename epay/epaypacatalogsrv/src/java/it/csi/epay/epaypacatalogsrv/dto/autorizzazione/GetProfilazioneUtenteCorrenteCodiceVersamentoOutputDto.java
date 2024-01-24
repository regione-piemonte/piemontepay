/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import java.io.Serializable;


public class GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codice;

	private String descrizione;

	private Boolean flagMbPrimario;

	private Boolean flagMbSecondario;

	@Override
	public String toString () {
		return "GetInfoUtenteCodiceVersamentoOutputDto [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getCodice () {
		return codice;
	}

	public void setCodice ( String codice ) {
		this.codice = codice;
	}

	public String getDescrizione () {
		return descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public Boolean getFlagMbPrimario () {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Boolean getFlagMbSecondario () {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
		this.flagMbSecondario = flagMbSecondario;
	}

}
