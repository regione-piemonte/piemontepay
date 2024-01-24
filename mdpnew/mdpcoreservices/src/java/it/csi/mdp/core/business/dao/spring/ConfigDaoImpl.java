/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.ConfigDao;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.ConfigPk;
import it.csi.mdp.core.business.exceptions.ConfigDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class ConfigDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Config>, ConfigDao
{
	protected SimpleJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ConfigPk
	 */
	@Transactional
	public ConfigPk insert(Config dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( key, value, descrizione ) VALUES ( ?, ?, ? )",dto.getKey(),dto.getValue(),dto.getDescrizione());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the config table.
	 */
	@Transactional
	public void update(ConfigPk pk, Config dto) throws ConfigDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET key = ?, value = ?, descrizione = ? WHERE key = ?",dto.getKey(),dto.getValue(),dto.getDescrizione(),pk.getKey());
	}

	/** 
	 * Deletes a single row in the config table.
	 */
	@Transactional
	public void delete(ConfigPk pk) throws ConfigDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE key = ?",pk.getKey());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Config
	 */
	public Config mapRow(ResultSet rs, int row) throws SQLException
	{
		Config dto = new Config();
		dto.setKey( rs.getString( 1 ) );
		dto.setValue( rs.getString( 2 ) );
		dto.setDescrizione( rs.getString( 3 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "config";
	}

	/** 
	 * Returns all rows from the config table that match the criteria 'key = :key'.
	 */
	@Transactional
	public Config findByPrimaryKey(String key) throws ConfigDaoException
	{
		try {
			List<Config> list = jdbcTemplate.query("SELECT key, value, descrizione FROM " + getTableName() + " WHERE key = ?", this,key);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new ConfigDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the config table that match the criteria ''.
	 */
	@Transactional
	public List<Config> findAll() throws ConfigDaoException
	{
		try {
			return jdbcTemplate.query("SELECT key, value, descrizione FROM " + getTableName() + " ORDER BY key", this);
		}
		catch (Exception e) {
			throw new ConfigDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the config table that match the criteria 'key = :key'.
	 */
	@Transactional
	public List<Config> findWhereKeyEquals(String key) throws ConfigDaoException
	{
		try {
			return jdbcTemplate.query("SELECT key, value, descrizione FROM " + getTableName() + " WHERE key = ? ORDER BY key", this,key);
		}
		catch (Exception e) {
			throw new ConfigDaoException("Query failed", e);
		}
		
	}





	/** 
	 * Returns the rows from the config table that matches the specified primary-key value.
	 */
	public Config findByPrimaryKey(ConfigPk pk) throws ConfigDaoException
	{
		return findByPrimaryKey( pk.getKey() );
	}

}
