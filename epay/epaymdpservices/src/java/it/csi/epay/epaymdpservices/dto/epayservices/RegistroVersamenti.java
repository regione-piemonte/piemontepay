/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.dto.epayservices;

import java.io.Serializable;
import java.util.Date;

import it.csi.epay.epayservices.model.Anagrafica;

public class RegistroVersamenti implements Serializable{

	private static final long serialVersionUID = -5281106170703829439L;
	
	private Long idPagamento;
	private Date dataOperazione;
	private Anagrafica anagraficaVersante;
	private String stato;
	private String idTransazione;	
	private String iuv;
	public Long getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}
	public Date getDataOperazione() {
		return dataOperazione;
	}
	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}
	public Anagrafica getAnagraficaVersante() {
		return anagraficaVersante;
	}
	public void setAnagraficaVersante(Anagrafica anagraficaVersante) {
		this.anagraficaVersante = anagraficaVersante;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getIdTransazione() {
		return idTransazione;
	}
	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
