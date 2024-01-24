/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.TransazioneExtraAttributeDao;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.exceptions.TransazioneExtraAttributeDaoException;
import java.util.List;

public interface TransazioneExtraAttributeDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(TransazioneExtraAttribute dto);

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria ''.
	 */
	public List<TransazioneExtraAttribute> findAll(String idgroup) throws TransazioneExtraAttributeDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'EXTRA_ATTRIBUTE_ID = :extraAttributeId'.
	 */
	public List<TransazioneExtraAttribute> findWhereExtraAttributeIdEquals(long extraAttributeId,String idgroup) throws TransazioneExtraAttributeDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'NAME = :name'.
	 */
	public List<TransazioneExtraAttribute> findWhereNameEquals(String name,String idgroup) throws TransazioneExtraAttributeDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'VALUE = :value'.
	 */
	public List<TransazioneExtraAttribute> findWhereValueEquals(String value,String idgroup) throws TransazioneExtraAttributeDaoException;

	/** 
	 * Returns all rows from the TRANSAZIONE_EXTRA_ATTRIBUTE table that match the criteria 'TRANSACTION_ID = :transactionId'.
	 */
	public List<TransazioneExtraAttribute> findWhereTransactionIdEquals(String transactionId,String idgroup) throws TransazioneExtraAttributeDaoException;

}
