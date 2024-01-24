/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_agg_posizione_debitoria database table.
 * 
 */
@Entity
@Table(name="epaypa_t_agg_posizione_debitoria")
public class EpaypaTAggPosizioneDebitoriaLight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_agg_posizione_debitoria")
	private Long idAggPosizioneDebitoria;

	@Column(name = "id_flusso")
	private Long idFlusso;

	@Column(name="cod_esito")
	private String codEsito;

	@Column(name="codice_avviso")
	private String codiceAvviso;

	@Column(name="dettaglio_esito")
	private String dettaglioEsito;

	@Column(name="id_posizione_debitoria_est")
	private String idPosizioneDebitoriaEst;

	private String motivazione;

	@Column ( name = "importo_principale" )
	private BigDecimal importoPrincipale;

	@Column ( name = "importo_secondario_altro_ente" )
	private BigDecimal importoSecondarioAltroEnte;

	//uni-directional many-to-one association to EpaypaDTipoAggPosizioneDebitoria
	@ManyToOne
	@JoinColumn(name="id_tipo_agg_posizione_debitoria")
	private EpaypaDTipoAggPosizioneDebitoria epaypaDTipoAggPosizioneDebitoria;

	public EpaypaTAggPosizioneDebitoriaLight() {
		//empty constructor
	}

	public Long getIdAggPosizioneDebitoria() {
		return this.idAggPosizioneDebitoria;
	}

	public void setIdAggPosizioneDebitoria(Long idAggPosizioneDebitoria) {
		this.idAggPosizioneDebitoria = idAggPosizioneDebitoria;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
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

	public String getDettaglioEsito() {
		return this.dettaglioEsito;
	}

	public void setDettaglioEsito(String dettaglioEsito) {
		this.dettaglioEsito = dettaglioEsito;
	}

	public String getIdPosizioneDebitoriaEst() {
		return this.idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst(String idPosizioneDebitoriaEst) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
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
