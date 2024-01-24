/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class TransazioneMdp implements Serializable {
	private static final long serialVersionUID = 2433598082848468336L;
	
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
	
	/**
	 * @return the idTransazione
	 */
	public String getIdTransazione() {
		return idTransazione;
	}
	/**
	 * @param idTransazione the idTransazione to set
	 */
	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}
	
	/**
	 * @return the iuv
	 */
	public String getIuv() {
		return iuv;
	}
	/**
	 * @param iuv the iuv to set
	 */
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	/**
	 * @return the identificativoCanalePsp
	 */
	public String getIdentificativoCanalePsp() {
		return identificativoCanalePsp;
	}
	/**
	 * @param identificativoCanalePsp the identificativoCanalePsp to set
	 */
	public void setIdentificativoCanalePsp(String identificativoCanalePsp) {
		this.identificativoCanalePsp = identificativoCanalePsp;
	}
	/**
	 * @return the modelloPagamentoPsp
	 */
	public Integer getModelloPagamentoPsp() {
		return modelloPagamentoPsp;
	}
	/**
	 * @param modelloPagamentoPsp the modelloPagamentoPsp to set
	 */
	public void setModelloPagamentoPsp(Integer modelloPagamentoPsp) {
		this.modelloPagamentoPsp = modelloPagamentoPsp;
	}
	/**
	 * @return the ragioneSocialePsp
	 */
	public String getRagioneSocialePsp() {
		return ragioneSocialePsp;
	}
	/**
	 * @param ragioneSocialePsp the ragioneSocialePsp to set
	 */
	public void setRagioneSocialePsp(String ragioneSocialePsp) {
		this.ragioneSocialePsp = ragioneSocialePsp;
	}
	/**
	 * @return the tipoVersamentoPsp
	 */
	public String getTipoVersamentoPsp() {
		return tipoVersamentoPsp;
	}
	/**
	 * @param tipoVersamentoPsp the tipoVersamentoPsp to set
	 */
	public void setTipoVersamentoPsp(String tipoVersamentoPsp) {
		this.tipoVersamentoPsp = tipoVersamentoPsp;
	}
	/**
	 * @return the idGateway
	 */
	public String getIdGateway() {
		return idGateway;
	}
	/**
	 * @param idGateway the idGateway to set
	 */
	public void setIdGateway(String idGateway) {
		this.idGateway = idGateway;
	}
	/**
	 * @return the idPaymentMode
	 */
	public String getIdPaymentMode() {
		return idPaymentMode;
	}
	/**
	 * @param idPaymentMode the idPaymentMode to set
	 */
	public void setIdPaymentMode(String idPaymentMode) {
		this.idPaymentMode = idPaymentMode;
	}
	/**
	 * @return the idInformativaPsp
	 */
	public Integer getIdInformativaPsp() {
		return idInformativaPsp;
	}
	/**
	 * @param idInformativaPsp the idInformativaPsp to set
	 */
	public void setIdInformativaPsp(Integer idInformativaPsp) {
		this.idInformativaPsp = idInformativaPsp;
	}
	/**
	 * @return the identificativoPsp
	 */
	public String getIdentificativoPsp() {
		return identificativoPsp;
	}
	/**
	 * @param identificativoPsp the identificativoPsp to set
	 */
	public void setIdentificativoPsp(String identificativoPsp) {
		this.identificativoPsp = identificativoPsp;
	}
		
}
