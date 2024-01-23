/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.OptAttrDao;
import it.csi.mdp.core.business.dto.OptAttr;
import it.csi.mdp.core.business.exceptions.OptAttrDaoException;
import java.util.List;

public interface OptAttrDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(OptAttr dto);

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria ''.
	 */
	public List<OptAttr> findAll() throws OptAttrDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'OPT_ATTR_ID = :optAttrId'.
	 */
	public List<OptAttr> findWhereOptAttrIdEquals(long optAttrId) throws OptAttrDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'BUYER_NAME = :buyerName'.
	 */
	public List<OptAttr> findWhereBuyerNameEquals(String buyerName) throws OptAttrDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'BUYER_CODE = :buyerCode'.
	 */
	public List<OptAttr> findWhereBuyerCodeEquals(String buyerCode) throws OptAttrDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	public List<OptAttr> findWhereTransactionIdEquals(String transactionId) throws OptAttrDaoException;

}
