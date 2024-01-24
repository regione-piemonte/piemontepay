/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpBckofficegroupsDao;
import it.csi.mdp.core.business.dto.MdpBckofficegroups;
import it.csi.mdp.core.business.dto.MdpBckofficegroupsPk;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.MdpBckofficegroupsDaoException;
import java.util.List;

public interface MdpBckofficegroupsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return MdpBckofficegroupsPk
	 */
	public MdpBckofficegroupsPk insert(MdpBckofficegroups dto) throws DaoException;

	/** 
	 * Updates a single row in the mdp_bckofficegroups table.
	 */
	public void update(MdpBckofficegroupsPk pk, MdpBckofficegroups dto) throws MdpBckofficegroupsDaoException;

	/** 
	 * Deletes a single row in the mdp_bckofficegroups table.
	 */
	public void delete(MdpBckofficegroupsPk pk) throws MdpBckofficegroupsDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria 'idgroup = :idgroup'.
	 */
	public MdpBckofficegroups findByPrimaryKey(int idgroup) throws MdpBckofficegroupsDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria ''.
	 */
	public List<MdpBckofficegroups> findAll() throws MdpBckofficegroupsDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria 'description = :description'.
	 */
	public List<MdpBckofficegroups> findWhereDescriptionEquals(String description) throws MdpBckofficegroupsDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<MdpBckofficegroups> findWhereIdgroupEquals(int idgroup) throws MdpBckofficegroupsDaoException;

	/** 
	 * Returns the rows from the mdp_bckofficegroups table that matches the specified primary-key value.
	 */
	public MdpBckofficegroups findByPrimaryKey(MdpBckofficegroupsPk pk) throws MdpBckofficegroupsDaoException;

}
