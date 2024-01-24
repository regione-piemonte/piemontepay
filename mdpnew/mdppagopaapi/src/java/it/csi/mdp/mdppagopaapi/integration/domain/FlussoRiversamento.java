/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the flusso_riversamento database table.
 * 
 */
@Entity
@Table(name="flusso_riversamento")
@NamedQuery(name="FlussoRiversamento.findAll", query="SELECT f FROM FlussoRiversamento f")
public class FlussoRiversamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FLUSSO_RIVERSAMENTO_ID_GENERATOR", sequenceName="FLUSSO_RIVERSAMENTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FLUSSO_RIVERSAMENTO_ID_GENERATOR")
	private Integer id;

	private String codicebicbancadiriversamento;

	private Timestamp datainserimento;

	private Timestamp datamodifica;

	private Timestamp dataoraflusso;

	private Timestamp dataregolamento;

	private String denominazionemittente;

	private String denominazionericevente;

	private String identificativoflusso;

	private String identificativoistitutomittente;

	private String identificativoistitutoricevente;

	private String identificativopsp;

	private String identificativounivocoregolamento;

	private BigDecimal importototalepagamenti;

	private Integer numerototalepagamenti;

	private String versioneoggetto;

	private String xmlflusso;

	//bi-directional many-to-one association to StatoInvioFlussoRiversamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stato_invio_flusso_base")
	private StatoInvioFlussoRiversamento statoInvioFlussoRiversamento1;

	//bi-directional many-to-one association to StatoInvioFlussoRiversamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stato_invio_flusso_esteso")
	private StatoInvioFlussoRiversamento statoInvioFlussoRiversamento2;

	public FlussoRiversamento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodicebicbancadiriversamento() {
		return this.codicebicbancadiriversamento;
	}

	public void setCodicebicbancadiriversamento(String codicebicbancadiriversamento) {
		this.codicebicbancadiriversamento = codicebicbancadiriversamento;
	}

	public Timestamp getDatainserimento() {
		return this.datainserimento;
	}

	public void setDatainserimento(Timestamp datainserimento) {
		this.datainserimento = datainserimento;
	}

	public Timestamp getDatamodifica() {
		return this.datamodifica;
	}

	public void setDatamodifica(Timestamp datamodifica) {
		this.datamodifica = datamodifica;
	}

	public Timestamp getDataoraflusso() {
		return this.dataoraflusso;
	}

	public void setDataoraflusso(Timestamp dataoraflusso) {
		this.dataoraflusso = dataoraflusso;
	}

	public Timestamp getDataregolamento() {
		return this.dataregolamento;
	}

	public void setDataregolamento(Timestamp dataregolamento) {
		this.dataregolamento = dataregolamento;
	}

	public String getDenominazionemittente() {
		return this.denominazionemittente;
	}

	public void setDenominazionemittente(String denominazionemittente) {
		this.denominazionemittente = denominazionemittente;
	}

	public String getDenominazionericevente() {
		return this.denominazionericevente;
	}

	public void setDenominazionericevente(String denominazionericevente) {
		this.denominazionericevente = denominazionericevente;
	}

	public String getIdentificativoflusso() {
		return this.identificativoflusso;
	}

	public void setIdentificativoflusso(String identificativoflusso) {
		this.identificativoflusso = identificativoflusso;
	}

	public String getIdentificativoistitutomittente() {
		return this.identificativoistitutomittente;
	}

	public void setIdentificativoistitutomittente(String identificativoistitutomittente) {
		this.identificativoistitutomittente = identificativoistitutomittente;
	}

	public String getIdentificativoistitutoricevente() {
		return this.identificativoistitutoricevente;
	}

	public void setIdentificativoistitutoricevente(String identificativoistitutoricevente) {
		this.identificativoistitutoricevente = identificativoistitutoricevente;
	}

	public String getIdentificativopsp() {
		return this.identificativopsp;
	}

	public void setIdentificativopsp(String identificativopsp) {
		this.identificativopsp = identificativopsp;
	}

	public String getIdentificativounivocoregolamento() {
		return this.identificativounivocoregolamento;
	}

	public void setIdentificativounivocoregolamento(String identificativounivocoregolamento) {
		this.identificativounivocoregolamento = identificativounivocoregolamento;
	}

	public BigDecimal getImportototalepagamenti() {
		return this.importototalepagamenti;
	}

	public void setImportototalepagamenti(BigDecimal importototalepagamenti) {
		this.importototalepagamenti = importototalepagamenti;
	}

	public Integer getNumerototalepagamenti() {
		return this.numerototalepagamenti;
	}

	public void setNumerototalepagamenti(Integer numerototalepagamenti) {
		this.numerototalepagamenti = numerototalepagamenti;
	}

	public String getVersioneoggetto() {
		return this.versioneoggetto;
	}

	public void setVersioneoggetto(String versioneoggetto) {
		this.versioneoggetto = versioneoggetto;
	}

	public String getXmlflusso() {
		return this.xmlflusso;
	}

	public void setXmlflusso(String xmlflusso) {
		this.xmlflusso = xmlflusso;
	}

	public StatoInvioFlussoRiversamento getStatoInvioFlussoRiversamento1() {
		return this.statoInvioFlussoRiversamento1;
	}

	public void setStatoInvioFlussoRiversamento1(StatoInvioFlussoRiversamento statoInvioFlussoRiversamento1) {
		this.statoInvioFlussoRiversamento1 = statoInvioFlussoRiversamento1;
	}

	public StatoInvioFlussoRiversamento getStatoInvioFlussoRiversamento2() {
		return this.statoInvioFlussoRiversamento2;
	}

	public void setStatoInvioFlussoRiversamento2(StatoInvioFlussoRiversamento statoInvioFlussoRiversamento2) {
		this.statoInvioFlussoRiversamento2 = statoInvioFlussoRiversamento2;
	}

}
