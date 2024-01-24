/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the epaypa_t_flusso database table.
 * 
 */
@Entity
@Table(name="epaypa_t_flusso")
public class EpaypaTFlussoLight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_flusso")
	private Long idFlusso;

	//uni-directional many-to-one association to EpaypaDStatoFlusso
	@ManyToOne
	@JoinColumn(name="id_tipo_flusso")
	private EpaypaDTipoFlusso epaypaDTipoFlusso;

	//uni-directional many-to-one association to EpaypaDStatoFlusso
	@ManyToOne
	@JoinColumn(name="id_stato_flusso")
	private EpaypaDStatoFlusso epaypaDStatoFlusso;

	@Column(name="id_messaggio")
	private String idMessaggio;

	//uni-directional many-to-one association to EpaypaTCodiceVersamento
	@ManyToOne
	@JoinColumn(name="id_ente")
	private EpaypaTEnte epaypaTEnte;

	//uni-directional many-to-one association to EpaypaTCodiceVersamento
	@ManyToOne
	@JoinColumn(name="id_codice_versamento")
	private EpaypaTCodiceVersamento epaypaTCodiceVersamento;

	@Column(name="numero_elementi")
	private Integer numeroElementi;

	@Column(name="importo_totale")
	private BigDecimal importoTotale;

	@Column(name="pagamenti_spontanei")
	private Boolean pagamentiSpontanei;

	@Column(name="dt_inserimento")
	private Timestamp dtInserimento;

	@Column(name="dt_ultima_variazione")
	private Timestamp dtUltimaVariazione;

	@Column(name="utente_ultima_variazione")
	private String utenteUltimaVariazione;

	@Column(name="cod_esito")
	private String codEsito;

	@Column(name="dettaglio_esito")
	private String dettaglioEsito;

	//uni-directional many-to-one association to EpaypaTRendicontazione
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_flusso", updatable=false, insertable=false, nullable=true)
	private EpaypaTRendicontazioneLight epaypaTRendicontazione;

	//bi-directional many-to-one association to EpaypaTPosizioneDebitoria
    @OneToMany ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private List<EpaypaTPosizioneDebitoriaLight> epaypaTPosizioneDebitoriaLight;

	public EpaypaTFlussoLight() {
		//empty constructor
	}

	public List<EpaypaTPosizioneDebitoriaLight> getEpaypaTPosizioneDebitoriaLight () {
		return epaypaTPosizioneDebitoriaLight;
	}

	public void setEpaypaTPosizioneDebitoriaLight (
					List<EpaypaTPosizioneDebitoriaLight> epaypaTPosizioneDebitoriaLight ) {
		this.epaypaTPosizioneDebitoriaLight = epaypaTPosizioneDebitoriaLight;
	}

	public Long getIdFlusso() {
		return this.idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public EpaypaDTipoFlusso getEpaypaDTipoFlusso() {
		return this.epaypaDTipoFlusso;
	}

	public void setEpaypaDTipoFlusso(EpaypaDTipoFlusso epaypaDTipoFlusso) {
		this.epaypaDTipoFlusso = epaypaDTipoFlusso;
	}

	public EpaypaDStatoFlusso getEpaypaDStatoFlusso() {
		return this.epaypaDStatoFlusso;
	}

	public void setEpaypaDStatoFlusso(EpaypaDStatoFlusso epaypaDStatoFlusso) {
		this.epaypaDStatoFlusso = epaypaDStatoFlusso;
	}

	public String getIdMessaggio() {
		return this.idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public EpaypaTEnte getEpaypaTEnte() {
		return this.epaypaTEnte;
	}

	public void setEpaypaTEnte(EpaypaTEnte epaypaTEnte) {
		this.epaypaTEnte = epaypaTEnte;
	}

	public EpaypaTCodiceVersamento getEpaypaTCodiceVersamento() {
		return this.epaypaTCodiceVersamento;
	}

	public void setEpaypaTCodiceVersamento(EpaypaTCodiceVersamento epaypaTCodiceVersamento) {
		this.epaypaTCodiceVersamento = epaypaTCodiceVersamento;
	}

	public Integer getNumeroElementi() {
		return this.numeroElementi;
	}

	public void setNumeroElementi(Integer numeroElementi) {
		this.numeroElementi = numeroElementi;
	}

	public BigDecimal getImportoTotale() {
		return this.importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public Boolean getPagamentiSpontanei() {
		return this.pagamentiSpontanei;
	}

	public void setPagamentiSpontanei(Boolean pagamentiSpontanei) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}

	public Timestamp getDtInserimento() {
		return this.dtInserimento;
	}

	public void setDtInserimento(Timestamp dtInserimento) {
		this.dtInserimento = dtInserimento;
	}

	public Timestamp getDtUltimaVariazione() {
		return this.dtUltimaVariazione;
	}

	public void setDtUltimaVariazione(Timestamp dtUltimaVariazione) {
		this.dtUltimaVariazione = dtUltimaVariazione;
	}

	public String getUtenteUltimaVariazione() {
		return this.utenteUltimaVariazione;
	}

	public void setUtenteUltimaVariazione(String utenteUltimaVariazione) {
		this.utenteUltimaVariazione = utenteUltimaVariazione;
	}

	public String getCodEsito() {
		return this.codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDettaglioEsito() {
		return this.dettaglioEsito;
	}

	public void setDettaglioEsito(String dettaglioEsito) {
		this.dettaglioEsito = dettaglioEsito;
	}

	public EpaypaTRendicontazioneLight getEpaypaTRendicontazione() {
		return this.epaypaTRendicontazione;
	}

	public void setEpaypaTRendicontazione(EpaypaTRendicontazioneLight epaypaTRendicontazione) {
		this.epaypaTRendicontazione = epaypaTRendicontazione;
	}


}
