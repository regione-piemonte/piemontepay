/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class IcicodiciimmDaoException extends DaoException
{
	/**
	 * Method 'IcicodiciimmDaoException'
	 * 
	 * @param message
	 */
	public IcicodiciimmDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'IcicodiciimmDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public IcicodiciimmDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
