/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table ( name = "iuv_ottici" )
@NamedQuery ( name = "IuvOttici.findAll", query = "SELECT i FROM IuvOttici i" )
@SuppressWarnings ( "unused" )
public class IuvOttici implements Serializable {

	private static final long serialVersionUID = -2485319647490174042L;

	@Id
	@SequenceGenerator ( name = "IUV_OTTICI_ID_GENERATOR", sequenceName = "IUV_OTTICI_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "IUV_OTTICI_ID_GENERATOR" )
	private long id;

	@Column ( name = "cod_versamento" )
	private String codVersamento;

	@Column ( name = "data_creazione" )
	private Timestamp dataCreazione;

	@Column ( name = "data_riconciliazione" )
	private Timestamp dataRiconciliazione;

	@Column ( name = "iuv_ottico" )
	private String iuvOttico;

	@Column ( name = "iuv_standard" )
	private String iuvStandard;

	@ManyToOne ( fetch = FetchType.EAGER )
	private Application application;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "ente_id" )
	private Enti enti;

	public IuvOttici () {
	}

	public long getId () {
		return this.id;
	}

	public void setId ( long id ) {
		this.id = id;
	}

	public String getCodVersamento () {
		return this.codVersamento;
	}

	public void setCodVersamento ( String codVersamento ) {
		this.codVersamento = codVersamento;
	}

	public Timestamp getDataCreazione () {
		return this.dataCreazione;
	}

	public void setDataCreazione ( Timestamp dataCreazione ) {
		this.dataCreazione = dataCreazione;
	}

	public Timestamp getDataRiconciliazione () {
		return this.dataRiconciliazione;
	}

	public void setDataRiconciliazione ( Timestamp dataRiconciliazione ) {
		this.dataRiconciliazione = dataRiconciliazione;
	}

	public String getIuvOttico () {
		return this.iuvOttico;
	}

	public void setIuvOttico ( String iuvOttico ) {
		this.iuvOttico = iuvOttico;
	}

	public String getIuvStandard () {
		return this.iuvStandard;
	}

	public void setIuvStandard ( String iuvStandard ) {
		this.iuvStandard = iuvStandard;
	}

	public Application getApplication () {
		return this.application;
	}

	public void setApplication ( Application application ) {
		this.application = application;
	}

	public Enti getEnti () {
		return this.enti;
	}

	public void setEnti ( Enti enti ) {
		this.enti = enti;
	}

}
