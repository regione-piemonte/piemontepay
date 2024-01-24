/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.util.Date;
import java.util.List;

public interface RTDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(RT dto) throws DaoException;
	/**
	 * Method 'update'
	 * 
	 * @param dto
	 */
	
	public void update(RT dto) throws DaoException;
	/**
	 * Method 'update'
	 * 
	 * @param dto
	 */
	
	public void delete(Integer id) throws DaoException;
	
	public void updateIdRrByIuv(String iuv, Integer idRR) throws DaoException;
	
	
	public List<RT> findAll() throws DaoException;

	
	public List<RT> findWhereKeyidEquals(Integer id) throws DaoException;
	
	public List<RT> findWhereApplicationIdEquals(String applicationId) throws DaoException;
	
	public List<RT> findWhereTransactionIdEquals(String transactionId) throws DaoException;
	
	public List<RT> findWhereIUVEquals(String iuv) throws DaoException;

	public List<RT> getRTByParam(
			Integer id ,
			String applicationId,
			String transactionId,
			Date lastUpdateDa, 
			Date lastUpdateA, 
			Date insertDateDa,
			Date insertDateA,
			String iuv,
			String idEsitoPagamento,
			String idMsgRichiesta
			) throws DaoException;
	
	public void insertCoda(String iuv, String applicationId, String transactionId) throws DaoException;
}
