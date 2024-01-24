/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;

public class SoggettoAnagraficoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idUnivocoFiscale;
	private TipologiaSoggettoEnum tipologiaSoggettoEnum;
	private String ragioneSociale;
	private String cognome;
	private String nome;
	private String indirizzo;
	private String civico;
	private String cap;
	private String localita;
	private String provincia;
	private String nazione;
	private String email;

	public String getIdUnivocoFiscale() {
		return idUnivocoFiscale;
	}

	public void setIdUnivocoFiscale(String idUnivocoFiscale) {
		this.idUnivocoFiscale = idUnivocoFiscale;
	}

	public TipologiaSoggettoEnum getTipologiaSoggettoEnum() {
		return tipologiaSoggettoEnum;
	}

	public void setTipologiaSoggettoEnum(TipologiaSoggettoEnum tipologiaSoggettoEnum) {
		this.tipologiaSoggettoEnum = tipologiaSoggettoEnum;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognomeNomeOrRagioneSociale() {
		String s = null;
		if (tipologiaSoggettoEnum != null) {
			switch (tipologiaSoggettoEnum) {
			case PERSONA_FISICA:
				s = cognome + " " + nome;
				break;
			case PERSONA_GIURIDICA:
				s = ragioneSociale;
				break;
			}
		}
		return s;
	}
	
	public String getCognomeOrRagioneSociale() {
		String s = null;
		if (tipologiaSoggettoEnum != null) {
			switch (tipologiaSoggettoEnum) {
			case PERSONA_FISICA:
				s = cognome;
				break;
			case PERSONA_GIURIDICA:
				s = ragioneSociale;
				break;
			}
		}
		return s;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idUnivocoFiscale:").append(quote(idUnivocoFiscale)).append(COMMA);
		s.append("tipologiaSoggettoEnum:").append(tipologiaSoggettoEnum).append(COMMA);
		s.append("ragioneSociale:").append(quote(ragioneSociale)).append(COMMA);
		s.append("cognome:").append(quote(cognome)).append(COMMA);
		s.append("nome:").append(quote(nome)).append(COMMA);
		s.append("indirizzo:").append(quote(indirizzo)).append(COMMA);
		s.append("civico:").append(quote(civico)).append(COMMA);
		s.append("cap:").append(quote(cap)).append(COMMA);
		s.append("localita:").append(quote(localita)).append(COMMA);
		s.append("provincia:").append(quote(provincia)).append(COMMA);
		s.append("nazione:").append(quote(nazione)).append(COMMA);
		s.append("email:").append(quote(email));
		s.append(" }");
		return s.toString();
	}

}
