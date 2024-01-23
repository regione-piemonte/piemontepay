/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.StatiEsteriMinDao;
import it.csi.mdp.core.business.dto.StatiEsteriMin;
import it.csi.mdp.core.business.exceptions.StatiEsteriMinDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class StatiEsteriMinDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StatiEsteriMin>, StatiEsteriMinDao
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
	public void insert(StatiEsteriMin dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",dto.isIdStatoMinisteroNull()?null:dto.getIdStatoMinistero(),dto.getContinente(),dto.getStato(),dto.getTerritorio(),dto.getCodice(),dto.getDStart(),dto.getDStop(),dto.getCodrif(),dto.getCodicePrev(),dto.getCodiceNext(),dto.getRStatus());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return StatiEsteriMin
	 */
	public StatiEsteriMin mapRow(ResultSet rs, int row) throws SQLException
	{
		StatiEsteriMin dto = new StatiEsteriMin();
		dto.setIdStatoMinistero( rs.getLong( 1 ) );
		if (rs.wasNull()) {
			dto.setIdStatoMinisteroNull( true );
		}
		
		dto.setContinente( rs.getString( 2 ) );
		dto.setStato( rs.getString( 3 ) );
		dto.setTerritorio( rs.getString( 4 ) );
		dto.setCodice( rs.getString( 5 ) );
		dto.setDStart( rs.getTimestamp(6 ) );
		dto.setDStop( rs.getTimestamp(7 ) );
		dto.setCodrif( rs.getString( 8 ) );
		dto.setCodicePrev( rs.getString( 9 ) );
		dto.setCodiceNext( rs.getString( 10 ) );
		dto.setRStatus( rs.getString( 11 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "STATI_ESTERI_MIN";
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria ''.
	 */
	@Transactional
	public List<StatiEsteriMin> findAll() throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'ID_STATO_MINISTERO = :idStatoMinistero'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereIdStatoMinisteroEquals(long idStatoMinistero) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE ID_STATO_MINISTERO = ? ORDER BY ID_STATO_MINISTERO", this,idStatoMinistero);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CONTINENTE = :continente'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereContinenteEquals(String continente) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE CONTINENTE = ? ORDER BY CONTINENTE", this,continente);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'STATO = :stato'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereStatoEquals(String stato) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE STATO = ? ORDER BY STATO", this,stato);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'TERRITORIO = :territorio'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereTerritorioEquals(String territorio) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE TERRITORIO = ? ORDER BY TERRITORIO", this,territorio);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODICE = :codice'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereCodiceEquals(String codice) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE CODICE = ? ORDER BY CODICE", this,codice);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'D_START = :dStart'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereDStartEquals(Date dStart) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE D_START = ? ORDER BY D_START", this,dStart);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'D_STOP = :dStop'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereDStopEquals(Date dStop) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE D_STOP = ? ORDER BY D_STOP", this,dStop);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODRIF = :codrif'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereCodrifEquals(String codrif) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE CODRIF = ? ORDER BY CODRIF", this,codrif);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODICE_PREV = :codicePrev'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereCodicePrevEquals(String codicePrev) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE CODICE_PREV = ? ORDER BY CODICE_PREV", this,codicePrev);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODICE_NEXT = :codiceNext'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereCodiceNextEquals(String codiceNext) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE CODICE_NEXT = ? ORDER BY CODICE_NEXT", this,codiceNext);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'R_STATUS = :rStatus'.
	 */
	@Transactional
	public List<StatiEsteriMin> findWhereRStatusEquals(String rStatus) throws StatiEsteriMinDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_STATO_MINISTERO, CONTINENTE, STATO, TERRITORIO, CODICE, D_START, D_STOP, CODRIF, CODICE_PREV, CODICE_NEXT, R_STATUS FROM " + getTableName() + " WHERE R_STATUS = ? ORDER BY R_STATUS", this,rStatus);
		}
		catch (Exception e) {
			throw new StatiEsteriMinDaoException("Query failed", e);
		}
		
	}

}
