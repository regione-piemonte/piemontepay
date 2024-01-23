/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.BckofficegroupsDao;
import it.csi.mdp.core.business.dto.Bckofficegroups;
import it.csi.mdp.core.business.exceptions.BckofficegroupsDaoException;
import java.util.List;

public interface BckofficegroupsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Bckofficegroups dto);

	/** 
	 * Returns all rows from the BCKOFFICEGROUPS table that match the criteria ''.
	 */
	public List<Bckofficegroups> findAll() throws BckofficegroupsDaoException;

	/** 
	 * Returns all rows from the BCKOFFICEGROUPS table that match the criteria 'ID = :id'.
	 */
	public List<Bckofficegroups> findWhereIdEquals(long id) throws BckofficegroupsDaoException;

	/** 
	 * Returns all rows from the BCKOFFICEGROUPS table that match the criteria 'DESCRIPTION = :description'.
	 */
	public List<Bckofficegroups> findWhereDescriptionEquals(String description) throws BckofficegroupsDaoException;

}
