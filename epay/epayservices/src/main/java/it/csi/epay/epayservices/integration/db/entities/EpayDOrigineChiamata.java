/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the epay_d_origine_chiamata database table.
 * 
 */
@Entity
@Table(name="epay_d_origine_chiamata")
@NamedQuery(name="EpayDOrigineChiamata.findAll", query="SELECT e FROM EpayDOrigineChiamata e")
public class EpayDOrigineChiamata implements Serializable {

	private static final long serialVersionUID = 8715135229969884767L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to EpayTRegistroVersamenti
	@OneToMany(mappedBy="epayDOrigineChiamata")
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	public EpayDOrigineChiamata() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis() {
		return this.epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis(List<EpayTRegistroVersamenti> epayTRegistroVersamentis) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	public EpayTRegistroVersamenti addEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().add(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayDOrigineChiamata(this);

		return epayTRegistroVersamenti;
	}

	public EpayTRegistroVersamenti removeEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().remove(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayDOrigineChiamata(null);

		return epayTRegistroVersamenti;
	}

}
