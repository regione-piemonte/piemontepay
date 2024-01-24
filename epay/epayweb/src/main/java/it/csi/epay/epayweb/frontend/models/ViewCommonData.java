/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

import java.io.Serializable;

public class ViewCommonData implements Serializable {
	private static final long serialVersionUID = -1676056114461373637L;
	
	private String titolo;
	private String descrizione;
	private String formAction;
	private Boolean pagamentoSpontaneo;
	private Boolean accessoAutenticato;
	private Boolean indietro;
	private Boolean prosegui;
	private Boolean stampaAvviso;
	
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String mail;
	
	private String linguaLocale;
	
	 private Boolean flagCodFiscStraniero;
	 
	 private Boolean flagDonazione;
	
	
	
	private Step step;
	
	public ViewCommonData() {
	}
	
	public ViewCommonData(String titolo, String descrizione, String formAction, Boolean pagamentoSpontaneo,
			Boolean accessoAutenticato, Boolean indietro, Boolean prosegui) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.formAction = formAction;
		this.pagamentoSpontaneo = pagamentoSpontaneo;
		this.accessoAutenticato = accessoAutenticato;
		this.indietro = indietro;
		this.prosegui = prosegui;
	}

	/**
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * @param titolo the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * @return the formAction
	 */
	public String getFormAction() {
		return formAction;
	}
	/**
	 * @param formAction the formAction to set
	 */
	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	/**
	 * @return the pagamentoSpontaneo
	 */
	public Boolean getPagamentoSpontaneo() {
		return pagamentoSpontaneo;
	}

	/**
	 * @param pagamentoSpontaneo the pagamentoSpontaneo to set
	 */
	public void setPagamentoSpontaneo(Boolean pagamentoSpontaneo) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	/**
	 * @return the accessoAutenticato
	 */
	public Boolean getAccessoAutenticato() {
		return accessoAutenticato;
	}

	/**
	 * @param accessoAutenticato the accessoAutenticato to set
	 */
	public void setAccessoAutenticato(Boolean accessoAutenticato) {
		this.accessoAutenticato = accessoAutenticato;
	}

	/**
	 * @return the indietro
	 */
	public Boolean getIndietro() {
		return indietro;
	}

	/**
	 * @param indietro the indietro to set
	 */
	public void setIndietro(Boolean indietro) {
		this.indietro = indietro;
	}

	/**
	 * @return the prosegui
	 */
	public Boolean getProsegui() {
		return prosegui;
	}

	/**
	 * @param prosegui the prosegui to set
	 */
	public void setProsegui(Boolean prosegui) {
		this.prosegui = prosegui;
	}
	public Boolean getStampaAvviso() {
		return stampaAvviso;
	}

	public void setStampaAvviso(Boolean stampaAvviso) {
		this.stampaAvviso = stampaAvviso;
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}	
	
	
	public Boolean getFlagCodFiscStraniero() {
		return flagCodFiscStraniero;
	}

	public void setFlagCodFiscStraniero(Boolean flagCodFiscStraniero) {
		this.flagCodFiscStraniero = flagCodFiscStraniero;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return nome + " " + cognome;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public String getLinguaLocale() {
		return linguaLocale;
	}

	public void setLinguaLocale(String linguaLocale) {
		this.linguaLocale = linguaLocale;
	}

	public Boolean getFlagDonazione() {
		return flagDonazione;
	}

	public void setFlagDonazione(Boolean flagDonazione) {
		this.flagDonazione = flagDonazione;
	}
	
	
	
	
}
