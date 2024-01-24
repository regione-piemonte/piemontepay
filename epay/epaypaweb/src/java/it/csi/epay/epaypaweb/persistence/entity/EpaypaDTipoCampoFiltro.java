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
 * The persistent class for the epaypa_d_tipo_campo_filtro database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_campo_filtro")
@NamedQueries({
	@NamedQuery(name="EpaypaDTipoCampoFiltro.findOneByCodice",
				query="SELECT e " + 
						"FROM EpaypaDTipoCampoFiltro e " + 
						"WHERE e.codice = :codice"),
	@NamedQuery(name="EpaypaDTipoCampoFiltro.findAll",
				query="SELECT e FROM EpaypaDTipoCampoFiltro e")
})
public class EpaypaDTipoCampoFiltro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;
	
	//bi-directional many-to-one association to EpaypaTDatiFiltroReport
	@OneToMany(mappedBy="epaypaDTipoCampoFiltro")
	private List<EpaypaTDatiFiltroReport> epaypaTDatiFiltroReports;
	
	public EpaypaDTipoCampoFiltro() {
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

	public List<EpaypaTDatiFiltroReport> getEpaypaTDatiFiltroReports() {
		return epaypaTDatiFiltroReports;
	}

	public void setEpaypaTDatiFiltroReports(List<EpaypaTDatiFiltroReport> epaypaTDatiFiltroReports) {
		this.epaypaTDatiFiltroReports = epaypaTDatiFiltroReports;
	}
}
