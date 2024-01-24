/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.BckofficegroupsDao;
import it.csi.mdp.core.business.dto.Bckofficegroups;
import it.csi.mdp.core.business.exceptions.BckofficegroupsDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class BckofficegroupsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Bckofficegroups>, BckofficegroupsDao
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
	public void insert(Bckofficegroups dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID, DESCRIPTION ) VALUES ( ?, ? )",dto.getId(),dto.getDescription());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Bckofficegroups
	 */
	public Bckofficegroups mapRow(ResultSet rs, int row) throws SQLException
	{
		Bckofficegroups dto = new Bckofficegroups();
		dto.setId( rs.getLong( 1 ) );
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
		return "BCKOFFICEGROUPS";
	}

	/** 
	 * Returns all rows from the BCKOFFICEGROUPS table that match the criteria ''.
	 */
	@Transactional
	public List<Bckofficegroups> findAll() throws BckofficegroupsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID, DESCRIPTION FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new BckofficegroupsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the BCKOFFICEGROUPS table that match the criteria 'ID = :id'.
	 */
	@Transactional
	public List<Bckofficegroups> findWhereIdEquals(long id) throws BckofficegroupsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID, DESCRIPTION FROM " + getTableName() + " WHERE ID = ? ORDER BY ID", this,id);
		}
		catch (Exception e) {
			throw new BckofficegroupsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the BCKOFFICEGROUPS table that match the criteria 'DESCRIPTION = :description'.
	 */
	@Transactional
	public List<Bckofficegroups> findWhereDescriptionEquals(String description) throws BckofficegroupsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID, DESCRIPTION FROM " + getTableName() + " WHERE DESCRIPTION = ? ORDER BY DESCRIPTION", this,description);
		}
		catch (Exception e) {
			throw new BckofficegroupsDaoException("Query failed", e);
		}
		
	}

}
