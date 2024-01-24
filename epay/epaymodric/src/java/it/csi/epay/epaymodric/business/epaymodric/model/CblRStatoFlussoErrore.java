/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_r_stato_flusso_errore database table.
 * 
 */
@Entity
@Table(name="cbl_r_stato_flusso_errore",schema="epaymodric")
@NamedQuery(name="CblRStatoFlussoErrore.findAll", query="SELECT c FROM CblRStatoFlussoErrore c")
public class CblRStatoFlussoErrore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_R_STATO_FLUSSO_ERRORE_ID_GENERATOR", sequenceName="epaymodric.CBL_R_STATO_FLUSSO_ERRORE_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_R_STATO_FLUSSO_ERRORE_ID_GENERATOR")
	private Long id;

	@Column(name="data_aggiornamento_stato")
	private Timestamp dataAggiornamentoStato;

	@Column(name="descrizione_aggiuntiva_errore")
	private String descrizioneAggiuntivaErrore;

	//bi-directional many-to-one association to CblDErrore
	@ManyToOne
	@JoinColumn(name="id_errore", referencedColumnName="id")
	private CblDErrore cblDErrore;

	//bi-directional many-to-one association to CblTFlussoOrigine
	@ManyToOne
	@JoinColumn(name="id_flusso_origine")
	private CblTFlussoOrigine cblTFlussoOrigine;

	//bi-directional many-to-one association to CblTFlussoSintesi
	@OneToMany(mappedBy="cblRStatoFlussoErrore")
	private List<CblTFlussoSintesi> cblTFlussoSintesis;

	public CblRStatoFlussoErrore() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDataAggiornamentoStato() {
		return this.dataAggiornamentoStato;
	}

	public void setDataAggiornamentoStato(Timestamp dataAggiornamentoStato) {
		this.dataAggiornamentoStato = dataAggiornamentoStato;
	}

	public String getDescrizioneAggiuntivaErrore() {
		return this.descrizioneAggiuntivaErrore;
	}

	public void setDescrizioneAggiuntivaErrore(String descrizioneAggiuntivaErrore) {
		this.descrizioneAggiuntivaErrore = descrizioneAggiuntivaErrore;
	}

	public CblDErrore getCblDErrore() {
		return this.cblDErrore;
	}

	public void setCblDErrore(CblDErrore cblDErrore) {
		this.cblDErrore = cblDErrore;
	}

	public CblTFlussoOrigine getCblTFlussoOrigine() {
		return this.cblTFlussoOrigine;
	}

	public void setCblTFlussoOrigine(CblTFlussoOrigine cblTFlussoOrigine) {
		this.cblTFlussoOrigine = cblTFlussoOrigine;
	}

	public List<CblTFlussoSintesi> getCblTFlussoSintesis() {
		return this.cblTFlussoSintesis;
	}

	public void setCblTFlussoSintesis(List<CblTFlussoSintesi> cblTFlussoSintesis) {
		this.cblTFlussoSintesis = cblTFlussoSintesis;
	}

	public CblTFlussoSintesi addCblTFlussoSintesi(CblTFlussoSintesi cblTFlussoSintesi) {
		getCblTFlussoSintesis().add(cblTFlussoSintesi);
		cblTFlussoSintesi.setCblRStatoFlussoErrore(this);

		return cblTFlussoSintesi;
	}

	public CblTFlussoSintesi removeCblTFlussoSintesi(CblTFlussoSintesi cblTFlussoSintesi) {
		getCblTFlussoSintesis().remove(cblTFlussoSintesi);
		cblTFlussoSintesi.setCblRStatoFlussoErrore(null);

		return cblTFlussoSintesi;
	}

}
