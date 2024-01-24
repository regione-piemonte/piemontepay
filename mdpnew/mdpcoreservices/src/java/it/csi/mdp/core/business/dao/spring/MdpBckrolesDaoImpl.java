/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpBckrolesDao;
import it.csi.mdp.core.business.dto.MdpBckroles;
import it.csi.mdp.core.business.dto.MdpBckrolesPk;
import it.csi.mdp.core.business.exceptions.MdpBckrolesDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class MdpBckrolesDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpBckroles>, MdpBckrolesDao
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
	 * @return MdpBckrolesPk
	 */
	@Transactional
	public MdpBckrolesPk insert(MdpBckroles dto)
	{
		int last = 0;
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( roledescr ) VALUES (  ? )",dto.getRoledescr());
		last = jdbcTemplate.queryForInt("select max(idrole) from mdp_bckroles");
		dto.setIdrole(last);
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the mdp_bckroles table.
	 */
	@Transactional
	public void update(MdpBckrolesPk pk, MdpBckroles dto) throws MdpBckrolesDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET  roledescr = ? WHERE idrole = ?",dto.getRoledescr(),pk.getIdrole());
	}

	/** 
	 * Deletes a single row in the mdp_bckroles table.
	 */
	@Transactional
	public void delete(MdpBckrolesPk pk) throws MdpBckrolesDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE idrole = ?",pk.getIdrole());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpBckroles
	 */
	public MdpBckroles mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpBckroles dto = new MdpBckroles();
		dto.setIdrole( rs.getInt( 1 ) );
		dto.setRoledescr( rs.getString( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "mdp_bckroles";
	}

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria 'idrole = :idrole'.
	 */
	@Transactional
	public MdpBckroles findByPrimaryKey(int idrole) throws MdpBckrolesDaoException
	{
		try {
			List<MdpBckroles> list = jdbcTemplate.query("SELECT idrole, roledescr FROM " + getTableName() + " WHERE idrole = ?", this,idrole);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new MdpBckrolesDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria ''.
	 */
	@Transactional
	public List<MdpBckroles> findAll() throws MdpBckrolesDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, roledescr FROM " + getTableName() + " ORDER BY idrole", this);
		}
		catch (Exception e) {
			throw new MdpBckrolesDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria 'idrole = :idrole'.
	 */
	@Transactional
	public List<MdpBckroles> findWhereIdroleEquals(String idrole) throws MdpBckrolesDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, roledescr FROM " + getTableName() + " WHERE idrole = ? ORDER BY idrole", this,idrole);
		}
		catch (Exception e) {
			throw new MdpBckrolesDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckroles table that match the criteria 'roledescr = :roledescr'.
	 */
	@Transactional
	public List<MdpBckroles> findWhereRoledescrEquals(String roledescr) throws MdpBckrolesDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, roledescr FROM " + getTableName() + " WHERE roledescr = ? ORDER BY roledescr", this,roledescr);
		}
		catch (Exception e) {
			throw new MdpBckrolesDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the mdp_bckroles table that matches the specified primary-key value.
	 */
	public MdpBckroles findByPrimaryKey(MdpBckrolesPk pk) throws MdpBckrolesDaoException
	{
		return findByPrimaryKey( pk.getIdrole() );
	}

}
