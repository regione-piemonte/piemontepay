/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaywso_d_stato_richiesta database table.
 * 
 */
@Entity
@Table(name="epaywso_d_stato_richiesta")
@NamedQuery(name="EPaywsoDStatoRichiesta.findAll", query="SELECT e FROM EPaywsoDStatoRichiesta e")
public class EPaywsoDStatoRichiesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_stato_richiesta")
	private Integer idStatoRichiesta;

	private String descrizione;

	public EPaywsoDStatoRichiesta() {
	}

	public Integer getIdStatoRichiesta() {
		return this.idStatoRichiesta;
	}

	public void setIdStatoRichiesta(Integer idStatoRichiesta) {
		this.idStatoRichiesta = idStatoRichiesta;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
