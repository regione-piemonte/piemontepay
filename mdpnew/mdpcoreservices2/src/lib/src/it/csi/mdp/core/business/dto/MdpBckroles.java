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

public class MdpBckroles implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -112179423160439722L;

	/** 
	 * This attribute maps to the column idrole in the mdp_bckroles table.
	 */
	protected int idrole;

	/** 
	 * This attribute maps to the column roledescr in the mdp_bckroles table.
	 */
	protected String roledescr;

	/**
	 * Method 'MdpBckroles'
	 * 
	 */
	public MdpBckroles()
	{
	}

	/**
	 * Method 'getIdrole'
	 * 
	 * @return String
	 */
	public int getIdrole()
	{
		return idrole;
	}

	/**
	 * Method 'setIdrole'
	 * 
	 * @param idrole
	 */
	public void setIdrole(int idrole)
	{
		this.idrole = idrole;
	}

	/**
	 * Method 'getRoledescr'
	 * 
	 * @return String
	 */
	public String getRoledescr()
	{
		return roledescr;
	}

	/**
	 * Method 'setRoledescr'
	 * 
	 * @param roledescr
	 */
	public void setRoledescr(String roledescr)
	{
		this.roledescr = roledescr;
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
		
		if (!(_other instanceof MdpBckroles)) {
			return false;
		}
		
		final MdpBckroles _cast = (MdpBckroles) _other;
		if (idrole != _cast.idrole ) {
			return false;
		}
		
		if (roledescr == null ? _cast.roledescr != roledescr : !roledescr.equals( _cast.roledescr )) {
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
		int _hashCode = idrole;
		
		
		if (roledescr != null) {
			_hashCode = 29 * _hashCode + roledescr.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return MdpBckrolesPk
	 */
	public MdpBckrolesPk createPk()
	{
		return new MdpBckrolesPk(idrole);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.MdpBckroles: " );
		ret.append( "idrole=" + idrole );
		ret.append( ", roledescr=" + roledescr );
		return ret.toString();
	}

}
