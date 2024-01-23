/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;

public class ItemExtraAttribute implements Serializable
{
	/** 
	 * This attribute maps to the column EXTRA_ATTRIBUTE_ID in the ITEM_EXTRA_ATTRIBUTE table.
	 */
	protected long extraAttributeId;

	/** 
	 * This attribute maps to the column NAME in the ITEM_EXTRA_ATTRIBUTE table.
	 */
	protected String name;

	/** 
	 * This attribute maps to the column VALUE in the ITEM_EXTRA_ATTRIBUTE table.
	 */
	protected String value;

	/** 
	 * This attribute maps to the column ITEM_ID in the ITEM_EXTRA_ATTRIBUTE table.
	 */
	protected long itemId;

	/** 
	 * This attribute represents whether the primitive attribute itemId is null.
	 */
	protected boolean itemIdNull = true;

	/**
	 * Method 'ItemExtraAttribute'
	 * 
	 */
	public ItemExtraAttribute()
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
	 * Method 'getItemId'
	 * 
	 * @return long
	 */
	public long getItemId()
	{
		return itemId;
	}

	/**
	 * Method 'setItemId'
	 * 
	 * @param itemId
	 */
	public void setItemId(long itemId)
	{
		this.itemId = itemId;
		this.itemIdNull = false;
	}

	/**
	 * Method 'setItemIdNull'
	 * 
	 * @param value
	 */
	public void setItemIdNull(boolean value)
	{
		this.itemIdNull = value;
	}

	/**
	 * Method 'isItemIdNull'
	 * 
	 * @return boolean
	 */
	public boolean isItemIdNull()
	{
		return itemIdNull;
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
		
		if (!(_other instanceof ItemExtraAttribute)) {
			return false;
		}
		
		final ItemExtraAttribute _cast = (ItemExtraAttribute) _other;
		if (extraAttributeId != _cast.extraAttributeId) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
			return false;
		}
		
		if (value == null ? _cast.value != value : !value.equals( _cast.value )) {
			return false;
		}
		
		if (itemId != _cast.itemId) {
			return false;
		}
		
		if (itemIdNull != _cast.itemIdNull) {
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
		
		_hashCode = 29 * _hashCode + (int) (itemId ^ (itemId >>> 32));
		_hashCode = 29 * _hashCode + (itemIdNull ? 1 : 0);
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
		ret.append( "it.csi.mdp.core.business.dto.ItemExtraAttribute: " );
		ret.append( "extraAttributeId=" + extraAttributeId );
		ret.append( ", name=" + name );
		ret.append( ", value=" + value );
		ret.append( ", itemId=" + itemId );
		return ret.toString();
	}

}
