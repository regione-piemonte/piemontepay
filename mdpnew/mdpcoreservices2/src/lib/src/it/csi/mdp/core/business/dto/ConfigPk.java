/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the config table.
 */
public class ConfigPk implements Serializable
{
	protected String key;

	/** 
	 * Sets the value of key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/** 
	 * Gets the value of key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Method 'ConfigPk'
	 * 
	 */
	public ConfigPk()
	{
	}

	/**
	 * Method 'ConfigPk'
	 * 
	 * @param key
	 */
	public ConfigPk(final String key)
	{
		this.key = key;
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
		
		if (!(_other instanceof ConfigPk)) {
			return false;
		}
		
		final ConfigPk _cast = (ConfigPk) _other;
		if (key == null ? _cast.key != key : !key.equals( _cast.key )) {
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
		if (key != null) {
			_hashCode = 29 * _hashCode + key.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.ConfigPk: " );
		ret.append( "key=" + key );
		return ret.toString();
	}

}
