/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;


/** 
 * This class represents the primary key of the mdp_currency table.
 */
public class MdpCurrencyPk implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5808421304544675088L;

	protected String gatewayid;

	protected String mdpcurrencycode;

	protected String gtwcurrencycode;

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
	 * Sets the value of mdpcurrencycode
	 */
	public void setMdpcurrencycode(String mdpcurrencycode)
	{
		this.mdpcurrencycode = mdpcurrencycode;
	}

	/** 
	 * Gets the value of mdpcurrencycode
	 */
	public String getMdpcurrencycode()
	{
		return mdpcurrencycode;
	}

	/** 
	 * Sets the value of gtwcurrencycode
	 */
	public void setGtwcurrencycode(String gtwcurrencycode)
	{
		this.gtwcurrencycode = gtwcurrencycode;
	}

	/** 
	 * Gets the value of gtwcurrencycode
	 */
	public String getGtwcurrencycode()
	{
		return gtwcurrencycode;
	}

	/**
	 * Method 'MdpCurrencyPk'
	 * 
	 */
	public MdpCurrencyPk()
	{
	}

	/**
	 * Method 'MdpCurrencyPk'
	 * 
	 * @param gatewayid
	 * @param mdpcurrencycode
	 * @param gtwcurrencycode
	 */
	public MdpCurrencyPk(final String gatewayid, final String mdpcurrencycode, final String gtwcurrencycode)
	{
		this.gatewayid = gatewayid;
		this.mdpcurrencycode = mdpcurrencycode;
		this.gtwcurrencycode = gtwcurrencycode;
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
		
		if (!(_other instanceof MdpCurrencyPk)) {
			return false;
		}
		
		final MdpCurrencyPk _cast = (MdpCurrencyPk) _other;
		if (gatewayid == null ? _cast.gatewayid != gatewayid : !gatewayid.equals( _cast.gatewayid )) {
			return false;
		}
		
		if (mdpcurrencycode == null ? _cast.mdpcurrencycode != mdpcurrencycode : !mdpcurrencycode.equals( _cast.mdpcurrencycode )) {
			return false;
		}
		
		if (gtwcurrencycode == null ? _cast.gtwcurrencycode != gtwcurrencycode : !gtwcurrencycode.equals( _cast.gtwcurrencycode )) {
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
		if (gatewayid != null) {
			_hashCode = 29 * _hashCode + gatewayid.hashCode();
		}
		
		if (mdpcurrencycode != null) {
			_hashCode = 29 * _hashCode + mdpcurrencycode.hashCode();
		}
		
		if (gtwcurrencycode != null) {
			_hashCode = 29 * _hashCode + gtwcurrencycode.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.MdpCurrencyPk: " );
		ret.append( "gatewayid=" + gatewayid );
		ret.append( ", mdpcurrencycode=" + mdpcurrencycode );
		ret.append( ", gtwcurrencycode=" + gtwcurrencycode );
		return ret.toString();
	}

}
