/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class VappgroupDaoException extends DaoException
{
	/**
	 * Method 'VappgroupDaoException'
	 * 
	 * @param message
	 */
	public VappgroupDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'VappgroupDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public VappgroupDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
