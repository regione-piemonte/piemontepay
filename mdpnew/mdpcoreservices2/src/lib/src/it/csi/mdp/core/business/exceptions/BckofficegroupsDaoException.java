/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class BckofficegroupsDaoException extends DaoException
{
	/**
	 * Method 'BckofficegroupsDaoException'
	 * 
	 * @param message
	 */
	public BckofficegroupsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'BckofficegroupsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public BckofficegroupsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
