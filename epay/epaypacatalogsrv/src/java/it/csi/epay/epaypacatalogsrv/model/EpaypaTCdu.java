/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_cdu database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_t_cdu")
@NamedQuery(name="EpaypaTCdu.findAll", query="SELECT e FROM EpaypaTCdu e")
public class EpaypaTCdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cdu")
	private Integer idCdu;

	@Column(name="cod_categoria_cdu")
	private String codCategoriaCdu;

	@Column(name="cod_cdu")
	private String codCdu;

	private String descrizione;

	public EpaypaTCdu() {
	}

	public Integer getIdCdu() {
		return this.idCdu;
	}

	public void setIdCdu(Integer idCdu) {
		this.idCdu = idCdu;
	}

	public String getCodCategoriaCdu() {
		return this.codCategoriaCdu;
	}

	public void setCodCategoriaCdu(String codCategoriaCdu) {
		this.codCategoriaCdu = codCategoriaCdu;
	}

	public String getCodCdu() {
		return this.codCdu;
	}

	public void setCodCdu(String codCdu) {
		this.codCdu = codCdu;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
