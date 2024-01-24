/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_dati_singolo_esito database table.
 * 
 */
@Entity
@Table(name="epay_t_dati_singolo_esito")
@NamedQuery(name="EpayTDatiSingoloEsito.findAll", query="SELECT e FROM EpayTDatiSingoloEsito e")
public class EpayTDatiSingoloEsito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="causale_esito", nullable=false, length=140)
	private String causaleEsito;

	@Column(name="dati_aggiuntivi_esito", nullable=false, length=140)
	private String datiAggiuntiviEsito;

	@Id
	@SequenceGenerator(name="EPAY_T_DATI_SINGOLO_ESITO_GENERATOR", allocationSize=1, sequenceName="EPAY_T_DATI_SINGOLO_ESITO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_DATI_SINGOLO_ESITO_GENERATOR")
	@Column(name="id", unique=true, nullable=false)

	private Integer id;

	@Column(name="id_er", nullable=false)
	private Integer idEr;

	@Column(nullable=false, length=35)
	private String iur;

	@Column(name="singolo_importo_revocato", nullable=false, precision=13, scale=2)
	private BigDecimal singoloImportoRevocato;

	public EpayTDatiSingoloEsito() {
	}

	public String getCausaleEsito() {
		return this.causaleEsito;
	}

	public void setCausaleEsito(String causaleEsito) {
		this.causaleEsito = causaleEsito;
	}

	public String getDatiAggiuntiviEsito() {
		return this.datiAggiuntiviEsito;
	}

	public void setDatiAggiuntiviEsito(String datiAggiuntiviEsito) {
		this.datiAggiuntiviEsito = datiAggiuntiviEsito;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEr() {
		return this.idEr;
	}

	public void setIdEr(Integer idEr) {
		this.idEr = idEr;
	}

	public String getIur() {
		return this.iur;
	}

	public void setIur(String iur) {
		this.iur = iur;
	}

	public BigDecimal getSingoloImportoRevocato() {
		return this.singoloImportoRevocato;
	}

	public void setSingoloImportoRevocato(BigDecimal singoloImportoRevocato) {
		this.singoloImportoRevocato = singoloImportoRevocato;
	}

}
