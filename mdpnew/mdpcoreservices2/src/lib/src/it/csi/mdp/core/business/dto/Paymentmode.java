/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Paymentmode implements Serializable
{
	/** 
	 * This attribute maps to the column PAYMENTMODE_ID in the PAYMENTMODE table.
	 */
	protected String paymentmodeId;

	/** 
	 * This attribute maps to the column PAYMENTMODE_DESCRIPTION in the PAYMENTMODE table.
	 */
	protected String paymentmodeDescription;

	/** 
	 * This attribute maps to the column VALIDO_DAL in the PAYMENTMODE table.
	 */
	protected Date validoDal;

	/** 
	 * This attribute maps to the column VALIDO_AL in the PAYMENTMODE table.
	 */
	protected Date validoAl;

	/**
	 * Method 'Paymentmode'
	 * 
	 */
	public Paymentmode()
	{
	}

	/**
	 * Method 'getPaymentmodeId'
	 * 
	 * @return String
	 */
	public String getPaymentmodeId()
	{
		return paymentmodeId;
	}

	/**
	 * Method 'setPaymentmodeId'
	 * 
	 * @param paymentmodeId
	 */
	public void setPaymentmodeId(String paymentmodeId)
	{
		this.paymentmodeId = paymentmodeId;
	}

	/**
	 * Method 'getPaymentmodeDescription'
	 * 
	 * @return String
	 */
	public String getPaymentmodeDescription()
	{
		return paymentmodeDescription;
	}

	/**
	 * Method 'setPaymentmodeDescription'
	 * 
	 * @param paymentmodeDescription
	 */
	public void setPaymentmodeDescription(String paymentmodeDescription)
	{
		this.paymentmodeDescription = paymentmodeDescription;
	}

	/**
	 * Method 'getValidoDal'
	 * 
	 * @return Date
	 */
	public Date getValidoDal()
	{
		return validoDal;
	}

	/**
	 * Method 'setValidoDal'
	 * 
	 * @param validoDal
	 */
	public void setValidoDal(Date validoDal)
	{
		this.validoDal = validoDal;
	}

	/**
	 * Method 'getValidoAl'
	 * 
	 * @return Date
	 */
	public Date getValidoAl()
	{
		return validoAl;
	}

	/**
	 * Method 'setValidoAl'
	 * 
	 * @param validoAl
	 */
	public void setValidoAl(Date validoAl)
	{
		this.validoAl = validoAl;
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
		
		if (!(_other instanceof Paymentmode)) {
			return false;
		}
		
		final Paymentmode _cast = (Paymentmode) _other;
		if (paymentmodeId != _cast.paymentmodeId) {
			return false;
		}
		
		if (paymentmodeDescription == null ? _cast.paymentmodeDescription != paymentmodeDescription : !paymentmodeDescription.equals( _cast.paymentmodeDescription )) {
			return false;
		}
		
		if (validoDal == null ? _cast.validoDal != validoDal : !validoDal.equals( _cast.validoDal )) {
			return false;
		}
		
		if (validoAl == null ? _cast.validoAl != validoAl : !validoAl.equals( _cast.validoAl )) {
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
		_hashCode = 29 * _hashCode + paymentmodeId.hashCode();
		if (paymentmodeDescription != null) {
			_hashCode = 29 * _hashCode + paymentmodeDescription.hashCode();
		}
		
		if (validoDal != null) {
			_hashCode = 29 * _hashCode + validoDal.hashCode();
		}
		
		if (validoAl != null) {
			_hashCode = 29 * _hashCode + validoAl.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return PaymentmodePk
	 */
	public PaymentmodePk createPk()
	{
		return new PaymentmodePk(paymentmodeId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Paymentmode: " );
		ret.append( "paymentmodeId=" + paymentmodeId );
		ret.append( ", paymentmodeDescription=" + paymentmodeDescription );
		ret.append( ", validoDal=" + validoDal );
		ret.append( ", validoAl=" + validoAl );
		return ret.toString();
	}

}
