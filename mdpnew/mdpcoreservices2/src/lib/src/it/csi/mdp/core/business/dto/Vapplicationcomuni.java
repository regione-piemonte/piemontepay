/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class Vapplicationcomuni implements Serializable
{
	/** 
	 * This attribute maps to the column MERCHANTID in the VAPPLICATIONCOMUNI table.
	 */
	protected String merchantid;

	/** 
	 * This attribute maps to the column DESC_COMUNE in the VAPPLICATIONCOMUNI table.
	 */
	protected String descComune;

	/** 
	 * This attribute maps to the column CODICEIMM in the VAPPLICATIONCOMUNI table.
	 */
	protected String codiceimm;
protected String gatewayId;
protected String applicationId;	
/**
	 * Method 'Vapplicationcomuni'
	 * 
	 */
	public Vapplicationcomuni()
	{
	}

	/**
	 * Method 'getMerchantid'
	 * 
	 * @return String
	 */
	public String getMerchantid()
	{
		return merchantid;
	}

	/**
	 * Method 'setMerchantid'
	 * 
	 * @param merchantid
	 */
	public void setMerchantid(String merchantid)
	{
		this.merchantid = merchantid;
	}

	/**
	 * Method 'getDescComune'
	 * 
	 * @return String
	 */
	public String getDescComune()
	{
		return descComune;
	}

	/**
	 * Method 'setDescComune'
	 * 
	 * @param descComune
	 */
	public void setDescComune(String descComune)
	{
		this.descComune = descComune;
	}

	/**
	 * Method 'getCodiceimm'
	 * 
	 * @return String
	 */
	public String getCodiceimm()
	{
		return codiceimm;
	}

	/**
	 * Method 'setCodiceimm'
	 * 
	 * @param codiceimm
	 */
	public void setCodiceimm(String codiceimm)
	{
		this.codiceimm = codiceimm;
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
		
		if (!(_other instanceof Vapplicationcomuni)) {
			return false;
		}
		
		final Vapplicationcomuni _cast = (Vapplicationcomuni) _other;
		if (merchantid == null ? _cast.merchantid != merchantid : !merchantid.equals( _cast.merchantid )) {
			return false;
		}
		
		if (descComune == null ? _cast.descComune != descComune : !descComune.equals( _cast.descComune )) {
			return false;
		}
		
		if (codiceimm == null ? _cast.codiceimm != codiceimm : !codiceimm.equals( _cast.codiceimm )) {
			return false;
		}
		
		if (gatewayId == null ? _cast.gatewayId != gatewayId : !gatewayId.equals( _cast.gatewayId )) {
			return false;
		}
		if (applicationId == null ? _cast.applicationId != applicationId : !applicationId.equals( _cast.applicationId )) {
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
		if (merchantid != null) {
			_hashCode = 29 * _hashCode + merchantid.hashCode();
		}
		
		if (descComune != null) {
			_hashCode = 29 * _hashCode + descComune.hashCode();
		}
		
		if (codiceimm != null) {
			_hashCode = 29 * _hashCode + codiceimm.hashCode();
		}
		if (gatewayId != null) {
			_hashCode = 29 * _hashCode + gatewayId.hashCode();
		}
		if (applicationId != null) {
			_hashCode = 29 * _hashCode + applicationId.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dao.dto.Vapplicationcomuni: " );
		ret.append( "merchantid=" + merchantid );
		ret.append( ", descComune=" + descComune );
		ret.append( ", codiceimm=" + codiceimm );
		ret.append( ", applicationId=" + applicationId );
		ret.append( ", gatewayId=" + gatewayId );
		return ret.toString();
	}

	public String getGatewayId()
	{
		return gatewayId;
	}

	public void setGatewayId(String gatewayId)
	{
		this.gatewayId = gatewayId;
	}

	public String getApplicationId()
	{
		return applicationId;
	}

	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

}
