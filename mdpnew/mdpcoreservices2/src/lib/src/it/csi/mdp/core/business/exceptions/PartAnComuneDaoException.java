/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class PartAnComuneDaoException extends DaoException
{
	/**
	 * Method 'PartAnComuneDaoException'
	 * 
	 * @param message
	 */
	public PartAnComuneDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PartAnComuneDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PartAnComuneDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
