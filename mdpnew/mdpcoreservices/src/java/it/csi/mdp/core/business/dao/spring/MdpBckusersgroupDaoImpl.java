/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpBckusersgroupDao;
import it.csi.mdp.core.business.dto.MdpBckusersgroup;
import it.csi.mdp.core.business.dto.MdpBckusersgroupPk;
import it.csi.mdp.core.business.exceptions.MdpBckusersgroupDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class MdpBckusersgroupDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpBckusersgroup>, MdpBckusersgroupDao
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
	 * @return MdpBckusersgroupPk
	 */
	@Transactional
	public MdpBckusersgroupPk insert(MdpBckusersgroup dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( iduser, idgroup ) VALUES ( ?, ? )",dto.getIduser(),dto.getIdgroup());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the mdp_bckusersgroup table.
	 */
	@Transactional
	public void update(MdpBckusersgroupPk pk, MdpBckusersgroup dto) throws MdpBckusersgroupDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET iduser = ?, idgroup = ? WHERE iduser = ? AND idgroup = ?",dto.getIduser(),dto.getIdgroup(),pk.getIduser(),pk.getIdgroup());
	}

	/** 
	 * Deletes a single row in the mdp_bckusersgroup table.
	 */
	@Transactional
	public void delete(MdpBckusersgroupPk pk) throws MdpBckusersgroupDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE iduser = ? AND idgroup = ?",pk.getIduser(),pk.getIdgroup());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpBckusersgroup
	 */
	public MdpBckusersgroup mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpBckusersgroup dto = new MdpBckusersgroup();
		dto.setIduser( rs.getInt( 1 ) );
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
		return "mdp_bckusersgroup";
	}

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'iduser = :iduser AND idgroup = :idgroup'.
	 */
	@Transactional
	public MdpBckusersgroup findByPrimaryKey(int iduser, int idgroup) throws MdpBckusersgroupDaoException
	{
		try {
			List<MdpBckusersgroup> list = jdbcTemplate.query("SELECT iduser, idgroup FROM " + getTableName() + " WHERE iduser = ? AND idgroup = ?", this,iduser,idgroup);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new MdpBckusersgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria ''.
	 */
	@Transactional
	public List<MdpBckusersgroup> findAll() throws MdpBckusersgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT iduser, idgroup FROM " + getTableName() + " ORDER BY iduser, idgroup", this);
		}
		catch (Exception e) {
			throw new MdpBckusersgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<MdpBckusersgroup> findByMdpBckofficegroups(int idgroup) throws MdpBckusersgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT iduser, idgroup FROM " + getTableName() + " WHERE idgroup = ?", this,idgroup);
		}
		catch (Exception e) {
			throw new MdpBckusersgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'iduser = :iduser'.
	 */
	@Transactional
	public List<MdpBckusersgroup> findByMdpBckusers(int iduser) throws MdpBckusersgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT iduser, idgroup FROM " + getTableName() + " WHERE iduser = ?", this,iduser);
		}
		catch (Exception e) {
			throw new MdpBckusersgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'iduser = :iduser'.
	 */
	@Transactional
	public List<MdpBckusersgroup> findWhereIduserEquals(int iduser) throws MdpBckusersgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT iduser, idgroup FROM " + getTableName() + " WHERE iduser = ? ORDER BY iduser", this,iduser);
		}
		catch (Exception e) {
			throw new MdpBckusersgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusersgroup table that match the criteria 'idgroup = :idgroup'.
	 */
	@Transactional
	public List<MdpBckusersgroup> findWhereIdgroupEquals(int idgroup) throws MdpBckusersgroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT iduser, idgroup FROM " + getTableName() + " WHERE idgroup = ? ORDER BY idgroup", this,idgroup);
		}
		catch (Exception e) {
			throw new MdpBckusersgroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the mdp_bckusersgroup table that matches the specified primary-key value.
	 */
	public MdpBckusersgroup findByPrimaryKey(MdpBckusersgroupPk pk) throws MdpBckusersgroupDaoException
	{
		return findByPrimaryKey( pk.getIduser(), pk.getIdgroup() );
	}

}
