/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.filter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Entity
@Table ( name = "epaypa_t_soggetto" )
//@NamedQuery (name="EpaypaTSoggetto.findAll", query="SELECT e FROM EpaypaTSoggetto e")
public class EpaypaTSoggettoFilter implements Serializable {

	private static final long serialVersionUID = 5999923123233909053L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id_soggetto" )
	private Long idSoggetto;

	private String cap;

	private String civico;

	@Column ( name = "cognome_rag_soc" )
	private String cognomeRagSoc;

	private String email;

	@Column ( name = "id_univoco_fiscale" )
	private String idUnivocoFiscale;

	private String indirizzo;

	private String localita;

	private String nazione;

	private String nome;

	private String provincia;

	@Column ( name = "tipologia_soggetto" )
	private String tipologiaSoggetto;

	@OneToMany
	@Column ( name = "id_soggetto" )
	List<EpaypaTPosizioneDebitoriaFilter> epaypaTPosizioneDebitoriaFilterList;

	public EpaypaTSoggettoFilter () {
	}

	public List<EpaypaTPosizioneDebitoriaFilter> getEpaypaTPosizioneDebitoriaFilterList () {
		return epaypaTPosizioneDebitoriaFilterList;
	}

	public void setEpaypaTPosizioneDebitoriaFilterList (
					List<EpaypaTPosizioneDebitoriaFilter> epaypaTPosizioneDebitoriaFilterList ) {
		this.epaypaTPosizioneDebitoriaFilterList = epaypaTPosizioneDebitoriaFilterList;
	}

	public Long getIdSoggetto () {
		return idSoggetto;
	}

	public void setIdSoggetto ( Long idSoggetto ) {
		this.idSoggetto = idSoggetto;
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

	public String getCognomeRagSoc () {
		return cognomeRagSoc;
	}

	public void setCognomeRagSoc ( String cognomeRagSoc ) {
		this.cognomeRagSoc = cognomeRagSoc;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail ( String email ) {
		this.email = email;
	}

	public String getIdUnivocoFiscale () {
		return idUnivocoFiscale;
	}

	public void setIdUnivocoFiscale ( String idUnivocoFiscale ) {
		this.idUnivocoFiscale = idUnivocoFiscale;
	}

	public String getIndirizzo () {
		return indirizzo;
	}

	public void setIndirizzo ( String indirizzo ) {
		this.indirizzo = indirizzo;
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

	public String getNome () {
		return nome;
	}

	public void setNome ( String nome ) {
		this.nome = nome;
	}

	public String getProvincia () {
		return provincia;
	}

	public void setProvincia ( String provincia ) {
		this.provincia = provincia;
	}

	public String getTipologiaSoggetto () {
		return tipologiaSoggetto;
	}

	public void setTipologiaSoggetto ( String tipologiaSoggetto ) {
		this.tipologiaSoggetto = tipologiaSoggetto;
	}
}
