/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaywso_d_tipo_richiesta database table.
 * 
 */
@Entity
@Table(name="epaywso_d_tipo_richiesta")
@NamedQuery(name="EPaywsoDTipoRichiesta.findAll", query="SELECT e FROM EPaywsoDTipoRichiesta e")
public class EPaywsoDTipoRichiesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_richiesta")
	private Integer idTipoRichiesta;

	private String descrizione;
	
	@Column(name="multi_endpoint")
	private Boolean multiEndPoint;

	public Boolean getMultiEndPoint() {
		return multiEndPoint;
	}

	public void setMultiEndPoint(Boolean multiEndPoint) {
		this.multiEndPoint = multiEndPoint;
	}

	public EPaywsoDTipoRichiesta() {
	}

	public Integer getIdTipoRichiesta() {
		return this.idTipoRichiesta;
	}

	public void setIdTipoRichiesta(Integer idTipoRichiesta) {
		this.idTipoRichiesta = idTipoRichiesta;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
