/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.TransazioneDao;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazionePk;
import it.csi.mdp.core.business.exceptions.TransazioneDaoException;
import it.csi.mdp.core.util.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class TransazioneDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Transazione>, TransazioneDao
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
	 * @return TransazionePk
	 */
	@Transactional
	public TransazionePk insert(Transazione dto)
	{
		//super.res.getString("INIT_TRANSAZIONE");
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		log.debug("insert: "+ "INSERT INTO " + getTableName() + " ( TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE,  OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID,PAYURL ) VALUES ( ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" + dto.toString());
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE,  OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID,PAYURL ) VALUES ( ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				dto.getTransactionId(),dto.getApplicationId(),dto.getLanguage(),dto.getCcy(),dto.getBasketId(),dto.getCodStato(),dto.isCommissioniApplicateNull()?null:dto.getCommissioniApplicate(),dto.getStartDate(),dto.getFinishDate(),dto.getGatewayId(),dto.getGatewaypaymodeid(),dto.getAmount(),dto.isMscsorderidNull()?null:dto.getMscsorderid(),dto.getMerchantId(),dto.getPgresultcode(),dto.getProvidertimestamp(),dto.getAuthornumber(),dto.getOpernumber(),dto.getRispcomp(),substring(dto.getErrcode(), 0, 50),dto.getOldstate(),dto.getChangestatedate(),dto.getUserhaschange(),dto.getClientipaddress(),dto.getIncassokoerrormessage(),dto.getIntestatariocc(),dto.getPaymentid(),(dto.getPayurl()==null?"":(dto.getPayurl().length()>5000?dto.getPayurl().substring(0,4999):dto.getPayurl())));
		return dto.createPk();
	}
	
    @Transactional
    public List<Transazione> recuperaApplicationIdFromIuv ( String iuv ) throws TransazioneDaoException {
        
        String fields = "TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL";
        
        String query
            = "select " + fields + " from " + getTableName() + " where transaction_id in (select transaction_id from transazione_iuv where iuv = '" + iuv + "')";

        try {
            return jdbcTemplate.query ( query, this);
        } catch ( Exception e ) {
            throw new TransazioneDaoException ( "Query failed:" + e.getMessage (), e );
        }
    }

	/** 
	 * Updates a single row in the TRANSAZIONE table.
	 */
	@Transactional
	public void update(TransazionePk pk, Transazione dto) throws TransazioneDaoException
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		log.debug("update transazione: UPDATE " + getTableName() + " SET TRANSACTION_ID = ?, APPLICATION_ID = ?, LANGUAGE = ?, CCY = ?, BASKET_ID = ?, COD_STATO = ?, COMMISSIONI_APPLICATE = ?,  START_DATE = to_timestamp(?,'dd/mm/yyyy HH24:MI:SS') , FINISH_DATE = to_timestamp(?,'dd/mm/yyyy HH24:MI:SS'), GATEWAY_ID = ?, GATEWAYPAYMODEID = ?, AMOUNT = ?, MSCSORDERID = ?, MERCHANT_ID = ?, PGRESULTCODE = ?, PROVIDERTIMESTAMP = ?, AUTHORNUMBER = ?, OPERNUMBER = ?, RISPCOMP = ?, ERRCODE = ?,  OLDSTATE = ?, CHANGESTATEDATE = to_timestamp(?,'dd/mm/yyyy HH24:MI:SS'), USERHASCHANGE = ?, CLIENTIPADDRESS = ?, INCASSOKOERRORMESSAGE = ?, INTESTATARIOCC = ?, PAYMENTID = ?, PAYURL = ? WHERE TRANSACTION_ID = ? " + dto.toString());
		jdbcTemplate.update("UPDATE " + getTableName() + " SET TRANSACTION_ID = ?, APPLICATION_ID = ?, LANGUAGE = ?, CCY = ?, BASKET_ID = ?, COD_STATO = ?, COMMISSIONI_APPLICATE = ?,  START_DATE = to_timestamp(?,'dd/mm/yyyy HH24:MI:SS') , FINISH_DATE = to_timestamp(?,'dd/mm/yyyy HH24:MI:SS'), GATEWAY_ID = ?, GATEWAYPAYMODEID = ?, AMOUNT = ?, MSCSORDERID = ?, MERCHANT_ID = ?, PGRESULTCODE = ?, PROVIDERTIMESTAMP = ?, AUTHORNUMBER = ?, OPERNUMBER = ?, RISPCOMP = ?, ERRCODE = ?,  OLDSTATE = ?, CHANGESTATEDATE = to_timestamp(?,'dd/mm/yyyy HH24:MI:SS'), USERHASCHANGE = ?, CLIENTIPADDRESS = ?, INCASSOKOERRORMESSAGE = ?, INTESTATARIOCC = ?, PAYMENTID = ?, PAYURL = ? WHERE TRANSACTION_ID = ?"
				                                          ,dto.getTransactionId(),dto.getApplicationId(),dto.getLanguage(),dto.getCcy(),dto.getBasketId(),dto.getCodStato(),dto.isCommissioniApplicateNull()?null:dto.getCommissioniApplicate(),(dto.getStartDate()==null)?null:sdf.format(dto.getStartDate()),(dto.getFinishDate()==null)?null:sdf.format(dto.getFinishDate()),dto.getGatewayId(),dto.getGatewaypaymodeid(),dto.getAmount(),dto.isMscsorderidNull()?null:dto.getMscsorderid(),dto.getMerchantId(),dto.getPgresultcode(),dto.getProvidertimestamp(),dto.getAuthornumber(),dto.getOpernumber(),dto.getRispcomp(),substring(dto.getErrcode(), 0, 50),dto.getOldstate(),(dto.getChangestatedate()==null)?null:sdf.format(dto.getChangestatedate()),dto.getUserhaschange(),dto.getClientipaddress(),dto.getIncassokoerrormessage(),dto.getIntestatariocc(),dto.getPaymentid(),(dto.getPayurl()==null?"":(dto.getPayurl().length()>5000?dto.getPayurl().substring(0,4999):dto.getPayurl())),pk.getTransactionId());
	}

	/** 
	 * Deletes a single row in the TRANSAZIONE table.
	 */
	@Transactional
	public void delete(TransazionePk pk) throws TransazioneDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE TRANSACTION_ID = ?",pk.getTransactionId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Transazione
	 */
	public Transazione mapRow(ResultSet rs, int row) throws SQLException
	{
		Transazione dto = new Transazione();
		dto.setTransactionId( rs.getString( 1 ) );
		dto.setApplicationId( rs.getString( 2 ) );
		dto.setLanguage( rs.getString( 3 ) );
		dto.setCcy( rs.getString( 4 ) );
		dto.setBuyerEmail( rs.getString( 5 ) );
		dto.setBasketId( rs.getString( 6 ) );
		dto.setCodStato( rs.getLong( 7 ) );
		dto.setCommissioniApplicate( rs.getDouble( 8 ) );
		if (rs.wasNull()) 
		{
			dto.setCommissioniApplicateNull( true );
		}
		
		dto.setInitDate( rs.getTimestamp(9 ) );
		dto.setStartDate( rs.getTimestamp(10 ) );
		dto.setFinishDate( rs.getTimestamp(11 ) );
		dto.setGatewayId( rs.getString( 12 ) );
		dto.setGatewaypaymodeid( rs.getString( 13 ) );
		dto.setAmount( rs.getDouble( 14 ) );
		dto.setMscsorderid( rs.getLong( 15 ) );
		if (rs.wasNull()) {
			dto.setMscsorderidNull( true );
		}
		
		
		dto.setMerchantId( rs.getString( 16 ) );
		dto.setPgresultcode( rs.getString( 17 ) );
		dto.setProvidertimestamp( rs.getString( 18 ) );
		dto.setAuthornumber( rs.getString( 19 ) );
		dto.setOpernumber( rs.getString( 20 ) );
		dto.setRispcomp( rs.getString( 21 ) );
		dto.setErrcode( rs.getString( 22 ) );
		dto.setBuyername( rs.getString( 23 ) );
		dto.setBuyercodfisc( rs.getString( 24 ) );
		dto.setOldstate( rs.getLong( 25 ) );
		dto.setChangestatedate( rs.getTimestamp(26 ) );
		dto.setUserhaschange( rs.getString( 27 ) );
		dto.setClientipaddress( rs.getString( 28 ) );
		dto.setIncassokoerrormessage( rs.getString( 29 ) );
		dto.setIntestatariocc( rs.getString( 30 ) );
		dto.setPaymentid( rs.getString( 31 ) );
		dto.setPayurl(rs.getString(32));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "TRANSAZIONE";
	}

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	@Transactional
	public Transazione findByPrimaryKey(String transactionId,String idgroup) throws TransazioneDaoException
	{
		StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE TRANSACTION_ID = ? ");
		try {
			//query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE TRANSACTION_ID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			log.debug("mdpnew findbyprimarykey::query="+query.toString());
			List<Transazione> list = jdbcTemplate.query(query.toString(), this,transactionId);	
			if (list==null) return null;
			log.debug("mdpnew findbyprimarykey::transactions found ="+list.size());
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query " + query.toString()+ " failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria ''.
	 */
	@Transactional
	public List<Transazione> findAll(String idgroup) throws TransazioneDaoException
	{
		try {
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() );
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" WHERE APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	@Transactional
	public List<Transazione> findWhereTransactionIdEquals(String transactionId,String idgroup) throws TransazioneDaoException
	{
		try {
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName()+ " WHERE TRANSACTION_ID = ? " );
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,transactionId);
			
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'APPLICATION_ID = :applicationId'.
	 */
	@Transactional
	public List<Transazione> findWhereApplicationIdEquals(String applicationId,String idgroup) throws TransazioneDaoException
	{
		try {
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName()+ " WHERE APPLICATION_ID = ? " );
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,applicationId);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}


	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'BASKET_ID = :basketId'.
	 */
	@Transactional
	public List<Transazione> findWhereBasketIdEquals(String basketId,String idgroup) throws TransazioneDaoException
	{
		try {
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE BASKET_ID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,basketId);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'COD_STATO = :codStato'.
	 */
	@Transactional
	public List<Transazione> findWhereCodStatoEquals(long codStato,String idgroup) throws TransazioneDaoException
	{
		try 
		{
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE COD_STATO = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,codStato);
		}
		catch (Exception e) 
		{
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}


	







	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	@Transactional
	public List<Transazione> findWhereGatewayIdEquals(String gatewayId,String idgroup) throws TransazioneDaoException
	{
		try {
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE GATEWAY_ID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,gatewayId);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'GATEWAYPAYMODEID = :gatewaypaymodeid'.
	 */
	@Transactional
	public List<Transazione> findWhereGatewaypaymodeidEquals(String gatewaypaymodeid,String idgroup) throws TransazioneDaoException
	{
		try {
			
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE GATEWAYPAYMODEID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,gatewaypaymodeid);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}
	
	
	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'GATEWAYPAYMODEID = :gatewaypaymodeid'.
	 */
	@Transactional
	public List<Transazione> findWherePaymentidEquals(String paymodeid,String idgroup) throws TransazioneDaoException
	{
		try {
			
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE PAYMENTID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,paymodeid);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'GATEWAYPAYMODEID = :gatewaypaymodeid'.
	 */
	@Transactional
	public List<Transazione> findWhereAuthorNumberEquals(String authorNumber,String idgroup) throws TransazioneDaoException
	{
		try {
			
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE AUTHORNUMBER = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,authorNumber);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}



	/** 
	 * Returns all rows from the TRANSAZIONE table that match the criteria 'MERCHANT_ID = :merchantId'.
	 */
	@Transactional
	public List<Transazione> findWhereMerchantIdEquals(String merchantId,String idgroup) throws TransazioneDaoException
	{
		try {
			
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() + " WHERE MERCHANT_ID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,merchantId);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}


	public long getNextTransazioneId() throws TransazioneDaoException
	{
		try {
			
			
			
			return this.jdbcTemplate.queryForLong("select nextval('seq_transazione')");
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}	
	}
	
	
	
	/** 
	 * Returns the rows from the TRANSAZIONE table that matches the specified primary-key value.
	 */
	public Transazione findByPrimaryKey(TransazionePk pk,String idgroup) throws TransazioneDaoException
	{
		return findByPrimaryKey( pk.getTransactionId(), idgroup );
	}
	
	public List<Transazione> findWhereApplicationAndGatewayEquals(String applicationid,String gatewayid,String idgroup) throws TransazioneDaoException
	{
		try {
			
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() +
					" WHERE APPLICATION_ID = ? AND GATEWAY_ID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,applicationid, gatewayid);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
	}

	public List<Transazione> findWhereApplicationAndGatewayAndPaymodeEquals(String applicationid,String gatewayid,String paymode,String idgroup) throws TransazioneDaoException
	{
		try {
			
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() +
					" WHERE APPLICATION_ID = ? AND GATEWAY_ID = ? AND GATEWAYPAYMODEID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,applicationid, gatewayid,paymode);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
	}
	
	@Transactional
	public List<Transazione> findWhereGatewayidAndPaymodeidEquals(String gatewayid, String gatewaypaymodeid,String idgroup) throws TransazioneDaoException
	{
		try {
			
			StringBuffer query = new StringBuffer("SELECT TRANSACTION_ID, APPLICATION_ID, LANGUAGE, CCY, BUYER_EMAIL, BASKET_ID, COD_STATO, COMMISSIONI_APPLICATE, INIT_DATE, START_DATE, FINISH_DATE, GATEWAY_ID, GATEWAYPAYMODEID, AMOUNT, MSCSORDERID, MERCHANT_ID, PGRESULTCODE, PROVIDERTIMESTAMP, AUTHORNUMBER, OPERNUMBER, RISPCOMP, ERRCODE, BUYERNAME, BUYERCODFISC, OLDSTATE, CHANGESTATEDATE, USERHASCHANGE, CLIENTIPADDRESS, INCASSOKOERRORMESSAGE, INTESTATARIOCC, PAYMENTID, PAYURL FROM " + getTableName() +
					" WHERE GATEWAY_ID = ? ADN GATEWAYPAYMODEID = ? ");
			if (idgroup!=null && !idgroup.trim().equals(""))
			{
				query.append(" AND APPLICATION_ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = "+idgroup+")");
			}
			return jdbcTemplate.query(query.toString(), this,gatewayid,gatewaypaymodeid);
		}
		catch (Exception e) {
			throw new TransazioneDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	private String substring(String inputString, int start, int forChars) {
		if (inputString == null) return null;
		
		int end = (start + forChars) > inputString.length() ? inputString.length() : (start + forChars);
		return inputString.substring(start, end);
	}

	public Transazione getTransazioneByIdSession(String idSession) throws TransazioneDaoException {
		try {
			return jdbcTemplate.queryForObject("select t.* from " + getTableName() + " t, transazione_idsession s where t.transaction_id = s.transaction_id and s.id_session=? ", this, idSession);
		} catch (EmptyResultDataAccessException e ) {
			return null;
		}
	}

	public void updateIdSession(String transacionId, String idSession) throws TransazioneDaoException {
		jdbcTemplate.update("insert into transazione_idsession (transaction_id, id_session) values (?,?) ", transacionId, idSession);
	}
	
	public void insertTransazioneIuv(String transacionId, String iuv) throws TransazioneDaoException {
		jdbcTemplate.update("insert into transazione_iuv (transaction_id, iuv) values (?,?) ", transacionId, iuv);
	}

//	public List<StatisticaApplicazioneTransazione> getStatisticaTransazioneXApp(String transacionId, Date dataDa, Date dataA) throws TransazioneDaoException {
//		return null;
//	}
	
}
