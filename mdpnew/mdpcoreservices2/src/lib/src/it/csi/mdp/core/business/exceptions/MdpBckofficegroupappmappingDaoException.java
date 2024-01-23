/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class MdpBckofficegroupappmappingDaoException extends DaoException
{
	/**
	 * Method 'MdpBckofficegroupappmappingDaoException'
	 * 
	 * @param message
	 */
	public MdpBckofficegroupappmappingDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'MdpBckofficegroupappmappingDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public MdpBckofficegroupappmappingDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
