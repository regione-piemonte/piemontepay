/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the er database table.
 * 
 */
@Entity
@NamedQuery(name="Er.findAll", query="SELECT e FROM Er e")
public class Er implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ER_IDER_GENERATOR", sequenceName="ER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ER_IDER_GENERATOR")
	@Column(name="id_er")
	private Integer idEr;

	@Column(name="application_id")
	private String applicationId;

	@Column(name="codice_contesto_pagamento")
	private String codiceContestoPagamento;

	@Column(name="codice_identificativo_univoco_attestante")
	private String codiceIdentificativoUnivocoAttestante;

	@Column(name="data_ora_messaggio_esito")
	private Timestamp dataOraMessaggioEsito;

	@Column(name="denominazione_istituto_attestante")
	private String denominazioneIstitutoAttestante;

	@Column(name="id_dominio")
	private String idDominio;

	@Column(name="identificativo_messaggio_esito")
	private String identificativoMessaggioEsito;

	@Column(name="importo_totale_revocato")
	private BigDecimal importoTotaleRevocato;

	@Column(name="invio_ok_risposta_revoca")
	private Boolean invioOkRispostaRevoca;

	private String iuv;

	@Column(name="riferimento_data_revoca")
	private Timestamp riferimentoDataRevoca;

	@Column(name="riferimento_messaggio_revoca")
	private String riferimentoMessaggioRevoca;

	@Column(name="xml_er")
	private byte[] xmlEr;

	//bi-directional many-to-one association to DatiSingoloEsito
	@OneToMany(mappedBy="er1")
	private List<DatiSingoloEsito> datiSingoloEsitos1;

	//bi-directional many-to-one association to DatiSingoloEsito
	@OneToMany(mappedBy="er2")
	private List<DatiSingoloEsito> datiSingoloEsitos2;

	public Er() {
	}

	public Integer getIdEr() {
		return this.idEr;
	}

	public void setIdEr(Integer idEr) {
		this.idEr = idEr;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCodiceContestoPagamento() {
		return this.codiceContestoPagamento;
	}

	public void setCodiceContestoPagamento(String codiceContestoPagamento) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}

	public String getCodiceIdentificativoUnivocoAttestante() {
		return this.codiceIdentificativoUnivocoAttestante;
	}

	public void setCodiceIdentificativoUnivocoAttestante(String codiceIdentificativoUnivocoAttestante) {
		this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
	}

	public Timestamp getDataOraMessaggioEsito() {
		return this.dataOraMessaggioEsito;
	}

	public void setDataOraMessaggioEsito(Timestamp dataOraMessaggioEsito) {
		this.dataOraMessaggioEsito = dataOraMessaggioEsito;
	}

	public String getDenominazioneIstitutoAttestante() {
		return this.denominazioneIstitutoAttestante;
	}

	public void setDenominazioneIstitutoAttestante(String denominazioneIstitutoAttestante) {
		this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
	}

	public String getIdDominio() {
		return this.idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}

	public String getIdentificativoMessaggioEsito() {
		return this.identificativoMessaggioEsito;
	}

	public void setIdentificativoMessaggioEsito(String identificativoMessaggioEsito) {
		this.identificativoMessaggioEsito = identificativoMessaggioEsito;
	}

	public BigDecimal getImportoTotaleRevocato() {
		return this.importoTotaleRevocato;
	}

	public void setImportoTotaleRevocato(BigDecimal importoTotaleRevocato) {
		this.importoTotaleRevocato = importoTotaleRevocato;
	}

	public Boolean getInvioOkRispostaRevoca() {
		return this.invioOkRispostaRevoca;
	}

	public void setInvioOkRispostaRevoca(Boolean invioOkRispostaRevoca) {
		this.invioOkRispostaRevoca = invioOkRispostaRevoca;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public Timestamp getRiferimentoDataRevoca() {
		return this.riferimentoDataRevoca;
	}

	public void setRiferimentoDataRevoca(Timestamp riferimentoDataRevoca) {
		this.riferimentoDataRevoca = riferimentoDataRevoca;
	}

	public String getRiferimentoMessaggioRevoca() {
		return this.riferimentoMessaggioRevoca;
	}

	public void setRiferimentoMessaggioRevoca(String riferimentoMessaggioRevoca) {
		this.riferimentoMessaggioRevoca = riferimentoMessaggioRevoca;
	}

	public byte[] getXmlEr() {
		return this.xmlEr;
	}

	public void setXmlEr(byte[] xmlEr) {
		this.xmlEr = xmlEr;
	}

	public List<DatiSingoloEsito> getDatiSingoloEsitos1() {
		return this.datiSingoloEsitos1;
	}

	public void setDatiSingoloEsitos1(List<DatiSingoloEsito> datiSingoloEsitos1) {
		this.datiSingoloEsitos1 = datiSingoloEsitos1;
	}

	public DatiSingoloEsito addDatiSingoloEsitos1(DatiSingoloEsito datiSingoloEsitos1) {
		getDatiSingoloEsitos1().add(datiSingoloEsitos1);
		datiSingoloEsitos1.setEr1(this);

		return datiSingoloEsitos1;
	}

	public DatiSingoloEsito removeDatiSingoloEsitos1(DatiSingoloEsito datiSingoloEsitos1) {
		getDatiSingoloEsitos1().remove(datiSingoloEsitos1);
		datiSingoloEsitos1.setEr1(null);

		return datiSingoloEsitos1;
	}

	public List<DatiSingoloEsito> getDatiSingoloEsitos2() {
		return this.datiSingoloEsitos2;
	}

	public void setDatiSingoloEsitos2(List<DatiSingoloEsito> datiSingoloEsitos2) {
		this.datiSingoloEsitos2 = datiSingoloEsitos2;
	}

	public DatiSingoloEsito addDatiSingoloEsitos2(DatiSingoloEsito datiSingoloEsitos2) {
		getDatiSingoloEsitos2().add(datiSingoloEsitos2);
		datiSingoloEsitos2.setEr2(this);

		return datiSingoloEsitos2;
	}

	public DatiSingoloEsito removeDatiSingoloEsitos2(DatiSingoloEsito datiSingoloEsitos2) {
		getDatiSingoloEsitos2().remove(datiSingoloEsitos2);
		datiSingoloEsitos2.setEr2(null);

		return datiSingoloEsitos2;
	}

}
