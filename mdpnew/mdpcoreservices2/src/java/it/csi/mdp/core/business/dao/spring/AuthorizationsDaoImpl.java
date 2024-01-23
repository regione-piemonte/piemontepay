/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.AuthorizationsDao;
import it.csi.mdp.core.business.dto.Authorizations;
import it.csi.mdp.core.business.dto.AuthorizationsPk;
import it.csi.mdp.core.business.exceptions.AuthorizationsDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class AuthorizationsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Authorizations>, AuthorizationsDao
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
	 * @return AuthorizationsPk
	 */
	@Transactional
	public AuthorizationsPk insert(Authorizations dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( operazione, idrole ) VALUES ( ?, ? )",dto.getOperazione(),dto.getIdrole());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the authorizations table.
	 */
	@Transactional
	public void update(AuthorizationsPk pk, Authorizations dto) throws AuthorizationsDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET operazione = ?, idrole = ? WHERE idrole = ? AND operazione = ?",dto.getOperazione(),dto.getIdrole(),pk.getIdrole(),pk.getOperazione());
	}

	/** 
	 * Deletes a single row in the authorizations table.
	 */
	@Transactional
	public void delete(AuthorizationsPk pk) throws AuthorizationsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE idrole = ? AND operazione = ?",pk.getIdrole(),pk.getOperazione());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Authorizations
	 */
	public Authorizations mapRow(ResultSet rs, int row) throws SQLException
	{
		Authorizations dto = new Authorizations();
		dto.setOperazione( rs.getString( 1 ) );
		dto.setIdrole( rs.getInt( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "authorizations";
	}

	/** 
	 * Returns all rows from the authorizations table that match the criteria 'idrole = :idrole AND operazione = :operazione'.
	 */
	@Transactional
	public Authorizations findByPrimaryKey(int idrole, String operazione) throws AuthorizationsDaoException
	{
		try {
			List<Authorizations> list = jdbcTemplate.query("SELECT operazione, idrole FROM " + getTableName() + " WHERE idrole = ? AND operazione = ?", this,idrole,operazione);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new AuthorizationsDaoException("Query failed: "+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the authorizations table that match the criteria ''.
	 */
	@Transactional
	public List<Authorizations> findAll() throws AuthorizationsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT operazione, idrole FROM " + getTableName() + " ORDER BY idrole, operazione", this);
		}
		catch (Exception e) {
			throw new AuthorizationsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the authorizations table that match the criteria 'operazione = :operazione'.
	 */
	@Transactional
	public List<Authorizations> findWhereOperazioneEquals(String operazione) throws AuthorizationsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT operazione, idrole FROM " + getTableName() + " WHERE operazione = ? ORDER BY operazione", this,operazione);
		}
		catch (Exception e) {
			throw new AuthorizationsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the authorizations table that match the criteria 'idrole = :idrole'.
	 */
	@Transactional
	public List<Authorizations> findWhereIdroleEquals(int idrole) throws AuthorizationsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT operazione, idrole FROM " + getTableName() + " WHERE idrole = ? ORDER BY idrole", this,idrole);
		}
		catch (Exception e) {
			throw new AuthorizationsDaoException("Query failed", e);
		}
		
	}

		
	/** 
	 * Returns the rows from the authorizations table that matches the specified primary-key value.
	 */
	public Authorizations findByPrimaryKey(AuthorizationsPk pk) throws AuthorizationsDaoException
	{
		return findByPrimaryKey( pk.getIdrole(), pk.getOperazione() );
	}

}
