/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


public class Er implements Serializable {
	
	private static final long serialVersionUID = -4798523297268806406L;
	
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
	/**
	 * @return the idEr
	 */
	public Integer getIdEr() {
		return idEr;
	}
	/**
	 * @param idEr the idEr to set
	 */
	public void setIdEr(Integer idEr) {
		this.idEr = idEr;
	}
	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the codiceContestoPagamento
	 */
	public String getCodiceContestoPagamento() {
		return codiceContestoPagamento;
	}
	/**
	 * @param codiceContestoPagamento the codiceContestoPagamento to set
	 */
	public void setCodiceContestoPagamento(String codiceContestoPagamento) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}
	/**
	 * @return the codiceIdentificativoUnivocoAttestante
	 */
	public String getCodiceIdentificativoUnivocoAttestante() {
		return codiceIdentificativoUnivocoAttestante;
	}
	/**
	 * @param codiceIdentificativoUnivocoAttestante the codiceIdentificativoUnivocoAttestante to set
	 */
	public void setCodiceIdentificativoUnivocoAttestante(String codiceIdentificativoUnivocoAttestante) {
		this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
	}
	/**
	 * @return the dataOraMessaggioEsito
	 */
	public Timestamp getDataOraMessaggioEsito() {
		return dataOraMessaggioEsito;
	}
	/**
	 * @param dataOraMessaggioEsito the dataOraMessaggioEsito to set
	 */
	public void setDataOraMessaggioEsito(Timestamp dataOraMessaggioEsito) {
		this.dataOraMessaggioEsito = dataOraMessaggioEsito;
	}
	/**
	 * @return the denominazioneIstitutoAttestante
	 */
	public String getDenominazioneIstitutoAttestante() {
		return denominazioneIstitutoAttestante;
	}
	/**
	 * @param denominazioneIstitutoAttestante the denominazioneIstitutoAttestante to set
	 */
	public void setDenominazioneIstitutoAttestante(String denominazioneIstitutoAttestante) {
		this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
	}
	/**
	 * @return the identificativoDominio
	 */
	public String getIdentificativoDominio() {
		return identificativoDominio;
	}
	/**
	 * @param identificativoDominio the identificativoDominio to set
	 */
	public void setIdentificativoDominio(String identificativoDominio) {
		this.identificativoDominio = identificativoDominio;
	}
	/**
	 * @return the identificativoMessaggioEsito
	 */
	public String getIdentificativoMessaggioEsito() {
		return identificativoMessaggioEsito;
	}
	/**
	 * @param identificativoMessaggioEsito the identificativoMessaggioEsito to set
	 */
	public void setIdentificativoMessaggioEsito(String identificativoMessaggioEsito) {
		this.identificativoMessaggioEsito = identificativoMessaggioEsito;
	}
	/**
	 * @return the importoTotaleRevocato
	 */
	public BigDecimal getImportoTotaleRevocato() {
		return importoTotaleRevocato;
	}
	/**
	 * @param importoTotaleRevocato the importoTotaleRevocato to set
	 */
	public void setImportoTotaleRevocato(BigDecimal importoTotaleRevocato) {
		this.importoTotaleRevocato = importoTotaleRevocato;
	}
	/**
	 * @return the invioOkRispostaRevoca
	 */
	public Boolean getInvioOkRispostaRevoca() {
		return invioOkRispostaRevoca;
	}
	/**
	 * @param invioOkRispostaRevoca the invioOkRispostaRevoca to set
	 */
	public void setInvioOkRispostaRevoca(Boolean invioOkRispostaRevoca) {
		this.invioOkRispostaRevoca = invioOkRispostaRevoca;
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
	 * @return the riferimentoDataRevoca
	 */
	public Timestamp getRiferimentoDataRevoca() {
		return riferimentoDataRevoca;
	}
	/**
	 * @param riferimentoDataRevoca the riferimentoDataRevoca to set
	 */
	public void setRiferimentoDataRevoca(Timestamp riferimentoDataRevoca) {
		this.riferimentoDataRevoca = riferimentoDataRevoca;
	}
	/**
	 * @return the riferimentoMessaggioRevoca
	 */
	public String getRiferimentoMessaggioRevoca() {
		return riferimentoMessaggioRevoca;
	}
	/**
	 * @param riferimentoMessaggioRevoca the riferimentoMessaggioRevoca to set
	 */
	public void setRiferimentoMessaggioRevoca(String riferimentoMessaggioRevoca) {
		this.riferimentoMessaggioRevoca = riferimentoMessaggioRevoca;
	}
	/**
	 * @return the xmlEr
	 */
	public byte[] getXmlEr() {
		return xmlEr;
	}
	/**
	 * @param xmlEr the xmlEr to set
	 */
	public void setXmlEr(byte[] xmlEr) {
		this.xmlEr = xmlEr;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
