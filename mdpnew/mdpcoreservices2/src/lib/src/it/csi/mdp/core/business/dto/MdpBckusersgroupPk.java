/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the mdp_bckusersgroup table.
 */
public class MdpBckusersgroupPk implements Serializable
{
	protected int iduser;

	protected int idgroup;

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
	 * Method 'MdpBckusersgroupPk'
	 * 
	 */
	public MdpBckusersgroupPk()
	{
	}

	/**
	 * Method 'MdpBckusersgroupPk'
	 * 
	 * @param iduser
	 * @param idgroup
	 */
	public MdpBckusersgroupPk(final int iduser, final int idgroup)
	{
		this.iduser = iduser;
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
		
		if (!(_other instanceof MdpBckusersgroupPk)) {
			return false;
		}
		
		final MdpBckusersgroupPk _cast = (MdpBckusersgroupPk) _other;
		if (iduser != _cast.iduser ) {
			return false;
		}
		
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

			_hashCode = 29 * _hashCode + iduser;
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
		ret.append( "it.csi.mdp.core.business.dto.MdpBckusersgroupPk: " );
		ret.append( "iduser=" + iduser );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

}
