/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.TransazioneExtraAttributeDao;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.exceptions.TransazioneExtraAttributeDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class TransazioneExtraAttributeDaoImpl extends AbstractDAO implements ParameterizedRowMapper<TransazioneExtraAttribute>, TransazioneExtraAttributeDao
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
	public void insert(TransazioneExtraAttribute dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( EXTRA_ATTRIBUTE_ID, NAME, VALUE, TRANSACTION_ID ) VALUES ( nextval('SEQ_TRANSAZIONE_EXTRA_ATTR'), ?, ?, ? )",dto.getName(),dto.getValue(),dto.getTransactionId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return TransazioneExtraAttribute
	 */
	public TransazioneExtraAttribute mapRow(ResultSet rs, int row) throws SQLException
	{
		TransazioneExtraAttribute dto = new TransazioneExtraAttribute();
		dto.setExtraAttributeId( rs.getLong( 1 ) );
		dto.setName( rs.getString( 2 ) );
		dto.setValue( rs.getString( 3 ) );
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
		return "TRANSAZIONE_EXTRA_ATTRIBUTE";
	}

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria ''.
	 */
	@Transactional
	public List<TransazioneExtraAttribute> findAll(String idgroup) throws TransazioneExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, TRANSACTION_ID FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new TransazioneExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'EXTRA_ATTRIBUTE_ID = :extraAttributeId'.
	 */
	@Transactional
	public List<TransazioneExtraAttribute> findWhereExtraAttributeIdEquals(long extraAttributeId,String idgroup) throws TransazioneExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, TRANSACTION_ID FROM " + getTableName() + " WHERE EXTRA_ATTRIBUTE_ID = ? ORDER BY EXTRA_ATTRIBUTE_ID", this,extraAttributeId);
		}
		catch (Exception e) {
			throw new TransazioneExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'NAME = :name'.
	 */
	@Transactional
	public List<TransazioneExtraAttribute> findWhereNameEquals(String name,String idgroup) throws TransazioneExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, TRANSACTION_ID FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this,name);
		}
		catch (Exception e) {
			throw new TransazioneExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'VALUE = :value'.
	 */
	@Transactional
	public List<TransazioneExtraAttribute> findWhereValueEquals(String value,String idgroup) throws TransazioneExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, TRANSACTION_ID FROM " + getTableName() + " WHERE VALUE = ? ORDER BY VALUE", this,value);
		}
		catch (Exception e) {
			throw new TransazioneExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	@Transactional
	public List<TransazioneExtraAttribute> findWhereTransactionIdEquals(String transactionId,String idgroup) throws TransazioneExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, TRANSACTION_ID FROM " + getTableName() + " WHERE TRANSACTION_ID = ? ORDER BY TRANSACTION_ID", this,transactionId);
		}
		catch (Exception e) {
			throw new TransazioneExtraAttributeDaoException("Query failed", e);
		}
		
	}

}
