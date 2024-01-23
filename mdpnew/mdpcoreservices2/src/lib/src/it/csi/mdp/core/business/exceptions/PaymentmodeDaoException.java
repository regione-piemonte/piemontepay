/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class PaymentmodeDaoException extends DaoException
{
	/**
	 * Method 'PaymentmodeDaoException'
	 * 
	 * @param message
	 */
	public PaymentmodeDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PaymentmodeDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PaymentmodeDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
