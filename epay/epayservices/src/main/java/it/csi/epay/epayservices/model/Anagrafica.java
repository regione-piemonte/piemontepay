/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class Anagrafica implements Serializable {
	
	private static final long serialVersionUID = 1665855562058821949L;
	
	private Long idAnagrafica;
	private String cellulare;
	private String nome;
	private String cognome;
	private String ragioneSociale;
	private String codiceFiscale;
	private String indirizzo;
	private String email;
	private Boolean flagPersonaFisica;
	private String cap;
	private String civico;
	private String localita;
	private String nazione;
	private String provincia;
	private String telefono;

	
	/**
	 * @return the idAnagrafica
	 */
	public Long getIdAnagrafica() {
		return idAnagrafica;
	}
	/**
	 * @param idAnagrafica the idAnagrafica to set
	 */
	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	/**
	 * @return the cellulare
	 */
	public String getCellulare() {
		return cellulare;
	}
	/**
	 * @param cellulare the cellulare to set
	 */
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	/**
	 * @return the denominazione
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	/**
	 * @param denominazione the denominazione to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getFlagPersonaFisica() {
		return flagPersonaFisica;
	}
	public void setFlagPersonaFisica(Boolean flagPersonaFisica) {
		this.flagPersonaFisica = flagPersonaFisica;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

