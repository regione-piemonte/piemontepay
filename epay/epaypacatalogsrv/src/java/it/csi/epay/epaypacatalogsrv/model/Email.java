/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_t_email database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_email" )
@NamedQuery ( name = "Email.findAll", query = "SELECT e FROM Email e" )
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    private String body;

    private Timestamp data;

    private String error;

    @Column ( name = "id_ente" )
    private Integer idEnte;

    private String recipient;

    @Column ( name = "numero_tentativi" )
    private Integer numeroTentativi;

    private String subject;

    @Column ( name = "codice_stato" )
    private String codiceStato;

    public Email () {
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getBody () {
        return body;
    }

    public void setBody ( String body ) {
        this.body = body;
    }

    public Timestamp getData () {
        return data;
    }

    public void setData ( Timestamp data ) {
        this.data = data;
    }

    public String getError () {
        return error;
    }

    public void setError ( String error ) {
        this.error = error;
    }

    public String getRecipient () {
        return recipient;
    }

    public void setRecipient ( String recipient ) {
        this.recipient = recipient;
    }

    public Integer getNumeroTentativi () {
        return numeroTentativi;
    }

    public void setNumeroTentativi ( Integer numeroTentativi ) {
        this.numeroTentativi = numeroTentativi;
    }

    public String getSubject () {
        return subject;
    }

    public void setSubject ( String subject ) {
        this.subject = subject;
    }

    public Integer getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( Integer idEnte ) {
        this.idEnte = idEnte;
    }

    public String getCodiceStato () {
        return codiceStato;
    }

    public void setCodiceStato ( String codiceStato ) {
        this.codiceStato = codiceStato;
    }

	@Override
	public String toString() {
		return "Email [id=" + id + ", idEnte=" + idEnte + ", subject=" + subject + "]";
	}

}
