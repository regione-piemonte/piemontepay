/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.ApplicationcustomfieldsDao;
import it.csi.mdp.core.business.dao.GatewaycustomfieldsDao;

import it.csi.mdp.core.business.dto.Gatewaycustomfields;

import it.csi.mdp.core.business.exceptions.GatewaycustomfieldsDaoException;
import it.csi.mdp.core.util.Constants;

import java.util.List;


import javax.sql.DataSource;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;



import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class GatewaycustomfieldsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Gatewaycustomfields>,
		GatewaycustomfieldsDao
{
	protected SimpleJdbcTemplate jdbcTemplate;
	
	protected DataSource dataSource;
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);



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
	public void insert(Gatewaycustomfields dto)
	{
		

		jdbcTemplate.update("INSERT INTO " + getTableName() + 
				" (  fieldname, fieldvalue, gateway_id, fielddescription ) VALUES ( ?, ?, ?, ? )",
				dto.getFieldname(),dto.getFieldvalue(),dto.getGatewayId(),dto.getFielddescription());
		
	}

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	@Transactional
	public void update(Gatewaycustomfields dto)
	{

		
		jdbcTemplate.update("UPDATE " + getTableName() + " SET  FIELDVALUE = ? where gateway_id = ? AND FIELDNAME = ? ", dto
				.getFieldvalue(), dto.getGatewayId(), dto.getFieldname());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Applicationcustomfields
	 */
	public Gatewaycustomfields mapRow(ResultSet rs, int row) throws SQLException
	{
		Gatewaycustomfields dto = new Gatewaycustomfields();
		dto.setKeyid(rs.getString(1));
		dto.setFieldname(rs.getString(2));
		dto.setFieldvalue(rs.getString(3));
		dto.setGatewayId( rs.getString( 4 ) );
		dto.setFielddescription( rs.getString( 5 ) );
		
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "GATEWAYCUSTOMFIELDS";
	}

	/**
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the
	 * criteria ''.
	 */
	@Transactional
	public List<Gatewaycustomfields> findAll() throws GatewaycustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT keyid, fieldname, fieldvalue, gateway_id, fielddescription FROM " + getTableName() , this);
			
		} catch (Exception e)
		{
			throw new GatewaycustomfieldsDaoException("Query failed", e);
		}

	}






	/**
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the
	 * criteria 'FIELDNAME = :fieldname'.
	 */
	@Transactional
	public List<Gatewaycustomfields> findWhereGatewayidEquals(String gwid)  throws GatewaycustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT KEYID,  FIELDNAME, FIELDVALUE, gateway_id, fielddescription FROM " + getTableName()
					+ " WHERE gateway_id = ? ORDER BY FIELDNAME", this, gwid);
		} catch (Exception e)
		{
			throw new GatewaycustomfieldsDaoException("Query failed", e);
		}

	}
	
	@Transactional
	public List<Gatewaycustomfields> findWhereGatewayidFieldnameEquals(String gwid, String fieldname) throws GatewaycustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT KEYID, FIELDNAME, FIELDVALUE, gateway_id, fielddescription FROM " + getTableName()
					+ " WHERE GATEWAY_ID = ? AND FIELDNAME = ? ORDER BY FIELDNAME", this, gwid,fieldname);
		} catch (Exception e)
		{
			throw new GatewaycustomfieldsDaoException("Query failed", e);
		}

	}



	public static String asHex(byte buf[])
	{
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++)
		{
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	public void delete(String gwid) throws GatewaycustomfieldsDaoException
	{
		
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE gateway_id = ? ",gwid);
		
	}

}
