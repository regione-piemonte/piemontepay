/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.StrumentoPagamentoDao;
import it.csi.mdp.core.business.dto.StrumentoPagamento;
import it.csi.mdp.core.business.exceptions.StrumentoPagamentoDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class StrumentoPagamentoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StrumentoPagamento>, StrumentoPagamentoDao
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
	public void insert(StrumentoPagamento dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( SP_ID, TRANSACTION_ID, NOME, COD ) VALUES ( ?, ?, ?, ? )",dto.getSpId(),dto.getTransactionId(),dto.getNome(),dto.getCod());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return StrumentoPagamento
	 */
	public StrumentoPagamento mapRow(ResultSet rs, int row) throws SQLException
	{
		StrumentoPagamento dto = new StrumentoPagamento();
		dto.setSpId( rs.getLong( 1 ) );
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
		return "STRUMENTO_PAGAMENTO";
	}

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria ''.
	 */
	@Transactional
	public List<StrumentoPagamento> findAll() throws StrumentoPagamentoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT SP_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new StrumentoPagamentoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'SP_ID = :spId'.
	 */
	@Transactional
	public List<StrumentoPagamento> findWhereSpIdEquals(long spId) throws StrumentoPagamentoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT SP_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE SP_ID = ? ORDER BY SP_ID", this,spId);
		}
		catch (Exception e) {
			throw new StrumentoPagamentoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	@Transactional
	public List<StrumentoPagamento> findWhereTransactionIdEquals(String transactionId) throws StrumentoPagamentoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT SP_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE TRANSACTION_ID = ? ORDER BY TRANSACTION_ID", this,transactionId);
		}
		catch (Exception e) {
			throw new StrumentoPagamentoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'NOME = :nome'.
	 */
	@Transactional
	public List<StrumentoPagamento> findWhereNomeEquals(String nome) throws StrumentoPagamentoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT SP_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE NOME = ? ORDER BY NOME", this,nome);
		}
		catch (Exception e) {
			throw new StrumentoPagamentoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STRUMENTO_PAGAMENTO table that match the criteria 'COD = :cod'.
	 */
	@Transactional
	public List<StrumentoPagamento> findWhereCodEquals(String cod) throws StrumentoPagamentoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT SP_ID, TRANSACTION_ID, NOME, COD FROM " + getTableName() + " WHERE COD = ? ORDER BY COD", this,cod);
		}
		catch (Exception e) {
			throw new StrumentoPagamentoDaoException("Query failed", e);
		}
		
	}

}
