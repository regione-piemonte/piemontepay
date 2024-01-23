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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table ( name = "epay_t_dati_avviso_pagamento" )
@SuppressWarnings ( "unused" )
public class EpayTDatiAvvisoPagamento implements Serializable {

	private static final long serialVersionUID = -8544768981389940856L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_DATI_AVVISO_PAGAMENTO_ID_DATI_AVVISO_PAGAMENTO_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_DATI_AVVISO_PAGAMENTO_ID_DATI_AVVISO_PAGAMENTO_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_DATI_AVVISO_PAGAMENTO_ID_DATI_AVVISO_PAGAMENTO_GENERATOR" )
	@Column ( name = "id_dati_avviso_pagamento", unique = true, nullable = false )
	private Long idDatiAvvisoPagamento;

	@ManyToOne
	@JoinColumn ( name = "id_codice_versamento", nullable = false )
	private EpayTTipoPagamento epayTTipoPagamento;

	@Column ( length = 50 )
	private String settore;

	@Column ( length = 30 )
	private String indirizzo;

	@Column ( length = 30 )
	private String localita;

	@Column ( length = 5 )
	private String cap;

	@Column ( name = "sigla_provincia", length = 2 )
	private String siglaProvincia;

	@Column ( length = 27 )
	private String email;

	@Column ( name = "info_ente", length = 100 )
	private String infoEnte;

	@Column ( name = "intestatario_cc_postale", length = 50 )
	private String intestatarioCCPostale;

	@Column ( name = "numero_cc_postale" )
	private String numeroCCPostale;

	@Column ( name = "autorizzazione_da_poste_it", length = 37 )
	private String autorizzazioneDaPosteIt;

	public EpayTDatiAvvisoPagamento () {
	}

	public Long getIdDatiAvvisoPagamento () {
		return idDatiAvvisoPagamento;
	}

	public void setIdDatiAvvisoPagamento ( Long idDatiAvvisoPagamento ) {
		this.idDatiAvvisoPagamento = idDatiAvvisoPagamento;
	}

	public EpayTTipoPagamento getEpayTTipoPagamento () {
		return epayTTipoPagamento;
	}

	public void setEpayTTipoPagamento ( EpayTTipoPagamento epayTTipoPagamento ) {
		this.epayTTipoPagamento = epayTTipoPagamento;
	}

	public String getSettore () {
		return settore;
	}

	public void setSettore ( String settore ) {
		this.settore = settore;
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

	public String getCap () {
		return cap;
	}

	public void setCap ( String cap ) {
		this.cap = cap;
	}

	public String getSiglaProvincia () {
		return siglaProvincia;
	}

	public void setSiglaProvincia ( String siglaProvincia ) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail ( String email ) {
		this.email = email;
	}

	public String getInfoEnte () {
		return infoEnte;
	}

	public void setInfoEnte ( String infoEnte ) {
		this.infoEnte = infoEnte;
	}

	public String getIntestatarioCCPostale () {
		return intestatarioCCPostale;
	}

	public void setIntestatarioCCPostale ( String intestatarioCCPostale ) {
		this.intestatarioCCPostale = intestatarioCCPostale;
	}

	public String getNumeroCCPostale () {
		return numeroCCPostale;
	}

	public void setNumeroCCPostale ( String numeroCCPostale ) {
		this.numeroCCPostale = numeroCCPostale;
	}

	public String getAutorizzazioneDaPosteIt () {
		return autorizzazioneDaPosteIt;
	}

	public void setAutorizzazioneDaPosteIt ( String autorizzazioneDaPosteIt ) {
		this.autorizzazioneDaPosteIt = autorizzazioneDaPosteIt;
	}

	@Override
	public String toString () {
		return "{ " +
			"idDatiAvvisoPagamento:" + idDatiAvvisoPagamento +
			// non esporre epayTTipoPagamento
			", settore:" + settore +
			", indirizzo:" + indirizzo +
			", localita:" + localita +
			", cap:" + cap +
			", siglaProvincia:" + siglaProvincia +
			", email:" + email +
			", infoEnte:" + infoEnte +
			", intestatarioCCPostale:" + intestatarioCCPostale +
			", numeroCCPostale:" + numeroCCPostale +
			", autorizzazioneDaPosteIt:" + autorizzazioneDaPosteIt +
			" }";
	}
}
