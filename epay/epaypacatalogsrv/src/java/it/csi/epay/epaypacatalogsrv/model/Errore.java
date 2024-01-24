/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypacat_d_errore database table.
 *
 */
@Entity
@Table(name="epaycat_d_errore")
@NamedQuery ( name = "Errore.findAll", query = "SELECT e FROM Errore e" )
public class Errore extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

	@Id
	private Integer id;

	private String codice;

	@Column(name="codice_applicativo")
	private String codiceApplicativo;

	@Column(name="codice_lingua")
	private String codiceLingua;

	private String messaggio;

	public Errore() {
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

	public String getCodiceApplicativo() {
		return codiceApplicativo;
	}

	public void setCodiceApplicativo(String codiceApplicativo) {
		this.codiceApplicativo = codiceApplicativo;
	}

	public String getCodiceLingua() {
		return codiceLingua;
	}

	public void setCodiceLingua(String codiceLingua) {
		this.codiceLingua = codiceLingua;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	@Override
	public String toString() {
		return "Errore [id=" + id + ", codice=" + codice + ", codiceApplicativo=" + codiceApplicativo
				+ ", codiceLingua=" + codiceLingua + "]";
	}

}
