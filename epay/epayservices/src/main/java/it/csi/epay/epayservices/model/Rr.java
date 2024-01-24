/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


public class Rr implements Serializable {
	
	private static final long serialVersionUID = -4798523297268806406L;
	
	private Long idRr;
	private String idDominio;
	private String applicationId;
	private String identificativoMessaggioRevoca;
	private Timestamp dataOraMessaggioRevoca;
	private String codiceIdentificativoUnivocoAttestante;
	private String denominazioneIstitutoAttestante;
	private BigDecimal importoTotaleRevocato;
	private String iuv;
	private String codiceContestoPagamento;
	private String tipoRevoca;
	private byte[] rrXml;
	

	/**
	 * @return the idRr
	 */
	public Long getIdRr() {
		return idRr;
	}
	/**
	 * @param idRr the idRr to set
	 */
	public void setIdRr(Long idRr) {
		this.idRr = idRr;
	}
	/**
	 * @return the idDominio
	 */
	public String getIdDominio() {
		return idDominio;
	}
	/**
	 * @param idDominio the idDominio to set
	 */
	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
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
	 * @return the identificativoMessaggioRevoca
	 */
	public String getIdentificativoMessaggioRevoca() {
		return identificativoMessaggioRevoca;
	}
	/**
	 * @param identificativoMessaggioRevoca the identificativoMessaggioRevoca to set
	 */
	public void setIdentificativoMessaggioRevoca(String identificativoMessaggioRevoca) {
		this.identificativoMessaggioRevoca = identificativoMessaggioRevoca;
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
	 * @return the dataOraMessaggioRevoca
	 */
	public Timestamp getDataOraMessaggioRevoca() {
		return dataOraMessaggioRevoca;
	}
	/**
	 * @param dataOraMessaggioRevoca the dataOraMessaggioRevoca to set
	 */
	public void setDataOraMessaggioRevoca(Timestamp dataOraMessaggioRevoca) {
		this.dataOraMessaggioRevoca = dataOraMessaggioRevoca;
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
	 * @return the tipoRevoca
	 */
	public String getTipoRevoca() {
		return tipoRevoca;
	}
	/**
	 * @param tipoRevoca the tipoRevoca to set
	 */
	public void setTipoRevoca(String tipoRevoca) {
		this.tipoRevoca = tipoRevoca;
	}
	/**
	 * @return the rrXml
	 */
	public byte[] getRrXml() {
		return rrXml;
	}
	/**
	 * @param rrXml the rrXml to set
	 */
	public void setRrXml(byte[] rrXml) {
		this.rrXml = rrXml;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
