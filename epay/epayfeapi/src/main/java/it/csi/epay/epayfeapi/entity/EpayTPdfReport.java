/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table ( name = "epay_t_pdf_report" )
@SuppressWarnings ( "unused" )
public class EpayTPdfReport implements Serializable {

	private static final long serialVersionUID = 3816282757053355019L;

	@Id
	@Column ( name = "id" )
	private Long id;

	@Column ( name = "codice" )
	private String codice;

	@Column ( name = "nome_template" )
	private String nomeTemplate;

	@Column ( name = "template" )
	private byte[] template;

	@Column ( name = "template_compilato" )
	private byte[] templateCompilato;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getCodice () {
		return codice;
	}

	public void setCodice ( String codice ) {
		this.codice = codice;
	}

	public String getNomeTemplate () {
		return nomeTemplate;
	}

	public void setNomeTemplate ( String nomeTemplate ) {
		this.nomeTemplate = nomeTemplate;
	}

	public byte[] getTemplate () {
		return template;
	}

	public void setTemplate ( byte[] template ) {
		this.template = template;
	}

	public byte[] getTemplateCompilato () {
		return templateCompilato;
	}

	public void setTemplateCompilato ( byte[] templateCompilato ) {
		this.templateCompilato = templateCompilato;
	}

	@Override
	public String toString () {
		return "{ " +
						"id:" + id +
						", codice:" + codice +
						", nomeTemplate:" + nomeTemplate +
						// non esporre template
						// non esporre templateCompilato
						" }";
	}
}
