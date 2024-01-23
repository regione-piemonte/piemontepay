/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the TRANSAZIONE table.
 */
public class TransazionePk implements Serializable
{
	protected String transactionId;

	/** 
	 * Sets the value of transactionId
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	/** 
	 * Gets the value of transactionId
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * Method 'TransazionePk'
	 * 
	 */
	public TransazionePk()
	{
	}

	/**
	 * Method 'TransazionePk'
	 * 
	 * @param transactionId
	 */
	public TransazionePk(final String transactionId)
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
		
		if (!(_other instanceof TransazionePk)) {
			return false;
		}
		
		final TransazionePk _cast = (TransazionePk) _other;
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
		ret.append( "it.csi.mdp.core.business.dto.TransazionePk: " );
		ret.append( "transactionId=" + transactionId );
		return ret.toString();
	}

}
