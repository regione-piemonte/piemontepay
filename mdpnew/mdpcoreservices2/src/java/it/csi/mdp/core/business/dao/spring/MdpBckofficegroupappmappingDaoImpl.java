/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpBckofficegroupappmappingDao;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmapping;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmappingPk;
import it.csi.mdp.core.business.exceptions.MdpBckofficegroupappmappingDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class MdpBckofficegroupappmappingDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpBckofficegroupappmapping>, MdpBckofficegroupappmappingDao
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
	 * @return MdpBckofficegroupappmappingPk
	 */
	@Transactional
	public MdpBckofficegroupappmappingPk insert(MdpBckofficegroupappmapping dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( idapp, idgroup ) VALUES ( ?, ? )",dto.getIdapp(),dto.getIdgroup());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the mdp_bckofficegroupappmapping table.
	 */
	@Transactional
	public void update(MdpBckofficegroupappmappingPk pk, MdpBckofficegroupappmapping dto) throws MdpBckofficegroupappmappingDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET idapp = ?, idgroup = ? WHERE idapp = ? AND idgroup = ?",dto.getIdapp(),dto.getIdgroup(),pk.getIdapp(),pk.getIdgroup());
	}

	/** 
	 * Deletes a single row in the mdp_bckofficegroupappmapping table.
	 */
	@Transactional
	public void delete(MdpBckofficegroupappmappingPk pk) throws MdpBckofficegroupappmappingDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE idapp = ? AND idgroup = ?",pk.getIdapp(),pk.getIdgroup());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpBckofficegroupappmapping
	 */
	public MdpBckofficegroupappmapping mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpBckofficegroupappmapping dto = new MdpBckofficegroupappmapping();
		dto.setIdapp( rs.getString( 1 ) );
		dto.setIdgroup( rs.getInt( 2 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "mdp_bckofficegroupappmapping";
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idapp = :idapp AND idgroup = :idgroup'.
	 */
	@Transactional
	public MdpBckofficegroupappmapping findByPrimaryKey(String idapp, int idgroup) throws MdpBckofficegroupappmappingDaoException
	{
		try {
			List<MdpBckofficegroupappmapping> list = jdbcTemplate.query("SELECT idapp, idgroup FROM " + getTableName() + " WHERE idapp = ? AND idgroup = ?", this,idapp,idgroup);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupappmappingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria ''.
	 */
	@Transactional
	public List<MdpBckofficegroupappmapping> findAll() throws MdpBckofficegroupappmappingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idapp, idgroup FROM " + getTableName() + " ORDER BY idapp, idgroup", this);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupappmappingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<MdpBckofficegroupappmapping> findByMdpBckofficegroups(int idgroup) throws MdpBckofficegroupappmappingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idapp, idgroup FROM " + getTableName() + " WHERE idgroup = ?", this,idgroup);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupappmappingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idapp = :idapp'.
	 */
	@Transactional
	public List<MdpBckofficegroupappmapping> findWhereIdappEquals(String idapp) throws MdpBckofficegroupappmappingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idapp, idgroup FROM " + getTableName() + " WHERE idapp = ? ORDER BY idapp", this,idapp);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupappmappingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroupappmapping table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<MdpBckofficegroupappmapping> findWhereIdgroupEquals(int idgroup) throws MdpBckofficegroupappmappingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idapp, idgroup FROM " + getTableName() + " WHERE idgroup = ? ORDER BY idgroup", this,idgroup);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupappmappingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the mdp_bckofficegroupappmapping table that matches the specified primary-key value.
	 */
	public MdpBckofficegroupappmapping findByPrimaryKey(MdpBckofficegroupappmappingPk pk) throws MdpBckofficegroupappmappingDaoException
	{
		return findByPrimaryKey( pk.getIdapp(), pk.getIdgroup() );
	}

}
