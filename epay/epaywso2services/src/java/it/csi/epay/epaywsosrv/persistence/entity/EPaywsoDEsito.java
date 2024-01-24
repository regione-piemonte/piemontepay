/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaywso_d_esito database table.
 * 
 */
@Entity
@Table(name="epaywso_d_esito")
@NamedQuery(name="EPaywsoDEsito.findAll", query="SELECT e FROM EPaywsoDEsito e")
public class EPaywsoDEsito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_esito")
	private String codEsito;

	private String descrizione;

	public EPaywsoDEsito() {
	}

	public String getCodEsito() {
		return this.codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
