/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.*;

public class OptAttrExtraAttribute implements Serializable
{
	/** 
	 * This attribute maps to the column EXTRA_ATTRIBUTE_ID in the OPT_ATTR_EXTRA_ATTRIBUTE table.
	 */
	protected long extraAttributeId;

	/** 
	 * This attribute maps to the column NAME in the OPT_ATTR_EXTRA_ATTRIBUTE table.
	 */
	protected String name;

	/** 
	 * This attribute maps to the column VALUE in the OPT_ATTR_EXTRA_ATTRIBUTE table.
	 */
	protected String value;

	/** 
	 * This attribute maps to the column OPT_ATTR_ID in the OPT_ATTR_EXTRA_ATTRIBUTE table.
	 */
	protected long optAttrId;

	/** 
	 * This attribute represents whether the primitive attribute optAttrId is null.
	 */
	protected boolean optAttrIdNull = true;

	/**
	 * Method 'OptAttrExtraAttribute'
	 * 
	 */
	public OptAttrExtraAttribute()
	{
	}

	/**
	 * Method 'getExtraAttributeId'
	 * 
	 * @return long
	 */
	public long getExtraAttributeId()
	{
		return extraAttributeId;
	}

	/**
	 * Method 'setExtraAttributeId'
	 * 
	 * @param extraAttributeId
	 */
	public void setExtraAttributeId(long extraAttributeId)
	{
		this.extraAttributeId = extraAttributeId;
	}

	/**
	 * Method 'getName'
	 * 
	 * @return String
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Method 'setName'
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
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
	 * Method 'getOptAttrId'
	 * 
	 * @return long
	 */
	public long getOptAttrId()
	{
		return optAttrId;
	}

	/**
	 * Method 'setOptAttrId'
	 * 
	 * @param optAttrId
	 */
	public void setOptAttrId(long optAttrId)
	{
		this.optAttrId = optAttrId;
		this.optAttrIdNull = false;
	}

	/**
	 * Method 'setOptAttrIdNull'
	 * 
	 * @param value
	 */
	public void setOptAttrIdNull(boolean value)
	{
		this.optAttrIdNull = value;
	}

	/**
	 * Method 'isOptAttrIdNull'
	 * 
	 * @return boolean
	 */
	public boolean isOptAttrIdNull()
	{
		return optAttrIdNull;
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
		
		if (!(_other instanceof OptAttrExtraAttribute)) {
			return false;
		}
		
		final OptAttrExtraAttribute _cast = (OptAttrExtraAttribute) _other;
		if (extraAttributeId != _cast.extraAttributeId) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
			return false;
		}
		
		if (value == null ? _cast.value != value : !value.equals( _cast.value )) {
			return false;
		}
		
		if (optAttrId != _cast.optAttrId) {
			return false;
		}
		
		if (optAttrIdNull != _cast.optAttrIdNull) {
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
		_hashCode = 29 * _hashCode + (int) (extraAttributeId ^ (extraAttributeId >>> 32));
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}
		
		if (value != null) {
			_hashCode = 29 * _hashCode + value.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (optAttrId ^ (optAttrId >>> 32));
		_hashCode = 29 * _hashCode + (optAttrIdNull ? 1 : 0);
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
		ret.append( "it.csi.mdp.core.business.dto.OptAttrExtraAttribute: " );
		ret.append( "extraAttributeId=" + extraAttributeId );
		ret.append( ", name=" + name );
		ret.append( ", value=" + value );
		ret.append( ", optAttrId=" + optAttrId );
		return ret.toString();
	}

}
