/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.PaymentGatewayDao;
import it.csi.mdp.core.business.dto.PaymentGateway;
import it.csi.mdp.core.business.exceptions.PaymentGatewayDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class PaymentGatewayDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PaymentGateway>, PaymentGatewayDao
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
	public void insert(PaymentGateway dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( PG_ID, TRANSACTION_ID, NOME, COD ) VALUES ( ?, ?, ?, ? )",dto.getPgId(),dto.getTransactionId(),dto.getNome(),dto.getCod());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return PaymentGateway
	 */
	public PaymentGateway mapRow(ResultSet rs, int row) throws SQLException
	{
		PaymentGateway dto = new PaymentGateway();
		dto.setPgId( rs.getLong( 1 ) );
		dto.setTransactionId( rs.getString( 2 ) );
		dto.setNome( rs.getString( 3 ) );
		dto.setCod( rs.getString( 4 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "PAYMENT_GATEWAY";
	}

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria ''.
	 */
	@Transactional
	public List<PaymentGateway> findAll() throws PaymentGatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PG_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new PaymentGatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'PG_ID = :pgId'.
	 */
	@Transactional
	public List<PaymentGateway> findWherePgIdEquals(long pgId) throws PaymentGatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PG_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE PG_ID = ? ORDER BY PG_ID", this,pgId);
		}
		catch (Exception e) {
			throw new PaymentGatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	@Transactional
	public List<PaymentGateway> findWhereTransactionIdEquals(String transactionId) throws PaymentGatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PG_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE TRANSACTION_ID = ? ORDER BY TRANSACTION_ID", this,transactionId);
		}
		catch (Exception e) {
			throw new PaymentGatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'NOME = :nome'.
	 */
	@Transactional
	public List<PaymentGateway> findWhereNomeEquals(String nome) throws PaymentGatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PG_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE NOME = ? ORDER BY NOME", this,nome);
		}
		catch (Exception e) {
			throw new PaymentGatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENT_GATEWAY table that match the criteria 'COD = :cod'.
	 */
	@Transactional
	public List<PaymentGateway> findWhereCodEquals(String cod) throws PaymentGatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PG_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE COD = ? ORDER BY COD", this,cod);
		}
		catch (Exception e) {
			throw new PaymentGatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

}
