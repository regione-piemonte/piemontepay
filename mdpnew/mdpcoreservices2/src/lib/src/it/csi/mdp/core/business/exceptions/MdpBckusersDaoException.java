/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpBckusersDaoException extends DaoException
{
	/**
	 * Method 'MdpBckusersDaoException'
	 * 
	 * @param message
	 */
	public MdpBckusersDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpBckusersDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpBckusersDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
