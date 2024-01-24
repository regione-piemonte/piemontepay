/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.PdpstatiesteriDao;
import it.csi.mdp.core.business.dto.Pdpstatiesteri;
import it.csi.mdp.core.business.exceptions.PdpstatiesteriDaoException;
import java.util.List;

public interface PdpstatiesteriDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Pdpstatiesteri dto);

	/** 
	 * Returns all rows from the PDPSTATIESTERI table that match the criteria ''.
	 */
	public List<Pdpstatiesteri> findAll() throws PdpstatiesteriDaoException;

	/** 
	 * Returns all rows from the PDPSTATIESTERI table that match the criteria 'ID = :id'.
	 */
	public List<Pdpstatiesteri> findWhereIdEquals(long id) throws PdpstatiesteriDaoException;

	/** 
	 * Returns all rows from the PDPSTATIESTERI table that match the criteria 'STATO = :stato'.
	 */
	public List<Pdpstatiesteri> findWhereStatoEquals(String stato) throws PdpstatiesteriDaoException;

}
