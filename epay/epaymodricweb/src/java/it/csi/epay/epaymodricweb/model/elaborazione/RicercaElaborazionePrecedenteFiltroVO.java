/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.elaborazione;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import it.csi.epay.epaymodricweb.util.SecurityUtils;

public class RicercaElaborazionePrecedenteFiltroVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String validitaGenerica;
	
	private String idEnte;
	
	private String codiceFiscaleEnte;
	
	private String utenteElaborazione;
	
	private String statoElaborazione;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInizio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFine;
	
	
	
	public RicercaElaborazionePrecedenteFiltroVO() {
		codiceFiscaleEnte = SecurityUtils.getUser().getEnte().getCodiceFiscale();
	}
	
	public String getValiditaGenerica() {
		return validitaGenerica;
	}

	public void setValiditaGenerica(String validitaGenerica) {
		this.validitaGenerica = validitaGenerica;
	}

	public String getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getUtenteElaborazione() {
		return utenteElaborazione;
	}

	public void setUtenteElaborazione(String utenteElaborazione) {
		this.utenteElaborazione = utenteElaborazione;
	}

	public String getStatoElaborazione() {
		return statoElaborazione;
	}

	public void setStatoElaborazione(String statoElaborazione) {
		this.statoElaborazione = statoElaborazione;
	}

	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	
	
	
	
}
