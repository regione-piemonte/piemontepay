/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class RegistroElaborazioniFault implements Serializable {
	private static final long serialVersionUID = -3494669808338333070L;
	
	private Long idPagamento;
	private String codicePagamentoRifEnte;
	private String codiceMessaggio;
	private String descrizioneMessaggio;

	public RegistroElaborazioniFault() {
	}
	
	public RegistroElaborazioniFault(Long idPagamento, String codicePagamentoRifEnte, String codiceMessaggio, String descrizioneMessaggio) {
		super();
		this.idPagamento = idPagamento;
		this.codicePagamentoRifEnte = codicePagamentoRifEnte;
		this.codiceMessaggio = codiceMessaggio;
		this.descrizioneMessaggio = descrizioneMessaggio;
	}
	/**
	 * @return the idPagamento
	 */
	public Long getIdPagamento() {
		return idPagamento;
	}
	/**
	 * @param idPagamento the idPagamento to set
	 */
	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}
	/**
	 * @return the codicePagamentoRifEnte
	 */
	public String getCodicePagamentoRifEnte() {
		return codicePagamentoRifEnte;
	}
	/**
	 * @param codicePagamentoRifEnte the codicePagamentoRifEnte to set
	 */
	public void setCodicePagamentoRifEnte(String codicePagamentoRifEnte) {
		this.codicePagamentoRifEnte = codicePagamentoRifEnte;
	}
	/**
	 * @return the codiceMessaggio
	 */
	public String getCodiceMessaggio() {
		return codiceMessaggio;
	}
	/**
	 * @param codiceMessaggio the codiceMessaggio to set
	 */
	public void setCodiceMessaggio(String codiceMessaggio) {
		this.codiceMessaggio = codiceMessaggio;
	}
	/**
	 * @return the descrizioneMessaggio
	 */
	public String getDescrizioneMessaggio() {
		return descrizioneMessaggio;
	}
	/**
	 * @param descrizioneMessaggio the descrizioneMessaggio to set
	 */
	public void setDescrizioneMessaggio(String descrizioneMessaggio) {
		this.descrizioneMessaggio = descrizioneMessaggio;
	}
	
}
