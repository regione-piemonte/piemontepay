/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_t_voce_entrata database table.
 *
 */
@Entity
@Table(name="epaycat_t_voce_entrata")
@NamedQuery(name="VoceEntrata.findAll", query="SELECT v FROM VoceEntrata v")
public class VoceEntrata extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

	@Id
	private Long id;

	private String codice;

	private String descrizione;

	//uni-directional many-to-one association to MacrotipoPpay
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codice_macrotipo_ppay", referencedColumnName="codice")
	private MacrotipoPpay macrotipo;

	//uni-directional many-to-one association to TematicaPpay
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codice_tematica_ppay", referencedColumnName="codice")
	private TematicaPpay tematica;

	public VoceEntrata() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public MacrotipoPpay getMacrotipo() {
		return macrotipo;
	}

	public void setMacrotipo(MacrotipoPpay macrotipo) {
		this.macrotipo = macrotipo;
	}

	public TematicaPpay getTematica() {
		return tematica;
	}

	public void setTematica(TematicaPpay tematica) {
		this.tematica = tematica;
	}

	@Override
	public String toString() {
		return "VoceEntrata [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
