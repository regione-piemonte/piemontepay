/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class DComuniAttiviEChiusiDaoException extends DaoException
{
	/**
	 * Method 'DComuniAttiviEChiusiDaoException'
	 * 
	 * @param message
	 */
	public DComuniAttiviEChiusiDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'DComuniAttiviEChiusiDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public DComuniAttiviEChiusiDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
