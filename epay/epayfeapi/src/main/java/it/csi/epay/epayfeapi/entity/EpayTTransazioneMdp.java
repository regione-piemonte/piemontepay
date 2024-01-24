/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Entity
@Table ( name = "epay_t_transazione_mdp" )
@SuppressWarnings ( "unused" )
public class EpayTTransazioneMdp implements Serializable {

	private static final long serialVersionUID = -4812873853420382596L;

	@Id
	@Column ( name = "id_transazione", unique = true, nullable = false, length = 50 )
	private String idTransazione;

	@Column ( name = "id_gateway", length = 50 )
	private String idGateway;

	@Column ( name = "id_informativa_psp" )
	private Integer idInformativaPsp;

	@Column ( name = "id_payment_mode", length = 50 )
	private String idPaymentMode;

	@Column ( name = "identificativo_canale_psp", length = 250 )
	private String identificativoCanalePsp;

	@Column ( name = "identificativo_psp", length = 250 )
	private String identificativoPsp;

	@Column ( nullable = false, length = 25 )
	private String iuv;

	@Column ( name = "modello_pagamento_psp" )
	private Integer modelloPagamentoPsp;

	@Column ( name = "ragione_sociale_psp", length = 250 )
	private String ragioneSocialePsp;

	@Column ( name = "tipo_versamento_psp", length = 250 )
	private String tipoVersamentoPsp;

	@OneToMany ( mappedBy = "epayTTransazioneMdp" )
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	public EpayTTransazioneMdp () {
	}

	public String getIdTransazione () {
		return idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public String getIdGateway () {
		return idGateway;
	}

	public void setIdGateway ( String idGateway ) {
		this.idGateway = idGateway;
	}

	public Integer getIdInformativaPsp () {
		return idInformativaPsp;
	}

	public void setIdInformativaPsp ( Integer idInformativaPsp ) {
		this.idInformativaPsp = idInformativaPsp;
	}

	public String getIdPaymentMode () {
		return idPaymentMode;
	}

	public void setIdPaymentMode ( String idPaymentMode ) {
		this.idPaymentMode = idPaymentMode;
	}

	public String getIdentificativoCanalePsp () {
		return identificativoCanalePsp;
	}

	public void setIdentificativoCanalePsp ( String identificativoCanalePsp ) {
		this.identificativoCanalePsp = identificativoCanalePsp;
	}

	public String getIdentificativoPsp () {
		return identificativoPsp;
	}

	public void setIdentificativoPsp ( String identificativoPsp ) {
		this.identificativoPsp = identificativoPsp;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Integer getModelloPagamentoPsp () {
		return modelloPagamentoPsp;
	}

	public void setModelloPagamentoPsp ( Integer modelloPagamentoPsp ) {
		this.modelloPagamentoPsp = modelloPagamentoPsp;
	}

	public String getRagioneSocialePsp () {
		return ragioneSocialePsp;
	}

	public void setRagioneSocialePsp ( String ragioneSocialePsp ) {
		this.ragioneSocialePsp = ragioneSocialePsp;
	}

	public String getTipoVersamentoPsp () {
		return tipoVersamentoPsp;
	}

	public void setTipoVersamentoPsp ( String tipoVersamentoPsp ) {
		this.tipoVersamentoPsp = tipoVersamentoPsp;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis () {
		return epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis ( List<EpayTRegistroVersamenti> epayTRegistroVersamentis ) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	@Override
	public String toString () {
		return "{ " +
			"idTransazione:" + idTransazione +
			", idGateway:" + idGateway +
			", idInformativaPsp:" + idInformativaPsp +
			", idPaymentMode:" + idPaymentMode +
			", identificativoCanalePsp:" + identificativoCanalePsp +
			", identificativoPsp:" + identificativoPsp +
			", iuv:" + iuv +
			", modelloPagamentoPsp:" + modelloPagamentoPsp +
			", ragioneSocialePsp:" + ragioneSocialePsp +
			", tipoVersamentoPsp:" + tipoVersamentoPsp +
			// non esporre epayTRegistroVersamentis
			" }";
	}
}
