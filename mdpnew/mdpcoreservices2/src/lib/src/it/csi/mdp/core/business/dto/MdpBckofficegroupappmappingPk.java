/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the mdp_bckofficegroupappmapping table.
 */
public class MdpBckofficegroupappmappingPk implements Serializable
{
	protected String idapp;

	protected int idgroup;

	/** 
	 * Sets the value of idapp
	 */
	public void setIdapp(String idapp)
	{
		this.idapp = idapp;
	}

	/** 
	 * Gets the value of idapp
	 */
	public String getIdapp()
	{
		return idapp;
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
	 * Method 'MdpBckofficegroupappmappingPk'
	 * 
	 */
	public MdpBckofficegroupappmappingPk()
	{
	}

	/**
	 * Method 'MdpBckofficegroupappmappingPk'
	 * 
	 * @param idapp
	 * @param idgroup
	 */
	public MdpBckofficegroupappmappingPk(final String idapp, final int idgroup)
	{
		this.idapp = idapp;
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
		
		if (!(_other instanceof MdpBckofficegroupappmappingPk)) {
			return false;
		}
		
		final MdpBckofficegroupappmappingPk _cast = (MdpBckofficegroupappmappingPk) _other;
		if (idapp == null ? _cast.idapp != idapp : !idapp.equals( _cast.idapp )) {
			return false;
		}
		
		if (idgroup != _cast.idgroup  ) {
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
		ret.append( "it.csi.mdp.core.business.dto.MdpBckofficegroupappmappingPk: " );
		ret.append( "idapp=" + idapp );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

}
