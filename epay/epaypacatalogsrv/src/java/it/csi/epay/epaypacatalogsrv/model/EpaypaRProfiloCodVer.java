/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_r_profilo_cod_vers database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_r_profilo_cod_vers")
@NamedQuery(name="EpaypaRProfiloCodVer.findAll", query="SELECT e FROM EpaypaRProfiloCodVer e")
public class EpaypaRProfiloCodVer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EpaypaRProfiloCodVerPK id;

	public EpaypaRProfiloCodVer() {
	}

	public EpaypaRProfiloCodVerPK getId() {
		return this.id;
	}

	public void setId(EpaypaRProfiloCodVerPK id) {
		this.id = id;
	}

}
