/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypacat_t_operazione database table.
 *
 */
@Entity
@Table ( name = "epaycat_d_operazione" )
@NamedQuery ( name = "Operazione.findAll", query = "SELECT e FROM Operazione e" )
public class Operazione extends AbstractCSILogAuditedParentEntity implements Serializable {

    private static final long serialVersionUID = 1845786884248976367L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    private Long id;
    private String codice;

    @ManyToMany
    @JoinTable (
        name = "epaycat_r_operazione_sottoscrittore",
        joinColumns = { @JoinColumn ( name = "id_operazione", referencedColumnName = "id" ) },
        inverseJoinColumns = { @JoinColumn ( name = "id_sottoscrittore", referencedColumnName = "id" ) } )
    private List<Sottoscrittore> sottoscrittori;

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_servizio", referencedColumnName = "id" )
    private Servizio servizio;

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
     * @return the codice
     */
    public final String getCodice () {
        return codice;
    }

    /**
     * @param codice the codice to set
     */
    public final void setCodice ( String codice ) {
        this.codice = codice;
    }

    /**
     * @return the sottoscrittori
     */
    public final List<Sottoscrittore> getSottoscrittori () {
        return sottoscrittori;
    }

    /**
     * @param sottoscrittori the sottoscrittori to set
     */
    public final void setSottoscrittori ( List<Sottoscrittore> sottoscrittori ) {
        this.sottoscrittori = sottoscrittori;
    }

    /**
     * @return the servizio
     */
    public final Servizio getServizio () {
        return servizio;
    }

    /**
     * @param servizio the servizio to set
     */
    public final void setServizio ( Servizio servizio ) {
        this.servizio = servizio;
    }

    @Override
    public String toString() {
        return "Operazione [id=" + id + ", codice=" + codice + "]";
    }

}
