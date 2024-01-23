/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpBckrolesDaoException extends DaoException
{
	/**
	 * Method 'MdpBckrolesDaoException'
	 * 
	 * @param message
	 */
	public MdpBckrolesDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpBckrolesDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpBckrolesDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
