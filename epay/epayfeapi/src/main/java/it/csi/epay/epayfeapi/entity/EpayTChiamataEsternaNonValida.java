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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table ( name = "epay_t_chiamata_esterna_non_valida" )
@SuppressWarnings ( "unused" )
public class EpayTChiamataEsternaNonValida implements Serializable {

	private static final long serialVersionUID = -4645892137079767169L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_CHIAMATA_ESTERNA_NON_VALIDA_ID_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_CHIAMATA_ESTERNA_NON_VALIDA_ID_CHIAMATA_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_CHIAMATA_ESTERNA_NON_VALIDA_ID_GENERATOR" )
	@Column ( name = "id_chiamata", unique = true, nullable = false )
	private Long idChiamata;

	private String digest;

	private String iuv;

	@Column ( name = "codice_fiscale" )
	private String codiceFiscale;

	@Column ( name = "identificativo_pagamento" )
	private String identificativoPagamento;

	@Column ( name = "timestamp_chiamata", insertable = false, updatable = false, nullable = false )
	private Timestamp timestampChiamata;

	@Column ( name = "descrizione_errore" )
	private String descrizioneErrore;

	@Column ( name = "remote_host" )
	private String remoteHost;

	@Column ( name = "codice_chiamante", nullable = false )
	private String codiceChiamante;

	@Column ( name = "service_name", length = 256 )
	private String serviceName;

	@Column ( name = "duration" )
	private Integer duration;

	public EpayTChiamataEsternaNonValida () {
	}

	public Long getIdChiamata () {
		return this.idChiamata;
	}

	public void setIdChiamata ( Long idChiamata ) {
		this.idChiamata = idChiamata;
	}

	public String getDigest () {
		return this.digest;
	}

	public void setDigest ( String digest ) {
		this.digest = digest;
	}

	public String getIuv () {
		return this.iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getCodiceFiscale () {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIdentificativoPagamento () {
		return identificativoPagamento;
	}

	public void setIdentificativoPagamento ( String identificativoPagamento ) {
		this.identificativoPagamento = identificativoPagamento;
	}

	public Timestamp getTimestampChiamata () {
		return timestampChiamata;
	}

	public void setTimestampChiamata ( Timestamp timestampChiamata ) {
		this.timestampChiamata = timestampChiamata;
	}

	public String getDescrizioneErrore () {
		return descrizioneErrore;
	}

	public void setDescrizioneErrore ( String descrizioneErrore ) {
		this.descrizioneErrore = descrizioneErrore;
	}

	public String getRemoteHost () {
		return remoteHost;
	}

	public void setRemoteHost ( String remoteHost ) {
		this.remoteHost = remoteHost;
	}

	public String getCodiceChiamante () {
		return codiceChiamante;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		this.codiceChiamante = codiceChiamante;
	}

	public String getServiceName () {
		return serviceName;
	}

	public void setServiceName ( String serviceName ) {
		this.serviceName = serviceName;
	}

	public Integer getDuration () {
		return duration;
	}

	public void setDuration ( Integer duration ) {
		this.duration = duration;
	}

	@Override
	public String toString () {
		return "{ " +
						"idChiamata:" + idChiamata +
						", digest:" + digest +
						", iuv:" + iuv +
						", codiceFiscale:" + codiceFiscale +
						", identificativoPagamento:" + identificativoPagamento +
						", timestampChiamata:" + timestampChiamata +
						", descrizioneErrore:" + descrizioneErrore +
						", remoteHost:" + remoteHost +
						", codiceChiamante:" + codiceChiamante +
						" }";
	}
}
