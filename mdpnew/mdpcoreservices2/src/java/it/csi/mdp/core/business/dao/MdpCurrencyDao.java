/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpCurrencyDao;
import it.csi.mdp.core.business.dto.MdpCurrency;
import it.csi.mdp.core.business.dto.MdpCurrencyPk;
import it.csi.mdp.core.business.exceptions.MdpCurrencyDaoException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface MdpCurrencyDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(MdpCurrency dto);

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria ''.
	 */
	public List<MdpCurrency> findAll() throws MdpCurrencyDaoException;

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GATEWAYID = :gatewayid'.
	 */
	public List<MdpCurrency> findWhereGatewayidEquals(String gatewayid) throws MdpCurrencyDaoException;

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GTWCURRENCYCODE = :gtwcurrencycode'.
	 */
	public List<MdpCurrency> findWhereGtwcurrencycodeEquals(String gtwcurrencycode) throws MdpCurrencyDaoException;

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'MDPCURRENCYCODE = :mdpcurrencycode'.
	 */
	public List<MdpCurrency> findWhereMdpcurrencycodeEquals(String mdpcurrencycode) throws MdpCurrencyDaoException;

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'CURRENCYDESCR = :currencydescr'.
	 */
	public List<MdpCurrency> findWhereCurrencydescrEquals(String currencydescr) throws MdpCurrencyDaoException;

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'ENABLED = :enabled'.
	 */
	public List<MdpCurrency> findWhereEnabledEquals(String enabled) throws MdpCurrencyDaoException;
	
	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GATEWAYID = :gatewayid' and 'MDPCURRENCYCODE = :mdpcurrencycode'.
	 */
	public List<MdpCurrency> findWhereMdpccyAndGatewayIdEquals(String mdpcurrencycode, String gatewayId) throws MdpCurrencyDaoException;
	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GATEWAYID = :gatewayid' and 'GTWCURRENCYCODE = :mdpcurrencycode'.
	 */
	public List<MdpCurrency> findWhereGtwccyAndGatewayIdEquals(String gtwcurrencycode, String gatewayId) throws MdpCurrencyDaoException;
	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the primary key
	 */
	public MdpCurrency findByPrimaryKey(MdpCurrencyPk pk) throws MdpCurrencyDaoException;

	
}
