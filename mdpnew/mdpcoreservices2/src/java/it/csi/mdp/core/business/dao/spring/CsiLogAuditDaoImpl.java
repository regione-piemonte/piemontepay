/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;
 
import it.csi.mdp.core.business.dao.CsiLogAuditDao;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.CsiLogAudit;
import it.csi.mdp.core.business.dto.CsiLogAuditPk;
import it.csi.mdp.core.business.exceptions.CsiLogAuditDaoException;
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

public class CsiLogAuditDaoImpl extends AbstractDAO implements ParameterizedRowMapper<CsiLogAudit>, CsiLogAuditDao
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
	 * @return CsiLogAuditPk
	 */
	@Transactional
	public CsiLogAuditPk insert(CsiLogAudit dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address,  codfisc ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",dto.getDataOra(),dto.getDescrizione(),dto.getTransactionid(),dto.getApplicationid(),dto.getUtente(),dto.getGatewayid(),dto.getCodappmodify(),dto.getIdaction(),dto.getIdApp(),dto.getOperazione(),dto.getOggOper(),dto.getKeyOper(),dto.getIpAddress(),dto.getCodfisc());
		return dto.createPk();
	}

	

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return CsiLogAudit
	 */
	public CsiLogAudit mapRow(ResultSet rs, int row) throws SQLException
	{
		CsiLogAudit dto = new CsiLogAudit();
		dto.setDataOra( rs.getTimestamp(1 ) );
		dto.setDescrizione( rs.getString( 2 ) );
		dto.setTransactionid( rs.getString( 3 ) );
		dto.setApplicationid( rs.getString( 4 ) );
		dto.setUtente( rs.getInt( 5 ) );
		dto.setGatewayid( rs.getString( 6 ) );
		dto.setCodappmodify( rs.getString( 7 ) );
		dto.setIdaction( rs.getString( 8 ) );
		dto.setIdApp( rs.getString( 9 ) );
		dto.setOperazione( rs.getString( 10 ) );
		dto.setOggOper( rs.getString( 11 ) );
		dto.setKeyOper( rs.getString( 12 ) );
		dto.setIpAddress( rs.getString( 13 ) );
		dto.setUniqueid( rs.getInt( 14 ) );
		dto.setCodfisc( rs.getString( 15 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "csi_log_audit";
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'uniqueid = :uniqueid'.
	 */
	@Transactional
	public CsiLogAudit findByPrimaryKey(int uniqueid) throws CsiLogAuditDaoException
	{
		try {
			List<CsiLogAudit> list = jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE uniqueid = ?", this,uniqueid);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria ''.
	 */
	@Transactional
	public List<CsiLogAudit> findAll() throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " ORDER BY uniqueid", this);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'data_ora = :dataOra'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereDataOraEquals(Date dataOra) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE data_ora = ? ORDER BY data_ora", this,dataOra);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'descrizione = :descrizione'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereDescrizioneEquals(String descrizione) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE descrizione = ? ORDER BY descrizione", this,descrizione);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'transactionid = :transactionid'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereTransactionidEquals(String transactionid) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE transactionid = ? ORDER BY transactionid", this,transactionid);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'applicationid = :applicationid'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereApplicationidEquals(String applicationid) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE applicationid = ? ORDER BY applicationid", this,applicationid);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'utente = :utente'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereUtenteEquals(int utente) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE utente = ? ORDER BY utente", this,utente);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'gatewayid = :gatewayid'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereGatewayidEquals(String gatewayid) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE gatewayid = ? ORDER BY gatewayid", this,gatewayid);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'codappmodify = :codappmodify'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereCodappmodifyEquals(String codappmodify) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE codappmodify = ? ORDER BY codappmodify", this,codappmodify);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'idaction = :idaction'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereIdactionEquals(String idaction) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE idaction = ? ORDER BY idaction", this,idaction);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'id_app = :idApp'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereIdAppEquals(String idApp) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE id_app = ? ORDER BY id_app", this,idApp);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'operazione = :operazione'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereOperazioneEquals(String operazione) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE operazione = ? ORDER BY operazione", this,operazione);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'ogg_oper = :oggOper'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereOggOperEquals(String oggOper) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE ogg_oper = ? ORDER BY ogg_oper", this,oggOper);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'key_oper = :keyOper'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereKeyOperEquals(String keyOper) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE key_oper = ? ORDER BY key_oper", this,keyOper);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'ip_address = :ipAddress'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereIpAddressEquals(String ipAddress) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE ip_address = ? ORDER BY ip_address", this,ipAddress);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'uniqueid = :uniqueid'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereUniqueidEquals(int uniqueid) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE uniqueid = ? ORDER BY uniqueid", this,uniqueid);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'codfisc = :codfisc'.
	 */
	@Transactional
	public List<CsiLogAudit> findWhereCodfiscEquals(String codfisc) throws CsiLogAuditDaoException
	{
		try {
			return jdbcTemplate.query("SELECT data_ora, descrizione, transactionid, applicationid, utente, gatewayid, codappmodify, idaction, id_app, operazione, ogg_oper, key_oper, ip_address, uniqueid, codfisc FROM " + getTableName() + " WHERE codfisc = ? ORDER BY codfisc", this,codfisc);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the csi_log_audit table that matches the specified primary-key value.
	 */
	public CsiLogAudit findByPrimaryKey(CsiLogAuditPk pk) throws CsiLogAuditDaoException
	{
		return findByPrimaryKey( pk.getUniqueid() );
	}
	
	
	
	public List<CsiLogAudit> findWithFilters(List<Application> apps, java.util.Date datastart,java.util.Date dataend,String transactionId, List<String> actions, List<String> users) throws CsiLogAuditDaoException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		try {
			StringBuffer 	query = new StringBuffer("SELECT   csi_log_audit.data_ora,   csi_log_audit.descrizione,  csi_log_audit.transactionid,  csi_log_audit.applicationid,  csi_log_audit.utente,  csi_log_audit.gatewayid,  csi_log_audit.codappmodify,  auditactions.description,  csi_log_audit.id_app,  csi_log_audit.operazione,  csi_log_audit.ogg_oper,  csi_log_audit.key_oper,  csi_log_audit.ip_address,  0 , codfisc FROM csi_log_audit, auditactions WHERE  auditactions.idaction = csi_log_audit.idaction");
			if (apps !=null && apps.size() >0)
			{
				query.append(" AND applicationid in (");
				for (int i=0;i<apps.size();i++)
				{
					String id = apps.get(i).getId();
					query.append("'"+id+"'");
					if (i<apps.size()-1)
					{
						query.append(",");
					}
				}
				query.append(")");
			}
			if (datastart!=null  && dataend !=null)
			{
				query.append(" AND data_ora between to_timestamp('" +sdf.format(datastart)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(dataend)+"','YYYYMMDD HH24:MI')" );
			}
			else if (datastart != null)
			{
				query.append(" AND data_ora >= to_timestamp('" +sdf.format(datastart)+"','YYYYMMDD')"  );
			}
			else if (dataend != null)
			{
				query.append(" AND data_ora <= to_timestamp('" +sdf.format(dataend)+"','YYYYMMDD')"  );
			}
			
			if (transactionId!=null && !transactionId.trim().equals(""))
			{
				query.append(" AND csi_log_audit.transactionid = '" + transactionId.trim() + "'");
			}
			
			if (actions !=null && actions.size() >0)
			{
				query.append(" AND csi_log_audit.idaction in (");
				for (int i=0;i<actions.size();i++)
				{
					String id = actions.get(i).trim();
					query.append("'"+id+"'");
					if (i<actions.size()-1)
					{
						query.append(",");
					}
				}
				query.append(")");
			}	
			if (users !=null && users.size() >0)
			{
				query.append(" AND utente in (");
				for (int i=0;i<users.size();i++)
				{
					String id = users.get(i).trim();
					query.append("'"+id+"'");
					if (i<users.size()-1)
					{
						query.append(",");
					}
				}
				query.append(")");
			}	
			log.debug(this.getClass().getName()+"::findWithFilters query:"+query.toString());
			return jdbcTemplate.query(query.toString(), this);
		}
		catch (Exception e) {
			throw new CsiLogAuditDaoException("Query failed", e);
		}
	}

}
