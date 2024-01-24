/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpBckofficeuserappmappingDao;
import it.csi.mdp.core.business.dto.MdpBckofficeuserappmapping;
import it.csi.mdp.core.business.exceptions.MdpBckofficeuserappmappingDaoException;
import java.util.List;

public interface MdpBckofficeuserappmappingDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(MdpBckofficeuserappmapping dto);

	/** 
	 * Returns all rows from the MDP_BCKOFFICEUSERAPPMAPPING table that match the criteria ''.
	 */
	public List<MdpBckofficeuserappmapping> findAll() throws MdpBckofficeuserappmappingDaoException;

	/** 
	 * Returns all rows from the MDP_BCKOFFICEUSERAPPMAPPING table that match the criteria 'IDAPP = :idapp'.
	 */
	public List<MdpBckofficeuserappmapping> findWhereIdappEquals(String idapp) throws MdpBckofficeuserappmappingDaoException;

	/** 
	 * Returns all rows from the MDP_BCKOFFICEUSERAPPMAPPING table that match the criteria 'IDUSER = :iduser'.
	 */
	public List<MdpBckofficeuserappmapping> findWhereIduserEquals(String iduser) throws MdpBckofficeuserappmappingDaoException;

}
