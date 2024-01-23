/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class LanguageDaoException extends DaoException
{
	/**
	 * Method 'LanguageDaoException'
	 * 
	 * @param message
	 */
	public LanguageDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'LanguageDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public LanguageDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
