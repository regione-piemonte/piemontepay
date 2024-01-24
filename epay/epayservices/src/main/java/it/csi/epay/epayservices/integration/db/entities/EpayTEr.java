/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_er database table.
 * 
 */
@Entity
@Table(name="epay_t_er")
@NamedQuery(name="EpayTEr.findAll", query="SELECT e FROM EpayTEr e")
public class EpayTEr implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idEr;
	private String applicationId;
	private String codiceContestoPagamento;
	private String codiceIdentificativoUnivocoAttestante;
	private Timestamp dataOraMessaggioEsito;
	private String denominazioneIstitutoAttestante;
	private String identificativoDominio;
	private String identificativoMessaggioEsito;
	private BigDecimal importoTotaleRevocato;
	private Boolean invioOkRispostaRevoca;
	private String iuv;
	private Timestamp riferimentoDataRevoca;
	private String riferimentoMessaggioRevoca;
	private byte[] xmlEr;

	public EpayTEr() {
	}


	@Id
	@SequenceGenerator(name="EPAY_T_ER_ID_ER_GENERATOR",  allocationSize=1, sequenceName="EPAY_T_ER_ID_ER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_ER_ID_ER_GENERATOR")
	@Column(name="id_er", unique=true, nullable=false)
	public Integer getIdEr() {
		return this.idEr;
	}

	public void setIdEr(Integer idEr) {
		this.idEr = idEr;
	}


	@Column(name="application_id", nullable=false, length=50)
	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}


	@Column(name="codice_contesto_pagamento", nullable=false, length=35)
	public String getCodiceContestoPagamento() {
		return this.codiceContestoPagamento;
	}

	public void setCodiceContestoPagamento(String codiceContestoPagamento) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}


	@Column(name="codice_identificativo_univoco_attestante", nullable=false, length=35)
	public String getCodiceIdentificativoUnivocoAttestante() {
		return this.codiceIdentificativoUnivocoAttestante;
	}

	public void setCodiceIdentificativoUnivocoAttestante(String codiceIdentificativoUnivocoAttestante) {
		this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
	}


	@Column(name="data_ora_messaggio_esito")
	public Timestamp getDataOraMessaggioEsito() {
		return this.dataOraMessaggioEsito;
	}

	public void setDataOraMessaggioEsito(Timestamp dataOraMessaggioEsito) {
		this.dataOraMessaggioEsito = dataOraMessaggioEsito;
	}


	@Column(name="denominazione_istituto_attestante", length=35)
	public String getDenominazioneIstitutoAttestante() {
		return this.denominazioneIstitutoAttestante;
	}

	public void setDenominazioneIstitutoAttestante(String denominazioneIstitutoAttestante) {
		this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
	}


	@Column(name="identificativo_dominio", nullable=false, length=35)
	public String getIdentificativoDominio() {
		return this.identificativoDominio;
	}

	public void setIdentificativoDominio(String identificativoDominio) {
		this.identificativoDominio = identificativoDominio;
	}


	@Column(name="identificativo_messaggio_esito", nullable=false, length=35)
	public String getIdentificativoMessaggioEsito() {
		return this.identificativoMessaggioEsito;
	}

	public void setIdentificativoMessaggioEsito(String identificativoMessaggioEsito) {
		this.identificativoMessaggioEsito = identificativoMessaggioEsito;
	}


	@Column(name="importo_totale_revocato", nullable=false, precision=13, scale=2)
	public BigDecimal getImportoTotaleRevocato() {
		return this.importoTotaleRevocato;
	}

	public void setImportoTotaleRevocato(BigDecimal importoTotaleRevocato) {
		this.importoTotaleRevocato = importoTotaleRevocato;
	}


	@Column(name="invio_ok_risposta_revoca")
	public Boolean getInvioOkRispostaRevoca() {
		return this.invioOkRispostaRevoca;
	}

	public void setInvioOkRispostaRevoca(Boolean invioOkRispostaRevoca) {
		this.invioOkRispostaRevoca = invioOkRispostaRevoca;
	}


	@Column(nullable=false, length=35)
	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}


	@Column(name="riferimento_data_revoca")
	public Timestamp getRiferimentoDataRevoca() {
		return this.riferimentoDataRevoca;
	}

	public void setRiferimentoDataRevoca(Timestamp riferimentoDataRevoca) {
		this.riferimentoDataRevoca = riferimentoDataRevoca;
	}


	@Column(name="riferimento_messaggio_revoca", nullable=false, length=35)
	public String getRiferimentoMessaggioRevoca() {
		return this.riferimentoMessaggioRevoca;
	}

	public void setRiferimentoMessaggioRevoca(String riferimentoMessaggioRevoca) {
		this.riferimentoMessaggioRevoca = riferimentoMessaggioRevoca;
	}


	@Column(name="xml_er")
	public byte[] getXmlEr() {
		return this.xmlEr;
	}

	public void setXmlEr(byte[] xmlEr) {
		this.xmlEr = xmlEr;
	}

}
