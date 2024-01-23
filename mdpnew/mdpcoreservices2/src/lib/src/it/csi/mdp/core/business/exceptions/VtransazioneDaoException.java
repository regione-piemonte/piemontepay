/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class VtransazioneDaoException extends DaoException
{
	/**
	 * Method 'VtransazioneDaoException'
	 * 
	 * @param message
	 */
	public VtransazioneDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'VtransazioneDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public VtransazioneDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
