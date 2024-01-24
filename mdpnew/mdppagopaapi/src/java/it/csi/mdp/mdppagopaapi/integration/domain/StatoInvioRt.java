/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stato_invio_rt database table.
 * 
 */
@Entity
@Table(name="stato_invio_rt")
@NamedQuery(name="StatoInvioRt.findAll", query="SELECT s FROM StatoInvioRt s")
public class StatoInvioRt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATO_INVIO_RT_CODSTATO_GENERATOR", sequenceName="STATO_INVIO_RT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATO_INVIO_RT_CODSTATO_GENERATOR")
	@Column(name="cod_stato")
	private long codStato;

	private String descrizione;

	private String descrizioneestesa;

	public StatoInvioRt() {
	}

	public long getCodStato() {
		return this.codStato;
	}

	public void setCodStato(long codStato) {
		this.codStato = codStato;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneestesa() {
		return this.descrizioneestesa;
	}

	public void setDescrizioneestesa(String descrizioneestesa) {
		this.descrizioneestesa = descrizioneestesa;
	}

}
