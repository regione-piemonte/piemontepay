/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class ItemDaoException extends DaoException
{
	/**
	 * Method 'ItemDaoException'
	 * 
	 * @param message
	 */
	public ItemDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ItemDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ItemDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
