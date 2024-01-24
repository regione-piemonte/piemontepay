/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.List;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;

/*
 *
 * @NGueye
 */

public class DTOInputWsCambiaStatoFlusso extends DTOInputBase {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String idEnte;

	private String statoElaborazione;

	private String dataInizio;

	private String dataFine;

	private String utenteModifica;

	private Integer idErrore;

	private String msgErrore;

	private Long id;

	private String Flusso;

	private List<String> listaFlussi;

	public DTOInputWsCambiaStatoFlusso(Long id, String idEnte, String dataElaborazione, String statoElaborazione,
        String dataInizio, String dataFine, List<String> listaFlussi, String utenteModifica,
			Integer idErrore, String msgErrore) {
		super();

		this.idEnte = idEnte;

		this.statoElaborazione = statoElaborazione;

		this.listaFlussi = listaFlussi;

		this.utenteModifica = utenteModifica;

		this.idErrore = idErrore;

		this.msgErrore = msgErrore;

	}

	public DTOInputWsCambiaStatoFlusso() {

	}

	public String getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getStatoElaborazione() {
		return statoElaborazione;
	}

	public void setStatoElaborazione(String statoElaborazione) {
		this.statoElaborazione = statoElaborazione;
	}

	public List<String> getListaFlussi() {
		return listaFlussi;
	}

	public void setListaFlussi(List<String> listaFlussi) {
		this.listaFlussi = listaFlussi;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public String getUtenteModifica() {
		return utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	public Integer getIdErrore() {
		return idErrore;
	}

	public void setIdErrore(Integer idErrore) {
		this.idErrore = idErrore;
	}

	public String getMsgErrore() {
		return msgErrore;
	}

	public void setMsgErrore(String msgErrore) {
		this.msgErrore = msgErrore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlusso() {
		return Flusso;
	}

	public void setFlusso(String flusso) {
		Flusso = flusso;
	}

}
