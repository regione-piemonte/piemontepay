/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.OptAttrExtraAttributeDao;
import it.csi.mdp.core.business.dto.OptAttrExtraAttribute;
import it.csi.mdp.core.business.exceptions.OptAttrExtraAttributeDaoException;
import java.util.List;

public interface OptAttrExtraAttributeDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(OptAttrExtraAttribute dto);

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria ''.
	 */
	public List<OptAttrExtraAttribute> findAll() throws OptAttrExtraAttributeDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'EXTRA_ATTRIBUTE_ID = :extraAttributeId'.
	 */
	public List<OptAttrExtraAttribute> findWhereExtraAttributeIdEquals(long extraAttributeId) throws OptAttrExtraAttributeDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'NAME = :name'.
	 */
	public List<OptAttrExtraAttribute> findWhereNameEquals(String name) throws OptAttrExtraAttributeDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'VALUE = :value'.
	 */
	public List<OptAttrExtraAttribute> findWhereValueEquals(String value) throws OptAttrExtraAttributeDaoException;

	/** 
	 * Returns all rows from the OPT_ATTR_EXTRA_ATTRIBUTE table that match the criteria 'OPT_ATTR_ID = :optAttrId'.
	 */
	public List<OptAttrExtraAttribute> findWhereOptAttrIdEquals(long optAttrId) throws OptAttrExtraAttributeDaoException;

}
