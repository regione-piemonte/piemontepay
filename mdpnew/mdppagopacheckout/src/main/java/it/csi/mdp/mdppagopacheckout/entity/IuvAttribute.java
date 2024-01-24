/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the iuv_attribute database table.
 */
@Entity
@Table ( name = "iuv_attribute" )
@NamedQuery ( name = "IuvAttribute.findAll", query = "SELECT i FROM IuvAttribute i" )
@SuppressWarnings ( "unused" )
public class IuvAttribute implements Serializable {

	private static final long serialVersionUID = -6977926315893728762L;

	@EmbeddedId
	private IuvAttributePK id;

	@Temporal ( TemporalType.DATE )
	@Column ( name = "data_dismissione" )
	private Date dataDismissione;

	private Long progressivo;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "ente_id", insertable = false, updatable = false )
	private Enti enti;

	public IuvAttribute () {
	}

	public IuvAttributePK getId () {
		return this.id;
	}

	public void setId ( IuvAttributePK id ) {
		this.id = id;
	}

	public Date getDataDismissione () {
		return this.dataDismissione;
	}

	public void setDataDismissione ( Date dataDismissione ) {
		this.dataDismissione = dataDismissione;
	}

	public Long getProgressivo () {
		return this.progressivo;
	}

	public void setProgressivo ( Long progressivo ) {
		this.progressivo = progressivo;
	}

	public Enti getEnti () {
		return this.enti;
	}

	public void setEnti ( Enti enti ) {
		this.enti = enti;
	}

}
