/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.dto;

import java.io.Serializable;

/** 
 * This class represents the primary key of the applicationdetail table.
 */
public class ApplicationDetailPk implements Serializable
{
	protected String applicationid;

	protected String gatewayid;

	protected String paymentmodeid;

	/** 
	 * Sets the value of applicationid
	 */
	public void setApplicationid(String applicationid)
	{
		this.applicationid = applicationid;
	}

	/** 
	 * Gets the value of applicationid
	 */
	public String getApplicationid()
	{
		return applicationid;
	}

	/** 
	 * Sets the value of gatewayid
	 */
	public void setGatewayid(String gatewayid)
	{
		this.gatewayid = gatewayid;
	}

	/** 
	 * Gets the value of gatewayid
	 */
	public String getGatewayid()
	{
		return gatewayid;
	}

	/** 
	 * Sets the value of paymentmodeid
	 */
	public void setPaymentmodeid(String paymentmodeid)
	{
		this.paymentmodeid = paymentmodeid;
	}

	/** 
	 * Gets the value of paymentmodeid
	 */
	public String getPaymentmodeid()
	{
		return paymentmodeid;
	}

	/**
	 * Method 'ApplicationdetailPk'
	 * 
	 */
	public ApplicationDetailPk()
	{
	}

	/**
	 * Method 'ApplicationdetailPk'
	 * 
	 * @param applicationid
	 * @param gatewayid
	 * @param paymentmodeid
	 */
	public ApplicationDetailPk(final String applicationid, final String gatewayid, final String paymentmodeid)
	{
		this.applicationid = applicationid;
		this.gatewayid = gatewayid;
		this.paymentmodeid = paymentmodeid;
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
		
		if (!(_other instanceof ApplicationDetailPk)) {
			return false;
		}
		
		final ApplicationDetailPk _cast = (ApplicationDetailPk) _other;
		if (applicationid == null ? _cast.applicationid != applicationid : !applicationid.equals( _cast.applicationid )) {
			return false;
		}
		
		if (gatewayid == null ? _cast.gatewayid != gatewayid : !gatewayid.equals( _cast.gatewayid )) {
			return false;
		}
		
		if (paymentmodeid == null ? _cast.paymentmodeid != paymentmodeid : !paymentmodeid.equals( _cast.paymentmodeid )) {
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
		if (applicationid != null) {
			_hashCode = 29 * _hashCode + applicationid.hashCode();
		}
		
		if (gatewayid != null) {
			_hashCode = 29 * _hashCode + gatewayid.hashCode();
		}
		
		if (paymentmodeid != null) {
			_hashCode = 29 * _hashCode + paymentmodeid.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.ApplicationdetailPk: " );
		ret.append( "applicationid=" + applicationid );
		ret.append( ", gatewayid=" + gatewayid );
		ret.append( ", paymentmodeid=" + paymentmodeid );
		return ret.toString();
	}

}
