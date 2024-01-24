/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpBckrolesgroupmapDao;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmap;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmapPk;
import it.csi.mdp.core.business.exceptions.MdpBckrolesgroupmapDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class MdpBckrolesgroupmapDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpBckrolesgroupmap>, MdpBckrolesgroupmapDao
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
	 * @return MdpBckrolesgroupmapPk
	 */
	@Transactional
	public MdpBckrolesgroupmapPk insert(MdpBckrolesgroupmap dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( idrole, idgroup ) VALUES ( ?, ? )",dto.getIdrole(),dto.getIdgroup());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the mdp_bckrolesgroupmap table.
	 */
	@Transactional
	public void update(MdpBckrolesgroupmapPk pk, MdpBckrolesgroupmap dto) throws MdpBckrolesgroupmapDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET idrole = ?, idgroup = ? WHERE idrole = ? AND idgroup = ?",dto.getIdrole(),dto.getIdgroup(),pk.getIdrole(),pk.getIdgroup());
	}

	/** 
	 * Deletes a single row in the mdp_bckrolesgroupmap table.
	 */
	@Transactional
	public void delete(MdpBckrolesgroupmapPk pk) throws MdpBckrolesgroupmapDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE idrole = ? AND idgroup = ?",pk.getIdrole(),pk.getIdgroup());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpBckrolesgroupmap
	 */
	public MdpBckrolesgroupmap mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpBckrolesgroupmap dto = new MdpBckrolesgroupmap();
		dto.setIdrole( rs.getInt( 1 ) );
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
		return "mdp_bckrolesgroupmap";
	}

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idrole = :idrole AND idgroup = :idgroup'.
	 */
	@Transactional
	public MdpBckrolesgroupmap findByPrimaryKey(int idrole, int idgroup) throws MdpBckrolesgroupmapDaoException
	{
		try {
			List<MdpBckrolesgroupmap> list = jdbcTemplate.query("SELECT idrole, idgroup FROM " + getTableName() + " WHERE idrole = ? AND idgroup = ?", this,idrole,idgroup);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new MdpBckrolesgroupmapDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria ''.
	 */
	@Transactional
	public List<MdpBckrolesgroupmap> findAll() throws MdpBckrolesgroupmapDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, idgroup FROM " + getTableName() + " ORDER BY idrole, idgroup", this);
		}
		catch (Exception e) {
			throw new MdpBckrolesgroupmapDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<MdpBckrolesgroupmap> findByMdpBckofficegroups(int idgroup) throws MdpBckrolesgroupmapDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, idgroup FROM " + getTableName() + " WHERE idgroup = ?", this,idgroup);
		}
		catch (Exception e) {
			throw new MdpBckrolesgroupmapDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idrole = :idrole'.
	 */
	@Transactional
	public List<MdpBckrolesgroupmap> findByMdpBckroles(int idrole) throws MdpBckrolesgroupmapDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, idgroup FROM " + getTableName() + " WHERE idrole = ?", this,idrole);
		}
		catch (Exception e) {
			throw new MdpBckrolesgroupmapDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idrole = :idrole'.
	 */
	@Transactional
	public List<MdpBckrolesgroupmap> findWhereIdroleEquals(int idrole) throws MdpBckrolesgroupmapDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, idgroup FROM " + getTableName() + " WHERE idrole = ? ORDER BY idrole", this,idrole);
		}
		catch (Exception e) {
			throw new MdpBckrolesgroupmapDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckrolesgroupmap table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<MdpBckrolesgroupmap> findWhereIdgroupEquals(int idgroup) throws MdpBckrolesgroupmapDaoException
	{
		try {
			return jdbcTemplate.query("SELECT idrole, idgroup FROM " + getTableName() + " WHERE idgroup = ? ORDER BY idgroup", this,idgroup);
		}
		catch (Exception e) {
			throw new MdpBckrolesgroupmapDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the mdp_bckrolesgroupmap table that matches the specified primary-key value.
	 */
	public MdpBckrolesgroupmap findByPrimaryKey(MdpBckrolesgroupmapPk pk) throws MdpBckrolesgroupmapDaoException
	{
		return findByPrimaryKey( pk.getIdrole(), pk.getIdgroup() );
	}

}
