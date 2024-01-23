/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.PaymentmodeDao;
import it.csi.mdp.core.business.dto.Paymentmode;
import it.csi.mdp.core.business.dto.PaymentmodePk;
import it.csi.mdp.core.business.exceptions.PaymentmodeDaoException;
import java.util.Date;
import java.util.List;

public interface PaymentmodeDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PaymentmodePk
	 */
	public PaymentmodePk insert(Paymentmode dto);

	/** 
	 * Updates a single row in the PAYMENTMODE table.
	 */
	public void update(PaymentmodePk pk, Paymentmode dto) throws PaymentmodeDaoException;

	/** 
	 * Deletes a single row in the PAYMENTMODE table.
	 */
	public void delete(PaymentmodePk pk) throws PaymentmodeDaoException;

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'PAYMENTMODE_ID = :paymentmodeId'.
	 */
	public Paymentmode findByPrimaryKey(String paymentmodeId) throws PaymentmodeDaoException;

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria ''.
	 */
	public List<Paymentmode> findAll() throws PaymentmodeDaoException;

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'PAYMENTMODE_ID = :paymentmodeId'.
	 */
	public List<Paymentmode> findWherePaymentmodeIdEquals(String paymentmodeId) throws PaymentmodeDaoException;

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'PAYMENTMODE_DESCRIPTION = :paymentmodeDescription'.
	 */
	public List<Paymentmode> findWherePaymentmodeDescriptionEquals(String paymentmodeDescription) throws PaymentmodeDaoException;

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'VALIDO_DAL = :validoDal'.
	 */
	public List<Paymentmode> findWhereValidoDalEquals(Date validoDal) throws PaymentmodeDaoException;

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'VALIDO_AL = :validoAl'.
	 */
	public List<Paymentmode> findWhereValidoAlEquals(Date validoAl) throws PaymentmodeDaoException;

	/** 
	 * Returns the rows from the PAYMENTMODE table that matches the specified primary-key value.
	 */
	public Paymentmode findByPrimaryKey(PaymentmodePk pk) throws PaymentmodeDaoException;

}
