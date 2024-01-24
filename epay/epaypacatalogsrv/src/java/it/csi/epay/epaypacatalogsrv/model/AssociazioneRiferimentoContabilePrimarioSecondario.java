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
 * The persistent class for the epaycat_r_riferimento_contabile_mb database table.
 *
 */
@Entity
@Table(name="epaycat_r_riferimento_contabile_mb")
@NamedQuery(name="AssociazioneRiferimentoContabilePrimarioSecondario.findAll", query="SELECT a FROM AssociazioneCodiceVersamentoPrimarioSecondario a")
public class AssociazioneRiferimentoContabilePrimarioSecondario extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return String.valueOf ( id );
        } else {
            return String.valueOf (
                "{\"id_riferimento_contabile_primario \"=" + id.getIdRiferimentoContabilePrimario () + ", \"id_riferimento_contabile_secondario\"=" + id.getIdRiferimentoContabileSecondario () + "}" );
        }
    }

	@EmbeddedId
	private AssociazioneRiferimentoContabilePrimarioSecondarioPK id;

	public AssociazioneRiferimentoContabilePrimarioSecondario() {
	}

	public AssociazioneRiferimentoContabilePrimarioSecondarioPK getId() {
		return id;
	}

	public void setId(AssociazioneRiferimentoContabilePrimarioSecondarioPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AssociazioneRiferimentoContabilePrimarioSecondario [id=" + id + "]";
	}

	

}
