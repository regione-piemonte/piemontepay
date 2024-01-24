/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.IcicodiciimmDao;
import it.csi.mdp.core.business.dto.Icicodiciimm;
import it.csi.mdp.core.business.exceptions.IcicodiciimmDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class IcicodiciimmDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Icicodiciimm>, IcicodiciimmDao
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
	public void insert(Icicodiciimm dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( APPLICATIONID, CODICEIMM, CAUSALE ) VALUES ( ?, ?, ? )",dto.getApplicationId(),dto.getCodiceimm(),dto.getCausale());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Icicodiciimm
	 */
	public Icicodiciimm mapRow(ResultSet rs, int row) throws SQLException
	{
		Icicodiciimm dto = new Icicodiciimm();
		dto.setApplicationid( rs.getString( 1 ) );
		dto.setCodiceimm( rs.getString( 2 ) );
		dto.setCausale( rs.getString( 3 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "ICICODICIIMM";
	}

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria ''.
	 */
	@Transactional
	public List<Icicodiciimm> findAll() throws IcicodiciimmDaoException
	{
		try {
			return jdbcTemplate.query("SELECT APPLICATIONID, CODICEIMM, CAUSALE FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new IcicodiciimmDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria 'APPLICATIONID = :applicationid'.
	 */
	@Transactional
	public List<Icicodiciimm> findWhereApplicationidEquals(String applicationid) throws IcicodiciimmDaoException
	{
		try {
			return jdbcTemplate.query("SELECT APPLICATIONID, CODICEIMM, CAUSALE FROM " + getTableName() + " WHERE APPLICATIONID = ? ORDER BY APPLICATIONID", this,applicationid);
		}
		catch (Exception e) {
			throw new IcicodiciimmDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria 'CODICEIMM = :codiceimm'.
	 */
	@Transactional
	public List<Icicodiciimm> findWhereCodiceimmEquals(String codiceimm) throws IcicodiciimmDaoException
	{
		try {
			return jdbcTemplate.query("SELECT APPLICATIONID, CODICEIMM, CAUSALE FROM " + getTableName() + " WHERE CODICEIMM = ? ORDER BY CODICEIMM", this,codiceimm);
		}
		catch (Exception e) {
			throw new IcicodiciimmDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria 'CAUSALE = :causale'.
	 */
	@Transactional
	public List<Icicodiciimm> findWhereCausaleEquals(String causale) throws IcicodiciimmDaoException
	{
		try {
			return jdbcTemplate.query("SELECT APPLICATIONID, CODICEIMM, CAUSALE FROM " + getTableName() + " WHERE CAUSALE = ? ORDER BY CAUSALE", this,causale);
		}
		catch (Exception e) {
			throw new IcicodiciimmDaoException("Query failed", e);
		}
		
	}

}
