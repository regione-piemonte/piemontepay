/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.VappgroupDao;
import it.csi.mdp.core.business.dto.Vappgroup;
import it.csi.mdp.core.business.exceptions.VappgroupDaoException;
import java.util.List;

public interface VappgroupDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Vappgroup dto);

	/** 
	 * Returns all rows from the vappgroup table that match the criteria ''.
	 */
	public List<Vappgroup> findAll() throws VappgroupDaoException;

	/** 
	 * Returns all rows from the vappgroup table that match the criteria 'id = :id'.
	 */
	public List<Vappgroup> findWhereIdEquals(String id) throws VappgroupDaoException;

	/** 
	 * Returns all rows from the vappgroup table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<Vappgroup> findWhereIdgroupEquals(int idgroup) throws VappgroupDaoException;

}
