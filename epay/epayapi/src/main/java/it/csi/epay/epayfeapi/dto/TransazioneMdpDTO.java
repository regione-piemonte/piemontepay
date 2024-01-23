/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class TransazioneMdpDTO implements Serializable {

	private static final long serialVersionUID = 6228996575252240332L;

	private String idTransazione;

	private String iuv;

	private String idGateway;

	private String idPaymentMode;

	private Integer idInformativaPsp;

	private String identificativoPsp;

	private String identificativoCanalePsp;

	private Integer modelloPagamentoPsp;

	private String ragioneSocialePsp;

	private String tipoVersamentoPsp;

	public String getIdTransazione () {
		return idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getIdGateway () {
		return idGateway;
	}

	public void setIdGateway ( String idGateway ) {
		this.idGateway = idGateway;
	}

	public String getIdPaymentMode () {
		return idPaymentMode;
	}

	public void setIdPaymentMode ( String idPaymentMode ) {
		this.idPaymentMode = idPaymentMode;
	}

	public Integer getIdInformativaPsp () {
		return idInformativaPsp;
	}

	public void setIdInformativaPsp ( Integer idInformativaPsp ) {
		this.idInformativaPsp = idInformativaPsp;
	}

	public String getIdentificativoPsp () {
		return identificativoPsp;
	}

	public void setIdentificativoPsp ( String identificativoPsp ) {
		this.identificativoPsp = identificativoPsp;
	}

	public String getIdentificativoCanalePsp () {
		return identificativoCanalePsp;
	}

	public void setIdentificativoCanalePsp ( String identificativoCanalePsp ) {
		this.identificativoCanalePsp = identificativoCanalePsp;
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

	@Override public String toString () {
		return "TransazioneMdpDTO{" +
						"idTransazione='" + idTransazione + '\'' +
						", iuv='" + iuv + '\'' +
						", idGateway='" + idGateway + '\'' +
						", idPaymentMode='" + idPaymentMode + '\'' +
						", idInformativaPsp=" + idInformativaPsp +
						", identificativoPsp='" + identificativoPsp + '\'' +
						", identificativoCanalePsp='" + identificativoCanalePsp + '\'' +
						", modelloPagamentoPsp=" + modelloPagamentoPsp +
						", ragioneSocialePsp='" + ragioneSocialePsp + '\'' +
						", tipoVersamentoPsp='" + tipoVersamentoPsp + '\'' +
						'}';
	}
}
