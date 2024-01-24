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
 * The persistent class for the epaycat_d_mappatura_codice_versamento_epaypa database table.
 *
 */
@Entity
@Table ( name = "epaycat_d_mappatura_codice_versamento_epaypa" )
@NamedQuery ( name = "MappaturaCodiceVersamentoEpaypa.findAll", query = "SELECT m FROM MappaturaCodiceVersamentoEpaypa m" )
public class MappaturaCodiceVersamentoEpaypa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column ( name = "id_codice_versamento_old" )
    private Integer idCodiceVersamentoOld;

    private String codice;

    @Column ( name = "codice_nuovo" )
    private String codiceNuovo;

    @Column ( name = "codice_tipo_pagamento" )
    private String codiceTipoPagamento;

    @Column ( name = "codice_voce_entrata" )
    private String codiceVoceEntrata;

    private String descrizione;

    @Column ( name = "id_ente" )
    private Integer idEnte;
    
    public MappaturaCodiceVersamentoEpaypa () {
    }

    public Integer getIdCodiceVersamentoOld () {
        return idCodiceVersamentoOld;
    }

    public void setIdCodiceVersamentoOld ( Integer idCodiceVersamentoOld ) {
        this.idCodiceVersamentoOld = idCodiceVersamentoOld;
    }

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getCodiceNuovo () {
        return codiceNuovo;
    }

    public void setCodiceNuovo ( String codiceNuovo ) {
        this.codiceNuovo = codiceNuovo;
    }

    public String getCodiceTipoPagamento () {
        return codiceTipoPagamento;
    }

    public void setCodiceTipoPagamento ( String codiceTipoPagamento ) {
        this.codiceTipoPagamento = codiceTipoPagamento;
    }

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public Integer getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( Integer idEnte ) {
        this.idEnte = idEnte;
    }

	@Override
	public String toString() {
		return "MappaturaCodiceVersamentoEpaypa [id=" + id + ", idCodiceVersamentoOld=" + idCodiceVersamentoOld
				+ ", codiceNuovo=" + codiceNuovo + ", idEnte=" + idEnte + "]";
	}

}
