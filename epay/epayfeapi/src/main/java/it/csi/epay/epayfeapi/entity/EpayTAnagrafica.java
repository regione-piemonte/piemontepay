/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Entity
@Table ( name = "epay_t_anagrafica" )
@SuppressWarnings ( "unused" )
public class EpayTAnagrafica implements Serializable {

	private static final long serialVersionUID = 4278177671932425938L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_ANAGRAFICA_IDANAGRAFICA_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_ANAGRAFICA_ID_ANAGRAFICA_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_ANAGRAFICA_IDANAGRAFICA_GENERATOR" )
	@Column ( name = "id_anagrafica", unique = true, nullable = false )
	private Long idAnagrafica;

	@Column ( length = 250 )
	private String nome;

	@Column ( length = 250 )
	private String cognome;

	@Column ( name = "codice_fiscale", length = 35 )
	private String codiceFiscale;

	@Column ( length = 70 )
	private String indirizzo;

	@Column ( length = 16 )
	private String civico;

	@Column ( length = 35 )
	private String localita;

	@Column ( length = 35 )
	private String provincia;

	@Column ( length = 2 )
	private String nazione;

	@Column ( length = 16 )
	private String cap;

	@Column ( length = 30 )
	private String telefono;

	@Column ( length = 30 )
	private String cellulare;

	@Column ( length = 250 )
	private String email;

	@Column ( name = "flag_persona_fisica", nullable = false )
	private Boolean flagPersonaFisica;

	@Column ( name = "ragione_sociale", length = 250 )
	private String ragioneSociale;

	@OneToMany ( mappedBy = "epayTAnagrafica" )
	private List<EpayTPagamento> epayTPagamentos;

	@OneToMany ( mappedBy = "epayTAnagrafica" )
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	public EpayTAnagrafica () {
	}

	public Long getIdAnagrafica () {
		return idAnagrafica;
	}

	public void setIdAnagrafica ( Long idAnagrafica ) {
		this.idAnagrafica = idAnagrafica;
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

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCap () {
		return cap;
	}

	public String getIndirizzo () {
		return indirizzo;
	}

	public void setIndirizzo ( String indirizzo ) {
		this.indirizzo = indirizzo;
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

	public String getProvincia () {
		return provincia;
	}

	public void setProvincia ( String provincia ) {
		this.provincia = provincia;
	}

	public String getNazione () {
		return nazione;
	}

	public void setNazione ( String nazione ) {
		this.nazione = nazione;
	}

	public void setCap ( String cap ) {
		this.cap = cap;
	}

	public String getTelefono () {
		return telefono;
	}

	public void setTelefono ( String telefono ) {
		this.telefono = telefono;
	}

	public String getCellulare () {
		return cellulare;
	}

	public void setCellulare ( String cellulare ) {
		this.cellulare = cellulare;
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

	public String getRagioneSociale () {
		return ragioneSociale;
	}

	public void setRagioneSociale ( String ragioneSociale ) {
		this.ragioneSociale = ragioneSociale;
	}

	public List<EpayTPagamento> getEpayTPagamentos () {
		return epayTPagamentos;
	}

	public void setEpayTPagamentos ( List<EpayTPagamento> epayTPagamentos ) {
		this.epayTPagamentos = epayTPagamentos;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis () {
		return epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis ( List<EpayTRegistroVersamenti> epayTRegistroVersamentis ) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	@Override
	public String toString () {
		return "{ " +
			"idAnagrafica:" + idAnagrafica +
			", nome:" + nome +
			", cognome:" + cognome +
			", codiceFiscale:" + codiceFiscale +
			", indirizzo:" + indirizzo +
			", civico:" + civico +
			", localita:" + localita +
			", provincia:" + provincia +
			", nazione:" + nazione +
			", cap:" + cap +
			", telefono:" + telefono +
			", cellulare:" + cellulare +
			", email:" + email +
			", flagPersonaFisica:" + flagPersonaFisica +
			", ragioneSociale:" + ragioneSociale +
			// non esporre epayTPagamentos
			// non esporre epayTRegistroVersamentis
			" }";
	}
}
