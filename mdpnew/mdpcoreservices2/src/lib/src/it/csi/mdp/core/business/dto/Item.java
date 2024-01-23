/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Item implements Serializable
{
	/** 
	 * This attribute maps to the column ITEM_ID in the ITEM table.
	 */
	protected long itemId;

	/** 
	 * This attribute maps to the column BASKET_ID in the ITEM table.
	 */
	protected String basketId;

	/** 
	 * This attribute maps to the column QUANTITY in the ITEM table.
	 */
	protected long quantity;

	/** 
	 * This attribute represents whether the primitive attribute quantity is null.
	 */
	protected boolean quantityNull = true;

	/** 
	 * This attribute maps to the column PRICE_ITEM in the ITEM table.
	 */
	protected long priceItem;

	/** 
	 * This attribute represents whether the primitive attribute priceItem is null.
	 */
	protected boolean priceItemNull = true;

	/** 
	 * This attribute maps to the column DESCRIPTION in the ITEM table.
	 */
	protected String description;

	/** 
	 * This attribute maps to the column CREATION_DATE in the ITEM table.
	 */
	protected Date creationDate;

	/** 
	 * This attribute maps to the column TOTAL_PRICE in the ITEM table.
	 */
	protected long totalPrice;

	/** 
	 * This attribute represents whether the primitive attribute totalPrice is null.
	 */
	protected boolean totalPriceNull = true;

	/** 
	 * This attribute maps to the column ITEM_PK_ID in the ITEM table.
	 */
	protected long itemPkId;

	/**
	 * Method 'Item'
	 * 
	 */
	public Item()
	{
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
	 * Method 'getQuantity'
	 * 
	 * @return long
	 */
	public long getQuantity()
	{
		return quantity;
	}

	/**
	 * Method 'setQuantity'
	 * 
	 * @param quantity
	 */
	public void setQuantity(long quantity)
	{
		this.quantity = quantity;
		this.quantityNull = false;
	}

	/**
	 * Method 'setQuantityNull'
	 * 
	 * @param value
	 */
	public void setQuantityNull(boolean value)
	{
		this.quantityNull = value;
	}

	/**
	 * Method 'isQuantityNull'
	 * 
	 * @return boolean
	 */
	public boolean isQuantityNull()
	{
		return quantityNull;
	}

	/**
	 * Method 'getPriceItem'
	 * 
	 * @return long
	 */
	public long getPriceItem()
	{
		return priceItem;
	}

	/**
	 * Method 'setPriceItem'
	 * 
	 * @param priceItem
	 */
	public void setPriceItem(long priceItem)
	{
		this.priceItem = priceItem;
		this.priceItemNull = false;
	}

	/**
	 * Method 'setPriceItemNull'
	 * 
	 * @param value
	 */
	public void setPriceItemNull(boolean value)
	{
		this.priceItemNull = value;
	}

	/**
	 * Method 'isPriceItemNull'
	 * 
	 * @return boolean
	 */
	public boolean isPriceItemNull()
	{
		return priceItemNull;
	}

	/**
	 * Method 'getDescription'
	 * 
	 * @return String
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Method 'setDescription'
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
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
	 * Method 'getTotalPrice'
	 * 
	 * @return long
	 */
	public long getTotalPrice()
	{
		return totalPrice;
	}

	/**
	 * Method 'setTotalPrice'
	 * 
	 * @param totalPrice
	 */
	public void setTotalPrice(long totalPrice)
	{
		this.totalPrice = totalPrice;
		this.totalPriceNull = false;
	}

	/**
	 * Method 'setTotalPriceNull'
	 * 
	 * @param value
	 */
	public void setTotalPriceNull(boolean value)
	{
		this.totalPriceNull = value;
	}

	/**
	 * Method 'isTotalPriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isTotalPriceNull()
	{
		return totalPriceNull;
	}

	/**
	 * Method 'getItemPkId'
	 * 
	 * @return long
	 */
	public long getItemPkId()
	{
		return itemPkId;
	}

	/**
	 * Method 'setItemPkId'
	 * 
	 * @param itemPkId
	 */
	public void setItemPkId(long itemPkId)
	{
		this.itemPkId = itemPkId;
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
		
		if (!(_other instanceof Item)) {
			return false;
		}
		
		final Item _cast = (Item) _other;
		if (itemId != _cast.itemId) {
			return false;
		}
		
		if (basketId == null ? _cast.basketId != basketId : !basketId.equals( _cast.basketId )) {
			return false;
		}
		
		if (quantity != _cast.quantity) {
			return false;
		}
		
		if (quantityNull != _cast.quantityNull) {
			return false;
		}
		
		if (priceItem != _cast.priceItem) {
			return false;
		}
		
		if (priceItemNull != _cast.priceItemNull) {
			return false;
		}
		
		if (description == null ? _cast.description != description : !description.equals( _cast.description )) {
			return false;
		}
		
		if (creationDate == null ? _cast.creationDate != creationDate : !creationDate.equals( _cast.creationDate )) {
			return false;
		}
		
		if (totalPrice != _cast.totalPrice) {
			return false;
		}
		
		if (totalPriceNull != _cast.totalPriceNull) {
			return false;
		}
		
		if (itemPkId != _cast.itemPkId) {
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
		_hashCode = 29 * _hashCode + (int) (itemId ^ (itemId >>> 32));
		if (basketId != null) {
			_hashCode = 29 * _hashCode + basketId.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (quantity ^ (quantity >>> 32));
		_hashCode = 29 * _hashCode + (quantityNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) (priceItem ^ (priceItem >>> 32));
		_hashCode = 29 * _hashCode + (priceItemNull ? 1 : 0);
		if (description != null) {
			_hashCode = 29 * _hashCode + description.hashCode();
		}
		
		if (creationDate != null) {
			_hashCode = 29 * _hashCode + creationDate.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (totalPrice ^ (totalPrice >>> 32));
		_hashCode = 29 * _hashCode + (totalPriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) (itemPkId ^ (itemPkId >>> 32));
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
		ret.append( "it.csi.mdp.core.business.dto.Item: " );
		ret.append( "itemId=" + itemId );
		ret.append( ", basketId=" + basketId );
		ret.append( ", quantity=" + quantity );
		ret.append( ", priceItem=" + priceItem );
		ret.append( ", description=" + description );
		ret.append( ", creationDate=" + creationDate );
		ret.append( ", totalPrice=" + totalPrice );
		ret.append( ", itemPkId=" + itemPkId );
		return ret.toString();
	}

}
