/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpErroriDao;
import it.csi.mdp.core.business.dto.MdpErrori;
import it.csi.mdp.core.business.dto.MdpErroriPk;
import it.csi.mdp.core.business.exceptions.MdpErroriDaoException;
import it.csi.mdp.core.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class MdpErroriDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpErrori>, MdpErroriDao
{
	protected SimpleJdbcTemplate jdbcTemplate;
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
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
	 * @return MdpErroriPk
	 */
	@Transactional
	public MdpErroriPk insert(MdpErrori dto)
	{
		log.debug("MdpErroriDaoImpl:insert "+dto.toString());
		
		if(dto.getDescrizione()==null)
		{
			dto.setDescrizione("");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( descrizione, application_id, data, transaction_id ) VALUES ( ?, ?, to_timestamp(?,'dd/mm/yyyy HH24:MI:SS'), ? ) ",
				(dto.getDescrizione().trim().length()>4999?dto.getDescrizione().substring(0,4999):dto.getDescrizione()),dto.getApplicationId(),sdf.format(dto.getData()),dto.getTransactionId());
		return dto.createPk();
	}
 
//	/** 
//	 * Updates a single row in the mdp_errori table.
//	 */
//	@Transactional
//	public void update(MdpErroriPk pk, MdpErrori dto) throws MdpErroriDaoException
//	{
//		jdbcTemplate.update("UPDATE " + getTableName() + " SET id = ?, descrizione = ?, application_id = ?, data = ?, transaction_id = ? WHERE id = ?",dto.getId(),dto.getDescrizione().substring(0,10000),dto.getApplicationId(),dto.getData(),dto.getTransactionId(),pk.getId());
//	}
//
//	/** 
//	 * Deletes a single row in the mdp_errori table.
//	 */
//	@Transactional
//	public void delete(MdpErroriPk pk) throws MdpErroriDaoException
//	{
//		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
//	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpErrori
	 */
	public MdpErrori mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpErrori dto = new MdpErrori();
		dto.setId( rs.getInt( 1 ) );
		dto.setDescrizione( rs.getString( 2 ) );
		dto.setApplicationId( rs.getString( 3 ) );
		dto.setData( rs.getTimestamp(4 ) );
		dto.setTransactionId( rs.getString( 5 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "mdp_errori";
	}

	/** 
	 * Returns all rows from the mdp_errori table that match the criteria 'id = :id'.
	 */
	@Transactional
	public MdpErrori findByPrimaryKey(int id) throws MdpErroriDaoException
	{
		try {
			List<MdpErrori> list = jdbcTemplate.query("SELECT id, descrizione, application_id, data, transaction_id FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new MdpErroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_errori table that match the criteria ''.
	 */
	@Transactional
	public List<MdpErrori> findAll() throws MdpErroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, descrizione, application_id, data, transaction_id FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new MdpErroriDaoException("Query failed", e);
		}
		
	}


	/** 
	 * Returns the rows from the mdp_errori table that matches the specified primary-key value.
	 */
	public MdpErrori findByPrimaryKey(MdpErroriPk pk) throws MdpErroriDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}
