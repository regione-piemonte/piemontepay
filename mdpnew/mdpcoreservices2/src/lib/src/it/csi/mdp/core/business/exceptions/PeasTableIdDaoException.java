/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class PeasTableIdDaoException extends DaoException
{
	/**
	 * Method 'PeasTableIdDaoException'
	 * 
	 * @param message
	 */
	public PeasTableIdDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PeasTableIdDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PeasTableIdDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
