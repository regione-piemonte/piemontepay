/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the epay_t_elaborazione database table.
 * 
 */
@Entity
@Table(name="epay_t_elaborazione")
@NamedQuery(name="EpayTElaborazione.findAll", query="SELECT e FROM EpayTElaborazione e")
public class EpayTElaborazione implements Serializable {

	private static final long serialVersionUID = 8362947500353745081L;

	@Id
	@SequenceGenerator(name="EPAY_T_ELABORAZIONE_IDELABORAZIONE_GENERATOR", allocationSize=1, sequenceName="EPAY_T_ELABORAZIONE_ID_ELABORAZIONE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_ELABORAZIONE_IDELABORAZIONE_GENERATOR")
	@Column(name="id_elaborazione", unique=true, nullable=false)
	private Long idElaborazione;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_avvio")
	private Date dataAvvio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_termine")
	private Date dataTermine;

	@Column(name="documenti_archiviati")
	private Integer documentiArchiviati;

	@Column(name="documenti_elaborati")
	private Integer documentiElaborati;

	@Column(name="documenti_non_archiviati")
	private Integer documentiNonArchiviati;

	//bi-directional many-to-many association to EpayTQuietanzaDaElaborare
	@ManyToMany(mappedBy="epayTElaboraziones")
	private List<EpayTQuietanzaDaElaborare> epayTQuietanzaDaElaborares;

	public EpayTElaborazione() {
	}

	public Long getIdElaborazione() {
		return this.idElaborazione;
	}

	public void setIdElaborazione(Long idElaborazione) {
		this.idElaborazione = idElaborazione;
	}

	public Date getDataAvvio() {
		return this.dataAvvio;
	}

	public void setDataAvvio(Date dataAvvio) {
		this.dataAvvio = dataAvvio;
	}

	public Date getDataTermine() {
		return this.dataTermine;
	}

	public void setDataTermine(Date dataTermine) {
		this.dataTermine = dataTermine;
	}

	public Integer getDocumentiArchiviati() {
		return this.documentiArchiviati;
	}

	public void setDocumentiArchiviati(Integer documentiArchiviati) {
		this.documentiArchiviati = documentiArchiviati;
	}

	public Integer getDocumentiElaborati() {
		return this.documentiElaborati;
	}

	public void setDocumentiElaborati(Integer documentiElaborati) {
		this.documentiElaborati = documentiElaborati;
	}

	public Integer getDocumentiNonArchiviati() {
		return this.documentiNonArchiviati;
	}

	public void setDocumentiNonArchiviati(Integer documentiNonArchiviati) {
		this.documentiNonArchiviati = documentiNonArchiviati;
	}

	public List<EpayTQuietanzaDaElaborare> getEpayTQuietanzaDaElaborares() {
		return this.epayTQuietanzaDaElaborares;
	}

	public void setEpayTQuietanzaDaElaborares(List<EpayTQuietanzaDaElaborare> epayTQuietanzaDaElaborares) {
		this.epayTQuietanzaDaElaborares = epayTQuietanzaDaElaborares;
	}

}
