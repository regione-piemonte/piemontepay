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
 * The persistent class for the epaypacat_d_sottoscrittore database table.
 *
 */
@Entity
@Table ( name = "epaycat_d_sottoscrittore" )
@NamedQuery ( name = "Sottoscrittore.findAll", query = "SELECT e FROM Sottoscrittore e" )
public class Sottoscrittore extends AbstractCSILogAuditedParentEntity implements Serializable {

    private static final long serialVersionUID = 5945557422206254617L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    private Long id;

    private String codice;

    private String descrizione;

    private String indirizzo;

    private Integer priorita;

    public Sottoscrittore () {
    }

    public Sottoscrittore ( Long id ) {
        super ();
        this.id = id;
    }

    public Integer getPriorita () {
        return priorita;
    }

    public void setPriorita ( Integer priorita ) {
        this.priorita = priorita;
    }

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
     * @return the indirizzo
     */
    public final String getIndirizzo () {
        return indirizzo;
    }

    /**
     * @param indirizzo the indirizzo to set
     */
    public final void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

	@Override
	public String toString() {
		return "Sottoscrittore [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", indirizzo="
				+ indirizzo + ", priorita=" + priorita + "]";
	}

}
