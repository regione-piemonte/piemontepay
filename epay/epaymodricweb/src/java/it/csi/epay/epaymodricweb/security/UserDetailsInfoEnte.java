/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.security;

public class UserDetailsInfoEnte implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String codiceFiscale;
	protected String denominazione;
	protected String descrizioneUtenteAmministratore;
	protected Long id;
	protected String idEnte;
	protected Long idUtenteAmministratore;
	protected String indirizzo;
	protected String localita;

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDescrizioneUtenteAmministratore() {
		return descrizioneUtenteAmministratore;
	}

	public void setDescrizioneUtenteAmministratore(String descrizioneUtenteAmministratore) {
		this.descrizioneUtenteAmministratore = descrizioneUtenteAmministratore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUtenteAmministratore() {
		return idUtenteAmministratore;
	}

	public void setIdUtenteAmministratore(Long idUtenteAmministratore) {
		this.idUtenteAmministratore = idUtenteAmministratore;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}
	
	

}
