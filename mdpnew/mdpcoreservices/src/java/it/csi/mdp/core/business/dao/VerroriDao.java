/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.VerroriDao;
import it.csi.mdp.core.business.dto.Verrori;
import it.csi.mdp.core.business.exceptions.VerroriDaoException;
import it.csi.mdp.core.business.exceptions.VtransazioneDaoException;

import java.util.Date;
import java.util.List;

public interface VerroriDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */

	public List<Verrori> findAll() throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'application_id = :applicationId'.
	 */
	public List<Verrori> findWhereApplicationIdEquals(String applicationId) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'transaction_id = :transactionId'.
	 */
	public List<Verrori> findWhereTransactionIdEquals(String transactionId) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'data = :data'.
	 */
	public List<Verrori> findWhereDataEquals(Date data) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'descrizione = :descrizione'.
	 */
	public List<Verrori> findWhereDescrizioneEquals(String descrizione) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'atewaypaymodeid = :atewaypaymodeid'.
	 */
	public List<Verrori> findWhereAtewaypaymodeidEquals(String atewaypaymodeid) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'init_date = :initDate'.
	 */
	public List<Verrori> findWhereInitDateEquals(Date initDate) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'start_date = :startDate'.
	 */
	public List<Verrori> findWhereStartDateEquals(Date startDate) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'finish_date = :finishDate'.
	 */
	public List<Verrori> findWhereFinishDateEquals(Date finishDate) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'gateway_id = :gatewayId'.
	 */
	public List<Verrori> findWhereGatewayIdEquals(String gatewayId) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'gateway_description = :gatewayDescription'.
	 */
	public List<Verrori> findWhereGatewayDescriptionEquals(String gatewayDescription) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'paymentmode_description = :paymentmodeDescription'.
	 */
	public List<Verrori> findWherePaymentmodeDescriptionEquals(String paymentmodeDescription) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'cod_stato = :codStato'.
	 */
	public List<Verrori> findWhereCodStatoEquals(long codStato) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'descrizionestatotrans = :descrizionestatotrans'.
	 */
	public List<Verrori> findWhereDescrizionestatotransEquals(String descrizionestatotrans) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'amount = :amount'.
	 */
	public List<Verrori> findWhereAmountEquals(float amount) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'payurl = :payurl'.
	 */
	public List<Verrori> findWherePayurlEquals(String payurl) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'currencydescr = :currencydescr'.
	 */
	public List<Verrori> findWhereCurrencydescrEquals(String currencydescr) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'pgresultcode = :pgresultcode'.
	 */
	public List<Verrori> findWherePgresultcodeEquals(String pgresultcode) throws VerroriDaoException;

	/** 
	 * Returns all rows from the verrori table that match the criteria 'applicationname = :applicationname'.
	 */
	public List<Verrori> findWhereApplicationnameEquals(String applicationname) throws VerroriDaoException;
	public List<Verrori> findWithFilters(String appId, String transactionId, Date datastart, Date dataend, String gatewayId, String idgroup) throws VtransazioneDaoException;
}
