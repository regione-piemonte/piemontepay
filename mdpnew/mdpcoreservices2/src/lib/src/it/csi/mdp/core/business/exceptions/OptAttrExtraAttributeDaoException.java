/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class OptAttrExtraAttributeDaoException extends DaoException
{
	/**
	 * Method 'OptAttrExtraAttributeDaoException'
	 * 
	 * @param message
	 */
	public OptAttrExtraAttributeDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'OptAttrExtraAttributeDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public OptAttrExtraAttributeDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
