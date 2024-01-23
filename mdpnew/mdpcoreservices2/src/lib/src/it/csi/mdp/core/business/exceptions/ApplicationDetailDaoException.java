/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class ApplicationDetailDaoException extends DaoException
{
	/**
	 * Method 'ApplicationDetailDaoException'
	 * 
	 * @param message
	 */
	public ApplicationDetailDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ApplicationDetailDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ApplicationDetailDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
