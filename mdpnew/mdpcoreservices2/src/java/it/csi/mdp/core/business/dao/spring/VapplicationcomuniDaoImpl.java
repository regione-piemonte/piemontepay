/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.VapplicationcomuniDao;
import it.csi.mdp.core.business.dto.Vapplicationcomuni;
import it.csi.mdp.core.business.exceptions.VapplicationcomuniDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class VapplicationcomuniDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Vapplicationcomuni>, VapplicationcomuniDao
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


	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Vapplicationcomuni
	 */
	public Vapplicationcomuni mapRow(ResultSet rs, int row) throws SQLException
	{
		Vapplicationcomuni dto = new Vapplicationcomuni();
		dto.setMerchantid( rs.getString( 1 ) );
		dto.setDescComune( rs.getString( 2 ) );
		dto.setCodiceimm( rs.getString( 3 ) );
		dto.setGatewayId(rs.getString(4));
		dto.setApplicationId(rs.getString(5));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "VAPPLICATIONCOMUNI";
	}

	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria ''.
	 */
	@Transactional
	public List<Vapplicationcomuni> findAll() throws VapplicationcomuniDaoException
	{
		try {
			return jdbcTemplate.query("SELECT MERCHANTID, DESC_COMUNE, CODICEIMM, GATEWAYID, APPLICATIONID FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new VapplicationcomuniDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria 'MERCHANTID = :merchantid'.
	 */
	@Transactional
	public List<Vapplicationcomuni> findWhereMerchantidEquals(String merchantid) throws VapplicationcomuniDaoException
	{
		try {
			return jdbcTemplate.query("SELECT MERCHANTID, DESC_COMUNE, CODICEIMM, GATEWAYID, APPLICATIONID FROM " + getTableName() + " WHERE MERCHANTID = ? ORDER BY MERCHANTID", this,merchantid);
		}
		catch (Exception e) {
			throw new VapplicationcomuniDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria 'DESC_COMUNE = :descComune'.
	 */
	@Transactional
	public List<Vapplicationcomuni> findWhereDescComuneEquals(String descComune) throws VapplicationcomuniDaoException
	{
		try {
			return jdbcTemplate.query("SELECT MERCHANTID, DESC_COMUNE, CODICEIMM, GATEWAYID, APPLICATIONID FROM " + getTableName() + " WHERE DESC_COMUNE = ? ORDER BY DESC_COMUNE", this,descComune);
		}
		catch (Exception e) {
			throw new VapplicationcomuniDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria 'CODICEIMM = :codiceimm'.
	 */
	@Transactional
	public List<Vapplicationcomuni> findWhereCodiceimmEquals(String codiceimm) throws VapplicationcomuniDaoException
	{
		try {
			return jdbcTemplate.query("SELECT MERCHANTID, DESC_COMUNE, CODICEIMM, GATEWAYID, APPLICATIONID FROM " + getTableName() + " WHERE CODICEIMM = ? ORDER BY CODICEIMM", this,codiceimm);
		}
		catch (Exception e) {
			throw new VapplicationcomuniDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	public List<Vapplicationcomuni> findWhereAppIdandGwIdEquals(String appid, String gwid) throws VapplicationcomuniDaoException
	{
		try {
			return jdbcTemplate.query("SELECT MERCHANTID, DESC_COMUNE, CODICEIMM, GATEWAYID, APPLICATIONID FROM " + getTableName() + " WHERE APPLICATIONID = ? and GATEWAYID = ? ORDER BY CODICEIMM", this,appid,gwid);
		}
		catch (Exception e) {
			throw new VapplicationcomuniDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

}
