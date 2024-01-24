/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypacat_d_servizio database table.
 *
 */
@Entity
@Table ( name = "epaycat_d_servizio" )
@NamedQuery ( name = "Servizio.findAll", query = "SELECT e FROM Servizio e" )
public class Servizio extends AbstractCSILogAuditedParentEntity implements Serializable {

    private static final long serialVersionUID = 4490316177131905147L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    private Long id;
    private String descrizione;
    private String wsdl;

    /**
     * @return the id
     */
    public final Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the descrizione
     */
    public final String getDescrizione () {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public final void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    /**
     * @return the wsdl
     */
    public final String getWsdl () {
        return wsdl;
    }

    /**
     * @param wsdl the wsdl to set
     */
    public final void setWsdl ( String wsdl ) {
        this.wsdl = wsdl;
    }

	@Override
	public String toString() {
		return "Servizio [id=" + id + ", descrizione=" + descrizione + "]";
	}

}
