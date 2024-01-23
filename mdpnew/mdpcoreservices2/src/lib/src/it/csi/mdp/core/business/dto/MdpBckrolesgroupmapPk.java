/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the mdp_bckrolesgroupmap table.
 */
public class MdpBckrolesgroupmapPk implements Serializable
{
	protected int idrole;

	protected int idgroup;

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
	 * Method 'MdpBckrolesgroupmapPk'
	 * 
	 */
	public MdpBckrolesgroupmapPk()
	{
	}

	/**
	 * Method 'MdpBckrolesgroupmapPk'
	 * 
	 * @param idrole
	 * @param idgroup
	 */
	public MdpBckrolesgroupmapPk(final int idrole, final int idgroup)
	{
		this.idrole = idrole;
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
		
		if (!(_other instanceof MdpBckrolesgroupmapPk)) {
			return false;
		}
		
		final MdpBckrolesgroupmapPk _cast = (MdpBckrolesgroupmapPk) _other;
		if (idrole != _cast.idrole ) {
			return false;
		}
		
		if (idgroup !=  _cast.idgroup ) {
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
		ret.append( "it.csi.mdp.core.business.dto.MdpBckrolesgroupmapPk: " );
		ret.append( "idrole=" + idrole );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

}
