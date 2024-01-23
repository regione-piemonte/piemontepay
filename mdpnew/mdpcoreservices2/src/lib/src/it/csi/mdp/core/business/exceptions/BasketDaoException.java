/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class BasketDaoException extends DaoException
{
	/**
	 * Method 'BasketDaoException'
	 * 
	 * @param message
	 */
	public BasketDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'BasketDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public BasketDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
