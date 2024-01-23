/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpBckusersgroupDaoException extends DaoException
{
	/**
	 * Method 'MdpBckusersgroupDaoException'
	 * 
	 * @param message
	 */
	public MdpBckusersgroupDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpBckusersgroupDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpBckusersgroupDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
