/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class ConfigDaoException extends DaoException
{
	/**
	 * Method 'ConfigDaoException'
	 * 
	 * @param message
	 */
	public ConfigDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ConfigDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ConfigDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
