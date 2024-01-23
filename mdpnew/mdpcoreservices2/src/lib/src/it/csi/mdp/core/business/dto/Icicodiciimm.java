/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;


import java.io.Serializable;
import java.util.*;

public class Icicodiciimm implements Serializable
{
	/** 
	 * This attribute maps to the column APPLICATIONID in the ICICODICIIMM table.
	 */
	protected String applicationid;

	/** 
	 * This attribute maps to the column CODICEIMM in the ICICODICIIMM table.
	 */
	protected String codiceimm;

	/** 
	 * This attribute maps to the column CAUSALE in the ICICODICIIMM table.
	 */
	protected String causale;

	/**
	 * Method 'Icicodiciimm'
	 * 
	 */
	public Icicodiciimm()
	{
	}

	/**
	 * Method 'getApplicationId'
	 * 
	 * @return String
	 */
	public String getApplicationId()
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
	 * Method 'getCausale'
	 * 
	 * @return String
	 */
	public String getCausale()
	{
		return causale;
	}

	/**
	 * Method 'setCausale'
	 * 
	 * @param causale
	 */
	public void setCausale(String causale)
	{
		this.causale = causale;
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
		
		if (!(_other instanceof Icicodiciimm)) {
			return false;
		}
		
		final Icicodiciimm _cast = (Icicodiciimm) _other;
		if (applicationid == null ? _cast.applicationid != applicationid : !applicationid.equals( _cast.applicationid )) {
			return false;
		}
		
		if (codiceimm == null ? _cast.codiceimm != codiceimm : !codiceimm.equals( _cast.codiceimm )) {
			return false;
		}
		
		if (causale == null ? _cast.causale != causale : !causale.equals( _cast.causale )) {
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
		if (applicationid != null) {
			_hashCode = 29 * _hashCode + applicationid.hashCode();
		}
		
		if (codiceimm != null) {
			_hashCode = 29 * _hashCode + codiceimm.hashCode();
		}
		
		if (causale != null) {
			_hashCode = 29 * _hashCode + causale.hashCode();
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
		ret.append( "it.csi.mdp.core.business.dto.Icicodiciimm: " );
		ret.append( "applicationid=" + applicationid );
		ret.append( ", codiceimm=" + codiceimm );
		ret.append( ", causale=" + causale );
		return ret.toString();
	}

}
