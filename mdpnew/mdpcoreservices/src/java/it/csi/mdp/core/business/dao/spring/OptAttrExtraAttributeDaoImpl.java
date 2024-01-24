/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.OptAttrExtraAttributeDao;
import it.csi.mdp.core.business.dto.OptAttrExtraAttribute;
import it.csi.mdp.core.business.exceptions.OptAttrExtraAttributeDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class OptAttrExtraAttributeDaoImpl extends AbstractDAO implements ParameterizedRowMapper<OptAttrExtraAttribute>, OptAttrExtraAttributeDao
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
	public void insert(OptAttrExtraAttribute dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( EXTRA_ATTRIBUTE_ID, NAME, VALUE, OPT_ATTR_ID ) VALUES ( ?, ?, ?, ? )",dto.getExtraAttributeId(),dto.getName(),dto.getValue(),dto.isOptAttrIdNull()?null:dto.getOptAttrId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return OptAttrExtraAttribute
	 */
	public OptAttrExtraAttribute mapRow(ResultSet rs, int row) throws SQLException
	{
		OptAttrExtraAttribute dto = new OptAttrExtraAttribute();
		dto.setExtraAttributeId( rs.getLong( 1 ) );
		dto.setName( rs.getString( 2 ) );
		dto.setValue( rs.getString( 3 ) );
		dto.setOptAttrId( rs.getLong( 4 ) );
		if (rs.wasNull()) {
			dto.setOptAttrIdNull( true );
		}
		
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "OPT_ATTR_EXTRA_ATTRIBUTE";
	}

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria ''.
	 */
	@Transactional
	public List<OptAttrExtraAttribute> findAll() throws OptAttrExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, OPT_ATTR_ID FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new OptAttrExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'EXTRA_ATTRIBUTE_ID = :extraAttributeId'.
	 */
	@Transactional
	public List<OptAttrExtraAttribute> findWhereExtraAttributeIdEquals(long extraAttributeId) throws OptAttrExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, OPT_ATTR_ID FROM " + getTableName() + " WHERE EXTRA_ATTRIBUTE_ID = ? ORDER BY EXTRA_ATTRIBUTE_ID", this,extraAttributeId);
		}
		catch (Exception e) {
			throw new OptAttrExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'NAME = :name'.
	 */
	@Transactional
	public List<OptAttrExtraAttribute> findWhereNameEquals(String name) throws OptAttrExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, OPT_ATTR_ID FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this,name);
		}
		catch (Exception e) {
			throw new OptAttrExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'VALUE = :value'.
	 */
	@Transactional
	public List<OptAttrExtraAttribute> findWhereValueEquals(String value) throws OptAttrExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, OPT_ATTR_ID FROM " + getTableName() + " WHERE VALUE = ? ORDER BY VALUE", this,value);
		}
		catch (Exception e) {
			throw new OptAttrExtraAttributeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'OPT_ATTR_ID = :optAttrId'.
	 */
	@Transactional
	public List<OptAttrExtraAttribute> findWhereOptAttrIdEquals(long optAttrId) throws OptAttrExtraAttributeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT EXTRA_ATTRIBUTE_ID, NAME, VALUE, OPT_ATTR_ID FROM " + getTableName() + " WHERE OPT_ATTR_ID = ? ORDER BY OPT_ATTR_ID", this,optAttrId);
		}
		catch (Exception e) {
			throw new OptAttrExtraAttributeDaoException("Query failed", e);
		}
		
	}

}
