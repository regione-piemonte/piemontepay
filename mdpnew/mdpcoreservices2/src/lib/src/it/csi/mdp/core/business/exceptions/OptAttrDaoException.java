/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class OptAttrDaoException extends DaoException
{
	/**
	 * Method 'OptAttrDaoException'
	 * 
	 * @param message
	 */
	public OptAttrDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'OptAttrDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public OptAttrDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
