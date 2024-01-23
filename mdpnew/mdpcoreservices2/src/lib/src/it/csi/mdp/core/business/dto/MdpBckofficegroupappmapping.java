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

public class MdpBckofficegroupappmapping implements Serializable
{
	/** 
	 * This attribute maps to the column idapp in the mdp_bckofficegroupappmapping table.
	 */
	protected String idapp;

	/** 
	 * This attribute maps to the column idgroup in the mdp_bckofficegroupappmapping table.
	 */
	protected int idgroup;

	/**
	 * Method 'MdpBckofficegroupappmapping'
	 * 
	 */
	public MdpBckofficegroupappmapping()
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
	 * Method 'getIdgroup'
	 * 
	 * @return String
	 */
	public int getIdgroup()
	{
		return idgroup;
	}

	/**
	 * Method 'setIdgroup'
	 * 
	 * @param idgroup
	 */
	public void setIdgroup(int idgroup)
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
		
		if (!(_other instanceof MdpBckofficegroupappmapping)) {
			return false;
		}
		
		final MdpBckofficegroupappmapping _cast = (MdpBckofficegroupappmapping) _other;
		if (idapp == null ? _cast.idapp != idapp : !idapp.equals( _cast.idapp )) {
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
		if (idapp != null) {
			_hashCode = 29 * _hashCode + idapp.hashCode();
		}
		
	
			_hashCode = 29 * _hashCode + idgroup;
		
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return MdpBckofficegroupappmappingPk
	 */
	public MdpBckofficegroupappmappingPk createPk()
	{
		return new MdpBckofficegroupappmappingPk(idapp, idgroup);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.MdpBckofficegroupappmapping: " );
		ret.append( "idapp=" + idapp );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

}
