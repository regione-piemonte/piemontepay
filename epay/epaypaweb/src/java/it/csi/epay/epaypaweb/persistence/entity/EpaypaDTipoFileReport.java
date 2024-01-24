/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the epaypa_d_tipo_file_report database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_file_report")
@NamedQueries({
	@NamedQuery(name="EpaypaDTipoFileReport.findOneByCodice",
				query="SELECT e " + 
						"FROM EpaypaDTipoFileReport e " + 
						"WHERE e.codice = :codice"),
	@NamedQuery(name="EpaypaDTipoFileReport.findAll",
				query="SELECT e FROM EpaypaDTipoFileReport e")
})
public class EpaypaDTipoFileReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to EpaypaTReport
	@OneToMany(mappedBy="epaypaDTipoFileReport")
	private List<EpaypaTReport> epaypaTReports;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public List<EpaypaTReport> getEpaypaTReports() {
		return this.epaypaTReports;
	}

	public void setEpaypaTReports(List<EpaypaTReport> epaypaTReports) {
		this.epaypaTReports = epaypaTReports;
	}

	public EpaypaTReport addEpaypaTReport(EpaypaTReport epaypaTReport) {
		getEpaypaTReports().add(epaypaTReport);
		epaypaTReport.setEpaypaDTipoFileReport(this);

		return epaypaTReport;
	}

	public EpaypaTReport removeEpaypaTReport(EpaypaTReport epaypaTReport) {
		getEpaypaTReports().remove(epaypaTReport);
		epaypaTReport.setEpaypaDTipoFileReport(null);

		return epaypaTReport;
	}

}
