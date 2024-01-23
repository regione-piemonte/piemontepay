/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import it.csi.mdp.core.business.dao.*;
import it.csi.mdp.core.business.factory.*;
import it.csi.mdp.core.business.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class Config implements Serializable
{
	/** 
	 * This attribute maps to the column key in the config table.
	 */
	protected String key;

	/** 
	 * This attribute maps to the column value in the config table.
	 */
	protected String value;

	/** 
	 * This attribute maps to the column descrizione in the config table.
	 */
	protected String descrizione;

	/**
	 * Method 'Config'
	 * 
	 */
	public Config()
	{
	}

	/**
	 * Method 'getKey'
	 * 
	 * @return String
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Method 'setKey'
	 * 
	 * @param key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Method 'getValue'
	 * 
	 * @return String
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Method 'setValue'
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Method 'getDescrizione'
	 * 
	 * @return String
	 */
	public String getDescrizione()
	{
		return descrizione;
	}

	/**
	 * Method 'setDescrizione'
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
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
		
		if (!(_other instanceof Config)) {
			return false;
		}
		
		final Config _cast = (Config) _other;
		if (key == null ? _cast.key != key : !key.equals( _cast.key )) {
			return false;
		}
		
		if (value == null ? _cast.value != value : !value.equals( _cast.value )) {
			return false;
		}
		
		if (descrizione == null ? _cast.descrizione != descrizione : !descrizione.equals( _cast.descrizione )) {
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
		
		if (value != null) {
			_hashCode = 29 * _hashCode + value.hashCode();
		}
		
		if (descrizione != null) {
			_hashCode = 29 * _hashCode + descrizione.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return ConfigPk
	 */
	public ConfigPk createPk()
	{
		return new ConfigPk(key);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Config: " );
		ret.append( "key=" + key );
		ret.append( ", value=" + value );
		ret.append( ", descrizione=" + descrizione );
		return ret.toString();
	}

}
