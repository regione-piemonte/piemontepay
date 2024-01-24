/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class DocumentiDocmeDto implements Serializable {

	private static final long serialVersionUID = 2464641138771728457L;

	private byte[] content;

	private String descrizione;

	private String dataInizio;

	private String dataFine;

	private String filename;

	private String cfCittadino;

	private Long idAmbito;

	private Long idTipologia;

	private String dataCreazione;

	public byte[] getContent () {
		return content;
	}

	public void setContent ( byte[] content ) {
		this.content = content;
	}

	public String getDescrizione () {
		return descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public String getDataInizio () {
		return dataInizio;
	}

	public void setDataInizio ( String dataInizio ) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine () {
		return dataFine;
	}

	public void setDataFine ( String dataFine ) {
		this.dataFine = dataFine;
	}

	public String getFilename () {
		return filename;
	}

	public void setFilename ( String filename ) {
		this.filename = filename;
	}

	public String getCfCittadino () {
		return cfCittadino;
	}

	public void setCfCittadino ( String cfCittadino ) {
		this.cfCittadino = cfCittadino;
	}

	public Long getIdAmbito () {
		return idAmbito;
	}

	public void setIdAmbito ( Long idAmbito ) {
		this.idAmbito = idAmbito;
	}

	public Long getIdTipologia () {
		return idTipologia;
	}

	public void setIdTipologia ( Long idTipologia ) {
		this.idTipologia = idTipologia;
	}

	public String getDataCreazione () {
		return dataCreazione;
	}

	public void setDataCreazione ( String dataCreazione ) {
		this.dataCreazione = dataCreazione;
	}
}
