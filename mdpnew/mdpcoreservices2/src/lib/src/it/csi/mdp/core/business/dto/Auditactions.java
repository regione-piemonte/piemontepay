/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import it.csi.mdp.core.business.dao.*;
import it.csi.mdp.core.business.factory.*;
import it.csi.mdp.core.business.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class Auditactions implements Serializable
{
	/** 
	 * This attribute maps to the column idaction in the auditactions table.
	 */
	protected String idaction;

	/** 
	 * This attribute maps to the column description in the auditactions table.
	 */
	protected String description;

	/**
	 * Method 'Auditactions'
	 * 
	 */
	public Auditactions()
	{
	}

	/**
	 * Method 'getIdaction'
	 * 
	 * @return String
	 */
	public String getIdaction()
	{
		return idaction;
	}

	/**
	 * Method 'setIdaction'
	 * 
	 * @param idaction
	 */
	public void setIdaction(String idaction)
	{
		this.idaction = idaction;
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
		
		if (!(_other instanceof Auditactions)) {
			return false;
		}
		
		final Auditactions _cast = (Auditactions) _other;
		if (idaction == null ? _cast.idaction != idaction : !idaction.equals( _cast.idaction )) {
			return false;
		}
		
		if (description == null ? _cast.description != description : !description.equals( _cast.description )) {
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
		if (idaction != null) {
			_hashCode = 29 * _hashCode + idaction.hashCode();
		}
		
		if (description != null) {
			_hashCode = 29 * _hashCode + description.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return AuditactionsPk
	 */
	public AuditactionsPk createPk()
	{
		return new AuditactionsPk(idaction);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.Auditactions: " );
		ret.append( "idaction=" + idaction );
		ret.append( ", description=" + description );
		return ret.toString();
	}

}
