/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpCurrencyDaoException extends DaoException
{
	/**
	 * Method 'MdpCurrencyDaoException'
	 * 
	 * @param message
	 */
	public MdpCurrencyDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpCurrencyDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpCurrencyDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
