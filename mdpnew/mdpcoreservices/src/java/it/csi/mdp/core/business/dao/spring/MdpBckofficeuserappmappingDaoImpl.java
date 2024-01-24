/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpBckofficeuserappmappingDao;
import it.csi.mdp.core.business.dto.MdpBckofficeuserappmapping;
import it.csi.mdp.core.business.exceptions.MdpBckofficeuserappmappingDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class MdpBckofficeuserappmappingDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpBckofficeuserappmapping>, MdpBckofficeuserappmappingDao
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
	public void insert(MdpBckofficeuserappmapping dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( IDAPP, IDUSER ) VALUES ( ?, ? )",dto.getIdapp(),dto.getIduser());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpBckofficeuserappmapping
	 */
	public MdpBckofficeuserappmapping mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpBckofficeuserappmapping dto = new MdpBckofficeuserappmapping();
		dto.setIdapp( rs.getString( 1 ) );
		dto.setIduser( rs.getString( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "MDP_BCKOFFICEUSERAPPMAPPING";
	}

	/** 
	 * Returns all rows from the MDP_BCKOFFICEUSERAPPMAPPING table that match the criteria ''.
	 */
	@Transactional
	public List<MdpBckofficeuserappmapping> findAll() throws MdpBckofficeuserappmappingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT IDAPP, IDUSER FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new MdpBckofficeuserappmappingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the MDP_BCKOFFICEUSERAPPMAPPING table that match the criteria 'IDAPP = :idapp'.
	 */
	@Transactional
	public List<MdpBckofficeuserappmapping> findWhereIdappEquals(String idapp) throws MdpBckofficeuserappmappingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT IDAPP, IDUSER FROM " + getTableName() + " WHERE IDAPP = ? ORDER BY IDAPP", this,idapp);
		}
		catch (Exception e) {
			throw new MdpBckofficeuserappmappingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the MDP_BCKOFFICEUSERAPPMAPPING table that match the criteria 'IDUSER = :iduser'.
	 */
	@Transactional
	public List<MdpBckofficeuserappmapping> findWhereIduserEquals(String iduser) throws MdpBckofficeuserappmappingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT IDAPP, IDUSER FROM " + getTableName() + " WHERE IDUSER = ? ORDER BY IDUSER", this,iduser);
		}
		catch (Exception e) {
			throw new MdpBckofficeuserappmappingDaoException("Query failed", e);
		}
		
	}

}
