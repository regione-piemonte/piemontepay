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
 * The persistent class for the epaycat_d_template_email database table.
 *
 */
@Entity
@Table ( name = "epaycat_d_template_email" )
@NamedQuery ( name = "TemplateEmail.findAll", query = "SELECT t FROM TemplateEmail t" )
public class TemplateEmail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String codice;

    private String descrizione;

    private String template;

    private String oggetto;

    public String getOggetto () {
        return oggetto;
    }

    public void setOggetto ( String oggetto ) {
        this.oggetto = oggetto;
    }

    public TemplateEmail () {
    }

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getTemplate () {
        return template;
    }

    public void setTemplate ( String template ) {
        this.template = template;
    }

	@Override
	public String toString() {
		return "TemplateEmail [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", oggetto="
				+ oggetto + "]";
	}

}
