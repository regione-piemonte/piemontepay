/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import it.csi.mdp.core.business.dao.*;
import it.csi.mdp.core.business.factory.*;
import it.csi.mdp.core.business.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class Authorizations implements Serializable
{
	/** 
	 * This attribute maps to the column operazione in the authorizations table.
	 */
	protected String operazione;

	/** 
	 * This attribute maps to the column idgroup in the authorizations table.
	 */
	protected int idrole;

	/**
	 * Method 'Authorizations'
	 * 
	 */
	public Authorizations()
	{
	}

	/**
	 * Method 'getOperazione'
	 * 
	 * @return String
	 */
	public String getOperazione()
	{
		return operazione;
	}

	/**
	 * Method 'setOperazione'
	 * 
	 * @param operazione
	 */
	public void setOperazione(String operazione)
	{
		this.operazione = operazione;
	}

	/**
	 * Method 'getIdgroup'
	 * 
	 * @return int
	 */
	public int getIdrole()
	{
		return idrole;
	}

	/**
	 * Method 'setIdgroup'
	 * 
	 * @param idgroup
	 */
	public void setIdrole(int idgrole)
	{
		this.idrole = idrole;
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
		
		if (!(_other instanceof Authorizations)) {
			return false;
		}
		
		final Authorizations _cast = (Authorizations) _other;
		if (operazione == null ? _cast.operazione != operazione : !operazione.equals( _cast.operazione )) {
			return false;
		}
		
		if (idrole != _cast.idrole) {
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
		if (operazione != null) {
			_hashCode = 29 * _hashCode + operazione.hashCode();
		}
		
		_hashCode = 29 * _hashCode + idrole;
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return AuthorizationsPk
	 */
	public AuthorizationsPk createPk()
	{
		return new AuthorizationsPk(idrole, operazione);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Authorizations: " );
		ret.append( "operazione=" + operazione );
		ret.append( ", idgroup=" + idrole );
		return ret.toString();
	}

}
