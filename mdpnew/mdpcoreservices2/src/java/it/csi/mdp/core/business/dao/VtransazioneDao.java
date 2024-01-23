/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.VtransazioneDao;
import it.csi.mdp.core.business.dto.Vtransazione;
import it.csi.mdp.core.business.exceptions.VtransazioneDaoException;
import java.util.Date;
import java.util.List;

public interface VtransazioneDao
{


	/** 
	 * Returns all rows from the vtransazione table that match the criteria ''.
	 */
	public List<Vtransazione> findAll(String idgroup) throws VtransazioneDaoException;

	/** 
	 * Returns all rows from the vtransazione table that match the criteria 'transaction_id = :transactionId'.
	 */
	public List<Vtransazione> findWhereApplicationIdEquals(String transactionId,String idgroup) throws VtransazioneDaoException;
	public List<Vtransazione> findWhereTransactionIdEquals(String transactionId,String idgroup) throws VtransazioneDaoException;

	public List<Vtransazione> findWithFilters(String appId, long codstato, Date datastart, Date dataend, String idgroup) throws VtransazioneDaoException;
	public int findWithFiltersCount(String appId, long codstato, Date datastart, Date dataend, String idgroup) throws VtransazioneDaoException;
}
