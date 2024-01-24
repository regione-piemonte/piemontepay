/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_d_tipo_invio_mail database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_invio_mail")
@NamedQuery(name="EpaypaDTipoInvioMail.findAll", query="SELECT e FROM EpaypaDTipoInvioMail e")
public class EpaypaDTipoInvioMail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_invio_mail")
	private Integer idTipoInvioMail;

	private String descrizione;

	public EpaypaDTipoInvioMail() {
	}

	public Integer getIdTipoInvioMail() {
		return this.idTipoInvioMail;
	}

	public void setIdTipoInvioMail(Integer idTipoInvioMail) {
		this.idTipoInvioMail = idTipoInvioMail;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
