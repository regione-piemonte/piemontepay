/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the csi_log_audit table.
 */
public class CsiLogAuditPk implements Serializable
{
	protected int uniqueid;

	/** 
	 * This attribute represents whether the primitive attribute uniqueid is null.
	 */
	protected boolean uniqueidNull;

	/** 
	 * Sets the value of uniqueid
	 */
	public void setUniqueid(int uniqueid)
	{
		this.uniqueid = uniqueid;
	}

	/** 
	 * Gets the value of uniqueid
	 */
	public int getUniqueid()
	{
		return uniqueid;
	}

	/**
	 * Method 'CsiLogAuditPk'
	 * 
	 */
	public CsiLogAuditPk()
	{
	}

	/**
	 * Method 'CsiLogAuditPk'
	 * 
	 * @param uniqueid
	 */
	public CsiLogAuditPk(final int uniqueid)
	{
		this.uniqueid = uniqueid;
	}

	/** 
	 * Sets the value of uniqueidNull
	 */
	public void setUniqueidNull(boolean uniqueidNull)
	{
		this.uniqueidNull = uniqueidNull;
	}

	/** 
	 * Gets the value of uniqueidNull
	 */
	public boolean isUniqueidNull()
	{
		return uniqueidNull;
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
		
		if (!(_other instanceof CsiLogAuditPk)) {
			return false;
		}
		
		final CsiLogAuditPk _cast = (CsiLogAuditPk) _other;
		if (uniqueid != _cast.uniqueid) {
			return false;
		}
		
		if (uniqueidNull != _cast.uniqueidNull) {
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
		_hashCode = 29 * _hashCode + uniqueid;
		_hashCode = 29 * _hashCode + (uniqueidNull ? 1 : 0);
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
		ret.append( "it.csi.mdp.core.business.dto.CsiLogAuditPk: " );
		ret.append( "uniqueid=" + uniqueid );
		return ret.toString();
	}

}
