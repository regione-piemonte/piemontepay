/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;

public class Language implements Serializable
{
	/** 
	 * This attribute maps to the column GATEWAYID in the LANGUAGE table.
	 */
	protected String  gatewayid;

	/** 
	 * This attribute maps to the column GTWLANGUAGECODE in the LANGUAGE table.
	 */
	protected String gtwlanguagecode;

	/** 
	 * This attribute maps to the column MDPLANGUAGECODE in the LANGUAGE table.
	 */
	protected String mdplanguagecode;

	/** 
	 * This attribute maps to the column LANGUAGEDESCR in the LANGUAGE table.
	 */
	protected String languagedescr;

	/** 
	 * This attribute maps to the column ENABLED in the LANGUAGE table.
	 */
	protected String enabled;

	/**
	 * Method 'Language'
	 * 
	 */
	public Language()
	{
	}

	/**
	 * Method 'getGatewayId'
	 * 
	 * @return long
	 */
	public String  getGatewayid()
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
	 * Method 'getGtwlanguagecode'
	 * 
	 * @return String
	 */
	public String getGtwlanguagecode()
	{
		return gtwlanguagecode;
	}

	/**
	 * Method 'setGtwlanguagecode'
	 * 
	 * @param gtwlanguagecode
	 */
	public void setGtwlanguagecode(String gtwlanguagecode)
	{
		this.gtwlanguagecode = gtwlanguagecode;
	}

	/**
	 * Method 'getMdplanguagecode'
	 * 
	 * @return String
	 */
	public String getMdplanguagecode()
	{
		return mdplanguagecode;
	}

	/**
	 * Method 'setMdplanguagecode'
	 * 
	 * @param mdplanguagecode
	 */
	public void setMdplanguagecode(String mdplanguagecode)
	{
		this.mdplanguagecode = mdplanguagecode;
	}

	/**
	 * Method 'getLanguagedescr'
	 * 
	 * @return String
	 */
	public String getLanguagedescr()
	{
		return languagedescr;
	}

	/**
	 * Method 'setLanguagedescr'
	 * 
	 * @param languagedescr
	 */
	public void setLanguagedescr(String languagedescr)
	{
		this.languagedescr = languagedescr;
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
		
		if (!(_other instanceof Language)) {
			return false;
		}
		
		final Language _cast = (Language) _other;
		if (gatewayid != _cast.gatewayid) {
			return false;
		}
		
		if (gtwlanguagecode == null ? _cast.gtwlanguagecode != gtwlanguagecode : !gtwlanguagecode.equals( _cast.gtwlanguagecode )) {
			return false;
		}
		
		if (mdplanguagecode == null ? _cast.mdplanguagecode != mdplanguagecode : !mdplanguagecode.equals( _cast.mdplanguagecode )) {
			return false;
		}
		
		if (languagedescr == null ? _cast.languagedescr != languagedescr : !languagedescr.equals( _cast.languagedescr )) {
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
		if (gatewayid != null) {
			_hashCode = 29 * _hashCode + gatewayid.hashCode();
		}
		if (gtwlanguagecode != null) {
			_hashCode = 29 * _hashCode + gtwlanguagecode.hashCode();
		}
		
		if (mdplanguagecode != null) {
			_hashCode = 29 * _hashCode + mdplanguagecode.hashCode();
		}
		
		if (languagedescr != null) {
			_hashCode = 29 * _hashCode + languagedescr.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.Language: " );
		ret.append( "gatewayid=" + gatewayid );
		ret.append( ", gtwlanguagecode=" + gtwlanguagecode );
		ret.append( ", mdplanguagecode=" + mdplanguagecode );
		ret.append( ", languagedescr=" + languagedescr );
		ret.append( ", enabled=" + enabled );
		return ret.toString();
	}

}
