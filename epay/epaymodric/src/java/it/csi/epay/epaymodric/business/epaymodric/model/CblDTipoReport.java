/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cbl_d_tipo_report database table.
 * 
 */
@Entity
@Table(name="cbl_d_tipo_report")
@NamedQuery(name="CblDTipoReport.findAll", query="SELECT c FROM CblDTipoReport c")
public class CblDTipoReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to CblTReport
	@OneToMany(mappedBy="cblDTipoReport")
	private List<CblTReport> cblTReports;

	public CblDTipoReport() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public List<CblTReport> getCblTReports() {
		return this.cblTReports;
	}

	public void setCblTReports(List<CblTReport> cblTReports) {
		this.cblTReports = cblTReports;
	}

	public CblTReport addCblTReport(CblTReport cblTReport) {
		getCblTReports().add(cblTReport);
		cblTReport.setCblDTipoReport(this);

		return cblTReport;
	}

	public CblTReport removeCblTReport(CblTReport cblTReport) {
		getCblTReports().remove(cblTReport);
		cblTReport.setCblDTipoReport(null);

		return cblTReport;
	}

}
