/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.sql.Timestamp;
import java.util.Date;

public class RPTDTO extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7276534864983331908L;
	Integer id;
	  String applicationId;
	  String transactionId;
	  Date insertDate;
	  Date lastUpdate;
	  String authSoggetto;
	  Date dataMsgRichiesta;
	  String idCanale;
	  String idIntermPsp;
	  String idMsgRichiesta;
	  String idPsp ;
	  String identificativoDominio;
	  String identificativoIntermediarioPa;
	  String identificativoStazioneIntermediarioPa;
	  String rptXml;
	  String iuv ;
	  Integer idStatiRpt;
	  String descStatoRpt;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	/**
	 * @return the authSoggetto
	 */
	public String getAuthSoggetto() {
		return authSoggetto;
	}
	/**
	 * @param authSoggetto the authSoggetto to set
	 */
	public void setAuthSoggetto(String authSoggetto) {
		this.authSoggetto = authSoggetto;
	}
	/**
	 * @return the dataMsgRichiesta
	 */
	public Date getDataMsgRichiesta() {
		return dataMsgRichiesta;
	}
	/**
	 * @param dataMsgRichiesta the dataMsgRichiesta to set
	 */
	public void setDataMsgRichiesta(Date dataMsgRichiesta) {
		this.dataMsgRichiesta = dataMsgRichiesta;
	}
	/**
	 * @return the idCanale
	 */
	public String getIdCanale() {
		return idCanale;
	}
	/**
	 * @param idCanale the idCanale to set
	 */
	public void setIdCanale(String idCanale) {
		this.idCanale = idCanale;
	}
	/**
	 * @return the idIntermPsp
	 */
	public String getIdIntermPsp() {
		return idIntermPsp;
	}
	/**
	 * @param idIntermPsp the idIntermPsp to set
	 */
	public void setIdIntermPsp(String idIntermPsp) {
		this.idIntermPsp = idIntermPsp;
	}
	/**
	 * @return the idMsgRichiesta
	 */
	public String getIdMsgRichiesta() {
		return idMsgRichiesta;
	}
	/**
	 * @param idMsgRichiesta the idMsgRichiesta to set
	 */
	public void setIdMsgRichiesta(String idMsgRichiesta) {
		this.idMsgRichiesta = idMsgRichiesta;
	}
	/**
	 * @return the idPsp
	 */
	public String getIdPsp() {
		return idPsp;
	}
	/**
	 * @param idPsp the idPsp to set
	 */
	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
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
	 * @return the identificativoIntermediarioPa
	 */
	public String getIdentificativoIntermediarioPa() {
		return identificativoIntermediarioPa;
	}
	/**
	 * @param identificativoIntermediarioPa the identificativoIntermediarioPa to set
	 */
	public void setIdentificativoIntermediarioPa(String identificativoIntermediarioPa) {
		this.identificativoIntermediarioPa = identificativoIntermediarioPa;
	}
	/**
	 * @return the identificativoStazioneIntermediarioPa
	 */
	public String getIdentificativoStazioneIntermediarioPa() {
		return identificativoStazioneIntermediarioPa;
	}
	/**
	 * @param identificativoStazioneIntermediarioPa the identificativoStazioneIntermediarioPa to set
	 */
	public void setIdentificativoStazioneIntermediarioPa(String identificativoStazioneIntermediarioPa) {
		this.identificativoStazioneIntermediarioPa = identificativoStazioneIntermediarioPa;
	}
	/**
	 * @return the rptXml
	 */
	public String getRptXml() {
		return rptXml;
	}
	/**
	 * @param rptXml the rptXml to set
	 */
	public void setRptXml(String rptXml) {
		this.rptXml = rptXml;
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
	 * @return the id_stati_rpt
	 */
	public Integer getIdStatiRpt() {
		return idStatiRpt;
	}
	/**
	 * @param id_stati_rpt the id_stati_rpt to set
	 */
	public void setIdStatiRpt(Integer idStatiRpt) {
		this.idStatiRpt = idStatiRpt;
	}
	/**
	 * @return the descStatoRpt
	 */
	public String getDescStatoRpt() {
		return descStatoRpt;
	}
	/**
	 * @param descStatoRpt the descStatoRpt to set
	 */
	public void setDescStatoRpt(String descStatoRpt) {
		this.descStatoRpt = descStatoRpt;
	}
	
}
