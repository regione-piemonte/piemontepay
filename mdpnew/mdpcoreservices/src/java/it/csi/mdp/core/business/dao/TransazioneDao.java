/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.TransazioneDao;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazionePk;
import it.csi.mdp.core.business.exceptions.TransazioneDaoException;
import java.util.Date;
import java.util.List;

public interface TransazioneDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public TransazionePk insert(Transazione dto);

	public List<Transazione> recuperaApplicationIdFromIuv(String iuv) throws TransazioneDaoException;
	
	/** 
	 * Updates a single row in the TRANSAZIONE table.
	 */
	public void update(TransazionePk pk, Transazione dto) throws TransazioneDaoException;

	/** 
	 * Deletes a single row in the TRANSAZIONE table.
	 */
	public void delete(TransazionePk pk) throws TransazioneDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	public Transazione findByPrimaryKey(String transactionId,String idgroup) throws TransazioneDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria ''.
	 */
	public List<Transazione> findAll(String idgroup) throws TransazioneDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	public List<Transazione> findWhereTransactionIdEquals(String transactionId,String idgroup) throws TransazioneDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'APPLICATION_ID = :applicationId'.
	 */
	public List<Transazione> findWhereApplicationIdEquals(String applicationId,String idgroup) throws TransazioneDaoException;


	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'BASKET_ID = :basketId'.
	 */
	public List<Transazione> findWhereBasketIdEquals(String basketId,String idgroup) throws TransazioneDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'COD_STATO = :codStato'.
	 */
	public List<Transazione> findWhereCodStatoEquals(long codStato,String idgroup) throws TransazioneDaoException;


	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	public List<Transazione> findWhereGatewayIdEquals(String gatewayId,String idgroup) throws TransazioneDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'GATEWAYPAYMODEID = :gatewaypaymodeid'.
	 */
	public List<Transazione> findWhereGatewaypaymodeidEquals(String gatewaypaymodeid,String idgroup) throws TransazioneDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'MERCHANT_ID = :merchantId'.
	 */
	public List<Transazione> findWhereMerchantIdEquals(String merchantId,String idgroup) throws TransazioneDaoException;
	
	public List<Transazione> findWhereAuthorNumberEquals(String authorNumber,String idgroup) throws TransazioneDaoException;
	/** 
	 * Returns the rows from the TRANSAZIONE table that matches the specified primary-key value.
	 */
	public Transazione findByPrimaryKey(TransazionePk pk,String idgroup) throws TransazioneDaoException;
	public List<Transazione> findWherePaymentidEquals(String paymodeid,String idgroup) throws TransazioneDaoException;
	
	public List<Transazione> findWhereApplicationAndGatewayEquals(String applicationid,String gatewayid,String idgroup) throws TransazioneDaoException;
	public List<Transazione> findWhereApplicationAndGatewayAndPaymodeEquals(String applicationid,String gatewayid,String paymode,String idgroup) throws TransazioneDaoException;
	public List<Transazione> findWhereGatewayidAndPaymodeidEquals(String gatewayid, String gatewaypaymodeid,String idgroup) throws TransazioneDaoException;
	public long getNextTransazioneId()  throws TransazioneDaoException;
	public Transazione getTransazioneByIdSession(String idSession)  throws TransazioneDaoException;

	public void updateIdSession(String transacionId, String idSession) throws TransazioneDaoException;
	
	public void insertTransazioneIuv(String transacionId, String iuv) throws TransazioneDaoException;
	//public List<StatisticaApplicazioneTransazione> getStatisticaTransazioneXApp(String transacionId, Date dataDa ,Date dataA) throws TransazioneDaoException;
	
//	select 
//	--transaction_id,
//	application_id,
//	cod_stato,
//	count (cod_stato)
//	from 
//	transazione 
//	--where application_id = 'SICEE_CERT_ENERG_WEB'
//	group by application_id,cod_stato
//	order by application_id,cod_stato
	
}
