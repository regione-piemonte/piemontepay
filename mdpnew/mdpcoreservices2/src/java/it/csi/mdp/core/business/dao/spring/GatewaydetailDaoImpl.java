/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.GatewaydetailDao;
import it.csi.mdp.core.business.dto.Gatewaydetail;
import it.csi.mdp.core.business.dto.GatewaydetailPk;
import it.csi.mdp.core.business.exceptions.GatewaydetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class GatewaydetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Gatewaydetail>, GatewaydetailDao
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
	 * @return GatewaydetailPk
	 */
	@Transactional
	public GatewaydetailPk insert(Gatewaydetail dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
				,dto.getGatewayId(),dto.getPaymentmodeId(),dto.getDefaultpaymenturl(),dto.getBackofficeurl(),dto.getMdpgatewaypage(),
				dto.isHttpportNull()?null:dto.getHttpport(),dto.getContextpg(),dto.getReturnurlmdp(),dto.getReceipturl(),
						dto.getErrorurl(),dto.getSendmailonresponse(),dto.getEnabled(),dto.isVerificaesito()
						);
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the GATEWAYDETAIL table.
	 */
	@Transactional
	public void update(GatewaydetailPk pk, Gatewaydetail dto) throws GatewaydetailDaoException
	{
		jdbcTemplate.update("UPDATE " + getTableName() + " SET GATEWAY_ID = ?, PAYMENTMODE_ID = ?, DEFAULTPAYMENTURL = ?, BACKOFFICEURL = ?, MDPGATEWAYPAGE = ?, HTTPPORT = ?, CONTEXTPG = ?, RETURNURLMDP = ?, RECEIPTURL = ?, ERRORURL = ?, SENDMAILONRESPONSE = ?, ENABLED = ?, VERIFICAESITO = ?  WHERE GATEWAY_ID = ? AND PAYMENTMODE_ID = ?",dto.getGatewayId(),dto.getPaymentmodeId(),dto.getDefaultpaymenturl(),dto.getBackofficeurl(),dto.getMdpgatewaypage(),dto.getHttpport(),dto.getContextpg(),dto.getReturnurlmdp(),dto.getReceipturl(),dto.getErrorurl(),dto.getSendmailonresponse(),dto.getEnabled(),dto.isVerificaesito(),pk.getGatewayId(),pk.getPaymentmodeId());
	}

	/** 
	 * Deletes a single row in the GATEWAYDETAIL table.
	 */
	@Transactional
	public void delete(GatewaydetailPk pk) throws GatewaydetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE GATEWAY_ID = ? AND PAYMENTMODE_ID = ?",pk.getGatewayId(),pk.getPaymentmodeId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Gatewaydetail
	 */
	public Gatewaydetail mapRow(ResultSet rs, int row) throws SQLException
	{
		Gatewaydetail dto = new Gatewaydetail();
		dto.setGatewayId( rs.getString( 1 ) );
		dto.setPaymentmodeId( rs.getString( 2 ) );
		dto.setDefaultpaymenturl( rs.getString( 3 ) );
		dto.setBackofficeurl( rs.getString( 4 ) );
		dto.setMdpgatewaypage( rs.getString( 5 ) );
		dto.setHttpport( rs.getLong( 6 ) );
		if (rs.wasNull()) {
			dto.setHttpportNull( true );
		}
		
		dto.setContextpg( rs.getString( 7 ) );
		dto.setReturnurlmdp( rs.getString( 8 ) );
		dto.setReceipturl( rs.getString( 9 ) );
		dto.setErrorurl( rs.getString( 10 ) );
		dto.setSendmailonresponse( rs.getString( 11 ) );
		dto.setEnabled( rs.getString( 12 ) );
		dto.setVerificaesito(rs.getBoolean(13));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "GATEWAYDETAIL";
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'GATEWAY_ID = :gatewayId AND PAYMENTMODE_ID = :paymentmodeId'.
	 */
	@Transactional
	public Gatewaydetail findByPrimaryKey(String gatewayId, String paymentmodeId) throws GatewaydetailDaoException
	{
		try {
			List<Gatewaydetail> list = jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED,  VERIFICAESITO  FROM " + getTableName() + " WHERE GATEWAY_ID = ? AND PAYMENTMODE_ID = ? ", this,gatewayId,paymentmodeId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria ''.
	 */
	@Transactional
	public List<Gatewaydetail> findAll() throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " ORDER BY GATEWAY_ID, PAYMENTMODE_ID", this);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereGatewayIdEquals(String gatewayId) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED,  VERIFICAESITO FROM " + getTableName() + " WHERE GATEWAY_ID = ? ORDER BY GATEWAY_ID", this,gatewayId);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'PAYMENTMODE_ID = :paymentmodeId'.
	 */
	@Transactional
	public List<Gatewaydetail> findWherePaymentmodeIdEquals(String paymentmodeId) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED,  VERIFICAESITO FROM " + getTableName() + " WHERE PAYMENTMODE_ID = ? ORDER BY PAYMENTMODE_ID", this,paymentmodeId);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'DEFAULTPAYMENTURL = :defaultpaymenturl'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereDefaultpaymenturlEquals(String defaultpaymenturl) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE DEFAULTPAYMENTURL = ? ORDER BY DEFAULTPAYMENTURL", this,defaultpaymenturl);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'BACKOFFICEURL = :backofficeurl'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereBackofficeurlEquals(String backofficeurl) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE BACKOFFICEURL = ? ORDER BY BACKOFFICEURL", this,backofficeurl);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'MDPGATEWAYPAGE = :mdpgatewaypage'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereMdpgatewaypageEquals(String mdpgatewaypage) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE MDPGATEWAYPAGE = ? ORDER BY MDPGATEWAYPAGE", this,mdpgatewaypage);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'HTTPPORT = :httpport'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereHttpportEquals(long httpport) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE HTTPPORT = ? ORDER BY HTTPPORT", this,httpport);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'CONTEXTPG = :contextpg'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereContextpgEquals(String contextpg) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE CONTEXTPG = ? ORDER BY CONTEXTPG", this,contextpg);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'RETURNURLMDP = :returnurlmdp'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereReturnurlmdpEquals(String returnurlmdp) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE RETURNURLMDP = ? ORDER BY RETURNURLMDP", this,returnurlmdp);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'RECEIPTURL = :receipturl'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereReceipturlEquals(String receipturl) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE RECEIPTURL = ? ORDER BY RECEIPTURL", this,receipturl);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'ERRORURL = :errorurl'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereErrorurlEquals(String errorurl) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE ERRORURL = ? ORDER BY ERRORURL", this,errorurl);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'SENDMAILONRESPONSE = :sendmailonresponse'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereSendmailonresponseEquals(String sendmailonresponse) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE SENDMAILONRESPONSE = ? ORDER BY SENDMAILONRESPONSE", this,sendmailonresponse);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns all rows from the GATEWAYDETAIL table that match the criteria 'ENABLED = :enabled'.
	 */
	@Transactional
	public List<Gatewaydetail> findWhereEnabledEquals(String enabled) throws GatewaydetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT GATEWAY_ID, PAYMENTMODE_ID, DEFAULTPAYMENTURL, BACKOFFICEURL, MDPGATEWAYPAGE, HTTPPORT, CONTEXTPG, RETURNURLMDP, RECEIPTURL, ERRORURL, SENDMAILONRESPONSE, ENABLED, VERIFICAESITO FROM " + getTableName() + " WHERE ENABLED = ? ORDER BY ENABLED", this,enabled);
		}
		catch (Exception e) {
			throw new GatewaydetailDaoException("Query failed:"+e.getMessage(), e);
		}
		
	}

	/** 
	 * Returns the rows from the GATEWAYDETAIL table that matches the specified primary-key value.
	 */
	public Gatewaydetail findByPrimaryKey(GatewaydetailPk pk) throws GatewaydetailDaoException
	{
		return findByPrimaryKey( pk.getGatewayId(), pk.getPaymentmodeId() );
	}

}
