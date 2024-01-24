/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the applicationcustomfields table.
 */
public class ApplicationcustomfieldsPk implements Serializable
{
	protected String keyid;

	/** 
	 * This attribute represents whether the primitive attribute keyid is null.
	 */
	protected boolean keyidNull;

	/** 
	 * Sets the value of keyid
	 */
	public void setKeyid(String keyid)
	{
		this.keyid = keyid;
	}

	/** 
	 * Gets the value of keyid
	 */
	public String getKeyid()
	{
		return keyid;
	}

	/**
	 * Method 'ApplicationcustomfieldsPk'
	 * 
	 */
	public ApplicationcustomfieldsPk()
	{
	}

	/**
	 * Method 'ApplicationcustomfieldsPk'
	 * 
	 * @param keyid
	 */
	public ApplicationcustomfieldsPk(final String keyid)
	{
		this.keyid = keyid;
	}

	/** 
	 * Sets the value of keyidNull
	 */
	public void setKeyidNull(boolean keyidNull)
	{
		this.keyidNull = keyidNull;
	}

	/** 
	 * Gets the value of keyidNull
	 */
	public boolean isKeyidNull()
	{
		return keyidNull;
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
		
		if (!(_other instanceof ApplicationcustomfieldsPk)) {
			return false;
		}
		
		final ApplicationcustomfieldsPk _cast = (ApplicationcustomfieldsPk) _other;
		if (keyid != _cast.keyid) {
			return false;
		}
		
		if (keyidNull != _cast.keyidNull) {
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
		_hashCode = 29 * _hashCode + keyid.hashCode();
		_hashCode = 29 * _hashCode + (keyidNull ? 1 : 0);
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
		ret.append( "it.csi.mdp.core.business.dto.ApplicationcustomfieldsPk: " );
		ret.append( "keyid=" + keyid );
		return ret.toString();
	}

}
