/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_cdu database table.
 * 
 */
@Entity
@Table(name="epaypa_t_cdu")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTCdu.findAllByIdRuolo",
			query = "SELECT c "
					+ "FROM EpaypaTRuolo r, EpaypaTCdu c "
					+ "JOIN r.epaypaTCdus rel "
					+ "WHERE r.idRuolo = :idRuolo "
					+ "AND c.codCdu = rel.codCdu"),
	@NamedQuery(
			name = "EpaypaTCdu.findAll",
			query = "SELECT e FROM EpaypaTCdu e")
})
public class EpaypaTCdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cdu")
	private Integer idCdu;

	@Column(name="cod_cdu")
	private String codCdu;

	private String descrizione;

	//uni-directional many-to-one association to EpaypaDCategoriaCdu
	@ManyToOne
	@JoinColumn(name="cod_categoria_cdu", referencedColumnName="cod_categoria_cdu")
	private EpaypaDCategoriaCdu epaypaDCategoriaCdu;

	public EpaypaTCdu() {
	}

	public Integer getIdCdu() {
		return this.idCdu;
	}

	public void setIdCdu(Integer idCdu) {
		this.idCdu = idCdu;
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

	public EpaypaDCategoriaCdu getEpaypaDCategoriaCdu() {
		return this.epaypaDCategoriaCdu;
	}

	public void setEpaypaDCategoriaCdu(EpaypaDCategoriaCdu epaypaDCategoriaCdu) {
		this.epaypaDCategoriaCdu = epaypaDCategoriaCdu;
	}

}
