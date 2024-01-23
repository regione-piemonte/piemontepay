/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

public class ApplicationcustomfieldsDaoException extends DaoException
{
	/**
	 * Method 'ApplicationcustomfieldsDaoException'
	 * 
	 * @param message
	 */
	public ApplicationcustomfieldsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ApplicationcustomfieldsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ApplicationcustomfieldsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
