/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.PeasTableIdDao;
import it.csi.mdp.core.business.dto.PeasTableId;
import it.csi.mdp.core.business.dto.PeasTableIdPk;
import it.csi.mdp.core.business.exceptions.PeasTableIdDaoException;
import java.util.List;

public interface PeasTableIdDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(PeasTableId dto);

	public void update(PeasTableId dto) throws PeasTableIdDaoException;
	
	/** 
	 * Returns all rows from the PEAS_TABLE_ID table that match the criteria ''.
	 */
	public List<PeasTableId> findAll() throws PeasTableIdDaoException;

	/** 
	 * Returns all rows from the PEAS_TABLE_ID table that match the criteria 'TABLE_PK = :tablePk'.
	 */
	public List<PeasTableId> findWhereTablePkEquals(String tablePk) throws PeasTableIdDaoException;

	/** 
	 * Returns all rows from the PEAS_TABLE_ID table that match the criteria 'TABLE_ID = :tableId'.
	 */
	public List<PeasTableId> findWhereTableIdEquals(String tableId) throws PeasTableIdDaoException;

}
