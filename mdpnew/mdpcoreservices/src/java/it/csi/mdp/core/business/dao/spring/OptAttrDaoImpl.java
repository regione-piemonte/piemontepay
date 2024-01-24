/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.OptAttrDao;
import it.csi.mdp.core.business.dto.OptAttr;
import it.csi.mdp.core.business.exceptions.OptAttrDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class OptAttrDaoImpl extends AbstractDAO implements ParameterizedRowMapper<OptAttr>, OptAttrDao
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
	public void insert(OptAttr dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( OPT_ATTR_ID, BUYER_NAME, BUYER_CODE, TRANSACTION_ID ) VALUES ( ?, ?, ?, ? )",dto.getOptAttrId(),dto.getBuyerName(),dto.getBuyerCode(),dto.getTransactionId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return OptAttr
	 */
	public OptAttr mapRow(ResultSet rs, int row) throws SQLException
	{
		OptAttr dto = new OptAttr();
		dto.setOptAttrId( rs.getLong( 1 ) );
		dto.setBuyerName( rs.getString( 2 ) );
		dto.setBuyerCode( rs.getString( 3 ) );
		dto.setTransactionId( rs.getString( 4 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "OPT_ATTR";
	}

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria ''.
	 */
	@Transactional
	public List<OptAttr> findAll() throws OptAttrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT OPT_ATTR_ID, BUYER_NAME, BUYER_CODE, TRANSACTION_ID FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new OptAttrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'OPT_ATTR_ID = :optAttrId'.
	 */
	@Transactional
	public List<OptAttr> findWhereOptAttrIdEquals(long optAttrId) throws OptAttrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT OPT_ATTR_ID, BUYER_NAME, BUYER_CODE, TRANSACTION_ID FROM " + getTableName() + " WHERE OPT_ATTR_ID = ? ORDER BY OPT_ATTR_ID", this,optAttrId);
		}
		catch (Exception e) {
			throw new OptAttrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'BUYER_NAME = :buyerName'.
	 */
	@Transactional
	public List<OptAttr> findWhereBuyerNameEquals(String buyerName) throws OptAttrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT OPT_ATTR_ID, BUYER_NAME, BUYER_CODE, TRANSACTION_ID FROM " + getTableName() + " WHERE BUYER_NAME = ? ORDER BY BUYER_NAME", this,buyerName);
		}
		catch (Exception e) {
			throw new OptAttrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'BUYER_CODE = :buyerCode'.
	 */
	@Transactional
	public List<OptAttr> findWhereBuyerCodeEquals(String buyerCode) throws OptAttrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT OPT_ATTR_ID, BUYER_NAME, BUYER_CODE, TRANSACTION_ID FROM " + getTableName() + " WHERE BUYER_CODE = ? ORDER BY BUYER_CODE", this,buyerCode);
		}
		catch (Exception e) {
			throw new OptAttrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	@Transactional
	public List<OptAttr> findWhereTransactionIdEquals(String transactionId) throws OptAttrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT OPT_ATTR_ID, BUYER_NAME, BUYER_CODE, TRANSACTION_ID FROM " + getTableName() + " WHERE TRANSACTION_ID = ? ORDER BY TRANSACTION_ID", this,transactionId);
		}
		catch (Exception e) {
			throw new OptAttrDaoException("Query failed", e);
		}
		
	}

}
