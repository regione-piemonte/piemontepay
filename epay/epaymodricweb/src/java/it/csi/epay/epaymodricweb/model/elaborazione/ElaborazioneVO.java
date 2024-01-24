/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.elaborazione;

import java.util.Date;

public class ElaborazioneVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Date dataElaborazione;

	
	private Date dataFine;


	private Date dataInizio;


	private String idEnte;
	
	private Long idErrore;
	
	private String msgErrore;
	
	
	
	private String utenteIns; 

	private String statoElaborazione;

	private String listaFlussi;

	public ElaborazioneVO() {
		// TODO Auto-generated constructor stub
	}

	
	public String getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public Long getIdErrore() {
		return idErrore;
	}

	public void setIdErrore(Long idErrore) {
		this.idErrore = idErrore;
	}

	public String getMsgErrore() {
		return msgErrore;
	}

	public void setMsgErrore(String msgErrore) {
		this.msgErrore = msgErrore;
	}

	public String getUtenteIns() {
		return utenteIns;
	}

	public void setUtenteIns(String utenteIns) {
		this.utenteIns = utenteIns;
	}

	public String getStatoElaborazione() {
		return statoElaborazione;
	}

	public void setStatoElaborazione(String statoElaborazione) {
		this.statoElaborazione = statoElaborazione;
	}

	public String getListaFlussi() {
		return listaFlussi;
	}

	public void setListaFlussi(String listaFlussi) {
		this.listaFlussi = listaFlussi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getDataElaborazione() {
		return dataElaborazione;
	}


	public void setDataElaborazione(Date dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}


	public Date getDataFine() {
		return dataFine;
	}


	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}


	public Date getDataInizio() {
		return dataInizio;
	}


	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	
	
	
}
