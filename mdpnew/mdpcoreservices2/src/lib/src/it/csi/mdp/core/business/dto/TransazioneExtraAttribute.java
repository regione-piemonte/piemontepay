/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.*;

public class TransazioneExtraAttribute implements Serializable
{
	/** 
	 * This attribute maps to the column EXTRA_ATTRIBUTE_ID in the TRANSAZIONE_EXTRA_ATTRIBUTE table.
	 */
	protected long extraAttributeId;

	/** 
	 * This attribute maps to the column NAME in the TRANSAZIONE_EXTRA_ATTRIBUTE table.
	 */
	protected String name;

	/** 
	 * This attribute maps to the column VALUE in the TRANSAZIONE_EXTRA_ATTRIBUTE table.
	 */
	protected String value;

	/** 
	 * This attribute maps to the column TRANSACTION_ID in the TRANSAZIONE_EXTRA_ATTRIBUTE table.
	 */
	protected String transactionId;

	/**
	 * Method 'TransazioneExtraAttribute'
	 * 
	 */
	public TransazioneExtraAttribute()
	{
	}
	
	public TransazioneExtraAttribute(long extraAttributeId, String name, String transactionId, String value) {
		this.extraAttributeId = extraAttributeId;
		this.name = name;
		this.transactionId = transactionId;
		this.value = value;
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
	 * Method 'getTransactionId'
	 * 
	 * @return String
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * Method 'setTransactionId'
	 * 
	 * @param transactionId
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
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
		
		if (!(_other instanceof TransazioneExtraAttribute)) {
			return false;
		}
		
		final TransazioneExtraAttribute _cast = (TransazioneExtraAttribute) _other;
		if (extraAttributeId != _cast.extraAttributeId) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
			return false;
		}
		
		if (value == null ? _cast.value != value : !value.equals( _cast.value )) {
			return false;
		}
		
		if (transactionId == null ? _cast.transactionId != transactionId : !transactionId.equals( _cast.transactionId )) {
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
		
		if (transactionId != null) {
			_hashCode = 29 * _hashCode + transactionId.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.TransazioneExtraAttribute: " );
		ret.append( "extraAttributeId=" + extraAttributeId );
		ret.append( ", name=" + name );
		ret.append( ", value=" + value );
		ret.append( ", transactionId=" + transactionId );
		return ret.toString();
	}

}
