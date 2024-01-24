/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the epaypa_t_avviso_scaduto database table.
 * 
 */
@Entity
@Table(name="epaypa_t_avviso_scaduto")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTAvvisoScaduto.findAllByIdFlusso",
			query = "SELECT e FROM EpaypaTAvvisoScaduto e WHERE epaypaTFlusso.idFlusso = :idFlusso"),
	@NamedQuery(
			name = "EpaypaTAvvisoScaduto.findAll",
			query = "SELECT e FROM EpaypaTAvvisoScaduto e")
})
public class EpaypaTAvvisoScaduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_avviso_scaduto")
	private Long idAvvisoScaduto;

	@Column(name="dt_scadenza")
	private Timestamp dtScadenza;

	private BigDecimal importo;

	private String iuv;

	//bi-directional many-to-one association to EpaypaTFlusso
	@ManyToOne
	@JoinColumn(name="id_flusso", updatable=false, insertable=false, nullable=false)
	private EpaypaTFlusso epaypaTFlusso;

	public EpaypaTAvvisoScaduto() {
	}

	public Long getIdAvvisoScaduto() {
		return this.idAvvisoScaduto;
	}

	public void setIdAvvisoScaduto(Long idAvvisoScaduto) {
		this.idAvvisoScaduto = idAvvisoScaduto;
	}

	public Timestamp getDtScadenza() {
		return this.dtScadenza;
	}

	public void setDtScadenza(Timestamp dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public BigDecimal getImporto() {
		return this.importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public EpaypaTFlusso getEpaypaTFlusso() {
		return this.epaypaTFlusso;
	}

	public void setEpaypaTFlusso(EpaypaTFlusso epaypaTFlusso) {
		this.epaypaTFlusso = epaypaTFlusso;
	}

}
