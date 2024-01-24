/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.VerroriDao;
import it.csi.mdp.core.business.dto.Verrori;
import it.csi.mdp.core.business.dto.Vtransazione;
import it.csi.mdp.core.business.exceptions.VerroriDaoException;
import it.csi.mdp.core.business.exceptions.VtransazioneDaoException;
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

public class VerroriDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Verrori>, VerroriDao
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
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Verrori
	 */
	public Verrori mapRow(ResultSet rs, int row) throws SQLException
	{
		Verrori dto = new Verrori();
		dto.setApplicationId( rs.getString( 1 ) );
		dto.setTransactionId( rs.getString( 2 ) );
		dto.setData( rs.getTimestamp(3 ) );
		dto.setDescrizione( rs.getString( 4 ) );
		dto.setAtewaypaymodeid( rs.getString( 5 ) );
		dto.setInitDate( rs.getTimestamp(6 ) );
		dto.setStartDate( rs.getTimestamp(7 ) );
		dto.setFinishDate( rs.getTimestamp(8 ) );
		dto.setGatewayId( rs.getString( 9 ) );
		dto.setGatewayDescription( rs.getString( 10 ) );
		dto.setPaymentmodeDescription( rs.getString( 11 ) );
		dto.setCodStato( rs.getLong( 12 ) );
		if (rs.wasNull()) {
			dto.setCodStatoNull( true );
		}
		
		dto.setDescrizionestatotrans( rs.getString( 13 ) );
		dto.setAmount( rs.getFloat( 14 ) );
		if (rs.wasNull()) {
			dto.setAmountNull( true );
		}
		
		dto.setPayurl( rs.getString( 15 ) );
		dto.setCurrencydescr( rs.getString( 16 ) );
		dto.setPgresultcode( rs.getString( 17 ) );
		dto.setApplicationname( rs.getString( 18 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "verrori";
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria ''.
	 */
	@Transactional
	public List<Verrori> findAll() throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'application_id = :applicationId'.
	 */
	@Transactional
	public List<Verrori> findWhereApplicationIdEquals(String applicationId) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE application_id = ? ORDER BY application_id", this,applicationId);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'transaction_id = :transactionId'.
	 */
	@Transactional
	public List<Verrori> findWhereTransactionIdEquals(String transactionId) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE transaction_id = ? ORDER BY transaction_id", this,transactionId);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'data = :data'.
	 */
	@Transactional
	public List<Verrori> findWhereDataEquals(Date data) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE data = ? ORDER BY data", this,data);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'descrizione = :descrizione'.
	 */
	@Transactional
	public List<Verrori> findWhereDescrizioneEquals(String descrizione) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE descrizione = ? ORDER BY descrizione", this,descrizione);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'atewaypaymodeid = :atewaypaymodeid'.
	 */
	@Transactional
	public List<Verrori> findWhereAtewaypaymodeidEquals(String atewaypaymodeid) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE atewaypaymodeid = ? ORDER BY atewaypaymodeid", this,atewaypaymodeid);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'init_date = :initDate'.
	 */
	@Transactional
	public List<Verrori> findWhereInitDateEquals(Date initDate) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE init_date = ? ORDER BY init_date", this,initDate);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'start_date = :startDate'.
	 */
	@Transactional
	public List<Verrori> findWhereStartDateEquals(Date startDate) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE start_date = ? ORDER BY start_date", this,startDate);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'finish_date = :finishDate'.
	 */
	@Transactional
	public List<Verrori> findWhereFinishDateEquals(Date finishDate) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE finish_date = ? ORDER BY finish_date", this,finishDate);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'gateway_id = :gatewayId'.
	 */
	@Transactional
	public List<Verrori> findWhereGatewayIdEquals(String gatewayId) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE gateway_id = ? ORDER BY gateway_id", this,gatewayId);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'gateway_description = :gatewayDescription'.
	 */
	@Transactional
	public List<Verrori> findWhereGatewayDescriptionEquals(String gatewayDescription) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE gateway_description = ? ORDER BY gateway_description", this,gatewayDescription);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'paymentmode_description = :paymentmodeDescription'.
	 */
	@Transactional
	public List<Verrori> findWherePaymentmodeDescriptionEquals(String paymentmodeDescription) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE paymentmode_description = ? ORDER BY paymentmode_description", this,paymentmodeDescription);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'cod_stato = :codStato'.
	 */
	@Transactional
	public List<Verrori> findWhereCodStatoEquals(long codStato) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE cod_stato = ? ORDER BY cod_stato", this,codStato);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'descrizionestatotrans = :descrizionestatotrans'.
	 */
	@Transactional
	public List<Verrori> findWhereDescrizionestatotransEquals(String descrizionestatotrans) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE descrizionestatotrans = ? ORDER BY descrizionestatotrans", this,descrizionestatotrans);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'amount = :amount'.
	 */
	@Transactional
	public List<Verrori> findWhereAmountEquals(float amount) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE amount = ? ORDER BY amount", this,amount);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'payurl = :payurl'.
	 */
	@Transactional
	public List<Verrori> findWherePayurlEquals(String payurl) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE payurl = ? ORDER BY payurl", this,payurl);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'currencydescr = :currencydescr'.
	 */
	@Transactional
	public List<Verrori> findWhereCurrencydescrEquals(String currencydescr) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE currencydescr = ? ORDER BY currencydescr", this,currencydescr);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'pgresultcode = :pgresultcode'.
	 */
	@Transactional
	public List<Verrori> findWherePgresultcodeEquals(String pgresultcode) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE pgresultcode = ? ORDER BY pgresultcode", this,pgresultcode);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the verrori table that match the criteria 'applicationname = :applicationname'.
	 */
	@Transactional
	public List<Verrori> findWhereApplicationnameEquals(String applicationname) throws VerroriDaoException
	{
		try {
			return jdbcTemplate.query("SELECT application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName() + " WHERE applicationname = ? ORDER BY applicationname", this,applicationname);
		}
		catch (Exception e) {
			throw new VerroriDaoException("Query failed", e);
		}
		
	}
	
	public List<Verrori> findWithFilters(String appId, String transactionId, Date datastart, Date dataend, String gatewayId, String idgroup) throws VtransazioneDaoException
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
			StringBuffer query = new StringBuffer("select application_id, transaction_id, data, descrizione, atewaypaymodeid, init_date, start_date, finish_date, gateway_id, gateway_description, paymentmode_description, cod_stato, descrizionestatotrans, amount, payurl, currencydescr, pgresultcode, applicationname FROM " + getTableName());
			boolean where = false;
			if (appId != null && !appId.trim().equals(""))
			{
				query.append(" WHERE APPLICATION_ID = '" + appId.trim() + "'");
				where=true;
			}
			if (transactionId !=null && !transactionId.trim().equals(""))
			{
				if (where)
				{
					query.append(" AND ");
				}
				else
				{
					query.append(" WHERE ");
					where=true;
				}
				query.append(" transaction_id = '"+transactionId + "'");
			}
			if (gatewayId !=null && !gatewayId.trim().equals(""))
			{
				if (where)
				{
					query.append(" AND ");
				}
				else
				{
					query.append(" WHERE ");
					where=true;
				}
				query.append(" gateway_id = '"+gatewayId + "'");
			}
	
			if (datastart!=null  && dataend !=null)
			{
				if (where)
				{
					query.append(" AND ");
				}
				else
				{
					query.append(" WHERE ");
					where=true;
				}
				query.append(" INIT_DATE between to_timestamp('" +sdf.format(datastart)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(dataend)+"','YYYYMMDD HH24:MI')" );
			}
			else if (datastart != null)
			{
				if (where)
				{
					query.append(" AND ");
				}
				else
				{
					query.append(" WHERE ");
					where=true;
				}
				query.append(" INIT_DATE >= to_timestamp('" +sdf.format(datastart)+"','YYYYMMDD')"  );
			}
			else if (dataend != null)
			{
				if (where)
				{
					query.append(" AND ");
				}
				else
				{
					query.append(" WHERE ");
					where=true;
				}
				query.append(" INIT_DATE <= to_timestamp('" +sdf.format(dataend)+"','YYYYMMDD')"  );
			}
			
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				if (where)
				{
					query.append(" AND ");
				}
				else
				{
					query.append(" WHERE ");
					where=true;
				}
				query.append(" APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			log.debug("query:"+query.toString());
			return jdbcTemplate.query(query.toString(), this);
		}
		catch (Exception e) {
			throw new VtransazioneDaoException("Query failed:"+e.getMessage(), e);
		}	
	}


}
