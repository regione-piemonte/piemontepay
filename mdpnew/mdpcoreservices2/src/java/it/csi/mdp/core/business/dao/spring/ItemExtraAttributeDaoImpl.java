/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.ItemExtraAttributeDao;
import it.csi.mdp.core.business.dto.ItemExtraAttribute;
import it.csi.mdp.core.business.exceptions.ItemExtraAttributeDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class ItemExtraAttributeDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ItemExtraAttribute>, ItemExtraAttributeDao
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
	public void insert(ItemExtraAttribute dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( EXTRA_ATTRIBUTE_ID, NAME, VALUE, ITEM_ID ) VALUES ( ?, ?, ?, ? )",dto.getExtraAttributeId(),dto.getName(),dto.getValue(),dto.isItemIdNull()?null:dto.getItemId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return ItemExtraAttribute
	 */
	public ItemExtraAttribute mapRow(ResultSet rs, int row) throws SQLException
	{
		ItemExtraAttribute dto = new ItemExtraAttribute();
		dto.setExtraAttributeId( rs.getLong( 1 ) );
		dto.setName( rs.getString( 2 ) );
		dto.setValue( rs.getString( 3 ) );
		dto.setItemId( rs.getLong( 4 ) );
		if (rs.wasNull()) {
			dto.setItemIdNull( true );
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
		return "ITEM_EXTRA_ATTRIBUTE";
	}

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria ''.
	 */
	@Transactional
	public List<ItemExtraAttribute> findAll() throws ItemExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, ITEM_ID FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new ItemExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'EXTRA_ATTRIBUTE_ID = :extraAttributeId'.
	 */
	@Transactional
	public List<ItemExtraAttribute> findWhereExtraAttributeIdEquals(long extraAttributeId) throws ItemExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, ITEM_ID FROM " + getTableName() + " WHERE EXTRA_ATTRIBUTE_ID = ? ORDER BY EXTRA_ATTRIBUTE_ID", this,extraAttributeId);
		}
		catch (Exception e) {
			throw new ItemExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'NAME = :name'.
	 */
	@Transactional
	public List<ItemExtraAttribute> findWhereNameEquals(String name) throws ItemExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, ITEM_ID FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this,name);
		}
		catch (Exception e) {
			throw new ItemExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'VALUE = :value'.
	 */
	@Transactional
	public List<ItemExtraAttribute> findWhereValueEquals(String value) throws ItemExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, ITEM_ID FROM " + getTableName() + " WHERE VALUE = ? ORDER BY VALUE", this,value);
		}
		catch (Exception e) {
			throw new ItemExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'ITEM_ID = :itemId'.
	 */
	@Transactional
	public List<ItemExtraAttribute> findWhereItemIdEquals(long itemId) throws ItemExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, ITEM_ID FROM " + getTableName() + " WHERE ITEM_ID = ? ORDER BY ITEM_ID", this,itemId);
		}
		catch (Exception e) {
			throw new ItemExtraAttributeDaoException("Query failed", e);
		}
		
	}

}
