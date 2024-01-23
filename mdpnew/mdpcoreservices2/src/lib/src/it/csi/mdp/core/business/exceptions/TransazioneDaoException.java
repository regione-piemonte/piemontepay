/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class TransazioneDaoException extends DaoException
{
	/**
	 * Method 'TransazioneDaoException'
	 * 
	 * @param message
	 */
	public TransazioneDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'TransazioneDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public TransazioneDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
