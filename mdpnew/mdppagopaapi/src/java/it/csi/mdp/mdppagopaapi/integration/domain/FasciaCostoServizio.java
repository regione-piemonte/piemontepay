/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the fascia_costo_servizio database table.
 * 
 */
@Entity
@Table(name="fascia_costo_servizio")
@NamedQuery(name="FasciaCostoServizio.findAll", query="SELECT f FROM FasciaCostoServizio f")
public class FasciaCostoServizio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="costo_fisso")
	private BigDecimal costoFisso;

	@Column(name="data_validita")
	private Timestamp dataValidita;

	@Column(name="importo_massimo_fasci")
	private BigDecimal importoMassimoFasci;

	private Boolean valido;

	@Column(name="valore_commissione")
	private BigDecimal valoreCommissione;

	//bi-directional many-to-one association to InformativaPsp
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_informativa_psp")
	private InformativaPsp informativaPsp;

	public FasciaCostoServizio() {
	}

	public BigDecimal getCostoFisso() {
		return this.costoFisso;
	}

	public void setCostoFisso(BigDecimal costoFisso) {
		this.costoFisso = costoFisso;
	}

	public Timestamp getDataValidita() {
		return this.dataValidita;
	}

	public void setDataValidita(Timestamp dataValidita) {
		this.dataValidita = dataValidita;
	}

	public BigDecimal getImportoMassimoFasci() {
		return this.importoMassimoFasci;
	}

	public void setImportoMassimoFasci(BigDecimal importoMassimoFasci) {
		this.importoMassimoFasci = importoMassimoFasci;
	}

	public Boolean getValido() {
		return this.valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public BigDecimal getValoreCommissione() {
		return this.valoreCommissione;
	}

	public void setValoreCommissione(BigDecimal valoreCommissione) {
		this.valoreCommissione = valoreCommissione;
	}

	public InformativaPsp getInformativaPsp() {
		return this.informativaPsp;
	}

	public void setInformativaPsp(InformativaPsp informativaPsp) {
		this.informativaPsp = informativaPsp;
	}

}
