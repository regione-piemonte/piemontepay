/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class AuthorizationsDaoException extends DaoException
{
	/**
	 * Method 'AuthorizationsDaoException'
	 * 
	 * @param message
	 */
	public AuthorizationsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'AuthorizationsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public AuthorizationsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
