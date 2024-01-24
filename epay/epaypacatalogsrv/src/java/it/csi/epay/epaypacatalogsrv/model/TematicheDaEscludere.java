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
 * The persistent class for the epaycat_d_tematica_ppay database table.
 *
 */
@Entity
@Table ( name = "epaycat_d_tematiche_da_escludere" )
@NamedQuery ( name = "TematicheDaEscludere.findAll", query = "SELECT t FROM TematicheDaEscludere t" )
public class TematicheDaEscludere extends AbstractCSILogAuditedParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    private Integer id;

    @Column ( name = "id_tematica" )
    private Integer idTematica;

    public TematicheDaEscludere () {
    }

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public Integer getIdTematica () {
        return idTematica;
    }

    public void setIdTematica ( Integer idTematica ) {
        this.idTematica = idTematica;
    }

	@Override
	public String toString() {
		return "TematicheDaEscludere [id=" + id + ", idTematica=" + idTematica + "]";
	}

}
