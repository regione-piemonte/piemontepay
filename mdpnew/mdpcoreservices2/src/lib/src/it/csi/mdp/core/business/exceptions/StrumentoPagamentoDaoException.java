/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class StrumentoPagamentoDaoException extends DaoException
{
	/**
	 * Method 'StrumentoPagamentoDaoException'
	 * 
	 * @param message
	 */
	public StrumentoPagamentoDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StrumentoPagamentoDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StrumentoPagamentoDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
