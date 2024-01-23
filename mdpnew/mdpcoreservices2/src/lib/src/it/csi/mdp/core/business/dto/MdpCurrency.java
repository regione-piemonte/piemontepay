/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;

public class MdpCurrency implements Serializable
{
	/** 
	 * This attribute maps to the column GATEWAYID in the MDP_CURRENCY table.
	 */
	protected String gatewayid;

	/** 
	 * This attribute maps to the column GTWCURRENCYCODE in the MDP_CURRENCY table.
	 */
	protected String gtwcurrencycode;

	/** 
	 * This attribute maps to the column MDPCURRENCYCODE in the MDP_CURRENCY table.
	 */
	protected String mdpcurrencycode;

	/** 
	 * This attribute maps to the column CURRENCYDESCR in the MDP_CURRENCY table.
	 */
	protected String currencydescr;

	/** 
	 * This attribute maps to the column ENABLED in the MDP_CURRENCY table.
	 */
	protected String enabled;

	/**
	 * Method 'MdpCurrency'
	 * 
	 */
	public MdpCurrency()
	{
	}

	/**
	 * Method 'getGatewayId'
	 * 
	 * @return long
	 */
	public String getGatewayid()
	{
		return gatewayid;
	}

	/**
	 * Method 'setGatewayid'
	 * 
	 * @param gatewayid
	 */
	public void setGatewayid(String gatewayid)
	{
		this.gatewayid = gatewayid;
	}

	/**
	 * Method 'getGtwcurrencycode'
	 * 
	 * @return String
	 */
	public String getGtwcurrencycode()
	{
		return gtwcurrencycode;
	}

	/**
	 * Method 'setGtwcurrencycode'
	 * 
	 * @param gtwcurrencycode
	 */
	public void setGtwcurrencycode(String gtwcurrencycode)
	{
		this.gtwcurrencycode = gtwcurrencycode;
	}

	/**
	 * Method 'getMdpcurrencycode'
	 * 
	 * @return String
	 */
	public String getMdpcurrencycode()
	{
		return mdpcurrencycode;
	}

	/**
	 * Method 'setMdpcurrencycode'
	 * 
	 * @param mdpcurrencycode
	 */
	public void setMdpcurrencycode(String mdpcurrencycode)
	{
		this.mdpcurrencycode = mdpcurrencycode;
	}

	/**
	 * Method 'getCurrencydescr'
	 * 
	 * @return String
	 */
	public String getCurrencydescr()
	{
		return currencydescr;
	}

	/**
	 * Method 'setCurrencydescr'
	 * 
	 * @param currencydescr
	 */
	public void setCurrencydescr(String currencydescr)
	{
		this.currencydescr = currencydescr;
	}

	/**
	 * Method 'getEnabled'
	 * 
	 * @return String
	 */
	public String getEnabled()
	{
		return enabled;
	}

	/**
	 * Method 'setEnabled'
	 * 
	 * @param enabled
	 */
	public void setEnabled(String enabled)
	{
		this.enabled = enabled;
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
		
		if (!(_other instanceof MdpCurrency)) {
			return false;
		}
		
		final MdpCurrency _cast = (MdpCurrency) _other;
		if (gatewayid != _cast.gatewayid) {
			return false;
		}
		
		if (gtwcurrencycode == null ? _cast.gtwcurrencycode != gtwcurrencycode : !gtwcurrencycode.equals( _cast.gtwcurrencycode )) {
			return false;
		}
		
		if (mdpcurrencycode == null ? _cast.mdpcurrencycode != mdpcurrencycode : !mdpcurrencycode.equals( _cast.mdpcurrencycode )) {
			return false;
		}
		
		if (currencydescr == null ? _cast.currencydescr != currencydescr : !currencydescr.equals( _cast.currencydescr )) {
			return false;
		}
		
		if (enabled == null ? _cast.enabled != enabled : !enabled.equals( _cast.enabled )) {
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
		if (gatewayid!= null) {
			_hashCode = 29 * _hashCode + gatewayid.hashCode();
		}
		if (gtwcurrencycode != null) {
			_hashCode = 29 * _hashCode + gtwcurrencycode.hashCode();
		}
		
		if (mdpcurrencycode != null) {
			_hashCode = 29 * _hashCode + mdpcurrencycode.hashCode();
		}
		
		if (currencydescr != null) {
			_hashCode = 29 * _hashCode + currencydescr.hashCode();
		}
		
		if (enabled != null) {
			_hashCode = 29 * _hashCode + enabled.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.MdpCurrency: " );
		ret.append( "gatewayid=" + gatewayid );
		ret.append( ", gtwcurrencycode=" + gtwcurrencycode );
		ret.append( ", mdpcurrencycode=" + mdpcurrencycode );
		ret.append( ", currencydescr=" + currencydescr );
		ret.append( ", enabled=" + enabled );
		return ret.toString();
	}

}
