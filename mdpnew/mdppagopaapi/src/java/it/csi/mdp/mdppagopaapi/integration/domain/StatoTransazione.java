/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stato_transazione database table.
 * 
 */
@Entity
@Table(name="stato_transazione")
@NamedQuery(name="StatoTransazione.findAll", query="SELECT s FROM StatoTransazione s")
public class StatoTransazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATO_TRANSAZIONE_CODSTATO_GENERATOR", sequenceName="STATO_TRANSAZIONE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATO_TRANSAZIONE_CODSTATO_GENERATOR")
	@Column(name="cod_stato")
	private long codStato;

	private String descrizione;

	private String descrizioneestesa;

	//bi-directional many-to-one association to Transazione
	@OneToMany(mappedBy="statoTransazione")
	private List<Transazione> transaziones;

	public StatoTransazione() {
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

	public List<Transazione> getTransaziones() {
		return this.transaziones;
	}

	public void setTransaziones(List<Transazione> transaziones) {
		this.transaziones = transaziones;
	}

	public Transazione addTransazione(Transazione transazione) {
		getTransaziones().add(transazione);
		transazione.setStatoTransazione(this);

		return transazione;
	}

	public Transazione removeTransazione(Transazione transazione) {
		getTransaziones().remove(transazione);
		transazione.setStatoTransazione(null);

		return transazione;
	}

}
