/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.BasketDao;
import it.csi.mdp.core.business.dto.Basket;
import it.csi.mdp.core.business.dto.BasketPk;
import it.csi.mdp.core.business.exceptions.BasketDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class BasketDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Basket>, BasketDao
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
	 * @return BasketPk
	 */
	@Transactional
	public BasketPk insert(Basket dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( BASKET_ID, CREATION_DATE, LAST_CHANGE_DATE ) VALUES ( ?, ?, ? )",dto.getBasketId(),dto.getCreationDate(),dto.getLastChangeDate());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the BASKET table.
	 */
	@Transactional
	public void update(BasketPk pk, Basket dto) throws BasketDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET BASKET_ID = ?, CREATION_DATE = ?, LAST_CHANGE_DATE = ? WHERE BASKET_ID = ?",dto.getBasketId(),dto.getCreationDate(),dto.getLastChangeDate(),pk.getBasketId());
	}

	/** 
	 * Deletes a single row in the BASKET table.
	 */
	@Transactional
	public void delete(BasketPk pk) throws BasketDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE BASKET_ID = ?",pk.getBasketId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Basket
	 */
	public Basket mapRow(ResultSet rs, int row) throws SQLException
	{
		Basket dto = new Basket();
		dto.setBasketId( rs.getString( 1 ) );
		dto.setCreationDate( rs.getTimestamp(2 ) );
		dto.setLastChangeDate( rs.getTimestamp(3 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "BASKET";
	}

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'BASKET_ID = :basketId'.
	 */
	@Transactional
	public Basket findByPrimaryKey(String basketId) throws BasketDaoException
	{
		try {
			List<Basket> list = jdbcTemplate.query("SELECT BASKET_ID, CREATION_DATE, LAST_CHANGE_DATE FROM " + getTableName() + " WHERE BASKET_ID = ?", this,basketId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new BasketDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the BASKET table that match the criteria ''.
	 */
	@Transactional
	public List<Basket> findAll() throws BasketDaoException
	{
		try {
			return jdbcTemplate.query("SELECT BASKET_ID, CREATION_DATE, LAST_CHANGE_DATE FROM " + getTableName() + " ORDER BY BASKET_ID", this);
		}
		catch (Exception e) {
			throw new BasketDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'BASKET_ID = :basketId'.
	 */
	@Transactional
	public List<Basket> findWhereBasketIdEquals(String basketId) throws BasketDaoException
	{
		try {
			return jdbcTemplate.query("SELECT BASKET_ID, CREATION_DATE, LAST_CHANGE_DATE FROM " + getTableName() + " WHERE BASKET_ID = ? ORDER BY BASKET_ID", this,basketId);
		}
		catch (Exception e) {
			throw new BasketDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'CREATION_DATE = :creationDate'.
	 */
	@Transactional
	public List<Basket> findWhereCreationDateEquals(Date creationDate) throws BasketDaoException
	{
		try {
			return jdbcTemplate.query("SELECT BASKET_ID, CREATION_DATE, LAST_CHANGE_DATE FROM " + getTableName() + " WHERE CREATION_DATE = ? ORDER BY CREATION_DATE", this,creationDate);
		}
		catch (Exception e) {
			throw new BasketDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'LAST_CHANGE_DATE = :lastChangeDate'.
	 */
	@Transactional
	public List<Basket> findWhereLastChangeDateEquals(Date lastChangeDate) throws BasketDaoException
	{
		try {
			return jdbcTemplate.query("SELECT BASKET_ID, CREATION_DATE, LAST_CHANGE_DATE FROM " + getTableName() + " WHERE LAST_CHANGE_DATE = ? ORDER BY LAST_CHANGE_DATE", this,lastChangeDate);
		}
		catch (Exception e) {
			throw new BasketDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the BASKET table that matches the specified primary-key value.
	 */
	public Basket findByPrimaryKey(BasketPk pk) throws BasketDaoException
	{
		return findByPrimaryKey( pk.getBasketId() );
	}

}
