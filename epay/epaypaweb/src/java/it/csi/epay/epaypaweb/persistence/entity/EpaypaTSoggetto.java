/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_soggetto database table.
 * 
 */
@Entity
@Table(name="epaypa_t_soggetto")
@NamedQuery(name="EpaypaTSoggetto.findAll", query="SELECT e FROM EpaypaTSoggetto e")
public class EpaypaTSoggetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_soggetto")
	private Long idSoggetto;

	private String cap;

	private String civico;

	@Column(name="cognome_rag_soc")
	private String cognomeRagSoc;

	private String email;

	@Column(name="id_univoco_fiscale")
	private String idUnivocoFiscale;

	private String indirizzo;

	private String localita;

	private String nazione;

	private String nome;

	private String provincia;

	@Column(name="tipologia_soggetto")
	private String tipologiaSoggetto;

	public EpaypaTSoggetto() {
	}

	public Long getIdSoggetto() {
		return this.idSoggetto;
	}

	public void setIdSoggetto(Long idSoggetto) {
		this.idSoggetto = idSoggetto;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCivico() {
		return this.civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCognomeRagSoc() {
		return this.cognomeRagSoc;
	}

	public void setCognomeRagSoc(String cognomeRagSoc) {
		this.cognomeRagSoc = cognomeRagSoc;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdUnivocoFiscale() {
		return this.idUnivocoFiscale;
	}

	public void setIdUnivocoFiscale(String idUnivocoFiscale) {
		this.idUnivocoFiscale = idUnivocoFiscale;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTipologiaSoggetto() {
		return this.tipologiaSoggetto;
	}

	public void setTipologiaSoggetto(String tipologiaSoggetto) {
		this.tipologiaSoggetto = tipologiaSoggetto;
	}

}
