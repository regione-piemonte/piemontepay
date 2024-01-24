/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.PaymentGatewayDao;
import it.csi.mdp.core.business.dto.PaymentGateway;
import it.csi.mdp.core.business.exceptions.PaymentGatewayDaoException;
import java.util.List;

public interface PaymentGatewayDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(PaymentGateway dto);

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria ''.
	 */
	public List<PaymentGateway> findAll() throws PaymentGatewayDaoException;

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'PG_ID = :pgId'.
	 */
	public List<PaymentGateway> findWherePgIdEquals(long pgId) throws PaymentGatewayDaoException;

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	public List<PaymentGateway> findWhereTransactionIdEquals(String transactionId) throws PaymentGatewayDaoException;

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'NOME = :nome'.
	 */
	public List<PaymentGateway> findWhereNomeEquals(String nome) throws PaymentGatewayDaoException;

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'COD = :cod'.
	 */
	public List<PaymentGateway> findWhereCodEquals(String cod) throws PaymentGatewayDaoException;

}
