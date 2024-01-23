/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class Commission implements Serializable
{
	/** 
	 * This attribute maps to the column COMMISSION_ID in the COMMISSION table.
	 */
	protected String commissionId;

	/** 
	 * This attribute maps to the column COMMISSION_DESCRIPTION in the COMMISSION table.
	 */
	protected String commissionDescription;

	/**
	 * Method 'Commission'
	 * 
	 */
	public Commission()
	{
	}

	/**
	 * Method 'getCommissionId'
	 * 
	 * @return String
	 */
	public String getCommissionId()
	{
		return commissionId;
	}

	/**
	 * Method 'setCommissionId'
	 * 
	 * @param commissionId
	 */
	public void setCommissionId(String commissionId)
	{
		this.commissionId = commissionId;
	}

	/**
	 * Method 'getCommissionDescription'
	 * 
	 * @return String
	 */
	public String getCommissionDescription()
	{
		return commissionDescription;
	}

	/**
	 * Method 'setCommissionDescription'
	 * 
	 * @param commissionDescription
	 */
	public void setCommissionDescription(String commissionDescription)
	{
		this.commissionDescription = commissionDescription;
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
		
		if (!(_other instanceof Commission)) {
			return false;
		}
		
		final Commission _cast = (Commission) _other;
		if (commissionId == null ? _cast.commissionId != commissionId : !commissionId.equals( _cast.commissionId )) {
			return false;
		}
		
		if (commissionDescription == null ? _cast.commissionDescription != commissionDescription : !commissionDescription.equals( _cast.commissionDescription )) {
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
		
		if (commissionDescription != null) {
			_hashCode = 29 * _hashCode + commissionDescription.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return CommissionPk
	 */
	public CommissionPk createPk()
	{
		return new CommissionPk(commissionId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Commission: " );
		ret.append( "commissionId=" + commissionId );
		ret.append( ", commissionDescription=" + commissionDescription );
		return ret.toString();
	}

}
