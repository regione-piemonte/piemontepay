/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class ApplicationDaoException extends DaoException
{
	/**
	 * Method 'ApplicationDaoException'
	 * 
	 * @param message
	 */
	public ApplicationDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ApplicationDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ApplicationDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
