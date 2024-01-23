/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class GatewaydetailDaoException extends DaoException
{
	/**
	 * Method 'GatewaydetailDaoException'
	 * 
	 * @param message
	 */
	public GatewaydetailDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'GatewaydetailDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public GatewaydetailDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
