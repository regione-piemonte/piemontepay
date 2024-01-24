/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.VtransazioneDao;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.Vtransazione;
import it.csi.mdp.core.business.exceptions.TransazioneDaoException;
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

public class VtransazioneDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Vtransazione>, VtransazioneDao
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
	 * @return Vtransazione
	 */
	public Vtransazione mapRow(ResultSet rs, int row) throws SQLException
	{
		Vtransazione dto = new Vtransazione();
		dto.setTransactionId( rs.getString( 1 ) );
		dto.setApplicationId( rs.getString( 2 ) );
		dto.setLanguage( rs.getString( 3 ) );
		dto.setBuyerEmail( rs.getString( 4 ) );
		dto.setBasketId( rs.getString( 5 ) );
		dto.setCommissioniApplicate( rs.getFloat( 6 ) );
		if (rs.wasNull()) {
			dto.setCommissioniApplicateNull( true );
		}
		
		dto.setInitDate( rs.getTimestamp(7 ) );
		dto.setStartDate( rs.getTimestamp(8 ) );
		dto.setFinishDate( rs.getTimestamp(9 ) );
		dto.setGatewaypaymodeid( rs.getString( 10 ) );
		dto.setAmount( rs.getFloat( 11 ) );
		if (rs.wasNull()) {
			dto.setAmountNull( true );
		}
		
		dto.setMscsorderid( rs.getLong( 12 ) );
		if (rs.wasNull()) {
			dto.setMscsorderidNull( true );
		}
		
		dto.setMerchantId( rs.getString( 13 ) );
		dto.setPgresultcode( rs.getString( 14 ) );
		dto.setProvidertimestamp( rs.getString( 15 ) );
		dto.setAuthornumber( rs.getString( 16 ) );
		dto.setOpernumber( rs.getString( 17 ) );
		dto.setRispcomp( rs.getString( 18 ) );
		dto.setErrcode( rs.getString( 19 ) );
		dto.setBuyername( rs.getString( 20 ) );
		dto.setBuyercodfisc( rs.getString( 21 ) );
		dto.setOldstate( rs.getLong( 22 ) );
		if (rs.wasNull()) {
			dto.setOldstateNull( true );
		}
		
		dto.setChangestatedate( rs.getTimestamp(23 ) );
		dto.setUserhaschange( rs.getString( 24 ) );
		dto.setClientipaddress( rs.getString( 25 ) );
		dto.setIncassokoerrormessage( rs.getString( 26 ) );
		dto.setIntestatariocc( rs.getString( 27 ) );
		dto.setPaymentid( rs.getString( 28 ) );
		dto.setPayurl( rs.getString( 29 ) );
		dto.setMdplanguagecode( rs.getString( 30 ) );
		dto.setCcy( rs.getString( 31 ) );
		dto.setCurrencydescr( rs.getString( 32 ) );
		dto.setGatewayId( rs.getString( 33 ) );
		dto.setDescrizione( rs.getString( 34 ) );
		dto.setCodStato( rs.getLong( 35 ) );
		if (rs.wasNull()) {
			dto.setCodStatoNull( true );
		}
		
		dto.setGatewayDescription( rs.getString( 36 ) );
		dto.setPaymentmodeDescription( rs.getString( 37 ) );
		dto.setApplicationname( rs.getString( 38 ) );
		dto.setCliente( rs.getString( 39 ) );
		dto.setProgetto( rs.getString( 40 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "vtransazione";
	}

	/** 
	 * Returns all rows from the vtransazione table that match the criteria ''.
	 */
	@Transactional
	public List<Vtransazione> findAll(String idgroup) throws VtransazioneDaoException
	{
		
		
		try {
			StringBuffer query = new StringBuffer("SELECT transaction_id, application_id, language, buyer_email, basket_id, commissioni_applicate, init_date, start_date, finish_date, gatewaypaymodeid, amount, mscsorderid, merchant_id, pgresultcode, providertimestamp, authornumber, opernumber, rispcomp, errcode, buyername, buyercodfisc, oldstate, changestatedate, userhaschange, clientipaddress, incassokoerrormessage, intestatariocc, paymentid, payurl, mdplanguagecode, ccy, currencydescr, gateway_id, descrizione, cod_stato, gateway_description, paymentmode_description, applicationname, cliente, progetto FROM " + getTableName() );
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" WHERE APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			query.append(" ORDER BY INIT_DATE DESC LIMIT 500");
			return jdbcTemplate.query(query.toString(), this);
		}
		catch (Exception e) {
			throw new VtransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the vtransazione table that match the criteria 'transaction_id = :transactionId'.
	 */
	@Transactional
	public List<Vtransazione> findWhereTransactionIdEquals(String transactionId, String idgroup) throws VtransazioneDaoException
	{
		try {
			StringBuffer query = new StringBuffer("SELECT transaction_id, application_id, language, buyer_email, basket_id, commissioni_applicate, init_date, start_date, finish_date, gatewaypaymodeid, amount, mscsorderid, merchant_id, pgresultcode, providertimestamp, authornumber, opernumber, rispcomp, errcode, buyername, buyercodfisc, oldstate, changestatedate, userhaschange, clientipaddress, incassokoerrormessage, intestatariocc, paymentid, payurl, mdplanguagecode, ccy, currencydescr, gateway_id, descrizione, cod_stato, gateway_description, paymentmode_description, applicationname, cliente, progetto FROM " + getTableName() + " WHERE transaction_id = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			
			return jdbcTemplate.query(query.toString(), this,transactionId);
		}
		catch (Exception e) {
			throw new VtransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	@Transactional
	public List<Vtransazione> findWhereApplicationIdEquals(String appId, String idgroup) throws VtransazioneDaoException
	{
		try {
			StringBuffer query = new StringBuffer("SELECT transaction_id, application_id, language, buyer_email, basket_id, commissioni_applicate, init_date, start_date, finish_date, gatewaypaymodeid, amount, mscsorderid, merchant_id, pgresultcode, providertimestamp, authornumber, opernumber, rispcomp, errcode, buyername, buyercodfisc, oldstate, changestatedate, userhaschange, clientipaddress, incassokoerrormessage, intestatariocc, paymentid, payurl, mdplanguagecode, ccy, currencydescr, gateway_id, descrizione, cod_stato, gateway_description, paymentmode_description, applicationname, cliente, progetto FROM " + getTableName() + " WHERE application_id = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" WHERE APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			query.append(" ORDER BY INIT_DATE DESC LIMIT 500");
			return jdbcTemplate.query(query.toString(), this,appId);
		}
		catch (Exception e) {
			throw new VtransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	
	public List<Vtransazione> findWithFilters(String appId, long codstato, Date datastart, Date dataend, String idgroup) throws VtransazioneDaoException
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
			StringBuffer query = new StringBuffer("SELECT transaction_id, application_id, language, buyer_email, basket_id, commissioni_applicate, init_date, start_date, finish_date, gatewaypaymodeid, amount, mscsorderid, merchant_id, pgresultcode, providertimestamp, authornumber, opernumber, rispcomp, errcode, buyername, buyercodfisc, oldstate, changestatedate, userhaschange, clientipaddress, incassokoerrormessage, intestatariocc, paymentid, payurl, mdplanguagecode, ccy, currencydescr, gateway_id, descrizione, cod_stato, gateway_description, paymentmode_description, applicationname, cliente, progetto FROM " + getTableName());
			boolean where = false;
			if (appId != null && !appId.trim().equals(""))
			{
				query.append(" WHERE APPLICATION_ID = '" + appId.trim() + "'");
				where=true;
			}
			if (codstato != -1 )
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
				query.append(" COD_STATO = '"+codstato + "'");
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
			
			query.append(" ORDER BY INIT_DATE DESC LIMIT 500");
			log.debug("query:"+query.toString());
			return jdbcTemplate.query(query.toString(), this);
		}
		catch (Exception e) {
			throw new VtransazioneDaoException("Query failed:"+e.getMessage(), e);
		}	
	}
	public int findWithFiltersCount(String appId, long codstato, Date datastart, Date dataend, String idgroup) throws VtransazioneDaoException
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
			StringBuffer query = new StringBuffer("SELECT count(*) FROM " + getTableName());
			boolean where = false;
			if (appId != null && !appId.trim().equals(""))
			{
				query.append(" WHERE APPLICATION_ID = '" + appId.trim() + "'");
				where=true;
			}
			if (codstato != -1 )
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
				query.append(" COD_STATO = '"+codstato + "'");
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
			
			//query.append(" ORDER BY INIT_DATE DESC LIMIT 500");
			log.debug("query:"+query.toString());
			return jdbcTemplate.queryForInt(query.toString());
		}
		catch (Exception e) {
			throw new VtransazioneDaoException("Query failed:"+e.getMessage(), e);
		}	
	}


}
