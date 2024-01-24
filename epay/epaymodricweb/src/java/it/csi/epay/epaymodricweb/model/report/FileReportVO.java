/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.report;

import java.io.Serializable;
import java.util.Date;

public class FileReportVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private byte[] contenutoFile;
	
	private Date dataInserimento;
	
	private Date dataModifica;
	
	private Date dataInizioElaborazione;
	
	private String nomeFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getContenutoFile() {
		return contenutoFile;
	}

	public void setContenutoFile(byte[] contenutoFile) {
		this.contenutoFile = contenutoFile;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Date getDataInizioElaborazione() {
		return dataInizioElaborazione;
	}

	public void setDataInizioElaborazione(Date dataInizioElaborazione) {
		this.dataInizioElaborazione = dataInizioElaborazione;
	}

}
