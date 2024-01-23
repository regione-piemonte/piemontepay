/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpBckofficegroupsDao;
import it.csi.mdp.core.business.dto.MdpBckofficegroups;
import it.csi.mdp.core.business.dto.MdpBckofficegroupsPk;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.MdpBckofficegroupsDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class MdpBckofficegroupsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpBckofficegroups>, MdpBckofficegroupsDao
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
	 * @return MdpBckofficegroupsPk
	 */
	@Transactional
	public MdpBckofficegroupsPk insert(MdpBckofficegroups dto) throws DaoException
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( description ) VALUES ( ? )",dto.getDescription());
		int last = jdbcTemplate.queryForInt("select max(idgroup) from "  + getTableName());
		dto.setIdgroup(last);
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the mdp_bckofficegroups table.
	 */
	@Transactional
	public void update(MdpBckofficegroupsPk pk, MdpBckofficegroups dto) throws MdpBckofficegroupsDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET description = ?, idgroup = ? WHERE idgroup = ?",dto.getDescription(),dto.getIdgroup(),pk.getIdgroup());
	}

	/** 
	 * Deletes a single row in the mdp_bckofficegroups table.
	 */
	@Transactional
	public void delete(MdpBckofficegroupsPk pk) throws MdpBckofficegroupsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE idgroup = ?",pk.getIdgroup());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpBckofficegroups
	 */
	public MdpBckofficegroups mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpBckofficegroups dto = new MdpBckofficegroups();
		dto.setDescription( rs.getString( 1 ) );
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
		return "mdp_bckofficegroups";
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public MdpBckofficegroups findByPrimaryKey(int idgroup) throws MdpBckofficegroupsDaoException
	{
		try {
			List<MdpBckofficegroups> list = jdbcTemplate.query("SELECT description, idgroup FROM " + getTableName() + " WHERE idgroup = ?", this,idgroup);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria ''.
	 */
	@Transactional
	public List<MdpBckofficegroups> findAll() throws MdpBckofficegroupsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT description, idgroup FROM " + getTableName() + " ORDER BY idgroup", this);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria 'description = :description'.
	 */
	@Transactional
	public List<MdpBckofficegroups> findWhereDescriptionEquals(String description) throws MdpBckofficegroupsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT description, idgroup FROM " + getTableName() + " WHERE description = ? ORDER BY description", this,description);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckofficegroups table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<MdpBckofficegroups> findWhereIdgroupEquals(int idgroup) throws MdpBckofficegroupsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT description, idgroup FROM " + getTableName() + " WHERE idgroup = ? ORDER BY idgroup", this,idgroup);
		}
		catch (Exception e) {
			throw new MdpBckofficegroupsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the mdp_bckofficegroups table that matches the specified primary-key value.
	 */
	public MdpBckofficegroups findByPrimaryKey(MdpBckofficegroupsPk pk) throws MdpBckofficegroupsDaoException
	{
		return findByPrimaryKey( pk.getIdgroup() );
	}

}
