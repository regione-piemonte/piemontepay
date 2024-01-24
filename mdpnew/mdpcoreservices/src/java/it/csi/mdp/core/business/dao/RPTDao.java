/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.util.Date;
import java.util.List;

public interface RPTDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(RPT dto) throws DaoException;
	/**
	 * Method 'update'
	 * 
	 * @param dto
	 */
	
	public void update(RPT dto) throws DaoException;
	/**
	 * Method 'update'
	 * 
	 * @param dto
	 */
	
	public void delete(Integer id) throws DaoException;
	
	public List<RPT> findAll() throws DaoException;
	
	public List<RPT> findWhereFiltro(RPT filtro) throws DaoException ;

	public List<RPT> getRPTByParam( Integer id ,
									String applicationId, 
									String transactionId, 
									Date lastUpdateDa, 
									Date lastUpdateA, 
									Date insertDateDa,
									Date insertDateA,
									String iuv, 
									String idStatiRpt ,
									String idMsgRichiesta) throws DaoException;

	public int findCountForIdDominioAndIuv(String identificativoDominio, String iuv);
	
	public int findNexValueIdDominio ();
}







