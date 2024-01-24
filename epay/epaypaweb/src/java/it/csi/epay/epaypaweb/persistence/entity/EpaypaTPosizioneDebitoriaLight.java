/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the epaypa_t_posizione_debitoria database table.
 * 
 */
@Entity
@Table(name = "epaypa_t_posizione_debitoria")
public class EpaypaTPosizioneDebitoriaLight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_posizione_debitoria")
	private Long idPosizioneDebitoria;

	@ManyToOne
	@JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
	private EpaypaTFlussoLight idFlusso;

	@Column(name = "iuv")
	private String iuv;

	@Column(name = "importo_totale")
	private BigDecimal importoTotale;

	@Column(name = "des_causale_versamento")
	private String desCausaleVersamento;

	@Column(name = "dt_scadenza")
	private Timestamp dtScadenza;

	@ManyToOne
	@JoinColumn(name="id_soggetto_debitore")
	private EpaypaTSoggetto epaypaTSoggettoDebitore;

	@Column ( name = "id_posizione_debitoria_est" )
	private String idPosizioneDebitoriaEst;

	public EpaypaTPosizioneDebitoriaLight() {
	}

	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(Long idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public EpaypaTFlussoLight getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(EpaypaTFlussoLight idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getDesCausaleVersamento() {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public Timestamp getDtScadenza() {
		return dtScadenza;
	}

	public void setDtScadenza(Timestamp dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public EpaypaTSoggetto getEpaypaTSoggettoDebitore() {
		return this.epaypaTSoggettoDebitore;
	}

	public void setEpaypaTSoggettoDebitore(EpaypaTSoggetto epaypaTSoggettoDebitore) {
		this.epaypaTSoggettoDebitore = epaypaTSoggettoDebitore;
	}

	public String getIdPosizioneDebitoriaEst () {
		return idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst ( String idPosizioneDebitoriaEst ) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}
}
