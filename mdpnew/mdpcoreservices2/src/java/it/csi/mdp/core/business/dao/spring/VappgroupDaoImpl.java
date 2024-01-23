/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.VappgroupDao;
import it.csi.mdp.core.business.dto.Vappgroup;
import it.csi.mdp.core.business.exceptions.VappgroupDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class VappgroupDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Vappgroup>, VappgroupDao
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
	public void insert(Vappgroup dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( id, idgroup ) VALUES ( ?, ? )",dto.getId(),dto.isIdgroupNull()?null:dto.getIdgroup());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Vappgroup
	 */
	public Vappgroup mapRow(ResultSet rs, int row) throws SQLException
	{
		Vappgroup dto = new Vappgroup();
		dto.setId( rs.getString( 1 ) );
		dto.setIdgroup( rs.getInt( 2 ) );
		if (rs.wasNull()) {
			dto.setIdgroupNull( true );
		}
		
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "vappgroup";
	}

	/** 
	 * Returns all rows from the vappgroup table that match the criteria ''.
	 */
	@Transactional
	public List<Vappgroup> findAll() throws VappgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, idgroup FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new VappgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vappgroup table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Vappgroup> findWhereIdEquals(String id) throws VappgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, idgroup FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new VappgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vappgroup table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<Vappgroup> findWhereIdgroupEquals(int idgroup) throws VappgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, idgroup FROM " + getTableName() + " WHERE idgroup = ? ORDER BY idgroup", this,idgroup);
		}
		catch (Exception e) {
			throw new VappgroupDaoException("Query failed", e);
		}
		
	}

}
