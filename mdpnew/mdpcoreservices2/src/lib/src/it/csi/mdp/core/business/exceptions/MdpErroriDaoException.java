/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpErroriDaoException extends DaoException
{
	/**
	 * Method 'MdpErroriDaoException'
	 * 
	 * @param message
	 */
	public MdpErroriDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpErroriDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpErroriDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
