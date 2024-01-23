/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.Date;

public class Gateway implements Serializable
{
	/** 
	 * This attribute maps to the column GATEWAY_ID in the GATEWAY table.
	 */
	protected String gatewayId;

	/** 
	 * This attribute maps to the column GATEWAY_DESCRIPTION in the GATEWAY table.
	 */
	protected String gatewayDescription;

	/** 
	 * This attribute maps to the column GATEWAY_PROVIDER in the GATEWAY table.
	 */
	protected String gatewayProvider;

	/** 
	 * This attribute maps to the column VALIDO_DAL in the GATEWAY table.
	 */
	protected Date validoDal;

	/** 
	 * This attribute maps to the column VALIDO_AL in the GATEWAY table.
	 */
	protected Date validoAl;

	/** 
	 * This attribute maps to the column GATEWAYSERVICENAME in the GATEWAY table.
	 */
	protected String gatewayservicename;

	/**
	 * Method 'Gateway'
	 * 
	 */
	public Gateway()
	{
	}

	/**
	 * Method 'getGatewayId'
	 * 
	 * @return long
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
	 * Method 'getGatewayDescription'
	 * 
	 * @return String
	 */
	public String getGatewayDescription()
	{
		return gatewayDescription;
	}

	/**
	 * Method 'setGatewayDescription'
	 * 
	 * @param gatewayDescription
	 */
	public void setGatewayDescription(String gatewayDescription)
	{
		this.gatewayDescription = gatewayDescription;
	}

	/**
	 * Method 'getGatewayProvider'
	 * 
	 * @return String
	 */
	public String getGatewayProvider()
	{
		return gatewayProvider;
	}

	/**
	 * Method 'setGatewayProvider'
	 * 
	 * @param gatewayProvider
	 */
	public void setGatewayProvider(String gatewayProvider)
	{
		this.gatewayProvider = gatewayProvider;
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
	 * Method 'getGatewayservicename'
	 * 
	 * @return String
	 */
	public String getGatewayservicename()
	{
		return gatewayservicename;
	}

	/**
	 * Method 'setGatewayservicename'
	 * 
	 * @param gatewayservicename
	 */
	public void setGatewayservicename(String gatewayservicename)
	{
		this.gatewayservicename = gatewayservicename;
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
		
		if (!(_other instanceof Gateway)) {
			return false;
		}
		
		final Gateway _cast = (Gateway) _other;
		if (gatewayId != _cast.gatewayId) {
			return false;
		}
		
		if (gatewayDescription == null ? _cast.gatewayDescription != gatewayDescription : !gatewayDescription.equals( _cast.gatewayDescription )) {
			return false;
		}
		
		if (gatewayProvider == null ? _cast.gatewayProvider != gatewayProvider : !gatewayProvider.equals( _cast.gatewayProvider )) {
			return false;
		}
		
		if (validoDal == null ? _cast.validoDal != validoDal : !validoDal.equals( _cast.validoDal )) {
			return false;
		}
		
		if (validoAl == null ? _cast.validoAl != validoAl : !validoAl.equals( _cast.validoAl )) {
			return false;
		}
		
		if (gatewayservicename == null ? _cast.gatewayservicename != gatewayservicename : !gatewayservicename.equals( _cast.gatewayservicename )) {
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
		_hashCode = 29 * _hashCode + gatewayId.hashCode();
		if (gatewayDescription != null) {
			_hashCode = 29 * _hashCode + gatewayDescription.hashCode();
		}
		
		if (gatewayProvider != null) {
			_hashCode = 29 * _hashCode + gatewayProvider.hashCode();
		}
		
		if (validoDal != null) {
			_hashCode = 29 * _hashCode + validoDal.hashCode();
		}
		
		if (validoAl != null) {
			_hashCode = 29 * _hashCode + validoAl.hashCode();
		}
		
		if (gatewayservicename != null) {
			_hashCode = 29 * _hashCode + gatewayservicename.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return GatewayPk
	 */
	public GatewayPk createPk()
	{
		return new GatewayPk(gatewayId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Gateway: " );
		ret.append( "gatewayId=" + gatewayId );
		ret.append( ", gatewayDescription=" + gatewayDescription );
		ret.append( ", gatewayProvider=" + gatewayProvider );
		ret.append( ", validoDal=" + validoDal );
		ret.append( ", validoAl=" + validoAl );
		ret.append( ", gatewayservicename=" + gatewayservicename );
		return ret.toString();
	}

}
