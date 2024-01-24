/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.PaymentmodeDao;
import it.csi.mdp.core.business.dto.Paymentmode;
import it.csi.mdp.core.business.dto.PaymentmodePk;
import it.csi.mdp.core.business.exceptions.PaymentmodeDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class PaymentmodeDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Paymentmode>, PaymentmodeDao
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
	 * @return PaymentmodePk
	 */
	@Transactional
	public PaymentmodePk insert(Paymentmode dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( PAYMENTMODE_ID, PAYMENTMODE_DESCRIPTION, VALIDO_DAL, VALIDO_AL ) VALUES ( ?, ?, ?, ? )",dto.getPaymentmodeId(),dto.getPaymentmodeDescription(),dto.getValidoDal(),dto.getValidoAl());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the PAYMENTMODE table.
	 */
	@Transactional
	public void update(PaymentmodePk pk, Paymentmode dto) throws PaymentmodeDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET PAYMENTMODE_ID = ?, PAYMENTMODE_DESCRIPTION = ?, VALIDO_DAL = ?, VALIDO_AL = ? WHERE PAYMENTMODE_ID = ?",dto.getPaymentmodeId(),dto.getPaymentmodeDescription(),dto.getValidoDal(),dto.getValidoAl(),pk.getPaymentmodeId());
	}

	/** 
	 * Deletes a single row in the PAYMENTMODE table.
	 */
	@Transactional
	public void delete(PaymentmodePk pk) throws PaymentmodeDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE PAYMENTMODE_ID = ?",pk.getPaymentmodeId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Paymentmode
	 */
	public Paymentmode mapRow(ResultSet rs, int row) throws SQLException
	{
		Paymentmode dto = new Paymentmode();
		dto.setPaymentmodeId( rs.getString( 1 ) );
		dto.setPaymentmodeDescription( rs.getString( 2 ) );
		dto.setValidoDal( rs.getTimestamp(3 ) );
		dto.setValidoAl( rs.getTimestamp(4 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "PAYMENTMODE";
	}

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'PAYMENTMODE_ID = :paymentmodeId'.
	 */
	@Transactional
	public Paymentmode findByPrimaryKey(String paymentmodeId) throws PaymentmodeDaoException
	{
		try {
			List<Paymentmode> list = jdbcTemplate.query("SELECT PAYMENTMODE_ID, PAYMENTMODE_DESCRIPTION, VALIDO_DAL, VALIDO_AL FROM " + getTableName() + " WHERE PAYMENTMODE_ID = ?", this,paymentmodeId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new PaymentmodeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria ''.
	 */
	@Transactional
	public List<Paymentmode> findAll() throws PaymentmodeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PAYMENTMODE_ID, PAYMENTMODE_DESCRIPTION, VALIDO_DAL, VALIDO_AL FROM " + getTableName() + " ORDER BY PAYMENTMODE_ID", this);
		}
		catch (Exception e) {
			throw new PaymentmodeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'PAYMENTMODE_ID = :paymentmodeId'.
	 */
	@Transactional
	public List<Paymentmode> findWherePaymentmodeIdEquals(String paymentmodeId) throws PaymentmodeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PAYMENTMODE_ID, PAYMENTMODE_DESCRIPTION, VALIDO_DAL, VALIDO_AL FROM " + getTableName() + " WHERE PAYMENTMODE_ID = ? ORDER BY PAYMENTMODE_ID", this,paymentmodeId);
		}
		catch (Exception e) {
			throw new PaymentmodeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'PAYMENTMODE_DESCRIPTION = :paymentmodeDescription'.
	 */
	@Transactional
	public List<Paymentmode> findWherePaymentmodeDescriptionEquals(String paymentmodeDescription) throws PaymentmodeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PAYMENTMODE_ID, PAYMENTMODE_DESCRIPTION, VALIDO_DAL, VALIDO_AL FROM " + getTableName() + " WHERE PAYMENTMODE_DESCRIPTION = ? ORDER BY PAYMENTMODE_DESCRIPTION", this,paymentmodeDescription);
		}
		catch (Exception e) {
			throw new PaymentmodeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'VALIDO_DAL = :validoDal'.
	 */
	@Transactional
	public List<Paymentmode> findWhereValidoDalEquals(Date validoDal) throws PaymentmodeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PAYMENTMODE_ID, PAYMENTMODE_DESCRIPTION, VALIDO_DAL, VALIDO_AL FROM " + getTableName() + " WHERE VALIDO_DAL = ? ORDER BY VALIDO_DAL", this,validoDal);
		}
		catch (Exception e) {
			throw new PaymentmodeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PAYMENTMODE table that match the criteria 'VALIDO_AL = :validoAl'.
	 */
	@Transactional
	public List<Paymentmode> findWhereValidoAlEquals(Date validoAl) throws PaymentmodeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT PAYMENTMODE_ID, PAYMENTMODE_DESCRIPTION, VALIDO_DAL, VALIDO_AL FROM " + getTableName() + " WHERE VALIDO_AL = ? ORDER BY VALIDO_AL", this,validoAl);
		}
		catch (Exception e) {
			throw new PaymentmodeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the PAYMENTMODE table that matches the specified primary-key value.
	 */
	public Paymentmode findByPrimaryKey(PaymentmodePk pk) throws PaymentmodeDaoException
	{
		return findByPrimaryKey( pk.getPaymentmodeId() );
	}

}
