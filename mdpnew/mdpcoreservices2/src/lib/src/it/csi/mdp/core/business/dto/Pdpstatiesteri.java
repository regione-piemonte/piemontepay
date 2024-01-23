/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;

public class Pdpstatiesteri implements Serializable
{
	/** 
	 * This attribute maps to the column ID in the PDPSTATIESTERI table.
	 */
	protected long id;

	/** 
	 * This attribute maps to the column STATO in the PDPSTATIESTERI table.
	 */
	protected String stato;

	/**
	 * Method 'Pdpstatiesteri'
	 * 
	 */
	public Pdpstatiesteri()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return long
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Method 'getStato'
	 * 
	 * @return String
	 */
	public String getStato()
	{
		return stato;
	}

	/**
	 * Method 'setStato'
	 * 
	 * @param stato
	 */
	public void setStato(String stato)
	{
		this.stato = stato;
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
		
		if (!(_other instanceof Pdpstatiesteri)) {
			return false;
		}
		
		final Pdpstatiesteri _cast = (Pdpstatiesteri) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (stato == null ? _cast.stato != stato : !stato.equals( _cast.stato )) {
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
		_hashCode = 29 * _hashCode + (int) (id ^ (id >>> 32));
		if (stato != null) {
			_hashCode = 29 * _hashCode + stato.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.Pdpstatiesteri: " );
		ret.append( "id=" + id );
		ret.append( ", stato=" + stato );
		return ret.toString();
	}

}
