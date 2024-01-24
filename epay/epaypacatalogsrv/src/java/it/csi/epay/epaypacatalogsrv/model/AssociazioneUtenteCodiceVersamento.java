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
 * The persistent class for the epaycat_r_utente_codice_versamento database table.
 *
 */
@Entity
@Table(name="epaycat_r_utente_codice_versamento")
@NamedQuery(name="AssociazioneUtenteCodiceVersamento.findAll", query="SELECT a FROM AssociazioneUtenteCodiceVersamento a")
public class AssociazioneUtenteCodiceVersamento extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return String.valueOf ( id );
        } else {
            return String.valueOf (
                "{\"id_utente\"=" + id.getIdUtente () + ", \"id_ente\"=" + id.getIdEnte () + ", \"id_codice_versamento\"=" + id.getIdCodiceVersamento ()
                    + "}" );
        }
    }

	@EmbeddedId
	private AssociazioneUtenteCodiceVersamentoPK id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_codice_versamento", referencedColumnName = "id", insertable = false, updatable = false )
    private CodiceVersamento codiceVersamento;

	public AssociazioneUtenteCodiceVersamento() {
	}

	public AssociazioneUtenteCodiceVersamentoPK getId() {
		return id;
	}

	public void setId(AssociazioneUtenteCodiceVersamentoPK id) {
		this.id = id;
    }

    public CodiceVersamento getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( CodiceVersamento codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

	@Override
	public String toString() {
		return "AssociazioneUtenteCodiceVersamento [id=" + id + "]";
	}

}
