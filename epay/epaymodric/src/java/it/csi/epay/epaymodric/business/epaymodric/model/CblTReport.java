/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cbl_t_report database table.
 * 
 */
@Entity
@Table(name="cbl_t_report")
@NamedQuery(name="CblTReport.findAll", query="SELECT c FROM CblTReport c")
public class CblTReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CBL_T_REPORT_ID_GENERATOR", sequenceName="epaymodric.CBL_T_REPORT_ID_SEQ", schema="epaymodric", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBL_T_REPORT_ID_GENERATOR")
	private Long id;

	@Column(name="codice_fiscale_ente")
	private String codiceFiscaleEnte;

	@Column(name="id_utente")
	private Long idUtente;

	@Column(name="codice_fiscale_utente")
	private String codiceFiscaleUtente;

	@Column(name="nominativo_export")
	private String nominativoExport;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ora_prenotazione")
	private Date dataOraPrenotazione;


	@OneToMany ( fetch = FetchType.LAZY, mappedBy = "cblTReport", cascade = CascadeType.REMOVE, orphanRemoval=true)
	private List<CblTDatiFiltroReport> cblTDatiFiltroReports;

	//bi-directional many-to-one association to CblDStatoReport
	@ManyToOne
	@JoinColumn(name="id_stato")
	private CblDStatoReport cblDStatoReport;

	//bi-directional many-to-one association to CblDTipoFileReport
	@ManyToOne
	@JoinColumn(name="id_tipo_file")
	private CblDTipoFileReport cblDTipoFileReport;

	//bi-directional many-to-one association to CblDTipoReport
	@ManyToOne
	@JoinColumn(name="id_tipo_report")
	private CblDTipoReport cblDTipoReport;

	//bi-directional many-to-one association to CblTFileReport
	@ManyToOne
	@JoinColumn(name="id_file")
	private CblTFileReport cblTFileReport;

	//bi-directional many-to-one association to CblTEnte
	@ManyToOne
	@JoinColumn(name="id_ente")
	private CblTEnte cblTEnte;

	public CblTReport() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceFiscaleEnte() {
		return this.codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getCodiceFiscaleUtente() {
		return this.codiceFiscaleUtente;
	}

	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
	}

	public Long getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}


	public String getNominativoExport() {
		return nominativoExport;
	}

	public void setNominativoExport(String nominativoExport) {
		this.nominativoExport = nominativoExport;
	}




	public List<CblTDatiFiltroReport> getCblTDatiFiltroReports() {
		return this.cblTDatiFiltroReports;
	}

	public void setCblTDatiFiltroReports(List<CblTDatiFiltroReport> cblTDatiFiltroReports) {
		this.cblTDatiFiltroReports = cblTDatiFiltroReports;
	}

	public CblTDatiFiltroReport addCblTDatiFiltroReport(CblTDatiFiltroReport cblTDatiFiltroReport) {
		getCblTDatiFiltroReports().add(cblTDatiFiltroReport);
		cblTDatiFiltroReport.setCblTReport(this);

		return cblTDatiFiltroReport;
	}

	public CblTDatiFiltroReport removeCblTDatiFiltroReport(CblTDatiFiltroReport cblTDatiFiltroReport) {
		getCblTDatiFiltroReports().remove(cblTDatiFiltroReport);
		cblTDatiFiltroReport.setCblTReport(null);

		return cblTDatiFiltroReport;
	}

	public CblDStatoReport getCblDStatoReport() {
		return this.cblDStatoReport;
	}

	public void setCblDStatoReport(CblDStatoReport cblDStatoReport) {
		this.cblDStatoReport = cblDStatoReport;
	}

	public CblDTipoFileReport getCblDTipoFileReport() {
		return this.cblDTipoFileReport;
	}

	public void setCblDTipoFileReport(CblDTipoFileReport cblDTipoFileReport) {
		this.cblDTipoFileReport = cblDTipoFileReport;
	}

	public CblDTipoReport getCblDTipoReport() {
		return this.cblDTipoReport;
	}

	public void setCblDTipoReport(CblDTipoReport cblDTipoReport) {
		this.cblDTipoReport = cblDTipoReport;
	}

	public CblTFileReport getCblTFileReport() {
		return this.cblTFileReport;
	}

	public void setCblTFileReport(CblTFileReport cblTFileReport) {
		this.cblTFileReport = cblTFileReport;
	}

	public CblTEnte getCblTEnte() {
		return this.cblTEnte;
	}

	public void setCblTEnte(CblTEnte cblTEnte) {
		this.cblTEnte = cblTEnte;
	}

	public Date getDataOraPrenotazione() {
		return dataOraPrenotazione;
	}

	public void setDataOraPrenotazione(Date dataOraPrenotazione) {
		this.dataOraPrenotazione = dataOraPrenotazione;
	}


}
