/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_revoca database table.
 * 
 */
@Entity
@Table(name="tipo_revoca")
@NamedQuery(name="TipoRevoca.findAll", query="SELECT t FROM TipoRevoca t")
public class TipoRevoca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_REVOCA_ID_GENERATOR", sequenceName="TIPO_REVOCA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_REVOCA_ID_GENERATOR")
	private long id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to Rr
	@OneToMany(mappedBy="tipoRevocaBean")
	private List<Rr> rrs;

	public TipoRevoca() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Rr> getRrs() {
		return this.rrs;
	}

	public void setRrs(List<Rr> rrs) {
		this.rrs = rrs;
	}

	public Rr addRr(Rr rr) {
		getRrs().add(rr);
		rr.setTipoRevocaBean(this);

		return rr;
	}

	public Rr removeRr(Rr rr) {
		getRrs().remove(rr);
		rr.setTipoRevocaBean(null);

		return rr;
	}

}
