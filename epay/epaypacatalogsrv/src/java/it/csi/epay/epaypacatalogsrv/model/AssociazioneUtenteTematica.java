/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_r_utente_tematica database table.
 *
 */
@Entity
@Table ( name = "epaycat_r_utente_tematica" )
@NamedQuery ( name = "AssociazioneUtenteTematica.findAll", query = "SELECT a FROM AssociazioneUtenteTematica a" )
public class AssociazioneUtenteTematica extends AbstractCSILogAuditedParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return String.valueOf ( id );
        } else {
            return String.valueOf (
                "{\"id_utente\"=" + id.getIdUtente () + ", \"id_ente\"=" + id.getIdEnte () + ", \"cod_tematica\"=\"" + id.getCodTematica () + "\"}" );
        }
    }

    @EmbeddedId
    private AssociazioneUtenteTematicaPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "cod_tematica", referencedColumnName = "codice", insertable = false, updatable = false )
    private TematicaPpay tematica;

    @Column ( name = "flag_visibilita_totale" )
    private Boolean flagVisibilitaTotale;

    public AssociazioneUtenteTematica () {
    }

    public Boolean getFlagVisibilitaTotale () {
        return flagVisibilitaTotale;
    }

    public void setFlagVisibilitaTotale ( Boolean flagVisibilitaTotale ) {
        this.flagVisibilitaTotale = flagVisibilitaTotale;
    }

    public AssociazioneUtenteTematicaPK getId () {
        return id;
    }

    public void setId ( AssociazioneUtenteTematicaPK id ) {
        this.id = id;
    }

    public TematicaPpay getTematica () {
        return tematica;
    }

    public void setTematica ( TematicaPpay tematica ) {
        this.tematica = tematica;
    }

	@Override
	public String toString() {
		return "AssociazioneUtenteTematica [id=" + id + ", flagVisibilitaTotale=" + flagVisibilitaTotale + "]";
	}

}
