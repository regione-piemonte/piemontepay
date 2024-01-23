/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name="SystemException", targetNamespace="http://www.csi.it/mdp/")
public class SystemException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4940717930297418797L;
	/**
	 * 
	 */
	
	protected Throwable throwable;

	/**
	 * Method 'DaoException'
	 * 
	 * @param message
	 */
	public SystemException(String message)
	{
		super(message);
	}

	/**
	 * Method 'DaoException'
	 * 
	 * @param message
	 * @param throwable
	 */
	public SystemException(String message, Throwable throwable)
	{
		super(message);
		this.throwable = throwable;
	}

	/**
	 * Method 'getCause'
	 * 
	 * @return Throwable
	 */
	public Throwable getCause()
	{
		return throwable;
	}

}
