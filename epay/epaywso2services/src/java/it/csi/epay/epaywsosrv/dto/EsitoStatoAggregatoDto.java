/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import java.io.Serializable;

public class EsitoStatoAggregatoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idFlusso;
	private String codFiscEnte;
	private String codEsito;
	private String descEsito;
	private String statoFlusso;
	private String codDatoAggiuntivo;
	private String desDatoAggiuntivo;



	public String getCodDatoAggiuntivo() {
		return codDatoAggiuntivo;
	}



	public void setCodDatoAggiuntivo(String codDatoAggiuntivo) {
		this.codDatoAggiuntivo = codDatoAggiuntivo;
	}


	public String getDesDatoAggiuntivo() {
		return desDatoAggiuntivo;
	}



	public void setDesDatoAggiuntivo(String desDatoAggiuntivo) {
		this.desDatoAggiuntivo = desDatoAggiuntivo;
	}



	public String getStatoFlusso() {
		return statoFlusso;
	}



	public void setStatoFlusso(String statoFlusso) {
		this.statoFlusso = statoFlusso;
	}



	public String getCodEsito() {
		return codEsito;
	}



	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}



	public String getDescEsito() {
		return descEsito;
	}



	public void setDescEsito(String descEsito) {
		this.descEsito = descEsito;
	}



	public String getIdFlusso() {
		return idFlusso;
	}



	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}



	public String getCodFiscEnte() {
		return codFiscEnte;
	}



	public void setCodFiscEnte(String codFiscEnte) {
		this.codFiscEnte = codFiscEnte;
	}



	

}
