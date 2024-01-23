/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class CsiLogAuditDaoException extends DaoException
{
	/**
	 * Method 'CsiLogAuditDaoException'
	 * 
	 * @param message
	 */
	public CsiLogAuditDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'CsiLogAuditDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public CsiLogAuditDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
