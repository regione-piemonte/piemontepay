/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.GatewayDao;
import it.csi.mdp.core.business.dto.Gateway;
import it.csi.mdp.core.business.dto.GatewayPk;
import it.csi.mdp.core.business.exceptions.ApplicationDaoException;
import it.csi.mdp.core.business.exceptions.GatewayDaoException;
import java.util.Date;
import java.util.List;

public interface GatewayDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return GatewayPk
	 */
	public GatewayPk insert(Gateway dto);

	/** 
	 * Updates a single row in the GATEWAY table.
	 */
	public void update(GatewayPk pk, Gateway dto) throws GatewayDaoException;

	/** 
	 * Deletes a single row in the GATEWAY table.
	 */
	public void delete(GatewayPk pk) throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	public Gateway findByPrimaryKey(String gatewayId) throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria ''.
	 */
	public List<Gateway> findAll() throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_ID = :gatewayId'.
	 */
	public List<Gateway> findWhereGatewayIdEquals(String gatewayId) throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_DESCRIPTION = :gatewayDescription'.
	 */
	public List<Gateway> findWhereGatewayDescriptionEquals(String gatewayDescription) throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAY_PROVIDER = :gatewayProvider'.
	 */
	public List<Gateway> findWhereGatewayProviderEquals(String gatewayProvider) throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'VALIDO_DAL = :validoDal'.
	 */
	public List<Gateway> findWhereValidoDalEquals(Date validoDal) throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'VALIDO_AL = :validoAl'.
	 */
	public List<Gateway> findWhereValidoAlEquals(Date validoAl) throws GatewayDaoException;

	/** 
	 * Returns all rows from the GATEWAY table that match the criteria 'GATEWAYSERVICENAME = :gatewayservicename'.
	 */
	public List<Gateway> findWhereGatewayservicenameEquals(String gatewayservicename) throws GatewayDaoException;

	/** 
	 * Returns the rows from the GATEWAY table that matches the specified primary-key value.
	 */
	public Gateway findByPrimaryKey(GatewayPk pk) throws GatewayDaoException;

	
	public void deleteGatewayConfiguration(GatewayPk pk) throws ApplicationDaoException;
}
