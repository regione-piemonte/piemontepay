/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;


/** 
 * This class represents the primary key of the GATEWAY table.
 */
public class GatewayPk implements Serializable
{
	protected String gatewayId;

	/** 
	 * This attribute represents whether the primitive attribute gatewayId is null.
	 */
	protected boolean gatewayIdNull;

	/** 
	 * Sets the value of gatewayId
	 */
	public void setGatewayId(String gatewayId)
	{
		this.gatewayId = gatewayId;
	}

	/** 
	 * Gets the value of gatewayId
	 */
	public String getGatewayId()
	{
		return gatewayId;
	}

	/**
	 * Method 'GatewayPk'
	 * 
	 */
	public GatewayPk()
	{
	}

	/**
	 * Method 'GatewayPk'
	 * 
	 * @param gatewayId
	 */
	public GatewayPk(final String gatewayId)
	{
		this.gatewayId = gatewayId;
	}

	/** 
	 * Sets the value of gatewayIdNull
	 */
	public void setGatewayIdNull(boolean gatewayIdNull)
	{
		this.gatewayIdNull = gatewayIdNull;
	}

	/** 
	 * Gets the value of gatewayIdNull
	 */
	public boolean isGatewayIdNull()
	{
		return gatewayIdNull;
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
		
		if (!(_other instanceof GatewayPk)) {
			return false;
		}
		
		final GatewayPk _cast = (GatewayPk) _other;
		if (gatewayId != _cast.gatewayId) {
			return false;
		}
		
		if (gatewayIdNull != _cast.gatewayIdNull) {
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
		ret.append( "it.csi.mdp.core.business.dto.GatewayPk: " );
		ret.append( "gatewayId=" + gatewayId );
		return ret.toString();
	}

}
