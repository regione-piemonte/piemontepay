/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.GatewayDao;

import it.csi.mdp.core.business.dto.ApplicationPk;
import it.csi.mdp.core.business.dto.Gateway;
import it.csi.mdp.core.business.dto.GatewayPk;
import it.csi.mdp.core.business.exceptions.ApplicationDaoException;
import it.csi.mdp.core.business.exceptions.GatewayDaoException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.transaction.annotation.Transactional;

public class GatewayDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Gateway>, GatewayDao
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
	 * @return GatewayPk
	 */
	@Transactional
	public GatewayPk insert(Gateway dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME ) VALUES ( ?, ?, ?, ?, ?, ? )",dto.getGatewayId(),dto.getGatewayDescription(),dto.getGatewayProvider(),dto.getValidoDal(),dto.getValidoAl(),dto.getGatewayservicename());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the GATEWAY table.
	 */
	@Transactional
	public void update(GatewayPk pk, Gateway dto) throws GatewayDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET GATEWAY_ID = ?, GATEWAY_DESCRIPTION = ?, GATEWAY_PROVIDER = ?, VALIDO_DAL = ?, VALIDO_AL = ?, GATEWAYSERVICENAME = ? WHERE GATEWAY_ID = ?",dto.getGatewayId(),dto.getGatewayDescription(),dto.getGatewayProvider(),dto.getValidoDal(),dto.getValidoAl(),dto.getGatewayservicename(),pk.getGatewayId());
	}

	/** 
	 * Deletes a single row in the GATEWAY table.
	 */
	@Transactional
	public void delete(GatewayPk pk) throws GatewayDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE GATEWAY_ID = ?",pk.getGatewayId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Gateway
	 */
	public Gateway mapRow(ResultSet rs, int row) throws SQLException
	{
		Gateway dto = new Gateway();
		dto.setGatewayId( rs.getString( 1 ) );
		dto.setGatewayDescription( rs.getString( 2 ) );
		dto.setGatewayProvider( rs.getString( 3 ) );
		dto.setValidoDal( rs.getTimestamp(4 ) );
		dto.setValidoAl( rs.getTimestamp(5 ) );
		dto.setGatewayservicename( rs.getString( 6 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "GATEWAY";
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	@Transactional
	public Gateway findByPrimaryKey(String gatewayId) throws GatewayDaoException
	{
		try {
			List<Gateway> list = jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " WHERE GATEWAY_ID = ?", this,gatewayId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria ''.
	 */
	@Transactional
	public List<Gateway> findAll() throws GatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " ORDER BY GATEWAY_ID", this);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	@Transactional
	public List<Gateway> findWhereGatewayIdEquals(String gatewayId) throws GatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " WHERE GATEWAY_ID = ? ORDER BY GATEWAY_ID", this,gatewayId);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_DESCRIPTION = :gatewayDescription'.
	 */
	@Transactional
	public List<Gateway> findWhereGatewayDescriptionEquals(String gatewayDescription) throws GatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " WHERE GATEWAY_DESCRIPTION = ? ORDER BY GATEWAY_DESCRIPTION", this,gatewayDescription);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_PROVIDER = :gatewayProvider'.
	 */
	@Transactional
	public List<Gateway> findWhereGatewayProviderEquals(String gatewayProvider) throws GatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " WHERE GATEWAY_PROVIDER = ? ORDER BY GATEWAY_PROVIDER", this,gatewayProvider);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'VALIDO_DAL = :validoDal'.
	 */
	@Transactional
	public List<Gateway> findWhereValidoDalEquals(Date validoDal) throws GatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " WHERE VALIDO_DAL = ? ORDER BY VALIDO_DAL", this,validoDal);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'VALIDO_AL = :validoAl'.
	 */
	@Transactional
	public List<Gateway> findWhereValidoAlEquals(Date validoAl) throws GatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " WHERE VALIDO_AL = ? ORDER BY VALIDO_AL", this,validoAl);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAYSERVICENAME = :gatewayservicename'.
	 */
	@Transactional
	public List<Gateway> findWhereGatewayservicenameEquals(String gatewayservicename) throws GatewayDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, GATEWAY_DESCRIPTION, GATEWAY_PROVIDER, VALIDO_DAL, VALIDO_AL, GATEWAYSERVICENAME FROM " + getTableName() + " WHERE GATEWAYSERVICENAME = ? ORDER BY GATEWAYSERVICENAME", this,gatewayservicename);
		}
		catch (Exception e) {
			throw new GatewayDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns the rows from the GATEWAY table that matches the specified primary-key value.
	 */
	public Gateway findByPrimaryKey(GatewayPk pk) throws GatewayDaoException
	{
		return findByPrimaryKey( pk.getGatewayId() );
	}
	
	public void deleteGatewayConfiguration(GatewayPk pk) throws ApplicationDaoException
	{
		GatewayStoredProcedure proc = new GatewayStoredProcedure(this.dataSource);
		Map<String, String> params = new HashMap<String, String>();
		params.put("p_gatewayid", pk.getGatewayId());
		proc.execute(params);
	}

	private class GatewayStoredProcedure extends StoredProcedure
	{
		public static final String SQL = "deletegatewayconfiguration";

		public GatewayStoredProcedure(DataSource ds)
		{
			setDataSource(ds);
			setSql(SQL);

			declareParameter(new SqlParameter("p_gatewayid", Types.VARCHAR));
			declareParameter(new SqlReturnResultSet("refcur", new GatewayRowMapper()));
			compile();
		}
	}

	private class GatewayRowMapper implements RowCallbackHandler
	{
		public void processRow(ResultSet rs) throws SQLException
		{
			// get our ref cursor
			ResultSet cur = (ResultSet) rs.getObject(1);
			while (cur.next())
			{
				System.out.println(cur.getBoolean(1));
			}
			cur.close();
		}
	}


}
