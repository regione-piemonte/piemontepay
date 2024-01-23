/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class PdpstatiesteriDaoException extends DaoException
{
	/**
	 * Method 'PdpstatiesteriDaoException'
	 * 
	 * @param message
	 */
	public PdpstatiesteriDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PdpstatiesteriDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PdpstatiesteriDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
