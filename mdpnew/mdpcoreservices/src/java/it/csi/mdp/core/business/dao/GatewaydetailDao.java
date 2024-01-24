/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.GatewaydetailDao;
import it.csi.mdp.core.business.dto.Gatewaydetail;
import it.csi.mdp.core.business.dto.GatewaydetailPk;
import it.csi.mdp.core.business.exceptions.GatewaydetailDaoException;
import java.util.List;

public interface GatewaydetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return GatewaydetailPk
	 */
	public GatewaydetailPk insert(Gatewaydetail dto);

	/** 
	 * Updates a single row in the GATEWAYDETAIL table.
	 */
	public void update(GatewaydetailPk pk, Gatewaydetail dto) throws GatewaydetailDaoException;

	/** 
	 * Deletes a single row in the GATEWAYDETAIL table.
	 */
	public void delete(GatewaydetailPk pk) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'GATEWAY_ID = :gatewayId AND PAYMENTMODE_ID = :paymentmodeId'.
	 */
	public Gatewaydetail findByPrimaryKey(String gatewayId, String paymentmodeId) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria ''.
	 */
	public List<Gatewaydetail> findAll() throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	public List<Gatewaydetail> findWhereGatewayIdEquals(String gatewayId) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'PAYMENTMODE_ID = :paymentmodeId'.
	 */
	public List<Gatewaydetail> findWherePaymentmodeIdEquals(String paymentmodeId) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'DEFAULTPAYMENTURL = :defaultpaymenturl'.
	 */
	public List<Gatewaydetail> findWhereDefaultpaymenturlEquals(String defaultpaymenturl) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'BACKOFFICEURL = :backofficeurl'.
	 */
	public List<Gatewaydetail> findWhereBackofficeurlEquals(String backofficeurl) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'MDPGATEWAYPAGE = :mdpgatewaypage'.
	 */
	public List<Gatewaydetail> findWhereMdpgatewaypageEquals(String mdpgatewaypage) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'HTTPPORT = :httpport'.
	 */
	public List<Gatewaydetail> findWhereHttpportEquals(long httpport) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'CONTEXTPG = :contextpg'.
	 */
	public List<Gatewaydetail> findWhereContextpgEquals(String contextpg) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'RETURNURLMDP = :returnurlmdp'.
	 */
	public List<Gatewaydetail> findWhereReturnurlmdpEquals(String returnurlmdp) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'RECEIPTURL = :receipturl'.
	 */
	public List<Gatewaydetail> findWhereReceipturlEquals(String receipturl) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'ERRORURL = :errorurl'.
	 */
	public List<Gatewaydetail> findWhereErrorurlEquals(String errorurl) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'SENDMAILONRESPONSE = :sendmailonresponse'.
	 */
	public List<Gatewaydetail> findWhereSendmailonresponseEquals(String sendmailonresponse) throws GatewaydetailDaoException;

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'ENABLED = :enabled'.
	 */
	public List<Gatewaydetail> findWhereEnabledEquals(String enabled) throws GatewaydetailDaoException;

	/** 
	 * Returns the rows from the GATEWAYDETAIL table that matches the specified primary-key value.
	 */
	public Gatewaydetail findByPrimaryKey(GatewaydetailPk pk) throws GatewaydetailDaoException;

}
