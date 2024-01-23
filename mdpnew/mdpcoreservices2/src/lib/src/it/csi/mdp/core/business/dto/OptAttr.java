/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;

public class OptAttr implements Serializable
{
	/** 
	 * This attribute maps to the column OPT_ATTR_ID in the OPT_ATTR table.
	 */
	protected long optAttrId;

	/** 
	 * This attribute maps to the column BUYER_NAME in the OPT_ATTR table.
	 */
	protected String buyerName;

	/** 
	 * This attribute maps to the column BUYER_CODE in the OPT_ATTR table.
	 */
	protected String buyerCode;

	/** 
	 * This attribute maps to the column TRANSACTION_ID in the OPT_ATTR table.
	 */
	protected String transactionId;

	/**
	 * Method 'OptAttr'
	 * 
	 */
	public OptAttr()
	{
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
	}

	/**
	 * Method 'getBuyerName'
	 * 
	 * @return String
	 */
	public String getBuyerName()
	{
		return buyerName;
	}

	/**
	 * Method 'setBuyerName'
	 * 
	 * @param buyerName
	 */
	public void setBuyerName(String buyerName)
	{
		this.buyerName = buyerName;
	}

	/**
	 * Method 'getBuyerCode'
	 * 
	 * @return String
	 */
	public String getBuyerCode()
	{
		return buyerCode;
	}

	/**
	 * Method 'setBuyerCode'
	 * 
	 * @param buyerCode
	 */
	public void setBuyerCode(String buyerCode)
	{
		this.buyerCode = buyerCode;
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
		
		if (!(_other instanceof OptAttr)) {
			return false;
		}
		
		final OptAttr _cast = (OptAttr) _other;
		if (optAttrId != _cast.optAttrId) {
			return false;
		}
		
		if (buyerName == null ? _cast.buyerName != buyerName : !buyerName.equals( _cast.buyerName )) {
			return false;
		}
		
		if (buyerCode == null ? _cast.buyerCode != buyerCode : !buyerCode.equals( _cast.buyerCode )) {
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
		_hashCode = 29 * _hashCode + (int) (optAttrId ^ (optAttrId >>> 32));
		if (buyerName != null) {
			_hashCode = 29 * _hashCode + buyerName.hashCode();
		}
		
		if (buyerCode != null) {
			_hashCode = 29 * _hashCode + buyerCode.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.OptAttr: " );
		ret.append( "optAttrId=" + optAttrId );
		ret.append( ", buyerName=" + buyerName );
		ret.append( ", buyerCode=" + buyerCode );
		ret.append( ", transactionId=" + transactionId );
		return ret.toString();
	}

}
