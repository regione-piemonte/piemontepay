/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_r_ente_codice_versamento database table.
 *
 */
@Entity
@Table(name="epaycat_r_ente_codice_versamento")
@NamedQuery(name="AssociazioneEnteCodiceVersamento.findAll", query="SELECT a FROM AssociazioneEnteCodiceVersamento a")
public class AssociazioneEnteCodiceVersamento extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return String.valueOf ( id );
        } else {
            return String.valueOf (
                "{\"id_ente \"=" + id.getIdEnte () + ", \"id_codice_versamento\"=" + id.getIdCodiceVersamento () + "}" );
        }
    }

	@EmbeddedId
	private AssociazioneEnteCodiceVersamentoPK id;

	public AssociazioneEnteCodiceVersamento() {
	}

	public AssociazioneEnteCodiceVersamentoPK getId() {
		return id;
	}

	public void setId(AssociazioneEnteCodiceVersamentoPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AssociazioneEnteCodiceVersamento [id=" + id + "]";
	}

}
