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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table ( name = "epay_t_tracciabilita_chiamante_esterno" )
@SuppressWarnings ( "unused" )
public class EpayTTracciabilitaChiamanteEsterno implements Serializable {

	private static final long serialVersionUID = -122473272538525847L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_TRACCIABILITA_CHIAMANTE_ESTERNO_IDCHIAMATA_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_TRACCIABILITA_CHIAMANTE_ESTERNO_ID_CHIAMATA_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_TRACCIABILITA_CHIAMANTE_ESTERNO_IDCHIAMATA_GENERATOR" )
	@Column ( name = "id_chiamata", unique = true, nullable = false )
	private Long idChiamata;

	private String digest;

	private String iuv;

	@Column ( name = "codice_fiscale" )
	private String codiceFiscale;

	@Column ( name = "id_transazione", unique = true )
	private String idTransazione;

	@Column ( name = "identificativo_pagamento" )
	private String identificativoPagamento;

	@Column ( name = "timestamp_chiamata", insertable = false, updatable = false, nullable = false )
	@Temporal ( TemporalType.TIMESTAMP )
	private Date timestampChiamata;

	@Column ( name = "remote_host" )
	private String remoteHost;

	@Column ( name = "descrizione_chiamante" )
	private String descrizioneChiamante;

	@ManyToOne
	@JoinColumn ( name = "codice_chiamante", nullable = false )
	private EpayDChiamanteEsterno epayDChiamanteEsterno;

	@Column ( name = "service_name", length = 256 )
	private String serviceName;

	@Column ( name = "duration" )
	private Integer duration;

	public EpayTTracciabilitaChiamanteEsterno () {
	}

	public Long getIdChiamata () {
		return this.idChiamata;
	}

	public void setIdChiamata ( Long idChiamata ) {
		this.idChiamata = idChiamata;
	}

	public String getCodiceFiscale () {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDigest () {
		return this.digest;
	}

	public void setDigest ( String digest ) {
		this.digest = digest;
	}

	public String getIdTransazione () {
		return this.idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public String getIuv () {
		return this.iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public EpayDChiamanteEsterno getEpayDChiamanteEsterno () {
		return this.epayDChiamanteEsterno;
	}

	public void setEpayDChiamanteEsterno ( EpayDChiamanteEsterno epayDChiamanteEsterno ) {
		this.epayDChiamanteEsterno = epayDChiamanteEsterno;
	}

	public Date getTimestampChiamata () {
		return timestampChiamata;
	}

	public void setTimestampChiamata ( Date timestampChiamata ) {
		this.timestampChiamata = timestampChiamata;
	}

	public String getRemoteHost () {
		return remoteHost;
	}

	public void setRemoteHost ( String remoteHost ) {
		this.remoteHost = remoteHost;
	}

	public String getIdentificativoPagamento () {
		return identificativoPagamento;
	}

	public void setIdentificativoPagamento ( String identificativoPagamento ) {
		this.identificativoPagamento = identificativoPagamento;
	}

	public String getDescrizioneChiamante () {
		return descrizioneChiamante;
	}

	public void setDescrizioneChiamante ( String descrizioneChiamante ) {
		this.descrizioneChiamante = descrizioneChiamante;
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
						", idTransazione:" + idTransazione +
						", identificativoPagamento:" + identificativoPagamento +
						", timestampChiamata:" + timestampChiamata +
						", remoteHost:" + remoteHost +
						", descrizioneChiamante:" + descrizioneChiamante +
						// non esporre epayDChiamanteEsterno
						" }";
	}
}
