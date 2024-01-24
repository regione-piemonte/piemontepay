/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_d_categoria_cdu database table.
 * 
 */
@Entity
@Table(name="epaypa_d_categoria_cdu")
@NamedQuery(name="EpaypaDCategoriaCdu.findAll", query="SELECT e FROM EpaypaDCategoriaCdu e")
public class EpaypaDCategoriaCdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_categoria_cdu")
	private Integer idCategoriaCdu;

	@Column(name="cod_categoria_cdu")
	private String codCategoriaCdu;

	private String descrizione;

	public EpaypaDCategoriaCdu() {
	}

	public Integer getIdCategoriaCdu() {
		return this.idCategoriaCdu;
	}

	public void setIdCategoriaCdu(Integer idCategoriaCdu) {
		this.idCategoriaCdu = idCategoriaCdu;
	}

	public String getCodCategoriaCdu() {
		return this.codCategoriaCdu;
	}

	public void setCodCategoriaCdu(String codCategoriaCdu) {
		this.codCategoriaCdu = codCategoriaCdu;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
