/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cbl_d_tipo_campo_filtro database table.
 * 
 */
@Entity
@Table(name="cbl_d_tipo_campo_filtro")
@NamedQuery(name="CblDTipoCampoFiltro.findAll", query="SELECT c FROM CblDTipoCampoFiltro c")
public class CblDTipoCampoFiltro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to CblTDatiFiltroReport
	@OneToMany(mappedBy="cblDTipoCampoFiltro")
	private List<CblTDatiFiltroReport> cblTDatiFiltroReports;

	public CblDTipoCampoFiltro() {
	}

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

	public List<CblTDatiFiltroReport> getCblTDatiFiltroReports() {
		return this.cblTDatiFiltroReports;
	}

	public void setCblTDatiFiltroReports(List<CblTDatiFiltroReport> cblTDatiFiltroReports) {
		this.cblTDatiFiltroReports = cblTDatiFiltroReports;
	}

	public CblTDatiFiltroReport addCblTDatiFiltroReport(CblTDatiFiltroReport cblTDatiFiltroReport) {
		getCblTDatiFiltroReports().add(cblTDatiFiltroReport);
		cblTDatiFiltroReport.setCblDTipoCampoFiltro(this);

		return cblTDatiFiltroReport;
	}

	public CblTDatiFiltroReport removeCblTDatiFiltroReport(CblTDatiFiltroReport cblTDatiFiltroReport) {
		getCblTDatiFiltroReports().remove(cblTDatiFiltroReport);
		cblTDatiFiltroReport.setCblDTipoCampoFiltro(null);

		return cblTDatiFiltroReport;
	}

}
