/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.ItemDao;
import it.csi.mdp.core.business.dto.Item;
import it.csi.mdp.core.business.exceptions.ItemDaoException;
import java.util.Date;
import java.util.List;

public interface ItemDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Item dto);

	/** 
	 * Returns all rows from the ITEM table that match the criteria ''.
	 */
	public List<Item> findAll() throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'ITEM_ID = :itemId'.
	 */
	public List<Item> findWhereItemIdEquals(long itemId) throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'BASKET_ID = :basketId'.
	 */
	public List<Item> findWhereBasketIdEquals(String basketId) throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'QUANTITY = :quantity'.
	 */
	public List<Item> findWhereQuantityEquals(long quantity) throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'PRICE_ITEM = :priceItem'.
	 */
	public List<Item> findWherePriceItemEquals(long priceItem) throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'DESCRIPTION = :description'.
	 */
	public List<Item> findWhereDescriptionEquals(String description) throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'CREATION_DATE = :creationDate'.
	 */
	public List<Item> findWhereCreationDateEquals(Date creationDate) throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'TOTAL_PRICE = :totalPrice'.
	 */
	public List<Item> findWhereTotalPriceEquals(long totalPrice) throws ItemDaoException;

	/** 
	 * Returns all rows from the ITEM table that match the criteria 'ITEM_PK_ID = :itemPkId'.
	 */
	public List<Item> findWhereItemPkIdEquals(long itemPkId) throws ItemDaoException;

}
