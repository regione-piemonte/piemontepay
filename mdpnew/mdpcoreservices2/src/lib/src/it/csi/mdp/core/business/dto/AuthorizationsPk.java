/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the authorizations table.
 */
public class AuthorizationsPk implements Serializable
{
	protected int idrole;

	protected String operazione;

	/** 
	 * This attribute represents whether the primitive attribute idrole is null.
	 */
	protected boolean idroleNull;

	/** 
	 * Sets the value of idrole
	 */
	public void setIdrole(int idrole)
	{
		this.idrole = idrole;
	}

	/** 
	 * Gets the value of idrole
	 */
	public int getIdrole()
	{
		return idrole;
	}

	/** 
	 * Sets the value of operazione
	 */
	public void setOperazione(String operazione)
	{
		this.operazione = operazione;
	}

	/** 
	 * Gets the value of operazione
	 */
	public String getOperazione()
	{
		return operazione;
	}

	/**
	 * Method 'AuthorizationsPk'
	 * 
	 */
	public AuthorizationsPk()
	{
	}

	/**
	 * Method 'AuthorizationsPk'
	 * 
	 * @param idrole
	 * @param operazione
	 */
	public AuthorizationsPk(final int idrole, final String operazione)
	{
		this.idrole = idrole;
		this.operazione = operazione;
	}

	/** 
	 * Sets the value of idroleNull
	 */
	public void setIdroleNull(boolean idroleNull)
	{
		this.idroleNull = idroleNull;
	}

	/** 
	 * Gets the value of idroleNull
	 */
	public boolean isIdroleNull()
	{
		return idroleNull;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof AuthorizationsPk)) {
			return false;
		}
		
		final AuthorizationsPk _cast = (AuthorizationsPk) _other;
		if (idrole != _cast.idrole) {
			return false;
		}
		
		if (operazione == null ? _cast.operazione != operazione : !operazione.equals( _cast.operazione )) {
			return false;
		}
		
		if (idroleNull != _cast.idroleNull) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + idrole;
		if (operazione != null) {
			_hashCode = 29 * _hashCode + operazione.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (idroleNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.AuthorizationsPk: " );
		ret.append( "idrole=" + idrole );
		ret.append( ", operazione=" + operazione );
		return ret.toString();
	}

}
