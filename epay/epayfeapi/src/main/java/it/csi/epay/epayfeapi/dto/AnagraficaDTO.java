/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class AnagraficaDTO implements Serializable {

	private static final long serialVersionUID = 8030246207804909458L;

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

	public Long getIdAnagrafica () {
		return idAnagrafica;
	}

	public void setIdAnagrafica ( Long idAnagrafica ) {
		this.idAnagrafica = idAnagrafica;
	}

	public String getCellulare () {
		return cellulare;
	}

	public void setCellulare ( String cellulare ) {
		this.cellulare = cellulare;
	}

	public String getNome () {
		return nome;
	}

	public void setNome ( String nome ) {
		this.nome = nome;
	}

	public String getCognome () {
		return cognome;
	}

	public void setCognome ( String cognome ) {
		this.cognome = cognome;
	}

	public String getRagioneSociale () {
		return ragioneSociale;
	}

	public void setRagioneSociale ( String ragioneSociale ) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo () {
		return indirizzo;
	}

	public void setIndirizzo ( String indirizzo ) {
		this.indirizzo = indirizzo;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail ( String email ) {
		this.email = email;
	}

	public Boolean getFlagPersonaFisica () {
		return flagPersonaFisica;
	}

	public void setFlagPersonaFisica ( Boolean flagPersonaFisica ) {
		this.flagPersonaFisica = flagPersonaFisica;
	}

	public String getCap () {
		return cap;
	}

	public void setCap ( String cap ) {
		this.cap = cap;
	}

	public String getCivico () {
		return civico;
	}

	public void setCivico ( String civico ) {
		this.civico = civico;
	}

	public String getLocalita () {
		return localita;
	}

	public void setLocalita ( String localita ) {
		this.localita = localita;
	}

	public String getNazione () {
		return nazione;
	}

	public void setNazione ( String nazione ) {
		this.nazione = nazione;
	}

	public String getProvincia () {
		return provincia;
	}

	public void setProvincia ( String provincia ) {
		this.provincia = provincia;
	}

	public String getTelefono () {
		return telefono;
	}

	public void setTelefono ( String telefono ) {
		this.telefono = telefono;
	}
}
