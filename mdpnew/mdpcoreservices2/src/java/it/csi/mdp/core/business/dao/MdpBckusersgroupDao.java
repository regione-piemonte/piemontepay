/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpBckusersgroupDao;
import it.csi.mdp.core.business.dto.MdpBckusersgroup;
import it.csi.mdp.core.business.dto.MdpBckusersgroupPk;
import it.csi.mdp.core.business.exceptions.MdpBckusersgroupDaoException;
import java.util.List;

public interface MdpBckusersgroupDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return MdpBckusersgroupPk
	 */
	public MdpBckusersgroupPk insert(MdpBckusersgroup dto);

	/** 
	 * Updates a single row in the mdp_bckusersgroup table.
	 */
	public void update(MdpBckusersgroupPk pk, MdpBckusersgroup dto) throws MdpBckusersgroupDaoException;

	/** 
	 * Deletes a single row in the mdp_bckusersgroup table.
	 */
	public void delete(MdpBckusersgroupPk pk) throws MdpBckusersgroupDaoException;

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'iduser = :iduser AND idgroup = :idgroup'.
	 */
	public MdpBckusersgroup findByPrimaryKey(int iduser, int idgroup) throws MdpBckusersgroupDaoException;

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria ''.
	 */
	public List<MdpBckusersgroup> findAll() throws MdpBckusersgroupDaoException;

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<MdpBckusersgroup> findByMdpBckofficegroups(int idgroup) throws MdpBckusersgroupDaoException;

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'iduser = :iduser'.
	 */
	public List<MdpBckusersgroup> findByMdpBckusers(int iduser) throws MdpBckusersgroupDaoException;

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'iduser = :iduser'.
	 */
	public List<MdpBckusersgroup> findWhereIduserEquals(int iduser) throws MdpBckusersgroupDaoException;

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<MdpBckusersgroup> findWhereIdgroupEquals(int idgroup) throws MdpBckusersgroupDaoException;

	/** 
	 * Returns the rows from the mdp_bckusersgroup table that matches the specified primary-key value.
	 */
	public MdpBckusersgroup findByPrimaryKey(MdpBckusersgroupPk pk) throws MdpBckusersgroupDaoException;

}
