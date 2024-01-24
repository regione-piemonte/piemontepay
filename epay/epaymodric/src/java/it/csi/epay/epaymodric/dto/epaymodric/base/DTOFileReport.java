/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType( XmlAccessType.PROPERTY )
@XmlType( name = "dtoFileReport" )
public class DTOFileReport implements Serializable {
	
	/**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
	
	private String nomeFile;
	
	private byte[] contenuto;
	
	private Date dataInserimento;
	
	private Date dataModifica;
	
	private Date dataInizioElaborazione;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	
	public byte[] getContenuto() {
		return contenuto;
	}

	public void setContenuto(byte[] contenuto) {
		this.contenuto = contenuto;
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

	public Date getDataInizioElaborazione() {
		return dataInizioElaborazione;
	}

	public void setDataInizioElaborazione(Date dataInizioElaborazione) {
		this.dataInizioElaborazione = dataInizioElaborazione;
	}

}
