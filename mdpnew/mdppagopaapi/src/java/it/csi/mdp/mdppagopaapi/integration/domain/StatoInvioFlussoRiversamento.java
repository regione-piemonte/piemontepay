/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stato_invio_flusso_riversamento database table.
 * 
 */
@Entity
@Table(name="stato_invio_flusso_riversamento")
@NamedQuery(name="StatoInvioFlussoRiversamento.findAll", query="SELECT s FROM StatoInvioFlussoRiversamento s")
public class StatoInvioFlussoRiversamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATO_INVIO_FLUSSO_RIVERSAMENTO_CODSTATO_GENERATOR", sequenceName="STATO_INVIO_FLUSSO_RIVERSAMENTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATO_INVIO_FLUSSO_RIVERSAMENTO_CODSTATO_GENERATOR")
	@Column(name="cod_stato")
	private long codStato;

	private String descrizione;

	private String descrizioneestesa;

	//bi-directional many-to-one association to FlussoRiversamento
	@OneToMany(mappedBy="statoInvioFlussoRiversamento1")
	private List<FlussoRiversamento> flussoRiversamentos1;

	//bi-directional many-to-one association to FlussoRiversamento
	@OneToMany(mappedBy="statoInvioFlussoRiversamento2")
	private List<FlussoRiversamento> flussoRiversamentos2;

	public StatoInvioFlussoRiversamento() {
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

	public List<FlussoRiversamento> getFlussoRiversamentos1() {
		return this.flussoRiversamentos1;
	}

	public void setFlussoRiversamentos1(List<FlussoRiversamento> flussoRiversamentos1) {
		this.flussoRiversamentos1 = flussoRiversamentos1;
	}

	public FlussoRiversamento addFlussoRiversamentos1(FlussoRiversamento flussoRiversamentos1) {
		getFlussoRiversamentos1().add(flussoRiversamentos1);
		flussoRiversamentos1.setStatoInvioFlussoRiversamento1(this);

		return flussoRiversamentos1;
	}

	public FlussoRiversamento removeFlussoRiversamentos1(FlussoRiversamento flussoRiversamentos1) {
		getFlussoRiversamentos1().remove(flussoRiversamentos1);
		flussoRiversamentos1.setStatoInvioFlussoRiversamento1(null);

		return flussoRiversamentos1;
	}

	public List<FlussoRiversamento> getFlussoRiversamentos2() {
		return this.flussoRiversamentos2;
	}

	public void setFlussoRiversamentos2(List<FlussoRiversamento> flussoRiversamentos2) {
		this.flussoRiversamentos2 = flussoRiversamentos2;
	}

	public FlussoRiversamento addFlussoRiversamentos2(FlussoRiversamento flussoRiversamentos2) {
		getFlussoRiversamentos2().add(flussoRiversamentos2);
		flussoRiversamentos2.setStatoInvioFlussoRiversamento2(this);

		return flussoRiversamentos2;
	}

	public FlussoRiversamento removeFlussoRiversamentos2(FlussoRiversamento flussoRiversamentos2) {
		getFlussoRiversamentos2().remove(flussoRiversamentos2);
		flussoRiversamentos2.setStatoInvioFlussoRiversamento2(null);

		return flussoRiversamentos2;
	}

}
