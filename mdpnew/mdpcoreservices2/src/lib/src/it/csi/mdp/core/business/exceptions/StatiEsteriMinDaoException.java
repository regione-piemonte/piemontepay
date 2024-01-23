/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class StatiEsteriMinDaoException extends DaoException
{
	/**
	 * Method 'StatiEsteriMinDaoException'
	 * 
	 * @param message
	 */
	public StatiEsteriMinDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StatiEsteriMinDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StatiEsteriMinDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
