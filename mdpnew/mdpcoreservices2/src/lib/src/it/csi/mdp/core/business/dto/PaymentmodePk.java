/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the PAYMENTMODE table.
 */
public class PaymentmodePk implements Serializable
{
	protected String paymentmodeId;

	/** 
	 * This attribute represents whether the primitive attribute paymentmodeId is null.
	 */
	protected boolean paymentmodeIdNull;

	/** 
	 * Sets the value of paymentmodeId
	 */
	public void setPaymentmodeId(String paymentmodeId)
	{
		this.paymentmodeId = paymentmodeId;
	}

	/** 
	 * Gets the value of paymentmodeId
	 */
	public String getPaymentmodeId()
	{
		return paymentmodeId;
	}

	/**
	 * Method 'PaymentmodePk'
	 * 
	 */
	public PaymentmodePk()
	{
	}

	/**
	 * Method 'PaymentmodePk'
	 * 
	 * @param paymentmodeId
	 */
	public PaymentmodePk(final String paymentmodeId)
	{
		this.paymentmodeId = paymentmodeId;
	}

	/** 
	 * Sets the value of paymentmodeIdNull
	 */
	public void setPaymentmodeIdNull(boolean paymentmodeIdNull)
	{
		this.paymentmodeIdNull = paymentmodeIdNull;
	}

	/** 
	 * Gets the value of paymentmodeIdNull
	 */
	public boolean isPaymentmodeIdNull()
	{
		return paymentmodeIdNull;
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
		
		if (!(_other instanceof PaymentmodePk)) {
			return false;
		}
		
		final PaymentmodePk _cast = (PaymentmodePk) _other;
		if (paymentmodeId != _cast.paymentmodeId) {
			return false;
		}
		
		if (paymentmodeIdNull != _cast.paymentmodeIdNull) {
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
		ret.append( "it.csi.mdp.core.business.dto.PaymentmodePk: " );
		ret.append( "paymentmodeId=" + paymentmodeId );
		return ret.toString();
	}

}
