/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the mdp_bckofficegroups table.
 */
public class MdpBckofficegroupsPk implements Serializable
{
	protected int idgroup;

	/** 
	 * Sets the value of idgroup
	 */
	public void setIdgroup(int idgroup)
	{
		this.idgroup = idgroup;
	}

	/** 
	 * Gets the value of idgroup
	 */
	public int getIdgroup()
	{
		return idgroup;
	}

	/**
	 * Method 'MdpBckofficegroupsPk'
	 * 
	 */
	public MdpBckofficegroupsPk()
	{
	}

	/**
	 * Method 'MdpBckofficegroupsPk'
	 * 
	 * @param idgroup
	 */
	public MdpBckofficegroupsPk(final int idgroup)
	{
		this.idgroup = idgroup;
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
		
		if (!(_other instanceof MdpBckofficegroupsPk)) {
			return false;
		}
		
		final MdpBckofficegroupsPk _cast = (MdpBckofficegroupsPk) _other;
		if (idgroup != _cast.idgroup) {
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
		
			_hashCode = 29 * _hashCode + idgroup;
		
		
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
		ret.append( "it.csi.mdp.core.business.dto.MdpBckofficegroupsPk: " );
		ret.append( "idgroup=" + idgroup );
		return ret.toString();
	}

}
