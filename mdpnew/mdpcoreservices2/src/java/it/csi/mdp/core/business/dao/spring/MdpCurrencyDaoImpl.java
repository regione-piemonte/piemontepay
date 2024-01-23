/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpCurrencyDao;
import it.csi.mdp.core.business.dto.MdpCurrency;
import it.csi.mdp.core.business.dto.MdpCurrencyPk;
import it.csi.mdp.core.business.exceptions.MdpCurrencyDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import it.csi.mdp.core.business.dao.*;
public class MdpCurrencyDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpCurrency>, MdpCurrencyDao
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
	public void insert(MdpCurrency dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED ) VALUES ( ?, ?, ?, ?, ? )",dto.getGatewayid(),dto.getGtwcurrencycode(),dto.getMdpcurrencycode(),dto.getCurrencydescr(),dto.getEnabled());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpCurrency
	 */
	public MdpCurrency mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpCurrency dto = new MdpCurrency();
		dto.setGatewayid( rs.getString( 1 ) );
		dto.setGtwcurrencycode( rs.getString( 2 ) );
		dto.setMdpcurrencycode( rs.getString( 3 ) );
		dto.setCurrencydescr( rs.getString( 4 ) );
		dto.setEnabled( rs.getString( 5 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "MDP_CURRENCY";
	}

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria ''.
	 */
	@Transactional
	public List<MdpCurrency> findAll() throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GATEWAYID = :gatewayid'.
	 */
	@Transactional
	public List<MdpCurrency> findWhereGatewayidEquals(String gatewayid) throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE GATEWAYID = ? ORDER BY GATEWAYID", this,gatewayid);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GTWCURRENCYCODE = :gtwcurrencycode'.
	 */
	@Transactional
	public List<MdpCurrency> findWhereGtwcurrencycodeEquals(String gtwcurrencycode) throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE GTWCURRENCYCODE = ? ORDER BY GTWCURRENCYCODE", this,gtwcurrencycode);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'MDPCURRENCYCODE = :mdpcurrencycode'.
	 */
	@Transactional
	public List<MdpCurrency> findWhereMdpcurrencycodeEquals(String mdpcurrencycode) throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE MDPCURRENCYCODE = ? ORDER BY MDPCURRENCYCODE", this,mdpcurrencycode);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GATEWAYID = :gatewayid' and 'MDPCURRENCYCODE = :mdpcurrencycode'.
	 */
	@Transactional
	public List<MdpCurrency> findWhereMdpccyAndGatewayIdEquals(String mdpcurrencycode, String gatewayId) throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE MDPCURRENCYCODE = ? AND GATEWAYID = ? AND ENABLED = '1' ORDER BY MDPCURRENCYCODE", this,mdpcurrencycode,gatewayId);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'GATEWAYID = :gatewayid' and 'GTWCURRENCYCODE = :mdpcurrencycode'.
	 */
	@Transactional
	public List<MdpCurrency> findWhereGtwccyAndGatewayIdEquals(String gtwcurrencycode, String gatewayId) throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE GTWCURRENCYCODE = ? AND GATEWAYID = ? AND ENABLED = '1' ORDER BY MDPCURRENCYCODE", this,gtwcurrencycode,gatewayId);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'CURRENCYDESCR = :currencydescr'.
	 */
	@Transactional
	public List<MdpCurrency> findWhereCurrencydescrEquals(String currencydescr) throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE CURRENCYDESCR = ? ORDER BY CURRENCYDESCR", this,currencydescr);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
 
	/** 
	 * Returns all rows from the MDP_CURRENCY table that match the criteria 'ENABLED = :enabled'.
	 */
	@Transactional
	public List<MdpCurrency> findWhereEnabledEquals(String enabled) throws MdpCurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE ENABLED = ? ORDER BY ENABLED", this,enabled);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	
	
	public MdpCurrency findByPrimaryKey(MdpCurrencyPk pk) throws MdpCurrencyDaoException
	{
		try {
			return (jdbcTemplate.query("SELECT GATEWAYID, GTWCURRENCYCODE, MDPCURRENCYCODE, CURRENCYDESCR, ENABLED FROM " + getTableName() + " WHERE  GATEWAYID = ? AND GTWCURRENCYCODE = ?  AND MDPCURRENCYCODE = ?", this,pk.getGatewayid(),pk.getGtwcurrencycode(),pk.getMdpcurrencycode())).get(0);
		}
		catch (Exception e) {
			throw new MdpCurrencyDaoException("Query failed:"+e.getMessage(), e);
		}
	}

}
