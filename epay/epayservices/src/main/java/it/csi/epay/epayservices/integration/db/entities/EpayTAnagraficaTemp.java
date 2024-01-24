/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_anagrafica_temp database table.
 * 
 */
@Entity
@Table(name="epay_t_anagrafica_temp")
@NamedQuery(name="EpayTAnagraficaTemp.findAll", query="SELECT e FROM EpayTAnagraficaTemp e")
public class EpayTAnagraficaTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_anagrafica")
	private Long idAnagrafica;

	@Column(name="codice_fiscale")
	private String codiceFiscale;

	public EpayTAnagraficaTemp() {
	}

	public Long getIdAnagrafica() {
		return this.idAnagrafica;
	}

	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

}
