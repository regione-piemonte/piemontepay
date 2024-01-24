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
 * The persistent class for the epaycat_r_codice_versamento_mb database table.
 *
 */
@Entity
@Table(name="epaycat_r_codice_versamento_mb")
@NamedQuery(name="AssociazioneCodiceVersamentoPrimarioSecondario.findAll", query="SELECT a FROM AssociazioneCodiceVersamentoPrimarioSecondario a")
public class AssociazioneCodiceVersamentoPrimarioSecondario extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return String.valueOf ( id );
        } else {
            return String.valueOf (
                "{\"id_codice_versamento_primario \"=" + id.getIdCodiceVersamentoPrimario () + ", \"id_codice_versamento_primario\"=" + id.getIdCodiceVersamentoSecondario () + "}" );
        }
    }

	@EmbeddedId
	private AssociazioneCodiceVersamentoPrimarioSecondarioPK id;

	public AssociazioneCodiceVersamentoPrimarioSecondario() {
	}

	public AssociazioneCodiceVersamentoPrimarioSecondarioPK getId() {
		return id;
	}

	public void setId(AssociazioneCodiceVersamentoPrimarioSecondarioPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AssociazioneCodiceVersamentoPrimarioSecondario [id=" + id + "]";
	}

	

}
