/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.CommissionDao;
import it.csi.mdp.core.business.dto.Commission;
import it.csi.mdp.core.business.dto.CommissionPk;
import it.csi.mdp.core.business.exceptions.CommissionDaoException;
import java.util.List;

public interface CommissionDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CommissionPk
	 */
	public CommissionPk insert(Commission dto);

	/** 
	 * Updates a single row in the COMMISSION table.
	 */
	public void update(CommissionPk pk, Commission dto) throws CommissionDaoException;

	/** 
	 * Deletes a single row in the COMMISSION table.
	 */
	public void delete(CommissionPk pk) throws CommissionDaoException;

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria 'COMMISSION_ID = :commissionId'.
	 */
	public Commission findByPrimaryKey(String commissionId) throws CommissionDaoException;

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria ''.
	 */
	public List<Commission> findAll() throws CommissionDaoException;

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria 'COMMISSION_ID = :commissionId'.
	 */
	public List<Commission> findWhereCommissionIdEquals(String commissionId) throws CommissionDaoException;

	/** 
	 * Returns all rows from the COMMISSION table that match the criteria 'COMMISSION_DESCRIPTION = :commissionDescription'.
	 */
	public List<Commission> findWhereCommissionDescriptionEquals(String commissionDescription) throws CommissionDaoException;

	/** 
	 * Returns the rows from the COMMISSION table that matches the specified primary-key value.
	 */
	public Commission findByPrimaryKey(CommissionPk pk) throws CommissionDaoException;

}
