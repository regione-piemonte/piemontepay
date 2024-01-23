/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.CsiLogAuditDao;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.CsiLogAudit;
import it.csi.mdp.core.business.dto.CsiLogAuditPk;
import it.csi.mdp.core.business.exceptions.CsiLogAuditDaoException;
import java.util.Date;
import java.util.List;

public interface CsiLogAuditDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CsiLogAuditPk
	 */
	public CsiLogAuditPk insert(CsiLogAudit dto);


	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'uniqueid = :uniqueid'.
	 */
	public CsiLogAudit findByPrimaryKey(int uniqueid) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria ''.
	 */
	public List<CsiLogAudit> findAll() throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'data_ora = :dataOra'.
	 */
	public List<CsiLogAudit> findWhereDataOraEquals(Date dataOra) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'descrizione = :descrizione'.
	 */
	public List<CsiLogAudit> findWhereDescrizioneEquals(String descrizione) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'transactionid = :transactionid'.
	 */
	public List<CsiLogAudit> findWhereTransactionidEquals(String transactionid) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'applicationid = :applicationid'.
	 */
	public List<CsiLogAudit> findWhereApplicationidEquals(String applicationid) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'utente = :utente'.
	 */
	public List<CsiLogAudit> findWhereUtenteEquals(int utente) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'gatewayid = :gatewayid'.
	 */
	public List<CsiLogAudit> findWhereGatewayidEquals(String gatewayid) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'codappmodify = :codappmodify'.
	 */
	public List<CsiLogAudit> findWhereCodappmodifyEquals(String codappmodify) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'idaction = :idaction'.
	 */
	public List<CsiLogAudit> findWhereIdactionEquals(String idaction) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'id_app = :idApp'.
	 */
	public List<CsiLogAudit> findWhereIdAppEquals(String idApp) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'operazione = :operazione'.
	 */
	public List<CsiLogAudit> findWhereOperazioneEquals(String operazione) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'ogg_oper = :oggOper'.
	 */
	public List<CsiLogAudit> findWhereOggOperEquals(String oggOper) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'key_oper = :keyOper'.
	 */
	public List<CsiLogAudit> findWhereKeyOperEquals(String keyOper) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'ip_address = :ipAddress'.
	 */
	public List<CsiLogAudit> findWhereIpAddressEquals(String ipAddress) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'uniqueid = :uniqueid'.
	 */
	public List<CsiLogAudit> findWhereUniqueidEquals(int uniqueid) throws CsiLogAuditDaoException;

	/** 
	 * Returns all rows from the csi_log_audit table that match the criteria 'codfisc = :codfisc'.
	 */
	public List<CsiLogAudit> findWhereCodfiscEquals(String codfisc) throws CsiLogAuditDaoException;

	/** 
	 * Returns the rows from the csi_log_audit table that matches the specified primary-key value.
	 */
	public CsiLogAudit findByPrimaryKey(CsiLogAuditPk pk) throws CsiLogAuditDaoException;
	public List<CsiLogAudit> findWithFilters(List<Application> apps, java.util.Date datastart,java.util.Date dataend,String transactionId, List<String> actions, List<String> users) throws CsiLogAuditDaoException;
}
