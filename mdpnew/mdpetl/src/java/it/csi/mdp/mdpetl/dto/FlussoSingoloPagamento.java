/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class FlussoSingoloPagamento extends BaseDTO {

	private static final long serialVersionUID = 1046749593678092606L;

	private Integer idFlusso;

	private String iuv;

	private Integer idRPT;
    
    private Integer idReceipt;

	private String identificativoUnivocoRiscossione;

	private BigDecimal singoloImportoPagato;

	private String codiceEsitoSingoloPagamento;

	private String applicationId;

	private Timestamp dataEsitoSingoloPagamento;

	private Timestamp dataInserimento;

	private Timestamp dataModifica;

	private Integer indiceDatiSingoloPagamento;

	private String identificativoPsp;

	private String denominazioneMittente;

	private String identificativoIstitutoMittente;

	private String transactionId;

	private String esitoUltimoInvioAFruitore;

	private Timestamp dataUltimoInvioAFruitore;

	private String msgUltimoEsitoInvioAFruitore;

	private String tipoRPT;

	/*
	 * campo gp.
	 */
	private Integer idGetpayment;

	private Integer id;

	public String getTipoRPT () {
		return tipoRPT;
	}

	public void setTipoRPT ( String tipoRPT ) {
		this.tipoRPT = tipoRPT;
	}

	public Integer getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( Integer idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getIdentificativoUnivocoRiscossione () {
		return identificativoUnivocoRiscossione;
	}

	public void setIdentificativoUnivocoRiscossione ( String identificativoUnivocoRiscossione ) {
		this.identificativoUnivocoRiscossione = identificativoUnivocoRiscossione;
	}

	public BigDecimal getSingoloImportoPagato () {
		return singoloImportoPagato;
	}

	public void setSingoloImportoPagato ( BigDecimal singoloImportoPagato ) {
		this.singoloImportoPagato = singoloImportoPagato;
	}

	public String getCodiceEsitoSingoloPagamento () {
		return codiceEsitoSingoloPagamento;
	}

	public void setCodiceEsitoSingoloPagamento ( String codiceEsitoSingoloPagamento ) {
		this.codiceEsitoSingoloPagamento = codiceEsitoSingoloPagamento;
	}

	public Timestamp getDataEsitoSingoloPagamento () {
		return dataEsitoSingoloPagamento;
	}

	public void setDataEsitoSingoloPagamento ( Timestamp dataEsitoSingoloPagamento ) {
		this.dataEsitoSingoloPagamento = dataEsitoSingoloPagamento;
	}

	public Timestamp getDataInserimento () {
		return dataInserimento;
	}

	public void setDataInserimento ( Timestamp dataInserimento ) {
		this.dataInserimento = dataInserimento;
	}

	public Timestamp getDataModifica () {
		return dataModifica;
	}

	public void setDataModifica ( Timestamp dataModifica ) {
		this.dataModifica = dataModifica;
	}

	public String getApplicationId () {
		return applicationId;
	}

	public void setApplicationId ( String applicationId ) {
		this.applicationId = applicationId;
	}

	public Integer getIndiceDatiSingoloPagamento () {
		return indiceDatiSingoloPagamento;
	}

	public void setIndiceDatiSingoloPagamento ( Integer indiceDatiSingoloPagamento ) {
		this.indiceDatiSingoloPagamento = indiceDatiSingoloPagamento;
	}

	/**
	 * @return the idRPT
	 */
	public Integer getIdRPT () {
		return idRPT;
	}

	/**
	 * @param idRPT the idRPT to set
	 */
	public void setIdRPT ( Integer idRPT ) {
		this.idRPT = idRPT;
	}

	/**
	 * @return the identificativoPsp
	 */
	public String getIdentificativoPsp () {
		return identificativoPsp;
	}

	/**
	 * @return the denominazioneMittente
	 */
	public String getDenominazioneMittente () {
		return denominazioneMittente;
	}

	/**
	 * @return the identificativoIstitutoMittente
	 */
	public String getIdentificativoIstitutoMittente () {
		return identificativoIstitutoMittente;
	}

	/**
	 * @param identificativoPsp the identificativoPsp to set
	 */
	public void setIdentificativoPsp ( String identificativoPsp ) {
		this.identificativoPsp = identificativoPsp;
	}

	/**
	 * @param denominazioneMittente the denominazioneMittente to set
	 */
	public void setDenominazioneMittente ( String denominazioneMittente ) {
		this.denominazioneMittente = denominazioneMittente;
	}

	/**
	 * @param identificativoIstitutoMittente the identificativoIstitutoMittente to set
	 */
	public void setIdentificativoIstitutoMittente ( String identificativoIstitutoMittente ) {
		this.identificativoIstitutoMittente = identificativoIstitutoMittente;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId () {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId ( String transactionId ) {
		this.transactionId = transactionId;
	}

	public String getEsitoUltimoInvioAFruitore () {
		return esitoUltimoInvioAFruitore;
	}

	public void setEsitoUltimoInvioAFruitore ( String esitoUltimoInvioAFruitore ) {
		this.esitoUltimoInvioAFruitore = esitoUltimoInvioAFruitore;
	}

	public Timestamp getDataUltimoInvioAFruitore () {
		return dataUltimoInvioAFruitore;
	}

	public void setDataUltimoInvioAFruitore ( Timestamp dataUltimoInvioAFruitore ) {
		this.dataUltimoInvioAFruitore = dataUltimoInvioAFruitore;
	}

	public String getMsgUltimoEsitoInvioAFruitore () {
		return msgUltimoEsitoInvioAFruitore;
	}

	public void setMsgUltimoEsitoInvioAFruitore ( String msgUltimoEsitoInvioAFruitore ) {
		this.msgUltimoEsitoInvioAFruitore = msgUltimoEsitoInvioAFruitore;
	}

	public Integer getIdGetpayment () {
		return idGetpayment;
	}

	public void setIdGetpayment ( Integer idGetpayment ) {
		this.idGetpayment = idGetpayment;
	}

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}
}
