/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.AuditactionsDao;
import it.csi.mdp.core.business.dto.Auditactions;
import it.csi.mdp.core.business.dto.AuditactionsPk;
import it.csi.mdp.core.business.exceptions.AuditactionsDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class AuditactionsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Auditactions>, AuditactionsDao
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
	 * @return AuditactionsPk
	 */
	@Transactional
	public AuditactionsPk insert(Auditactions dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( idaction, description ) VALUES ( ?, ? )",dto.getIdaction(),dto.getDescription());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the auditactions table.
	 */
	@Transactional
	public void update(AuditactionsPk pk, Auditactions dto) throws AuditactionsDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET idaction = ?, description = ? WHERE idaction = ?",dto.getIdaction(),dto.getDescription(),pk.getIdaction());
	}

	/** 
	 * Deletes a single row in the auditactions table.
	 */
	@Transactional
	public void delete(AuditactionsPk pk) throws AuditactionsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE idaction = ?",pk.getIdaction());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Auditactions
	 */
	public Auditactions mapRow(ResultSet rs, int row) throws SQLException
	{
		Auditactions dto = new Auditactions();
		dto.setIdaction( rs.getString( 1 ) );
		dto.setDescription( rs.getString( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "auditactions";
	}

	/** 
	 * Returns all rows from the auditactions table that match the criteria 'idaction = :idaction'.
	 */
	@Transactional
	public Auditactions findByPrimaryKey(String idaction) throws AuditactionsDaoException
	{
		try {
			List<Auditactions> list = jdbcTemplate.query("SELECT idaction, description FROM " + getTableName() + " WHERE idaction = ?", this,idaction);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new AuditactionsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the auditactions table that match the criteria ''.
	 */
	@Transactional
	public List<Auditactions> findAll() throws AuditactionsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idaction, description FROM " + getTableName() + " ORDER BY idaction", this);
		}
		catch (Exception e) {
			throw new AuditactionsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the auditactions table that match the criteria 'idaction = :idaction'.
	 */
	@Transactional
	public List<Auditactions> findWhereIdactionEquals(String idaction) throws AuditactionsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idaction, description FROM " + getTableName() + " WHERE idaction = ? ORDER BY idaction", this,idaction);
		}
		catch (Exception e) {
			throw new AuditactionsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the auditactions table that match the criteria 'description = :description'.
	 */
	@Transactional
	public List<Auditactions> findWhereDescriptionEquals(String description) throws AuditactionsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idaction, description FROM " + getTableName() + " WHERE description = ? ORDER BY description", this,description);
		}
		catch (Exception e) {
			throw new AuditactionsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the auditactions table that matches the specified primary-key value.
	 */
	public Auditactions findByPrimaryKey(AuditactionsPk pk) throws AuditactionsDaoException
	{
		return findByPrimaryKey( pk.getIdaction() );
	}

}
