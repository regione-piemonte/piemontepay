/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.AuditactionsDao;
import it.csi.mdp.core.business.dto.Auditactions;
import it.csi.mdp.core.business.dto.AuditactionsPk;
import it.csi.mdp.core.business.exceptions.AuditactionsDaoException;
import java.util.List;

public interface AuditactionsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return AuditactionsPk
	 */
	public AuditactionsPk insert(Auditactions dto);

	/** 
	 * Updates a single row in the auditactions table.
	 */
	public void update(AuditactionsPk pk, Auditactions dto) throws AuditactionsDaoException;

	/** 
	 * Deletes a single row in the auditactions table.
	 */
	public void delete(AuditactionsPk pk) throws AuditactionsDaoException;

	/** 
	 * Returns all rows from the auditactions table that match the criteria 'idaction = :idaction'.
	 */
	public Auditactions findByPrimaryKey(String idaction) throws AuditactionsDaoException;

	/** 
	 * Returns all rows from the auditactions table that match the criteria ''.
	 */
	public List<Auditactions> findAll() throws AuditactionsDaoException;

	/** 
	 * Returns all rows from the auditactions table that match the criteria 'idaction = :idaction'.
	 */
	public List<Auditactions> findWhereIdactionEquals(String idaction) throws AuditactionsDaoException;

	/** 
	 * Returns all rows from the auditactions table that match the criteria 'description = :description'.
	 */
	public List<Auditactions> findWhereDescriptionEquals(String description) throws AuditactionsDaoException;

	/** 
	 * Returns the rows from the auditactions table that matches the specified primary-key value.
	 */
	public Auditactions findByPrimaryKey(AuditactionsPk pk) throws AuditactionsDaoException;

}
