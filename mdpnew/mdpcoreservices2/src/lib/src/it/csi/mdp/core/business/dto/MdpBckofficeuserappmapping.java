/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.*;

public class MdpBckofficeuserappmapping implements Serializable
{
	/** 
	 * This attribute maps to the column IDAPP in the MDP_BCKOFFICEUSERAPPMAPPING table.
	 */
	protected String idapp;

	/** 
	 * This attribute maps to the column IDUSER in the MDP_BCKOFFICEUSERAPPMAPPING table.
	 */
	protected String iduser;

	/**
	 * Method 'MdpBckofficeuserappmapping'
	 * 
	 */
	public MdpBckofficeuserappmapping()
	{
	}

	/**
	 * Method 'getIdapp'
	 * 
	 * @return String
	 */
	public String getIdapp()
	{
		return idapp;
	}

	/**
	 * Method 'setIdapp'
	 * 
	 * @param idapp
	 */
	public void setIdapp(String idapp)
	{
		this.idapp = idapp;
	}

	/**
	 * Method 'getIduser'
	 * 
	 * @return String
	 */
	public String getIduser()
	{
		return iduser;
	}

	/**
	 * Method 'setIduser'
	 * 
	 * @param iduser
	 */
	public void setIduser(String iduser)
	{
		this.iduser = iduser;
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
		
		if (!(_other instanceof MdpBckofficeuserappmapping)) {
			return false;
		}
		
		final MdpBckofficeuserappmapping _cast = (MdpBckofficeuserappmapping) _other;
		if (idapp == null ? _cast.idapp != idapp : !idapp.equals( _cast.idapp )) {
			return false;
		}
		
		if (iduser == null ? _cast.iduser != iduser : !iduser.equals( _cast.iduser )) {
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
		if (idapp != null) {
			_hashCode = 29 * _hashCode + idapp.hashCode();
		}
		
		if (iduser != null) {
			_hashCode = 29 * _hashCode + iduser.hashCode();
		}
		
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
		ret.append( "it.csi.mdp.core.business.dto.MdpBckofficeuserappmapping: " );
		ret.append( "idapp=" + idapp );
		ret.append( ", iduser=" + iduser );
		return ret.toString();
	}

}
