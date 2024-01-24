/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.LanguageDao;
import it.csi.mdp.core.business.dto.Language;
import it.csi.mdp.core.business.exceptions.LanguageDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class LanguageDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Language>, LanguageDao
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
	 */
	@Transactional
	public void insert(Language dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED ) VALUES ( ?, ?, ?, ?, ? )",dto.getGatewayid(),dto.getGtwlanguagecode(),dto.getMdplanguagecode(),dto.getLanguagedescr(),dto.getEnabled());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Language
	 */
	public Language mapRow(ResultSet rs, int row) throws SQLException
	{
		Language dto = new Language();
		dto.setGatewayid( rs.getString( 1 ) );
		dto.setGtwlanguagecode( rs.getString( 2 ) );
		dto.setMdplanguagecode( rs.getString( 3 ) );
		dto.setLanguagedescr( rs.getString( 4 ) );
		dto.setEnabled( rs.getString( 5 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "LANGUAGE";
	}

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria ''.
	 */
	@Transactional
	public List<Language> findAll() throws LanguageDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new LanguageDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'GATEWAYID = :gatewayid'.
	 */
	@Transactional
	public List<Language> findWhereGatewayidEquals(String gatewayid) throws LanguageDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED FROM " + getTableName() + " WHERE GATEWAYID = ? ORDER BY GATEWAYID", this,gatewayid);
		}
		catch (Exception e) {
			throw new LanguageDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'GATEWAYID = :gatewayid'.
	 */
	@Transactional
	public List<Language> findWhereGatewayidandMdpLanguageidEquals(String gatewayid, String language) throws LanguageDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED FROM " + getTableName() + " WHERE GATEWAYID = ? AND MDPLANGUAGECODE = ? ORDER BY GATEWAYID", this,gatewayid,language);
		}
		catch (Exception e) {
			throw new LanguageDaoException("Query failed", e);
		}
		
	}
	
	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'GTWLANGUAGECODE = :gtwlanguagecode'.
	 */
	@Transactional
	public List<Language> findWhereGtwlanguagecodeEquals(String gtwlanguagecode) throws LanguageDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED FROM " + getTableName() + " WHERE GTWLANGUAGECODE = ? ORDER BY GTWLANGUAGECODE", this,gtwlanguagecode);
		}
		catch (Exception e) {
			throw new LanguageDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'MDPLANGUAGECODE = :mdplanguagecode'.
	 */
	@Transactional
	public List<Language> findWhereMdplanguagecodeEquals(String mdplanguagecode) throws LanguageDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED FROM " + getTableName() + " WHERE MDPLANGUAGECODE = ? ORDER BY MDPLANGUAGECODE", this,mdplanguagecode);
		}
		catch (Exception e) {
			throw new LanguageDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'LANGUAGEDESCR = :languagedescr'.
	 */
	@Transactional
	public List<Language> findWhereLanguagedescrEquals(String languagedescr) throws LanguageDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED FROM " + getTableName() + " WHERE LANGUAGEDESCR = ? ORDER BY LANGUAGEDESCR", this,languagedescr);
		}
		catch (Exception e) {
			throw new LanguageDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'ENABLED = :enabled'.
	 */
	@Transactional
	public List<Language> findWhereEnabledEquals(String enabled) throws LanguageDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWLANGUAGECODE, MDPLANGUAGECODE, LANGUAGEDESCR, ENABLED FROM " + getTableName() + " WHERE ENABLED = ? ORDER BY ENABLED", this,enabled);
		}
		catch (Exception e) {
			throw new LanguageDaoException("Query failed", e);
		}
		
	}

}
