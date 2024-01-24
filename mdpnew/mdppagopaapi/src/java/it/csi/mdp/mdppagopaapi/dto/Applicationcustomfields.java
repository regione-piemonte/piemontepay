/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.dto;


import java.io.Serializable;
import java.util.*;

public class Applicationcustomfields implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7886023149745020326L;

	/** 
	 * This attribute maps to the column keyid in the applicationcustomfields table.
	 */
	protected String keyid;

	/** 
	 * This attribute maps to the column applicationid in the applicationcustomfields table.
	 */
	protected String applicationid;

	/** 
	 * This attribute maps to the column fieldname in the applicationcustomfields table.
	 */
	protected String fieldname;

	/** 
	 * This attribute maps to the column fieldvalue in the applicationcustomfields table.
	 */
	protected String fieldvalue;

	/** 
	 * This attribute maps to the column gateway_id in the applicationcustomfields table.
	 */
	protected String gatewayId;

	/** 
	 * This attribute maps to the column fielddescription in the applicationcustomfields table.
	 */
	protected String fielddescription;

	/**
	 * Method 'Applicationcustomfields'
	 * 
	 */
	public Applicationcustomfields()
	{
	}

	/**
	 * Method 'getKeyid'
	 * 
	 * @return int
	 */
	public String getKeyid()
	{
		return keyid;
	}

	/**
	 * Method 'setKeyid'
	 * 
	 * @param keyid
	 */
	public void setKeyid(String keyid)
	{
		this.keyid = keyid;
	}

	/**
	 * Method 'getApplicationid'
	 * 
	 * @return String
	 */
	public String getApplicationid()
	{
		return applicationid;
	}

	/**
	 * Method 'setApplicationid'
	 * 
	 * @param applicationid
	 */
	public void setApplicationid(String applicationid)
	{
		this.applicationid = applicationid;
	}

	/**
	 * Method 'getFieldname'
	 * 
	 * @return String
	 */
	public String getFieldname()
	{
		return fieldname;
	}

	/**
	 * Method 'setFieldname'
	 * 
	 * @param fieldname
	 */
	public void setFieldname(String fieldname)
	{
		this.fieldname = fieldname;
	}

	/**
	 * Method 'getFieldvalue'
	 * 
	 * @return String
	 */
	public String getFieldvalue()
	{
		return fieldvalue;
	}

	/**
	 * Method 'setFieldvalue'
	 * 
	 * @param fieldvalue
	 */
	public void setFieldvalue(String fieldvalue)
	{
		this.fieldvalue = fieldvalue;
	}

	/**
	 * Method 'getGatewayId'
	 * 
	 * @return String
	 */
	public String getGatewayId()
	{
		return gatewayId;
	}

	/**
	 * Method 'setGatewayId'
	 * 
	 * @param gatewayId
	 */
	public void setGatewayId(String gatewayId)
	{
		this.gatewayId = gatewayId;
	}

	/**
	 * Method 'getFielddescription'
	 * 
	 * @return String
	 */
	public String getFielddescription()
	{
		return fielddescription;
	}

	/**
	 * Method 'setFielddescription'
	 * 
	 * @param fielddescription
	 */
	public void setFielddescription(String fielddescription)
	{
		this.fielddescription = fielddescription;
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
		
		if (!(_other instanceof Applicationcustomfields)) {
			return false;
		}
		
		final Applicationcustomfields _cast = (Applicationcustomfields) _other;
		if (keyid != _cast.keyid) {
			return false;
		}
		
		if (applicationid == null ? _cast.applicationid != applicationid : !applicationid.equals( _cast.applicationid )) {
			return false;
		}
		
		if (fieldname == null ? _cast.fieldname != fieldname : !fieldname.equals( _cast.fieldname )) {
			return false;
		}
		
		if (fieldvalue == null ? _cast.fieldvalue != fieldvalue : !fieldvalue.equals( _cast.fieldvalue )) {
			return false;
		}
		
		if (gatewayId == null ? _cast.gatewayId != gatewayId : !gatewayId.equals( _cast.gatewayId )) {
			return false;
		}
		
		if (fielddescription == null ? _cast.fielddescription != fielddescription : !fielddescription.equals( _cast.fielddescription )) {
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
		if (applicationid != null) {
			_hashCode = 29 * _hashCode + applicationid.hashCode();
		}
		
		if (fieldname != null) {
			_hashCode = 29 * _hashCode + fieldname.hashCode();
		}
		
		if (fieldvalue != null) {
			_hashCode = 29 * _hashCode + fieldvalue.hashCode();
		}
		
		if (gatewayId != null) {
			_hashCode = 29 * _hashCode + gatewayId.hashCode();
		}
		
		if (fielddescription != null) {
			_hashCode = 29 * _hashCode + fielddescription.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return ApplicationcustomfieldsPk
	 */
	public ApplicationcustomfieldsPk createPk()
	{
		return new ApplicationcustomfieldsPk(keyid);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Applicationcustomfields: " );
		ret.append( "keyid=" + keyid );
		ret.append( ", applicationid=" + applicationid );
		ret.append( ", fieldname=" + fieldname );
		ret.append( ", fieldvalue=" + fieldvalue );
		ret.append( ", gatewayId=" + gatewayId );
		ret.append( ", fielddescription=" + fielddescription );
		return ret.toString();
	}

}
