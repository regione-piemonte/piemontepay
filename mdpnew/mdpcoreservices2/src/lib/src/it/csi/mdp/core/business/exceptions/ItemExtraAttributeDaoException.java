/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class ItemExtraAttributeDaoException extends DaoException
{
	/**
	 * Method 'ItemExtraAttributeDaoException'
	 * 
	 * @param message
	 */
	public ItemExtraAttributeDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ItemExtraAttributeDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ItemExtraAttributeDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
