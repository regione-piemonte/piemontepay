/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.BasketDao;
import it.csi.mdp.core.business.dto.Basket;
import it.csi.mdp.core.business.dto.BasketPk;
import it.csi.mdp.core.business.exceptions.BasketDaoException;
import java.util.Date;
import java.util.List;

public interface BasketDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return BasketPk
	 */
	public BasketPk insert(Basket dto);

	/** 
	 * Updates a single row in the BASKET table.
	 */
	public void update(BasketPk pk, Basket dto) throws BasketDaoException;

	/** 
	 * Deletes a single row in the BASKET table.
	 */
	public void delete(BasketPk pk) throws BasketDaoException;

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'BASKET_ID = :basketId'.
	 */
	public Basket findByPrimaryKey(String basketId) throws BasketDaoException;

	/** 
	 * Returns all rows from the BASKET table that match the criteria ''.
	 */
	public List<Basket> findAll() throws BasketDaoException;

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'BASKET_ID = :basketId'.
	 */
	public List<Basket> findWhereBasketIdEquals(String basketId) throws BasketDaoException;

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'CREATION_DATE = :creationDate'.
	 */
	public List<Basket> findWhereCreationDateEquals(Date creationDate) throws BasketDaoException;

	/** 
	 * Returns all rows from the BASKET table that match the criteria 'LAST_CHANGE_DATE = :lastChangeDate'.
	 */
	public List<Basket> findWhereLastChangeDateEquals(Date lastChangeDate) throws BasketDaoException;

	/** 
	 * Returns the rows from the BASKET table that matches the specified primary-key value.
	 */
	public Basket findByPrimaryKey(BasketPk pk) throws BasketDaoException;

}
