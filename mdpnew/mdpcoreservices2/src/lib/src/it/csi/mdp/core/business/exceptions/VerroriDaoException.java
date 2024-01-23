/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class VerroriDaoException extends DaoException
{
	/**
	 * Method 'VerroriDaoException'
	 * 
	 * @param message
	 */
	public VerroriDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'VerroriDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public VerroriDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
