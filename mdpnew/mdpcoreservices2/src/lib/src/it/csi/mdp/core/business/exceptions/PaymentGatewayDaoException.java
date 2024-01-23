/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class PaymentGatewayDaoException extends DaoException
{
	/**
	 * Method 'PaymentGatewayDaoException'
	 * 
	 * @param message
	 */
	public PaymentGatewayDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PaymentGatewayDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PaymentGatewayDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
