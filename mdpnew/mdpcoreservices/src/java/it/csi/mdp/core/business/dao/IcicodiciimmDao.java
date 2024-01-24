/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.IcicodiciimmDao;
import it.csi.mdp.core.business.dto.Icicodiciimm;
import it.csi.mdp.core.business.exceptions.IcicodiciimmDaoException;
import java.util.List;

public interface IcicodiciimmDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Icicodiciimm dto);

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria ''.
	 */
	public List<Icicodiciimm> findAll() throws IcicodiciimmDaoException;

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria 'APPLICATIONID = :applicationid'.
	 */
	public List<Icicodiciimm> findWhereApplicationidEquals(String applicationid) throws IcicodiciimmDaoException;

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria 'CODICEIMM = :codiceimm'.
	 */
	public List<Icicodiciimm> findWhereCodiceimmEquals(String codiceimm) throws IcicodiciimmDaoException;

	/** 
	 * Returns all rows from the ICICODICIIMM table that match the criteria 'CAUSALE = :causale'.
	 */
	public List<Icicodiciimm> findWhereCausaleEquals(String causale) throws IcicodiciimmDaoException;

}
