/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the epaypa_t_file_report database table.
 * 
 */
@Entity
@Table(name="epaypa_t_file_report")
@NamedQuery(name="EpaypaTFileReport.findAll", query="SELECT e FROM EpaypaTFileReport e")
public class EpaypaTFileReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAYPA_T_FILE_REPORT_ID_GENERATOR", sequenceName="EPAYPA_T_FILE_REPORT_ID_SEQ", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYPA_T_FILE_REPORT_ID_GENERATOR")
	private Long id;

	@Column(name="contenuto_file")
	private byte[] contenutoFile;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inserimento")
	private Date dataInserimento;

	@Temporal(TemporalType.DATE)
	@Column(name="data_modifica")
	private Date dataModifica;

	@Column(name="nome_file")
	private String nomeFile;

	//bi-directional many-to-one association to EpaypaTReport
	@OneToMany(mappedBy="epaypaTFileReport")
	private List<EpaypaTReport> epaypaTReports;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getContenutoFile() {
		return this.contenutoFile;
	}

	public void setContenutoFile(byte[] contenutoFile) {
		this.contenutoFile = contenutoFile;
	}

	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getNomeFile() {
		return this.nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public List<EpaypaTReport> getEpaypaTReports() {
		return this.epaypaTReports;
	}

	public void setEpaypaTReports(List<EpaypaTReport> epaypaTReports) {
		this.epaypaTReports = epaypaTReports;
	}

	public EpaypaTReport addEpaypaTReport(EpaypaTReport epaypaTReport) {
		getEpaypaTReports().add(epaypaTReport);
		epaypaTReport.setEpaypaTFileReport(this);

		return epaypaTReport;
	}

	public EpaypaTReport removeEpaypaTReport(EpaypaTReport epaypaTReport) {
		getEpaypaTReports().remove(epaypaTReport);
		epaypaTReport.setEpaypaTFileReport(null);

		return epaypaTReport;
	}

}
