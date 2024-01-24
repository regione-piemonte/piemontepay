/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the epaypacat_t_cdu database table.
 *
 */
@Entity
@Table(name = "epaycat_t_cdu")
@NamedQuery(name = "Cdu.findAll", query = "SELECT e FROM Cdu e")
public class Cdu extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String getPrimaryKeyRepresentation() {
		return String.valueOf(id);
	}

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	// uni-directional many-to-one association to EpaypacatDCategoriaCdu
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codice_categoria", referencedColumnName = "codice")
	private CategoriaCdu categoria;

	@Column(name = "codice_categoria", insertable = false, updatable = false)
	private String codiceCategoriaRO;

	public Cdu() {
	}

	public String getCodiceCategoriaRO() {
		return codiceCategoriaRO;
	}

	public void setCodiceCategoriaRO(String codiceCategoriaRO) {
		this.codiceCategoriaRO = codiceCategoriaRO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public CategoriaCdu getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaCdu categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Cdu [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", codiceCategoriaRO="
				+ codiceCategoriaRO + "]";
	}

}
