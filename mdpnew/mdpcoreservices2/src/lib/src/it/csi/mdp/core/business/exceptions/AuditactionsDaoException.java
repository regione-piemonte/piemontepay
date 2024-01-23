/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class AuditactionsDaoException extends DaoException
{
	/**
	 * Method 'AuditactionsDaoException'
	 * 
	 * @param message
	 */
	public AuditactionsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'AuditactionsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public AuditactionsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
