/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.VapplicationcomuniDao;
import it.csi.mdp.core.business.dto.Vapplicationcomuni;
import it.csi.mdp.core.business.exceptions.VapplicationcomuniDaoException;
import java.util.List;

public interface VapplicationcomuniDao
{


	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria ''.
	 */
	public List<Vapplicationcomuni> findAll() throws VapplicationcomuniDaoException;

	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria 'MERCHANTID = :merchantid'.
	 */
	public List<Vapplicationcomuni> findWhereMerchantidEquals(String merchantid) throws VapplicationcomuniDaoException;

	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria 'DESC_COMUNE = :descComune'.
	 */
	public List<Vapplicationcomuni> findWhereDescComuneEquals(String descComune) throws VapplicationcomuniDaoException;

	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria 'CODICEIMM = :codiceimm'.
	 */
	public List<Vapplicationcomuni> findWhereCodiceimmEquals(String codiceimm) throws VapplicationcomuniDaoException;
	/** 
	 * Returns all rows from the VAPPLICATIONCOMUNI table that match the criteria 'CODICEIMM = :codiceimm'.
	 */
	public List<Vapplicationcomuni> findWhereAppIdandGwIdEquals(String appid, String gwid) throws VapplicationcomuniDaoException;

}
