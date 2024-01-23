/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

/** 
 * This class represents the primary key of the COMMISSION table.
 */
public class CommissionPk implements Serializable
{
	protected String commissionId;

	/** 
	 * Sets the value of commissionId
	 */
	public void setCommissionId(String commissionId)
	{
		this.commissionId = commissionId;
	}

	/** 
	 * Gets the value of commissionId
	 */
	public String getCommissionId()
	{
		return commissionId;
	}

	/**
	 * Method 'CommissionPk'
	 * 
	 */
	public CommissionPk()
	{
	}

	/**
	 * Method 'CommissionPk'
	 * 
	 * @param commissionId
	 */
	public CommissionPk(final String commissionId)
	{
		this.commissionId = commissionId;
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
		
		if (!(_other instanceof CommissionPk)) {
			return false;
		}
		
		final CommissionPk _cast = (CommissionPk) _other;
		if (commissionId == null ? _cast.commissionId != commissionId : !commissionId.equals( _cast.commissionId )) {
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
		if (commissionId != null) {
			_hashCode = 29 * _hashCode + commissionId.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.CommissionPk: " );
		ret.append( "commissionId=" + commissionId );
		return ret.toString();
	}

}
