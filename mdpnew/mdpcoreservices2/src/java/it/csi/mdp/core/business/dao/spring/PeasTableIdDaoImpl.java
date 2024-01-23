/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.PeasTableIdDao;
import it.csi.mdp.core.business.dto.PeasTableId;
import it.csi.mdp.core.business.dto.PeasTableIdPk;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazionePk;
import it.csi.mdp.core.business.exceptions.PeasTableIdDaoException;
import it.csi.mdp.core.business.exceptions.TransazioneDaoException;

import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class PeasTableIdDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PeasTableId>, PeasTableIdDao
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
	public void insert(PeasTableId dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( TABLE_PK, TABLE_ID ) VALUES ( ?, ? )",dto.getTablePk(),dto.getTableId());
	}
	
	/** 
	 * Updates a single row in the PeasTableId table.
	 */
	@Transactional
	public void update(PeasTableId dto) throws PeasTableIdDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET TABLE_ID= ? WHERE TABLE_PK = ?",dto.getTableId(), dto.getTablePk());
	}
	
	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return PeasTableId
	 */
	public PeasTableId mapRow(ResultSet rs, int row) throws SQLException
	{
		PeasTableId dto = new PeasTableId();
		dto.setTablePk( rs.getString( 1 ) );
		dto.setTableId( rs.getString( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "PEAS_TABLE_ID";
	}

	/** 
	 * Returns all rows from the PEAS_TABLE_ID table that match the criteria ''.
	 */
	@Transactional
	public List<PeasTableId> findAll() throws PeasTableIdDaoException
	{
		try {
			return jdbcTemplate.query("SELECT TABLE_PK, TABLE_ID FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new PeasTableIdDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PEAS_TABLE_ID table that match the criteria 'TABLE_PK = :tablePk'.
	 */
	@Transactional
	public List<PeasTableId> findWhereTablePkEquals(String tablePk) throws PeasTableIdDaoException
	{
		try {
			return jdbcTemplate.query("SELECT TABLE_PK, TABLE_ID FROM " + getTableName() + " WHERE TABLE_PK = ? ORDER BY TABLE_PK", this,tablePk);
		}
		catch (Exception e) {
			throw new PeasTableIdDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PEAS_TABLE_ID table that match the criteria 'TABLE_ID = :tableId'.
	 */
	@Transactional
	public List<PeasTableId> findWhereTableIdEquals(String tableId) throws PeasTableIdDaoException
	{
		try {
			return jdbcTemplate.query("SELECT TABLE_PK, TABLE_ID FROM " + getTableName() + " WHERE TABLE_ID = ? ORDER BY TABLE_ID", this,tableId);
		}
		catch (Exception e) {
			throw new PeasTableIdDaoException("Query failed", e);
		}
		
	}

}
