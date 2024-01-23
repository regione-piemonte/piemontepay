/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class CommissionDaoException extends DaoException
{
	/**
	 * Method 'CommissionDaoException'
	 * 
	 * @param message
	 */
	public CommissionDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'CommissionDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public CommissionDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
