/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpBckofficegroupappmappingDao;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmapping;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmappingPk;
import it.csi.mdp.core.business.exceptions.MdpBckofficegroupappmappingDaoException;
import java.util.List;

public interface MdpBckofficegroupappmappingDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return MdpBckofficegroupappmappingPk
	 */
	public MdpBckofficegroupappmappingPk insert(MdpBckofficegroupappmapping dto);

	/** 
	 * Updates a single row in the mdp_bckofficegroupappmapping table.
	 */
	public void update(MdpBckofficegroupappmappingPk pk, MdpBckofficegroupappmapping dto) throws MdpBckofficegroupappmappingDaoException;

	/** 
	 * Deletes a single row in the mdp_bckofficegroupappmapping table.
	 */
	public void delete(MdpBckofficegroupappmappingPk pk) throws MdpBckofficegroupappmappingDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idapp = :idapp AND idgroup = :idgroup'.
	 */
	public MdpBckofficegroupappmapping findByPrimaryKey(String idapp, int idgroup) throws MdpBckofficegroupappmappingDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria ''.
	 */
	public List<MdpBckofficegroupappmapping> findAll() throws MdpBckofficegroupappmappingDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<MdpBckofficegroupappmapping> findByMdpBckofficegroups(int idgroup) throws MdpBckofficegroupappmappingDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idapp = :idapp'.
	 */
	public List<MdpBckofficegroupappmapping> findWhereIdappEquals(String idapp) throws MdpBckofficegroupappmappingDaoException;

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idgroup = :idgroup'.
	 */
	public List<MdpBckofficegroupappmapping> findWhereIdgroupEquals(int idgroup) throws MdpBckofficegroupappmappingDaoException;

	/** 
	 * Returns the rows from the mdp_bckofficegroupappmapping table that matches the specified primary-key value.
	 */
	public MdpBckofficegroupappmapping findByPrimaryKey(MdpBckofficegroupappmappingPk pk) throws MdpBckofficegroupappmappingDaoException;

}
