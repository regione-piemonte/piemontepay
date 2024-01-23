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

public class Vappgroup implements Serializable
{
	/** 
	 * This attribute maps to the column id in the vappgroup table.
	 */
	protected String id;

	/** 
	 * This attribute maps to the column idgroup in the vappgroup table.
	 */
	protected int idgroup;

	/** 
	 * This attribute represents whether the primitive attribute idgroup is null.
	 */
	protected boolean idgroupNull = true;

	/**
	 * Method 'Vappgroup'
	 * 
	 */
	public Vappgroup()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return String
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Method 'getIdgroup'
	 * 
	 * @return int
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
		this.idgroupNull = false;
	}

	/**
	 * Method 'setIdgroupNull'
	 * 
	 * @param value
	 */
	public void setIdgroupNull(boolean value)
	{
		this.idgroupNull = value;
	}

	/**
	 * Method 'isIdgroupNull'
	 * 
	 * @return boolean
	 */
	public boolean isIdgroupNull()
	{
		return idgroupNull;
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
		
		if (!(_other instanceof Vappgroup)) {
			return false;
		}
		
		final Vappgroup _cast = (Vappgroup) _other;
		if (id == null ? _cast.id != id : !id.equals( _cast.id )) {
			return false;
		}
		
		if (idgroup != _cast.idgroup) {
			return false;
		}
		
		if (idgroupNull != _cast.idgroupNull) {
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
		if (id != null) {
			_hashCode = 29 * _hashCode + id.hashCode();
		}
		
		_hashCode = 29 * _hashCode + idgroup;
		_hashCode = 29 * _hashCode + (idgroupNull ? 1 : 0);
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
		ret.append( "it.csi.mdp.core.business.dto.Vappgroup: " );
		ret.append( "id=" + id );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

}
