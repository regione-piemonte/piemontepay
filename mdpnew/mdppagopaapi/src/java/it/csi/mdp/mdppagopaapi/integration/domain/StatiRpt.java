/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stati_rpt database table.
 * 
 */
@Entity
@Table(name="stati_rpt")
@NamedQuery(name="StatiRpt.findAll", query="SELECT s FROM StatiRpt s")
public class StatiRpt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATI_RPT_IDSTATIRPT_GENERATOR", sequenceName="STATI_RPT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATI_RPT_IDSTATIRPT_GENERATOR")
	@Column(name="id_stati_rpt")
	private Integer idStatiRpt;

	private String descrizione;

	public StatiRpt() {
	}

	public Integer getIdStatiRpt() {
		return this.idStatiRpt;
	}

	public void setIdStatiRpt(Integer idStatiRpt) {
		this.idStatiRpt = idStatiRpt;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
