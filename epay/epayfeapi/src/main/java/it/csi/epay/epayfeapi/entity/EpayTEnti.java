/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table ( name = "epay_t_enti" )
@SuppressWarnings ( "unused" )
public class EpayTEnti implements Serializable {

	private static final long serialVersionUID = -6043606166880903881L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_ENTI_IDENTE_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_ENTI_ID_ENTE_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_ENTI_IDENTE_GENERATOR" )
	@Column ( name = "id_ente", unique = true, nullable = false )
	private Long idEnte;

	@Column ( name = "codice_fiscale", nullable = false, length = 16 )
	private String codiceFiscale;

	@Column ( name = "codice_gs1_gln", nullable = false, precision = 13 )
	private BigDecimal codiceGs1Gln;

	@Column ( name = "flag_invio_pagamenti", nullable = false )
	private Boolean flagInvioPagamenti;

	@Column ( name = "flag_intermediato" )
	private Boolean flagIntermediato;

	@Column ( nullable = false, length = 250 )
	private String nome;

	@Column ( length = 2000 )
	private String orari;

	@Column ( name = "codice_interbancario", length = 5 )
	private String codiceInterbancario;

	@Column ( name = "flag_invio_notificatore", nullable = false )
	private Boolean flagInvioNotificatore;

	@OneToMany ( mappedBy = "epayTEnti" )
	private List<EpayTTipoPagamento> epayTTipoPagamentos;

	@Column ( name = "flag_riconciliazione_versamenti" )
	private Boolean flagRiconciliazioneVersamenti;

	@Lob
	@Column ( name = "logo", nullable = false )
	@Type ( type = "org.hibernate.type.BinaryType" )
	private byte [] logo;

	public Boolean getFlagRiconciliazioneVersamenti () {
		return flagRiconciliazioneVersamenti;
	}

	public void setFlagRiconciliazioneVersamenti ( Boolean flagRiconciliazioneVersamenti ) {
		this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
	}

	public EpayTEnti () {
	}

	public EpayTEnti ( Long idEnte, String codiceFiscale, BigDecimal codiceGs1Gln, String codiceInterbancario, Boolean flagInvioPagamenti, String nome,
		String orari ) {
		this.idEnte = idEnte;
		this.codiceFiscale = codiceFiscale;
		this.codiceGs1Gln = codiceGs1Gln;
		this.codiceInterbancario = codiceInterbancario;
		this.flagInvioPagamenti = flagInvioPagamenti;
		this.nome = nome;
		this.orari = orari;
	}

	public Long getIdEnte () {
		return idEnte;
	}

	public void setIdEnte ( Long idEnte ) {
		this.idEnte = idEnte;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public BigDecimal getCodiceGs1Gln () {
		return codiceGs1Gln;
	}

	public void setCodiceGs1Gln ( BigDecimal codiceGs1Gln ) {
		this.codiceGs1Gln = codiceGs1Gln;
	}

	public Boolean getFlagInvioPagamenti () {
		return flagInvioPagamenti;
	}

	public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
		this.flagInvioPagamenti = flagInvioPagamenti;
	}

	public Boolean getFlagIntermediato () {
		return flagIntermediato;
	}

	public void setFlagIntermediato ( Boolean flagIntermediato ) {
		this.flagIntermediato = flagIntermediato;
	}

	public String getNome () {
		return nome;
	}

	public void setNome ( String nome ) {
		this.nome = nome;
	}

	public String getOrari () {
		return orari;
	}

	public void setOrari ( String orari ) {
		this.orari = orari;
	}

	public String getCodiceInterbancario () {
		return codiceInterbancario;
	}

	public void setCodiceInterbancario ( String codiceInterbancario ) {
		this.codiceInterbancario = codiceInterbancario;
	}

	public Boolean getFlagInvioNotificatore () {
		return flagInvioNotificatore;
	}

	public void setFlagInvioNotificatore ( Boolean flagInvioNotificatore ) {
		this.flagInvioNotificatore = flagInvioNotificatore;
	}

	public List<EpayTTipoPagamento> getEpayTTipoPagamentos () {
		return epayTTipoPagamentos;
	}

	public void setEpayTTipoPagamentos ( List<EpayTTipoPagamento> epayTTipoPagamentos ) {
		this.epayTTipoPagamentos = epayTTipoPagamentos;
	}

	public byte [] getLogo () {
		return logo;
	}

	public void setLogo ( byte [] logo ) {
		this.logo = logo;
	}

	@Override
	public String toString () {
		return "{ " +
			"idEnte:" + idEnte +
			", codiceFiscale:" + codiceFiscale +
			", codiceGs1Gln:" + codiceGs1Gln +
			", flagInvioPagamenti:" + flagInvioPagamenti +
			", flagIntermediato:" + flagIntermediato +
			", nome:" + nome +
			", orari:" + orari +
			", codiceInterbancario:" + codiceInterbancario +
			", flagInvioNotificatore:" + flagInvioNotificatore +
			// non esporre epayTTipoPagamentos
			", flagRiconciliazioneVersamenti:" + flagRiconciliazioneVersamenti +
			// non esporre logo
			" }";
	}
}
