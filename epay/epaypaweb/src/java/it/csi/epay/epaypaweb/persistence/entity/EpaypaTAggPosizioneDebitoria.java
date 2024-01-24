/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

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


/**
 * The persistent class for the epaypa_t_agg_posizione_debitoria database table.
 * 
 */
@Entity
@Table(name="epaypa_t_agg_posizione_debitoria")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTAggPosizioneDebitoria.findAllByIdFlusso",
			query = "SELECT e FROM EpaypaTAggPosizioneDebitoria e WHERE epaypaTFlusso.idFlusso = :idFlusso"),
	@NamedQuery(
			name = "EpaypaTAggPosizioneDebitoria.findAll",
			query = "SELECT e FROM EpaypaTAggPosizioneDebitoria e"),
	@NamedQuery(
	    name = "EpaypaTAggPosizioneDebitoria.findAllByIdPosizioneDebitoriaEst",
	    query = "SELECT e FROM EpaypaTAggPosizioneDebitoria e where idPosizioneDebitoriaEst = :idPosizioneDebitoriaEst and epaypaDTipoAggPosizioneDebitoria = 'M' and codEsito = '000' ORDER BY idAggPosizioneDebitoria DESC")
	
})
public class EpaypaTAggPosizioneDebitoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_agg_posizione_debitoria")
	private Long idAggPosizioneDebitoria;

	@Column(name="cod_esito")
	private String codEsito;

	@Column(name="codice_avviso")
	private String codiceAvviso;

	@Column(name="des_causale_versamento")
	private String desCausaleVersamento;

	@Column(name="dettaglio_esito")
	private String dettaglioEsito;

	@Column(name="dt_fine_validita")
	private Timestamp dtFineValidita;

	@Column(name="dt_inizio_validita")
	private Timestamp dtInizioValidita;

	@Column(name="dt_scadenza")
	private Timestamp dtScadenza;

	@Column(name="id_posizione_debitoria_est")
	private String idPosizioneDebitoriaEst;

	@Column(name="importo_totale")
	private BigDecimal importoTotale;

	@Column ( name = "importo_principale" )
	private BigDecimal importoPrincipale;

	@Column ( name = "importo_secondario_altro_ente" )
	private BigDecimal importoSecondarioAltroEnte;

	private String motivazione;

	//uni-directional many-to-one association to EpaypaDTipoAggPosizioneDebitoria
	@ManyToOne
	@JoinColumn(name="id_tipo_agg_posizione_debitoria")
	private EpaypaDTipoAggPosizioneDebitoria epaypaDTipoAggPosizioneDebitoria;

	//bi-directional many-to-one association to EpaypaTFlusso
	@ManyToOne
	@JoinColumn(name="id_flusso", updatable=false, insertable=false, nullable=false)
	private EpaypaTFlusso epaypaTFlusso;

	//uni-directional many-to-many association to EpaypaTComponenteImporto
    @ManyToMany ( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinTable(
		name="epaypa_r_agg_pd_comp_imp"
		, joinColumns={
			@JoinColumn(name="id_agg_posizione_debitoria")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_componente")
			}
		)
    private Set<EpaypaTComponenteImporto> epaypaTComponenteImportos;

    //uni-directional many-to-many association to EpaypaTRiferimentoPagamento
    @ManyToMany ( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable (
                    name = "epaypa_r_agg_pd_rif_pagamento", joinColumns = {
                        @JoinColumn ( name = "id_agg_posizione_debitoria" )
                    },
                    inverseJoinColumns = {
                        @JoinColumn ( name = "id_riferimento" )
                    } )
    private Set<EpaypaTRiferimentoPagamento> epaypaTRiferimentoPagamentos;
    
    
    //uni-directional many-to-one  association to EpaypaTSoggetto
    @ManyToOne ( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable (
                    name = "epaypa_r_agg_pd_soggetto", joinColumns = {
                        @JoinColumn ( name = "id_agg_posizione_debitoria" )
                    },
                    inverseJoinColumns = {
                        @JoinColumn ( name = "id_soggetto" )
                    } )
    private EpaypaTSoggetto epaypaTSoggetto;

	public EpaypaTAggPosizioneDebitoria() {
		//constructor
	}

	public Long getIdAggPosizioneDebitoria() {
		return this.idAggPosizioneDebitoria;
	}

	public void setIdAggPosizioneDebitoria(Long idAggPosizioneDebitoria) {
		this.idAggPosizioneDebitoria = idAggPosizioneDebitoria;
	}

	public String getCodEsito() {
		return this.codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getCodiceAvviso() {
		return this.codiceAvviso;
	}

	public void setCodiceAvviso(String codiceAvviso) {
		this.codiceAvviso = codiceAvviso;
	}

	public String getDesCausaleVersamento() {
		return this.desCausaleVersamento;
	}

	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public String getDettaglioEsito() {
		return this.dettaglioEsito;
	}

	public void setDettaglioEsito(String dettaglioEsito) {
		this.dettaglioEsito = dettaglioEsito;
	}

	public Timestamp getDtFineValidita() {
		return this.dtFineValidita;
	}

	public void setDtFineValidita(Timestamp dtFineValidita) {
		this.dtFineValidita = dtFineValidita;
	}

	public Timestamp getDtInizioValidita() {
		return this.dtInizioValidita;
	}

	public void setDtInizioValidita(Timestamp dtInizioValidita) {
		this.dtInizioValidita = dtInizioValidita;
	}

	public Timestamp getDtScadenza() {
		return this.dtScadenza;
	}

	public void setDtScadenza(Timestamp dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public String getIdPosizioneDebitoriaEst() {
		return this.idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst(String idPosizioneDebitoriaEst) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}

	public BigDecimal getImportoTotale() {
		return this.importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getMotivazione() {
		return this.motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public EpaypaDTipoAggPosizioneDebitoria getEpaypaDTipoAggPosizioneDebitoria() {
		return this.epaypaDTipoAggPosizioneDebitoria;
	}

	public void setEpaypaDTipoAggPosizioneDebitoria(EpaypaDTipoAggPosizioneDebitoria epaypaDTipoAggPosizioneDebitoria) {
		this.epaypaDTipoAggPosizioneDebitoria = epaypaDTipoAggPosizioneDebitoria;
	}

	public EpaypaTFlusso getEpaypaTFlusso() {
		return this.epaypaTFlusso;
	}

	public void setEpaypaTFlusso(EpaypaTFlusso epaypaTFlusso) {
		this.epaypaTFlusso = epaypaTFlusso;
	}

    public Set<EpaypaTComponenteImporto> getEpaypaTComponenteImportos () {
		return this.epaypaTComponenteImportos;
	}

    public void setEpaypaTComponenteImportos ( Set<EpaypaTComponenteImporto> epaypaTComponenteImportos ) {
		this.epaypaTComponenteImportos = epaypaTComponenteImportos;
	}

    public Set<EpaypaTRiferimentoPagamento> getEpaypaTRiferimentoPagamentos () {
        return epaypaTRiferimentoPagamentos;
    }

    public void setEpaypaTRiferimentoPagamentos ( Set<EpaypaTRiferimentoPagamento> epaypaTRiferimentoPagamentos ) {
        this.epaypaTRiferimentoPagamentos = epaypaTRiferimentoPagamentos;
    }

	public EpaypaTSoggetto getEpaypaTSoggetto() {
		return epaypaTSoggetto;
	}

	public void setEpaypaTSoggetto(EpaypaTSoggetto epaypaTSoggetto) {
		this.epaypaTSoggetto = epaypaTSoggetto;
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

}
