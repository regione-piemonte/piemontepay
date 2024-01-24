/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.ItemExtraAttributeDao;
import it.csi.mdp.core.business.dto.ItemExtraAttribute;
import it.csi.mdp.core.business.exceptions.ItemExtraAttributeDaoException;
import java.util.List;

public interface ItemExtraAttributeDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(ItemExtraAttribute dto);

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria ''.
	 */
	public List<ItemExtraAttribute> findAll() throws ItemExtraAttributeDaoException;

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'EXTRA_ATTRIBUTE_ID = :extraAttributeId'.
	 */
	public List<ItemExtraAttribute> findWhereExtraAttributeIdEquals(long extraAttributeId) throws ItemExtraAttributeDaoException;

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'NAME = :name'.
	 */
	public List<ItemExtraAttribute> findWhereNameEquals(String name) throws ItemExtraAttributeDaoException;

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'VALUE = :value'.
	 */
	public List<ItemExtraAttribute> findWhereValueEquals(String value) throws ItemExtraAttributeDaoException;

	/** 
	 * Returns all rows from the ITEM_EXTRA_ATTRIBUTE table that match the criteria 'ITEM_ID = :itemId'.
	 */
	public List<ItemExtraAttribute> findWhereItemIdEquals(long itemId) throws ItemExtraAttributeDaoException;

}
