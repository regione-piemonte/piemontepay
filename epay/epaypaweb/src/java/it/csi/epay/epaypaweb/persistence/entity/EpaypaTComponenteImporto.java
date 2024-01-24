/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_componente_importo database table.
 * 
 */
@Entity
@Table(name="epaypa_t_componente_importo")
@NamedQuery(name="EpaypaTComponenteImporto.findAll", query="SELECT e FROM EpaypaTComponenteImporto e")
public class EpaypaTComponenteImporto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_componente")
	private Long idComponente;

	private String causale;

	@Column(name="dati_spec_riscossione")
	private String datiSpecRiscossione;

	private BigDecimal importo;

	@Column ( name = "anno_accertamento" )
	private Integer annoAccertamento;

	@Column ( name = "numero_accertamento" )
	private Integer numeroAccertamento;

	@Column ( name = "flag_componente_secondario" )
	private Boolean flagComponenteSecondario;

	public Boolean getFlagComponenteSecondario () {
		return flagComponenteSecondario;
	}

	public void setFlagComponenteSecondario ( Boolean flagComponenteSecondario ) {
		this.flagComponenteSecondario = flagComponenteSecondario;
	}

	public EpaypaTComponenteImporto() {
		//empty constructor
	}

	public Long getIdComponente() {
		return this.idComponente;
	}

	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}

	public String getCausale() {
		return this.causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getDatiSpecRiscossione() {
		return this.datiSpecRiscossione;
	}

	public void setDatiSpecRiscossione(String datiSpecRiscossione) {
		this.datiSpecRiscossione = datiSpecRiscossione;
	}

	public BigDecimal getImporto() {
		return this.importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public Integer getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( Integer numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

}
