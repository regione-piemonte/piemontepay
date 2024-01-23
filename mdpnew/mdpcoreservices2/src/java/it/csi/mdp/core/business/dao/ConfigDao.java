/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.ConfigDao;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.ConfigPk;
import it.csi.mdp.core.business.exceptions.ConfigDaoException;
import java.util.List;

public interface ConfigDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ConfigPk
	 */
	public ConfigPk insert(Config dto);

	/** 
	 * Updates a single row in the config table.
	 */
	public void update(ConfigPk pk, Config dto) throws ConfigDaoException;

	/** 
	 * Deletes a single row in the config table.
	 */
	public void delete(ConfigPk pk) throws ConfigDaoException;

	/** 
	 * Returns all rows from the config table that match the criteria 'key = :key'.
	 */
	public Config findByPrimaryKey(String key) throws ConfigDaoException;

	/** 
	 * Returns all rows from the config table that match the criteria ''.
	 */
	public List<Config> findAll() throws ConfigDaoException;

	/** 
	 * Returns all rows from the config table that match the criteria 'key = :key'.
	 */
	public List<Config> findWhereKeyEquals(String key) throws ConfigDaoException;



	/** 
	 * Returns the rows from the config table that matches the specified primary-key value.
	 */
	public Config findByPrimaryKey(ConfigPk pk) throws ConfigDaoException;

}
