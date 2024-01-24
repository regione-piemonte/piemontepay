/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpBckrolesgroupmapDao;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmap;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmapPk;
import it.csi.mdp.core.business.exceptions.MdpBckrolesgroupmapDaoException;
import java.util.List;

public interface MdpBckrolesgroupmapDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return MdpBckrolesgroupmapPk
	 */
	public MdpBckrolesgroupmapPk insert(MdpBckrolesgroupmap dto);

	/** 
	 * Updates a single row in the mdp_bckrolesgroupmap table.
	 */
	public void update(MdpBckrolesgroupmapPk pk, MdpBckrolesgroupmap dto) throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Deletes a single row in the mdp_bckrolesgroupmap table.
	 */
	public void delete(MdpBckrolesgroupmapPk pk) throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idrole = :idrole AND idgroup = :idgroup'.
	 */
	public MdpBckrolesgroupmap findByPrimaryKey(int idrole, int idgroup) throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria ''.
	 */
	public List<MdpBckrolesgroupmap> findAll() throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<MdpBckrolesgroupmap> findByMdpBckofficegroups(int idgroup) throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idrole = :idrole'.
	 */
	public List<MdpBckrolesgroupmap> findByMdpBckroles(int idrole) throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idrole = :idrole'.
	 */
	public List<MdpBckrolesgroupmap> findWhereIdroleEquals(int idrole) throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<MdpBckrolesgroupmap> findWhereIdgroupEquals(int idgroup) throws MdpBckrolesgroupmapDaoException;

	/** 
	 * Returns the rows from the mdp_bckrolesgroupmap table that matches the specified primary-key value.
	 */
	public MdpBckrolesgroupmap findByPrimaryKey(MdpBckrolesgroupmapPk pk) throws MdpBckrolesgroupmapDaoException;

}
