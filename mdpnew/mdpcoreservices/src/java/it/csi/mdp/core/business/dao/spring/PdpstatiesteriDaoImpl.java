/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.PdpstatiesteriDao;
import it.csi.mdp.core.business.dto.Pdpstatiesteri;
import it.csi.mdp.core.business.exceptions.PdpstatiesteriDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class PdpstatiesteriDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Pdpstatiesteri>, PdpstatiesteriDao
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
	public void insert(Pdpstatiesteri dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID, STATO ) VALUES ( ?, ? )",dto.getId(),dto.getStato());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Pdpstatiesteri
	 */
	public Pdpstatiesteri mapRow(ResultSet rs, int row) throws SQLException
	{
		Pdpstatiesteri dto = new Pdpstatiesteri();
		dto.setId( rs.getLong( 1 ) );
		dto.setStato( rs.getString( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "PDPSTATIESTERI";
	}

	/** 
	 * Returns all rows from the PDPSTATIESTERI table that match the criteria ''.
	 */
	@Transactional
	public List<Pdpstatiesteri> findAll() throws PdpstatiesteriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID, STATO FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new PdpstatiesteriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PDPSTATIESTERI table that match the criteria 'ID = :id'.
	 */
	@Transactional
	public List<Pdpstatiesteri> findWhereIdEquals(long id) throws PdpstatiesteriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID, STATO FROM " + getTableName() + " WHERE ID = ? ORDER BY ID", this,id);
		}
		catch (Exception e) {
			throw new PdpstatiesteriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PDPSTATIESTERI table that match the criteria 'STATO = :stato'.
	 */
	@Transactional
	public List<Pdpstatiesteri> findWhereStatoEquals(String stato) throws PdpstatiesteriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID, STATO FROM " + getTableName() + " WHERE STATO = ? ORDER BY STATO", this,stato);
		}
		catch (Exception e) {
			throw new PdpstatiesteriDaoException("Query failed", e);
		}
		
	}

}
