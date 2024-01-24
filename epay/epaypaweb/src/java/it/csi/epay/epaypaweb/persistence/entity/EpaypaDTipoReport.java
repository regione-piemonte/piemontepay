/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the epaypa_d_tipo_report database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_report")
@NamedQueries({
	@NamedQuery(name="EpaypaDTipoReport.findOneByCodice",
				query="SELECT e " + 
						"FROM EpaypaDTipoReport e " + 
						"WHERE e.codice = :codice"),
	@NamedQuery(name="EpaypaDTipoReport.findAll",
				query="SELECT e FROM EpaypaDTipoReport e")
})
public class EpaypaDTipoReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;
	
	//bi-directional many-to-one association to EpaypaTReport
	@OneToMany(mappedBy="epaypaDTipoReport")
	private List<EpaypaTReport> epaypaTReports;

	public EpaypaDTipoReport() {
		super();
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

	public List<EpaypaTReport> getEpaypaTReports() {
		return epaypaTReports;
	}

	public void setEpaypaTReports(List<EpaypaTReport> epaypaTReports) {
		this.epaypaTReports = epaypaTReports;
	}
}
