/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class StatiRptDaoException extends DaoException
{
	/**
	 * Method 'GatewayDaoException'
	 * 
	 * @param message
	 */
	public StatiRptDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'GatewayDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StatiRptDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
