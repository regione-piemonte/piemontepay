/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the codici_esito_pagamento database table.
 * 
 */
@Entity
@Table(name="codici_esito_pagamento")
@NamedQuery(name="CodiciEsitoPagamento.findAll", query="SELECT c FROM CodiciEsitoPagamento c")
public class CodiciEsitoPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CODICI_ESITO_PAGAMENTO_IDESITOPAGAMENTO_GENERATOR", sequenceName="CODICI_ESITO_PAGAMENTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CODICI_ESITO_PAGAMENTO_IDESITOPAGAMENTO_GENERATOR")
	@Column(name="id_esito_pagamento")
	private Integer idEsitoPagamento;

	private String descrizione;

	public CodiciEsitoPagamento() {
	}

	public Integer getIdEsitoPagamento() {
		return this.idEsitoPagamento;
	}

	public void setIdEsitoPagamento(Integer idEsitoPagamento) {
		this.idEsitoPagamento = idEsitoPagamento;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
