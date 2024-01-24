/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpErroriDao;
import it.csi.mdp.core.business.dto.MdpErrori;
import it.csi.mdp.core.business.dto.MdpErroriPk;
import it.csi.mdp.core.business.exceptions.MdpErroriDaoException;
import java.util.Date;
import java.util.List;

public interface MdpErroriDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return MdpErroriPk
	 */
	public MdpErroriPk insert(MdpErrori dto);

	/** 
	 * Updates a single row in the mdp_errori table.
	 */
//	public void update(MdpErroriPk pk, MdpErrori dto) throws MdpErroriDaoException;
//
//	/** 
//	 * Deletes a single row in the mdp_errori table.
//	 */
//	public void delete(MdpErroriPk pk) throws MdpErroriDaoException;

	/** 
	 * Returns all rows from the mdp_errori table that match the criteria 'id = :id'.
	 */
	public MdpErrori findByPrimaryKey(int id) throws MdpErroriDaoException;


	/** 
	 * Returns the rows from the mdp_errori table that matches the specified primary-key value.
	 */
	public MdpErrori findByPrimaryKey(MdpErroriPk pk) throws MdpErroriDaoException;
	
	public List<MdpErrori> findAll() throws MdpErroriDaoException;

}
