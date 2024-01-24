/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.ItemDao;
import it.csi.mdp.core.business.dto.Item;
import it.csi.mdp.core.business.exceptions.ItemDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class ItemDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Item>, ItemDao
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
	public void insert(Item dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )",dto.getItemId(),dto.getBasketId(),dto.isQuantityNull()?null:dto.getQuantity(),dto.isPriceItemNull()?null:dto.getPriceItem(),dto.getDescription(),dto.getCreationDate(),dto.isTotalPriceNull()?null:dto.getTotalPrice(),dto.getItemPkId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Item
	 */
	public Item mapRow(ResultSet rs, int row) throws SQLException
	{
		Item dto = new Item();
		dto.setItemId( rs.getLong( 1 ) );
		dto.setBasketId( rs.getString( 2 ) );
		dto.setQuantity( rs.getLong( 3 ) );
		if (rs.wasNull()) {
			dto.setQuantityNull( true );
		}
		
		dto.setPriceItem( rs.getLong( 4 ) );
		if (rs.wasNull()) {
			dto.setPriceItemNull( true );
		}
		
		dto.setDescription( rs.getString( 5 ) );
		dto.setCreationDate( rs.getTimestamp(6 ) );
		dto.setTotalPrice( rs.getLong( 7 ) );
		if (rs.wasNull()) {
			dto.setTotalPriceNull( true );
		}
		
		dto.setItemPkId( rs.getLong( 8 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "ITEM";
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria ''.
	 */
	@Transactional
	public List<Item> findAll() throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'ITEM_ID = :itemId'.
	 */
	@Transactional
	public List<Item> findWhereItemIdEquals(long itemId) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE ITEM_ID = ? ORDER BY ITEM_ID", this,itemId);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'BASKET_ID = :basketId'.
	 */
	@Transactional
	public List<Item> findWhereBasketIdEquals(String basketId) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE BASKET_ID = ? ORDER BY BASKET_ID", this,basketId);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'QUANTITY = :quantity'.
	 */
	@Transactional
	public List<Item> findWhereQuantityEquals(long quantity) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE QUANTITY = ? ORDER BY QUANTITY", this,quantity);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'PRICE_ITEM = :priceItem'.
	 */
	@Transactional
	public List<Item> findWherePriceItemEquals(long priceItem) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE PRICE_ITEM = ? ORDER BY PRICE_ITEM", this,priceItem);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'DESCRIPTION = :description'.
	 */
	@Transactional
	public List<Item> findWhereDescriptionEquals(String description) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE DESCRIPTION = ? ORDER BY DESCRIPTION", this,description);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'CREATION_DATE = :creationDate'.
	 */
	@Transactional
	public List<Item> findWhereCreationDateEquals(Date creationDate) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE CREATION_DATE = ? ORDER BY CREATION_DATE", this,creationDate);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'TOTAL_PRICE = :totalPrice'.
	 */
	@Transactional
	public List<Item> findWhereTotalPriceEquals(long totalPrice) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE TOTAL_PRICE = ? ORDER BY TOTAL_PRICE", this,totalPrice);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'ITEM_PK_ID = :itemPkId'.
	 */
	@Transactional
	public List<Item> findWhereItemPkIdEquals(long itemPkId) throws ItemDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ITEM_ID, BASKET_ID, QUANTITY, PRICE_ITEM, DESCRIPTION, CREATION_DATE, TOTAL_PRICE, ITEM_PK_ID FROM " + getTableName() + " WHERE ITEM_PK_ID = ? ORDER BY ITEM_PK_ID", this,itemPkId);
		}
		catch (Exception e) {
			throw new ItemDaoException("Query failed", e);
		}
		
	}

}
