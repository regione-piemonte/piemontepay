/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the auditactions table.
 */
public class AuditactionsPk implements Serializable
{
	protected String idaction;

	/** 
	 * Sets the value of idaction
	 */
	public void setIdaction(String idaction)
	{
		this.idaction = idaction;
	}

	/** 
	 * Gets the value of idaction
	 */
	public String getIdaction()
	{
		return idaction;
	}

	/**
	 * Method 'AuditactionsPk'
	 * 
	 */
	public AuditactionsPk()
	{
	}

	/**
	 * Method 'AuditactionsPk'
	 * 
	 * @param idaction
	 */
	public AuditactionsPk(final String idaction)
	{
		this.idaction = idaction;
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
		
		if (!(_other instanceof AuditactionsPk)) {
			return false;
		}
		
		final AuditactionsPk _cast = (AuditactionsPk) _other;
		if (idaction == null ? _cast.idaction != idaction : !idaction.equals( _cast.idaction )) {
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
		if (idaction != null) {
			_hashCode = 29 * _hashCode + idaction.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.AuditactionsPk: " );
		ret.append( "idaction=" + idaction );
		return ret.toString();
	}

}
