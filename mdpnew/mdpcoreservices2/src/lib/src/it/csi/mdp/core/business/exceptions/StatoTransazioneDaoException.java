/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class StatoTransazioneDaoException extends DaoException
{
	/**
	 * Method 'StatoTransazioneDaoException'
	 * 
	 * @param message
	 */
	public StatoTransazioneDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StatoTransazioneDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StatoTransazioneDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
