/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_r_utente_cdu database table.
 *
 */
@Entity
@Table ( name = "epaycat_r_utente_cdu" )
@NamedQuery ( name = "AssociazioneUtenteCdu.findAll", query = "SELECT a FROM AssociazioneUtenteCdu a" )
public class AssociazioneUtenteCdu extends AbstractCSILogAuditedParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return String.valueOf ( id );
        } else {
            return String.valueOf (
                "{\"id_utente\"=" + id.getIdUtente () + ", \"id_ente\"=" + id.getIdEnte () + ", \"cod_cdu\"=\"" + id.getCodCdu () + "\"}" );
        }
    }

    @EmbeddedId
    private AssociazioneUtenteCduPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "cod_cdu", referencedColumnName = "codice", insertable = false, updatable = false )
    private Cdu cdu;

    public AssociazioneUtenteCdu () {
    }

    public AssociazioneUtenteCduPK getId () {
        return id;
    }

    public void setId ( AssociazioneUtenteCduPK id ) {
        this.id = id;
    }

    public Cdu getCdu () {
        return cdu;
    }

    public void setCdu ( Cdu cdu ) {
        this.cdu = cdu;
    }

	@Override
	public String toString() {
		return "AssociazioneUtenteCdu [id=" + id + "]";
	}

}
