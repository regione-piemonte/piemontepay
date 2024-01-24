/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_r_ruolo_cdu database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_r_ruolo_cdu")
@NamedQuery(name="EpaypaRRuoloCdu.findAll", query="SELECT e FROM EpaypaRRuoloCdu e")
public class EpaypaRRuoloCdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EpaypaRRuoloCduPK id;

	public EpaypaRRuoloCdu() {
	}

	public EpaypaRRuoloCduPK getId() {
		return this.id;
	}

	public void setId(EpaypaRRuoloCduPK id) {
		this.id = id;
	}

}
