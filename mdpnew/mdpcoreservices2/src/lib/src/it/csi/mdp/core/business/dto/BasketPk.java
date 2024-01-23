/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

/** 
 * This class represents the primary key of the BASKET table.
 */
public class BasketPk implements Serializable
{
	protected String basketId;

	/** 
	 * Sets the value of basketId
	 */
	public void setBasketId(String basketId)
	{
		this.basketId = basketId;
	}

	/** 
	 * Gets the value of basketId
	 */
	public String getBasketId()
	{
		return basketId;
	}

	/**
	 * Method 'BasketPk'
	 * 
	 */
	public BasketPk()
	{
	}

	/**
	 * Method 'BasketPk'
	 * 
	 * @param basketId
	 */
	public BasketPk(final String basketId)
	{
		this.basketId = basketId;
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
		
		if (!(_other instanceof BasketPk)) {
			return false;
		}
		
		final BasketPk _cast = (BasketPk) _other;
		if (basketId == null ? _cast.basketId != basketId : !basketId.equals( _cast.basketId )) {
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
		if (basketId != null) {
			_hashCode = 29 * _hashCode + basketId.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.BasketPk: " );
		ret.append( "basketId=" + basketId );
		return ret.toString();
	}

}
