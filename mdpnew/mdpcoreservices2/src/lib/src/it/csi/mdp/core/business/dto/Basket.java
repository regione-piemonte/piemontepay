/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.Date;

public class Basket implements Serializable
{
	/** 
	 * This attribute maps to the column BASKET_ID in the BASKET table.
	 */
	protected String basketId;

	/** 
	 * This attribute maps to the column CREATION_DATE in the BASKET table.
	 */
	protected Date creationDate;

	/** 
	 * This attribute maps to the column LAST_CHANGE_DATE in the BASKET table.
	 */
	protected Date lastChangeDate;

	/**
	 * Method 'Basket'
	 * 
	 */
	public Basket()
	{
	}

	/**
	 * Method 'getBasketId'
	 * 
	 * @return String
	 */
	public String getBasketId()
	{
		return basketId;
	}

	/**
	 * Method 'setBasketId'
	 * 
	 * @param basketId
	 */
	public void setBasketId(String basketId)
	{
		this.basketId = basketId;
	}

	/**
	 * Method 'getCreationDate'
	 * 
	 * @return Date
	 */
	public Date getCreationDate()
	{
		return creationDate;
	}

	/**
	 * Method 'setCreationDate'
	 * 
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	/**
	 * Method 'getLastChangeDate'
	 * 
	 * @return Date
	 */
	public Date getLastChangeDate()
	{
		return lastChangeDate;
	}

	/**
	 * Method 'setLastChangeDate'
	 * 
	 * @param lastChangeDate
	 */
	public void setLastChangeDate(Date lastChangeDate)
	{
		this.lastChangeDate = lastChangeDate;
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
		
		if (!(_other instanceof Basket)) {
			return false;
		}
		
		final Basket _cast = (Basket) _other;
		if (basketId == null ? _cast.basketId != basketId : !basketId.equals( _cast.basketId )) {
			return false;
		}
		
		if (creationDate == null ? _cast.creationDate != creationDate : !creationDate.equals( _cast.creationDate )) {
			return false;
		}
		
		if (lastChangeDate == null ? _cast.lastChangeDate != lastChangeDate : !lastChangeDate.equals( _cast.lastChangeDate )) {
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
		
		if (creationDate != null) {
			_hashCode = 29 * _hashCode + creationDate.hashCode();
		}
		
		if (lastChangeDate != null) {
			_hashCode = 29 * _hashCode + lastChangeDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return BasketPk
	 */
	public BasketPk createPk()
	{
		return new BasketPk(basketId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Basket: " );
		ret.append( "basketId=" + basketId );
		ret.append( ", creationDate=" + creationDate );
		ret.append( ", lastChangeDate=" + lastChangeDate );
		return ret.toString();
	}

}
