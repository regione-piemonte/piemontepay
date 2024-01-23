/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

/** 
 * This class represents the primary key of the GATEWAYDETAIL table.
 */
public class GatewaydetailPk implements Serializable
{
	protected String gatewayId;

	protected String paymentmodeId;

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
	 * Sets the value of paymentmodeId
	 */
	public void setPaymentmodeId(String paymentmodeId)
	{
		this.paymentmodeId = paymentmodeId;
	}

	/** 
	 * Gets the value of paymentmodeId
	 */
	public String getPaymentmodeId()
	{
		return paymentmodeId;
	}

	/**
	 * Method 'GatewaydetailPk'
	 * 
	 */
	public GatewaydetailPk()
	{
	}

	/**
	 * Method 'GatewaydetailPk'
	 * 
	 * @param gatewayId
	 * @param paymentmodeId
	 */
	public GatewaydetailPk(final String gatewayId, final String paymentmodeId)
	{
		this.gatewayId = gatewayId;
		this.paymentmodeId = paymentmodeId;
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
		
		if (!(_other instanceof GatewaydetailPk)) {
			return false;
		}
		
		final GatewaydetailPk _cast = (GatewaydetailPk) _other;
		if (gatewayId == null ? _cast.gatewayId != gatewayId : !gatewayId.equals( _cast.gatewayId )) {
			return false;
		}
		
		if (paymentmodeId == null ? _cast.paymentmodeId != paymentmodeId : !paymentmodeId.equals( _cast.paymentmodeId )) {
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
		if (gatewayId != null) {
			_hashCode = 29 * _hashCode + gatewayId.hashCode();
		}
		
		if (paymentmodeId != null) {
			_hashCode = 29 * _hashCode + paymentmodeId.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.GatewaydetailPk: " );
		ret.append( "gatewayId=" + gatewayId );
		ret.append( ", paymentmodeId=" + paymentmodeId );
		return ret.toString();
	}

}
