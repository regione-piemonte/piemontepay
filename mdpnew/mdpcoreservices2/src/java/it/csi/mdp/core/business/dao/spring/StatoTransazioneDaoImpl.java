/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.StatoTransazioneDao;
import it.csi.mdp.core.business.dto.StatoTransazione;
import it.csi.mdp.core.business.dto.StatoTransazionePk;
import it.csi.mdp.core.business.exceptions.StatoTransazioneDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class StatoTransazioneDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StatoTransazione>, StatoTransazioneDao
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
	 * @return StatoTransazionePk
	 */
	@Transactional
	public StatoTransazionePk insert(StatoTransazione dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( descrizione, cod_stato, descrizioneestesa ) VALUES ( ?, ?, ? )",dto.getDescrizione(),dto.getCodStato(),dto.getDescrizoneestesa());
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the stato_transazione table.
	 */
	@Transactional
	public void update(StatoTransazionePk pk, StatoTransazione dto) throws StatoTransazioneDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET descrizione = ?, cod_stato = ?, descrizioneestesa = ? WHERE cod_stato = ?",dto.getDescrizione(),dto.getCodStato(),dto.getDescrizoneestesa(),pk.getCodStato());
	}

	/** 
	 * Deletes a single row in the stato_transazione table.
	 */
	@Transactional
	public void delete(StatoTransazionePk pk) throws StatoTransazioneDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE cod_stato = ?",pk.getCodStato());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return StatoTransazione
	 */
	public StatoTransazione mapRow(ResultSet rs, int row) throws SQLException
	{
		StatoTransazione dto = new StatoTransazione();
		dto.setDescrizione( rs.getString( 1 ) );
		dto.setCodStato( rs.getInt( 2 ) );
		dto.setDescrizoneestesa( rs.getString( 3 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "stato_transazione";
	}

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria 'cod_stato = :codStato'.
	 */
	@Transactional
	public StatoTransazione findByPrimaryKey(int codStato) throws StatoTransazioneDaoException
	{
		try {
			List<StatoTransazione> list = jdbcTemplate.query("SELECT descrizione, cod_stato, descrizioneestesa FROM " + getTableName() + " WHERE cod_stato = ?", this,codStato);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new StatoTransazioneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria ''.
	 */
	@Transactional
	public List<StatoTransazione> findAll() throws StatoTransazioneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT descrizione, cod_stato, descrizioneestesa FROM " + getTableName() + " ORDER BY cod_stato", this);
		}
		catch (Exception e) {
			throw new StatoTransazioneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria 'descrizione = :descrizione'.
	 */
	@Transactional
	public List<StatoTransazione> findWhereDescrizioneEquals(String descrizione) throws StatoTransazioneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT descrizione, cod_stato, descrizioneestesa FROM " + getTableName() + " WHERE descrizione = ? ORDER BY descrizione", this,descrizione);
		}
		catch (Exception e) {
			throw new StatoTransazioneDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria 'cod_stato = :codStato'.
	 */
	@Transactional
	public List<StatoTransazione> findWhereCodStatoEquals(int codStato) throws StatoTransazioneDaoException
	{
		try {
			return jdbcTemplate.query("SELECT descrizione, cod_stato, descrizioneestesa FROM " + getTableName() + " WHERE cod_stato = ? ORDER BY cod_stato", this,codStato);
		}
		catch (Exception e) {
			throw new StatoTransazioneDaoException("Query failed", e);
		}
		
	}



	/** 
	 * Returns the rows from the stato_transazione table that matches the specified primary-key value.
	 */
	public StatoTransazione findByPrimaryKey(StatoTransazionePk pk) throws StatoTransazioneDaoException
	{
		return findByPrimaryKey( pk.getCodStato() );
	}

}
