/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpBckofficeuserappmappingDaoException extends DaoException
{
	/**
	 * Method 'MdpBckofficeuserappmappingDaoException'
	 * 
	 * @param message
	 */
	public MdpBckofficeuserappmappingDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpBckofficeuserappmappingDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpBckofficeuserappmappingDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
