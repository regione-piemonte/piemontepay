/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cbl_t_file_report database table.
 * 
 */
@Entity
@Table(name="cbl_t_file_report")
@NamedQuery(name="CblTFileReport.findAll", query="SELECT c FROM CblTFileReport c")
public class CblTFileReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CBL_T_FILE_REPORT_ID_GENERATOR", sequenceName="epaymodric.CBL_T_FILE_REPORT_ID_SEQ", schema="epaymodric", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBL_T_FILE_REPORT_ID_GENERATOR")
	private Long id;

	@Column(name="contenuto_file")
	private byte[] contenutoFile;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ora_inserimento")
	private Date dataInserimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ora_modifica")
	private Date dataModifica;

	@Column(name="nome_file")
	private String nomeFile;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ora_inizio_elaborazione")
	private Date dataInizioElaborazione;
	
	//bi-directional many-to-one association to CblTReport
	@OneToMany(mappedBy="cblTFileReport")
	private List<CblTReport> cblTReports;

	public CblTFileReport() {
	}

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

	public List<CblTReport> getCblTReports() {
		return this.cblTReports;
	}

	public void setCblTReports(List<CblTReport> cblTReports) {
		this.cblTReports = cblTReports;
	}

	public CblTReport addCblTReport(CblTReport cblTReport) {
		getCblTReports().add(cblTReport);
		cblTReport.setCblTFileReport(this);

		return cblTReport;
	}

	public CblTReport removeCblTReport(CblTReport cblTReport) {
		getCblTReports().remove(cblTReport);
		cblTReport.setCblTFileReport(null);

		return cblTReport;
	}

	public Date getDataInizioElaborazione() {
		return dataInizioElaborazione;
	}

	public void setDataInizioElaborazione(Date dataInizioElaborazione) {
		this.dataInizioElaborazione = dataInizioElaborazione;
	}
	
	

}
