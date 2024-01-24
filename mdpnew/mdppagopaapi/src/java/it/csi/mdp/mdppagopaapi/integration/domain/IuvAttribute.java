/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the iuv_attribute database table.
 * 
 */
@Entity
@Table(name="iuv_attribute")
@NamedQuery(name="IuvAttribute.findAll", query="SELECT i FROM IuvAttribute i")
public class IuvAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IuvAttributePK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_dismissione")
	private Date dataDismissione;

	private Long progressivo;

	//bi-directional many-to-one association to Enti
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ente_id", insertable=false, updatable=false)
	private Enti enti;

	public IuvAttribute() {
	}

	public IuvAttributePK getId() {
		return this.id;
	}

	public void setId(IuvAttributePK id) {
		this.id = id;
	}

	public Date getDataDismissione() {
		return this.dataDismissione;
	}

	public void setDataDismissione(Date dataDismissione) {
		this.dataDismissione = dataDismissione;
	}

	public Long getProgressivo() {
		return this.progressivo;
	}

	public void setProgressivo(Long progressivo) {
		this.progressivo = progressivo;
	}

	public Enti getEnti() {
		return this.enti;
	}

	public void setEnti(Enti enti) {
		this.enti = enti;
	}

}
