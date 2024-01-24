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
 * Entity usata solo per il servizio del job alert.
 *
 */
@Entity
@Table ( name = "epaycat_t_ente" )
@NamedQuery ( name = "EnteLight.findAll", query = "SELECT e FROM EnteLight e" )
public class EnteLight extends AbstractCSILogAuditedParentEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7389834269916870507L;

    @Id
    private Long id;

    @Column ( name = "codice_fiscale" )
    private String codiceFiscale;

    private String denominazione;

    private String email;

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the codiceFiscale
     */
    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    /**
     * @param codiceFiscale the codiceFiscale to set
     */
    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * @return the denominazione
     */
    public String getDenominazione () {
        return denominazione;
    }

    /**
     * @param denominazione the denominazione to set
     */
    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    /**
     * @return the email
     */
    public String getEmail () {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail ( String email ) {
        this.email = email;
    }

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

	@Override
	public String toString() {
		return "EnteLight [id=" + id + ", codiceFiscale=" + codiceFiscale + ", denominazione=" + denominazione
				+ ", email=" + email + "]";
	}

}
