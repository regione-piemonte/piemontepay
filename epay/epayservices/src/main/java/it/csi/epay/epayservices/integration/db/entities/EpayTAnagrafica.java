/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_anagrafica database table.
 * 
 */
@Entity
@Table(name="epay_t_anagrafica")
public class EpayTAnagrafica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAY_T_ANAGRAFICA_IDANAGRAFICA_GENERATOR", allocationSize=1, sequenceName="EPAY_T_ANAGRAFICA_ID_ANAGRAFICA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_ANAGRAFICA_IDANAGRAFICA_GENERATOR")
	@Column(name="id_anagrafica", unique=true, nullable=false)
	private Long idAnagrafica;

	@Column(length=16)
	private String cap;

	@Column(length=30)
	private String cellulare;

	@Column(length=16)
	private String civico;

	@Column(name="codice_fiscale", length=35)
	private String codiceFiscale;

	@Column(length=250)
	private String cognome;

	@Column(length=250)
	private String email;

	@Column(name="flag_persona_fisica", nullable=false)
	private Boolean flagPersonaFisica;

	@Column(length=70)
	private String indirizzo;

	@Column(length=35)
	private String localita;

	@Column(length=2)
	private String nazione;

	@Column(length=250)
	private String nome;

	@Column(length=35)
	private String provincia;

	@Column(name="ragione_sociale", length=250)
	private String ragioneSociale;

	@Column(length=30)
	private String telefono;

	//bi-directional many-to-one association to EpayTPagamento
	@OneToMany(mappedBy="epayTAnagrafica")
	private List<EpayTPagamento> epayTPagamentos;

	//bi-directional many-to-one association to EpayTRegistroVersamenti
	@OneToMany(mappedBy="epayTAnagrafica")
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	public EpayTAnagrafica() {
	}

	public Long getIdAnagrafica() {
		return this.idAnagrafica;
	}

	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCellulare() {
		return this.cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getCivico() {
		return this.civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getFlagPersonaFisica() {
		return this.flagPersonaFisica;
	}

	public void setFlagPersonaFisica(Boolean flagPersonaFisica) {
		this.flagPersonaFisica = flagPersonaFisica;
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

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<EpayTPagamento> getEpayTPagamentos() {
		return this.epayTPagamentos;
	}

	public void setEpayTPagamentos(List<EpayTPagamento> epayTPagamentos) {
		this.epayTPagamentos = epayTPagamentos;
	}

	public EpayTPagamento addEpayTPagamento(EpayTPagamento epayTPagamento) {
		getEpayTPagamentos().add(epayTPagamento);
		epayTPagamento.setEpayTAnagrafica(this);

		return epayTPagamento;
	}

	public EpayTPagamento removeEpayTPagamento(EpayTPagamento epayTPagamento) {
		getEpayTPagamentos().remove(epayTPagamento);
		epayTPagamento.setEpayTAnagrafica(null);

		return epayTPagamento;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis() {
		return this.epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis(List<EpayTRegistroVersamenti> epayTRegistroVersamentis) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	public EpayTRegistroVersamenti addEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().add(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayTAnagrafica(this);

		return epayTRegistroVersamenti;
	}

	public EpayTRegistroVersamenti removeEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().remove(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayTAnagrafica(null);

		return epayTRegistroVersamenti;
	}

}
