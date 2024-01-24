/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the dati_singolo_esito database table.
 * 
 */
@Entity
@Table(name="dati_singolo_esito")
@NamedQuery(name="DatiSingoloEsito.findAll", query="SELECT d FROM DatiSingoloEsito d")
public class DatiSingoloEsito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="causale_esito")
	private String causaleEsito;

	@Column(name="dati_aggiuntivi_esito")
	private String datiAggiuntiviEsito;

	private String iur;

	@Column(name="singolo_importo_revocato")
	private BigDecimal singoloImportoRevocato;

	//bi-directional many-to-one association to Er
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_er")
	private Er er1;

	//bi-directional many-to-one association to Er
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_er")
	private Er er2;

	public DatiSingoloEsito() {
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

	public Er getEr1() {
		return this.er1;
	}

	public void setEr1(Er er1) {
		this.er1 = er1;
	}

	public Er getEr2() {
		return this.er2;
	}

	public void setEr2(Er er2) {
		this.er2 = er2;
	}

}
