package it.csi.epay.epayfeapi.model.epaypacatalogsrv;
/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings ( "unused" )
public class DatiSpecificiRiscossioneOutput implements Serializable {

	private static final long serialVersionUID = -6642394728442493639L;

	@JsonProperty ( "codiceEsito" )
	private String codiceEsito;

	@JsonProperty ( "descrizioneEsito" )
	private String descrizioneEsito;

	@JsonProperty ( "codiceFiscaleEnte" )
	private String codiceFiscaleEnte;

	@JsonProperty ( "tipoPagamento" )
	private String tipoPagamento;

	@JsonProperty ( "elencoDatiSpecifici" )
	private List<DatiSpecificiRiscossione> elencoDatiSpecifici;

	public String getCodiceEsito () {
		return codiceEsito;
	}

	public void setCodiceEsito ( String codiceEsito ) {
		this.codiceEsito = codiceEsito;
	}

	public String getDescrizioneEsito () {
		return descrizioneEsito;
	}

	public void setDescrizioneEsito ( String descrizioneEsito ) {
		this.descrizioneEsito = descrizioneEsito;
	}

	public String getCodiceFiscaleEnte () {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getTipoPagamento () {
		return tipoPagamento;
	}

	public void setTipoPagamento ( String tipoPagamento ) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<DatiSpecificiRiscossione> getElencoDatiSpecifici () {
		return elencoDatiSpecifici;
	}

	public void setElencoDatiSpecifici ( List<DatiSpecificiRiscossione> elencoDatiSpecifici ) {
		this.elencoDatiSpecifici = elencoDatiSpecifici;
	}
}
