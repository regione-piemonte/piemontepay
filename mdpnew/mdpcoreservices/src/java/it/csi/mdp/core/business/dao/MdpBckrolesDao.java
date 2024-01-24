/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpBckrolesDao;
import it.csi.mdp.core.business.dto.MdpBckroles;
import it.csi.mdp.core.business.dto.MdpBckrolesPk;
import it.csi.mdp.core.business.exceptions.MdpBckrolesDaoException;
import java.util.List;

public interface MdpBckrolesDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return MdpBckrolesPk
	 */
	public MdpBckrolesPk insert(MdpBckroles dto);

	/** 
	 * Updates a single row in the mdp_bckroles table.
	 */
	public void update(MdpBckrolesPk pk, MdpBckroles dto) throws MdpBckrolesDaoException;

	/** 
	 * Deletes a single row in the mdp_bckroles table.
	 */
	public void delete(MdpBckrolesPk pk) throws MdpBckrolesDaoException;

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria 'idrole = :idrole'.
	 */
	public MdpBckroles findByPrimaryKey(int idrole) throws MdpBckrolesDaoException;

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria ''.
	 */
	public List<MdpBckroles> findAll() throws MdpBckrolesDaoException;

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria 'idrole = :idrole'.
	 */
	public List<MdpBckroles> findWhereIdroleEquals(String idrole) throws MdpBckrolesDaoException;

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria 'roledescr = :roledescr'.
	 */
	public List<MdpBckroles> findWhereRoledescrEquals(String roledescr) throws MdpBckrolesDaoException;

	/** 
	 * Returns the rows from the mdp_bckroles table that matches the specified primary-key value.
	 */
	public MdpBckroles findByPrimaryKey(MdpBckrolesPk pk) throws MdpBckrolesDaoException;

}
