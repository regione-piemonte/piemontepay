/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpBckofficegroupsDaoException extends DaoException
{
	/**
	 * Method 'MdpBckofficegroupsDaoException'
	 * 
	 * @param message
	 */
	public MdpBckofficegroupsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpBckofficegroupsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpBckofficegroupsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
