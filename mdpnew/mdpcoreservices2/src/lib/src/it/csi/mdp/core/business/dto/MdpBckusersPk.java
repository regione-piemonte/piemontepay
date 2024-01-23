/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the mdp_bckusers table.
 */
public class MdpBckusersPk implements Serializable
{
	protected int iduser;

	/** 
	 * Sets the value of iduser
	 */
	public void setIduser(int iduser)
	{
		this.iduser = iduser;
	}

	/** 
	 * Gets the value of iduser
	 */
	public int getIduser()
	{
		return iduser;
	}

	/**
	 * Method 'MdpBckusersPk'
	 * 
	 */
	public MdpBckusersPk()
	{
	}

	/**
	 * Method 'MdpBckusersPk'
	 * 
	 * @param iduser
	 */
	public MdpBckusersPk(final int iduser)
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
		
		if (!(_other instanceof MdpBckusersPk)) {
			return false;
		}
		
		final MdpBckusersPk _cast = (MdpBckusersPk) _other;
		if (iduser!= _cast.iduser ) {
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
	
			_hashCode = 29 * _hashCode + iduser;
	
		
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
		ret.append( "it.csi.mdp.core.business.dto.MdpBckusersPk: " );
		ret.append( "iduser=" + iduser );
		return ret.toString();
	}

}
