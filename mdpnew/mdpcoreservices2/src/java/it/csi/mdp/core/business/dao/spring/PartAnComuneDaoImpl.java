/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.PartAnComuneDao;
import it.csi.mdp.core.business.dto.PartAnComune;
import it.csi.mdp.core.business.exceptions.PartAnComuneDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class PartAnComuneDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PartAnComune>, PartAnComuneDao
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
	public void insert(PartAnComune dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",dto.getIdComune(),dto.getRStatus(),dto.isIdComuneNextNull()?null:dto.getIdComuneNext(),dto.isIdComunePrevNull()?null:dto.getIdComunePrev(),dto.getDStart(),dto.getDStop(),dto.getIstatComune(),dto.getDescComune(),dto.getCap(),dto.getIstatProvincia(),dto.getDescProvincia(),dto.getSiglaProv(),dto.getIstatRegione(),dto.getDescRegione());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return PartAnComune
	 */
	public PartAnComune mapRow(ResultSet rs, int row) throws SQLException
	{
		PartAnComune dto = new PartAnComune();
		dto.setIdComune( rs.getLong( 1 ) );
		dto.setRStatus( rs.getString( 2 ) );
		dto.setIdComuneNext( rs.getLong( 3 ) );
		if (rs.wasNull()) {
			dto.setIdComuneNextNull( true );
		}
		
		dto.setIdComunePrev( rs.getLong( 4 ) );
		if (rs.wasNull()) {
			dto.setIdComunePrevNull( true );
		}
		
		dto.setDStart( rs.getTimestamp(5 ) );
		dto.setDStop( rs.getTimestamp(6 ) );
		dto.setIstatComune( rs.getString( 7 ) );
		dto.setDescComune( rs.getString( 8 ) );
		dto.setCap( rs.getString( 9 ) );
		dto.setIstatProvincia( rs.getString( 10 ) );
		dto.setDescProvincia( rs.getString( 11 ) );
		dto.setSiglaProv( rs.getString( 12 ) );
		dto.setIstatRegione( rs.getString( 13 ) );
		dto.setDescRegione( rs.getString( 14 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "PART_AN_COMUNE";
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria ''.
	 */
	@Transactional
	public List<PartAnComune> findAll() throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ID_COMUNE = :idComune'.
	 */
	@Transactional
	public List<PartAnComune> findWhereIdComuneEquals(long idComune) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE ID_COMUNE = ? ORDER BY ID_COMUNE", this,idComune);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'R_STATUS = :rStatus'.
	 */
	@Transactional
	public List<PartAnComune> findWhereRStatusEquals(String rStatus) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE R_STATUS = ? ORDER BY R_STATUS", this,rStatus);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ID_COMUNE_NEXT = :idComuneNext'.
	 */
	@Transactional
	public List<PartAnComune> findWhereIdComuneNextEquals(long idComuneNext) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE ID_COMUNE_NEXT = ? ORDER BY ID_COMUNE_NEXT", this,idComuneNext);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ID_COMUNE_PREV = :idComunePrev'.
	 */
	@Transactional
	public List<PartAnComune> findWhereIdComunePrevEquals(long idComunePrev) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE ID_COMUNE_PREV = ? ORDER BY ID_COMUNE_PREV", this,idComunePrev);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'D_START = :dStart'.
	 */
	@Transactional
	public List<PartAnComune> findWhereDStartEquals(Date dStart) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE D_START = ? ORDER BY D_START", this,dStart);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'D_STOP = :dStop'.
	 */
	@Transactional
	public List<PartAnComune> findWhereDStopEquals(Date dStop) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE D_STOP = ? ORDER BY D_STOP", this,dStop);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ISTAT_COMUNE = :istatComune'.
	 */
	@Transactional
	public List<PartAnComune> findWhereIstatComuneEquals(String istatComune) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE ISTAT_COMUNE = ? ORDER BY ISTAT_COMUNE", this,istatComune);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'DESC_COMUNE = :descComune'.
	 */
	@Transactional
	public List<PartAnComune> findWhereDescComuneEquals(String descComune) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE DESC_COMUNE = ? ORDER BY DESC_COMUNE", this,descComune);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'CAP = :cap'.
	 */
	@Transactional
	public List<PartAnComune> findWhereCapEquals(String cap) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE CAP = ? ORDER BY CAP", this,cap);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ISTAT_PROVINCIA = :istatProvincia'.
	 */
	@Transactional
	public List<PartAnComune> findWhereIstatProvinciaEquals(String istatProvincia) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE ISTAT_PROVINCIA = ? ORDER BY ISTAT_PROVINCIA", this,istatProvincia);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'DESC_PROVINCIA = :descProvincia'.
	 */
	@Transactional
	public List<PartAnComune> findWhereDescProvinciaEquals(String descProvincia) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE DESC_PROVINCIA = ? ORDER BY DESC_PROVINCIA", this,descProvincia);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'SIGLA_PROV = :siglaProv'.
	 */
	@Transactional
	public List<PartAnComune> findWhereSiglaProvEquals(String siglaProv) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE SIGLA_PROV = ? ORDER BY SIGLA_PROV", this,siglaProv);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ISTAT_REGIONE = :istatRegione'.
	 */
	@Transactional
	public List<PartAnComune> findWhereIstatRegioneEquals(String istatRegione) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE ISTAT_REGIONE = ? ORDER BY ISTAT_REGIONE", this,istatRegione);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'DESC_REGIONE = :descRegione'.
	 */
	@Transactional
	public List<PartAnComune> findWhereDescRegioneEquals(String descRegione) throws PartAnComuneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, R_STATUS, ID_COMUNE_NEXT, ID_COMUNE_PREV, D_START, D_STOP, ISTAT_COMUNE, DESC_COMUNE, CAP, ISTAT_PROVINCIA, DESC_PROVINCIA, SIGLA_PROV, ISTAT_REGIONE, DESC_REGIONE FROM " + getTableName() + " WHERE DESC_REGIONE = ? ORDER BY DESC_REGIONE", this,descRegione);
		}
		catch (Exception e) {
			throw new PartAnComuneDaoException("Query failed", e);
		}
		
	}

}
