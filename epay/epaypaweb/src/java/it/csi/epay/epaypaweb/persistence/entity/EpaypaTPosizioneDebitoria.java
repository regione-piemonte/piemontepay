/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the epaypa_t_posizione_debitoria database table.
 * 
 */
@Entity
@Table ( name = "epaypa_t_posizione_debitoria" )
@NamedQueries ( {
	@NamedQuery (
		name = "EpaypaTPosizioneDebitoria.findAllByIdFlusso",
		query = "SELECT e FROM EpaypaTPosizioneDebitoria e WHERE epaypaTFlusso.idFlusso = :idFlusso" ),
	@NamedQuery (
		name = "EpaypaTPosizioneDebitoria.findByIuv",
		query = "SELECT e FROM EpaypaTPosizioneDebitoria e WHERE e.iuv = :iuv" ),
	@NamedQuery (
		name = "EpaypaTPosizioneDebitoria.findAll",
		query = "SELECT e FROM EpaypaTPosizioneDebitoria e" ),
	@NamedQuery (
		name = "EpaypaTPosizioneDebitoria.findByPosizioneDebitoriaEsternaAndIUV",
		query = "SELECT e FROM EpaypaTPosizioneDebitoria e WHERE e.idPosizioneDebitoriaEst LIKE :id_posizione_debitoria_est AND e.epaypaTFlusso.epaypaTEnte.id = :id_ente AND e.epaypaTFlusso.epaypaTCodiceVersamento.id = :id_cod_versamento AND e.iuv LIKE :iuv"
	),
	@NamedQuery (
		name = "EpaypaTPosizioneDebitoria.findByPosizioneDebitoriaEsterna",
		query = "SELECT e FROM EpaypaTPosizioneDebitoria e WHERE e.idPosizioneDebitoriaEst LIKE :id_posizione_debitoria_est AND e.epaypaTFlusso.epaypaTEnte.id = :id_ente AND e.epaypaTFlusso.epaypaTCodiceVersamento.id = :id_cod_versamento"
	),
	@NamedQuery ( // primo tentativo
		name = "EpaypaTPosizioneDebitoria.findByIUV",
		query = "SELECT e FROM EpaypaTPosizioneDebitoria e, EpaypaTAggPosizioneDebitoria a WHERE e.idPosizioneDebitoriaEst = a.idPosizioneDebitoriaEst AND e.iuv LIKE :iuv AND e.codEsito = '000' AND e.epaypaTFlusso.epaypaTEnte.id = :id_ente AND e.epaypaTFlusso.epaypaTCodiceVersamento.id = :id_cod_versamento"
	),
	@NamedQuery (
		name = "EpaypaTPosizioneDebitoria.findByIUVAlt",
		query = "SELECT e FROM EpaypaTPosizioneDebitoria e WHERE e.iuv LIKE :iuv AND e.codEsito = '000' AND e.epaypaTFlusso.epaypaTEnte.id = :id_ente AND e.epaypaTFlusso.epaypaTCodiceVersamento.id = :id_cod_versamento"
	)
} )
public class EpaypaTPosizioneDebitoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id_posizione_debitoria" )
	private Long idPosizioneDebitoria;

	@Column ( name = "anno_riferimento" )
	private Integer annoRiferimento;

	@Column ( name = "cod_esito" )
	private String codEsito;

	@Column ( name = "codice_avviso" )
	private String codiceAvviso;

	@Column ( name = "des_causale_versamento" )
	private String desCausaleVersamento;

	@Column ( name = "des_rata" )
	private String desRata;

	@Column ( name = "dettaglio_esito" )
	private String dettaglioEsito;

	@Column ( name = "dt_fine_validita" )
	private Timestamp dtFineValidita;

	@Column ( name = "dt_inizio_validita" )
	private Timestamp dtInizioValidita;

	@Column ( name = "dt_scadenza" )
	private Timestamp dtScadenza;

	@Column ( name = "id_posizione_debitoria_est" )
	private String idPosizioneDebitoriaEst;

	@Column ( name = "importo_totale" )
	private BigDecimal importoTotale;

	@Column ( name = "iuv" )
	private String iuv;

	@Column ( name = "note_per_pagatore" )
	private String notePerPagatore;

	@Column ( name = "importo_principale" )
	private BigDecimal importoPrincipale;

	@Column ( name = "importo_secondario_altro_ente" )
	private BigDecimal importoSecondarioAltroEnte;

	//uni-directional many-to-many association to EpaypaTComponenteImporto
	@ManyToMany ( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinTable (
		name = "epaypa_r_pos_deb_comp_imp", joinColumns = {
			@JoinColumn ( name = "id_posizione_debitoria" )
		},
		inverseJoinColumns = {
			@JoinColumn ( name = "id_componente" )
		} )
	private Set<EpaypaTComponenteImporto> epaypaTComponenteImportos;

	//uni-directional many-to-many association to EpaypaTRiferimentoPagamento
	@ManyToMany ( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinTable (
		name = "epaypa_r_pos_deb_rif_pagamento", joinColumns = {
			@JoinColumn ( name = "id_posizione_debitoria" )
		},
		inverseJoinColumns = {
			@JoinColumn ( name = "id_riferimento" )
		} )
	private Set<EpaypaTRiferimentoPagamento> epaypaTRiferimentoPagamentos;

	//bi-directional many-to-one association to EpaypaTFlusso
	@ManyToOne
	@JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
	private EpaypaTFlusso epaypaTFlusso;

	//uni-directional many-to-one association to EpaypaTSoggetto
	@ManyToOne ( cascade = CascadeType.ALL )
	@JoinColumn ( name = "id_soggetto_debitore" )
	private EpaypaTSoggetto epaypaTSoggettoDebitore;

	public EpaypaTPosizioneDebitoria () {
		//costruttore di default
	}

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public BigDecimal getImportoSecondarioAltroEnte () {
		return importoSecondarioAltroEnte;
	}

	public void setImportoSecondarioAltroEnte ( BigDecimal importoSecondarioAltroEnte ) {
		this.importoSecondarioAltroEnte = importoSecondarioAltroEnte;
	}

	public Long getIdPosizioneDebitoria () {
		return this.idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria ( Long idPosizioneDebitoria ) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public Integer getAnnoRiferimento () {
		return this.annoRiferimento;
	}

	public void setAnnoRiferimento ( Integer annoRiferimento ) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getCodEsito () {
		return this.codEsito;
	}

	public void setCodEsito ( String codEsito ) {
		this.codEsito = codEsito;
	}

	public String getCodiceAvviso () {
		return this.codiceAvviso;
	}

	public void setCodiceAvviso ( String codiceAvviso ) {
		this.codiceAvviso = codiceAvviso;
	}

	public String getDesCausaleVersamento () {
		return this.desCausaleVersamento;
	}

	public void setDesCausaleVersamento ( String desCausaleVersamento ) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public String getDesRata () {
		return this.desRata;
	}

	public void setDesRata ( String desRata ) {
		this.desRata = desRata;
	}

	public String getDettaglioEsito () {
		return this.dettaglioEsito;
	}

	public void setDettaglioEsito ( String dettaglioEsito ) {
		this.dettaglioEsito = dettaglioEsito;
	}

	public Timestamp getDtFineValidita () {
		return this.dtFineValidita;
	}

	public void setDtFineValidita ( Timestamp dtFineValidita ) {
		this.dtFineValidita = dtFineValidita;
	}

	public Timestamp getDtInizioValidita () {
		return this.dtInizioValidita;
	}

	public void setDtInizioValidita ( Timestamp dtInizioValidita ) {
		this.dtInizioValidita = dtInizioValidita;
	}

	public Timestamp getDtScadenza () {
		return this.dtScadenza;
	}

	public void setDtScadenza ( Timestamp dtScadenza ) {
		this.dtScadenza = dtScadenza;
	}

	public String getIdPosizioneDebitoriaEst () {
		return this.idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst ( String idPosizioneDebitoriaEst ) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}

	public BigDecimal getImportoTotale () {
		return this.importoTotale;
	}

	public void setImportoTotale ( BigDecimal importoTotale ) {
		this.importoTotale = importoTotale;
	}

	public String getIuv () {
		return this.iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getNotePerPagatore () {
		return this.notePerPagatore;
	}

	public void setNotePerPagatore ( String notePerPagatore ) {
		this.notePerPagatore = notePerPagatore;
	}

	public Set<EpaypaTComponenteImporto> getEpaypaTComponenteImportos () {
		return this.epaypaTComponenteImportos;
	}

	public void setEpaypaTComponenteImportos ( Set<EpaypaTComponenteImporto> epaypaTComponenteImportos ) {
		this.epaypaTComponenteImportos = epaypaTComponenteImportos;
	}

	public Set<EpaypaTRiferimentoPagamento> getEpaypaTRiferimentoPagamentos () {
		return this.epaypaTRiferimentoPagamentos;
	}

	public void setEpaypaTRiferimentoPagamentos ( Set<EpaypaTRiferimentoPagamento> epaypaTRiferimentoPagamentos ) {
		this.epaypaTRiferimentoPagamentos = epaypaTRiferimentoPagamentos;
	}

	public EpaypaTFlusso getEpaypaTFlusso () {
		return this.epaypaTFlusso;
	}

	public void setEpaypaTFlusso ( EpaypaTFlusso epaypaTFlusso ) {
		this.epaypaTFlusso = epaypaTFlusso;
	}

	public EpaypaTSoggetto getEpaypaTSoggettoDebitore () {
		return this.epaypaTSoggettoDebitore;
	}

	public void setEpaypaTSoggettoDebitore ( EpaypaTSoggetto epaypaTSoggettoDebitore ) {
		this.epaypaTSoggettoDebitore = epaypaTSoggettoDebitore;
	}

}
