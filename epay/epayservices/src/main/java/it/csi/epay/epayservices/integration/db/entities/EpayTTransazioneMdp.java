/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_transazione_mdp database table.
 * 
 */
@Entity
@Table(name="epay_t_transazione_mdp")
public class EpayTTransazioneMdp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_transazione", unique=true, nullable=false, length=50)
	private String idTransazione;

	@Column(name="id_gateway", length=50)
	private String idGateway;

	@Column(name="id_informativa_psp")
	private Integer idInformativaPsp;

	@Column(name="id_payment_mode", length=50)
	private String idPaymentMode;

	@Column(name="identificativo_canale_psp", length=250)
	private String identificativoCanalePsp;

	@Column(name="identificativo_psp", length=250)
	private String identificativoPsp;

	@Column(nullable=false, length=25)
	private String iuv;

	@Column(name="modello_pagamento_psp")
	private Integer modelloPagamentoPsp;

	@Column(name="ragione_sociale_psp", length=250)
	private String ragioneSocialePsp;

	@Column(name="tipo_versamento_psp", length=250)
	private String tipoVersamentoPsp;

	//bi-directional many-to-one association to EpayTRegistroVersamenti
	@OneToMany(mappedBy="epayTTransazioneMdp")
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	public EpayTTransazioneMdp() {
	}

	public String getIdTransazione() {
		return this.idTransazione;
	}

	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}

	public String getIdGateway() {
		return this.idGateway;
	}

	public void setIdGateway(String idGateway) {
		this.idGateway = idGateway;
	}

	public Integer getIdInformativaPsp() {
		return this.idInformativaPsp;
	}

	public void setIdInformativaPsp(Integer idInformativaPsp) {
		this.idInformativaPsp = idInformativaPsp;
	}

	public String getIdPaymentMode() {
		return this.idPaymentMode;
	}

	public void setIdPaymentMode(String idPaymentMode) {
		this.idPaymentMode = idPaymentMode;
	}

	public String getIdentificativoCanalePsp() {
		return this.identificativoCanalePsp;
	}

	public void setIdentificativoCanalePsp(String identificativoCanalePsp) {
		this.identificativoCanalePsp = identificativoCanalePsp;
	}

	public String getIdentificativoPsp() {
		return this.identificativoPsp;
	}

	public void setIdentificativoPsp(String identificativoPsp) {
		this.identificativoPsp = identificativoPsp;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public Integer getModelloPagamentoPsp() {
		return this.modelloPagamentoPsp;
	}

	public void setModelloPagamentoPsp(Integer modelloPagamentoPsp) {
		this.modelloPagamentoPsp = modelloPagamentoPsp;
	}

	public String getRagioneSocialePsp() {
		return this.ragioneSocialePsp;
	}

	public void setRagioneSocialePsp(String ragioneSocialePsp) {
		this.ragioneSocialePsp = ragioneSocialePsp;
	}

	public String getTipoVersamentoPsp() {
		return this.tipoVersamentoPsp;
	}

	public void setTipoVersamentoPsp(String tipoVersamentoPsp) {
		this.tipoVersamentoPsp = tipoVersamentoPsp;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis() {
		return this.epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis(List<EpayTRegistroVersamenti> epayTRegistroVersamentis) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	public EpayTRegistroVersamenti addEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().add(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayTTransazioneMdp(this);

		return epayTRegistroVersamenti;
	}

	public EpayTRegistroVersamenti removeEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
		getEpayTRegistroVersamentis().remove(epayTRegistroVersamenti);
		epayTRegistroVersamenti.setEpayTTransazioneMdp(null);

		return epayTRegistroVersamenti;
	}

}
