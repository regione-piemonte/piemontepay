/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.CommissionDao;
import it.csi.mdp.core.business.dto.Commission;
import it.csi.mdp.core.business.dto.CommissionPk;
import it.csi.mdp.core.business.exceptions.CommissionDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class CommissionDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Commission>, CommissionDao
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
	 * @return CommissionPk
	 */
	@Transactional
	public CommissionPk insert(Commission dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( COMMISSION_ID, COMMISSION_DESCRIPTION ) VALUES ( ?, ? )",dto.getCommissionId(),dto.getCommissionDescription());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the COMMISSION table.
	 */
	@Transactional
	public void update(CommissionPk pk, Commission dto) throws CommissionDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET COMMISSION_ID = ?, COMMISSION_DESCRIPTION = ? WHERE COMMISSION_ID = ?",dto.getCommissionId(),dto.getCommissionDescription(),pk.getCommissionId());
	}

	/** 
	 * Deletes a single row in the COMMISSION table.
	 */
	@Transactional
	public void delete(CommissionPk pk) throws CommissionDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE COMMISSION_ID = ?",pk.getCommissionId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Commission
	 */
	public Commission mapRow(ResultSet rs, int row) throws SQLException
	{
		Commission dto = new Commission();
		dto.setCommissionId( rs.getString( 1 ) );
		dto.setCommissionDescription( rs.getString( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "COMMISSION";
	}

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria 'COMMISSION_ID = :commissionId'.
	 */
	@Transactional
	public Commission findByPrimaryKey(String commissionId) throws CommissionDaoException
	{
		try {
			List<Commission> list = jdbcTemplate.query("SELECT COMMISSION_ID, COMMISSION_DESCRIPTION FROM " + getTableName() + " WHERE COMMISSION_ID = ?", this,commissionId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CommissionDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria ''.
	 */
	@Transactional
	public List<Commission> findAll() throws CommissionDaoException
	{
		try {
			return jdbcTemplate.query("SELECT COMMISSION_ID, COMMISSION_DESCRIPTION FROM " + getTableName() + " ORDER BY COMMISSION_ID", this);
		}
		catch (Exception e) {
			throw new CommissionDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria 'COMMISSION_ID = :commissionId'.
	 */
	@Transactional
	public List<Commission> findWhereCommissionIdEquals(String commissionId) throws CommissionDaoException
	{
		try {
			return jdbcTemplate.query("SELECT COMMISSION_ID, COMMISSION_DESCRIPTION FROM " + getTableName() + " WHERE COMMISSION_ID = ? ORDER BY COMMISSION_ID", this,commissionId);
		}
		catch (Exception e) {
			throw new CommissionDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria 'COMMISSION_DESCRIPTION = :commissionDescription'.
	 */
	@Transactional
	public List<Commission> findWhereCommissionDescriptionEquals(String commissionDescription) throws CommissionDaoException
	{
		try {
			return jdbcTemplate.query("SELECT COMMISSION_ID, COMMISSION_DESCRIPTION FROM " + getTableName() + " WHERE COMMISSION_DESCRIPTION = ? ORDER BY COMMISSION_DESCRIPTION", this,commissionDescription);
		}
		catch (Exception e) {
			throw new CommissionDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the COMMISSION table that matches the specified primary-key value.
	 */
	public Commission findByPrimaryKey(CommissionPk pk) throws CommissionDaoException
	{
		return findByPrimaryKey( pk.getCommissionId() );
	}

}
