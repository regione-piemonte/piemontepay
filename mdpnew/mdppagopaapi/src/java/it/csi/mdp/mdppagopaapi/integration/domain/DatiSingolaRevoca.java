/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the dati_singola_revoca database table.
 * 
 */
@Entity
@Table(name="dati_singola_revoca")
@NamedQuery(name="DatiSingolaRevoca.findAll", query="SELECT d FROM DatiSingolaRevoca d")
public class DatiSingolaRevoca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="causale_revoca")
	private String causaleRevoca;

	@Column(name="dati_aggiuntivi_revoca")
	private String datiAggiuntiviRevoca;

	private String iur;

	@Column(name="singolo_importo_revocato")
	private BigDecimal singoloImportoRevocato;

	//bi-directional many-to-one association to Rr
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_rr")
	private Rr rr;

	public DatiSingolaRevoca() {
	}

	public String getCausaleRevoca() {
		return this.causaleRevoca;
	}

	public void setCausaleRevoca(String causaleRevoca) {
		this.causaleRevoca = causaleRevoca;
	}

	public String getDatiAggiuntiviRevoca() {
		return this.datiAggiuntiviRevoca;
	}

	public void setDatiAggiuntiviRevoca(String datiAggiuntiviRevoca) {
		this.datiAggiuntiviRevoca = datiAggiuntiviRevoca;
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

	public Rr getRr() {
		return this.rr;
	}

	public void setRr(Rr rr) {
		this.rr = rr;
	}

}
