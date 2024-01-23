/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.AuthorizationsDao;
import it.csi.mdp.core.business.dto.Authorizations;
import it.csi.mdp.core.business.dto.AuthorizationsPk;
import it.csi.mdp.core.business.exceptions.AuthorizationsDaoException;
import java.util.List;

public interface AuthorizationsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return AuthorizationsPk
	 */
	public AuthorizationsPk insert(Authorizations dto);

	/** 
	 * Updates a single row in the authorizations table.
	 */
	public void update(AuthorizationsPk pk, Authorizations dto) throws AuthorizationsDaoException;

	/** 
	 * Deletes a single row in the authorizations table.
	 */
	public void delete(AuthorizationsPk pk) throws AuthorizationsDaoException;

	/** 
	 * Returns all rows from the authorizations table that match the criteria 'idrole = :idrole AND operazione = :operazione'.
	 */
	public Authorizations findByPrimaryKey(int idrole, String operazione) throws AuthorizationsDaoException;

	/** 
	 * Returns all rows from the authorizations table that match the criteria ''.
	 */
	public List<Authorizations> findAll() throws AuthorizationsDaoException;

	/** 
	 * Returns all rows from the authorizations table that match the criteria 'operazione = :operazione'.
	 */
	public List<Authorizations> findWhereOperazioneEquals(String operazione) throws AuthorizationsDaoException;

	/** 
	 * Returns all rows from the authorizations table that match the criteria 'idrole = :idrole'.
	 */
	public List<Authorizations> findWhereIdroleEquals(int idrole) throws AuthorizationsDaoException;
	
	/** 
	 * Returns the rows from the authorizations table that matches the specified primary-key value.
	 */
	public Authorizations findByPrimaryKey(AuthorizationsPk pk) throws AuthorizationsDaoException;

}
