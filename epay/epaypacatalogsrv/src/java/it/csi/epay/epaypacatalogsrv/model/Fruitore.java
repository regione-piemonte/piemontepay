/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epaycat_d_fruitore database table.
 *
 */
@Entity
@Table(name="epaycat_d_fruitore")
@NamedQuery(name="Fruitore.findAll", query="SELECT f FROM Fruitore f")
public class Fruitore extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

	@Id
	private Integer id;

	private String codice;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	private String descrizione;

	public Fruitore() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Fruitore [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
