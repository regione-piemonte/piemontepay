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

public class MdpBckofficegroups implements Serializable
{
	/** 
	 * This attribute maps to the column description in the mdp_bckofficegroups table.
	 */
	protected String description;

	/** 
	 * This attribute maps to the column idgroup in the mdp_bckofficegroups table.
	 */
	protected int idgroup;
	
	
	
	protected List<Application> appgrp = null;
	
	
	protected List<MdpBckroles> roles = null;
	
	protected List<MdpBckusers> users = null;

	/**
	 * Method 'MdpBckofficegroups'
	 * 
	 */
	public MdpBckofficegroups()
	{
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
	 * Method 'getIdgroup'
	 * 
	 * @return String
	 */
	public int getIdgroup()
	{
		return idgroup;
	}

	/**
	 * Method 'setIdgroup'
	 * 
	 * @param idgroup
	 */
	public void setIdgroup(int idgroup)
	{
		this.idgroup = idgroup;
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
		
		if (!(_other instanceof MdpBckofficegroups)) {
			return false;
		}
		
		final MdpBckofficegroups _cast = (MdpBckofficegroups) _other;
		if (description == null ? _cast.description != description : !description.equals( _cast.description )) {
			return false;
		}
		
		if (idgroup != _cast.idgroup ) {
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
		if (description != null) {
			_hashCode = 29 * _hashCode + description.hashCode();
		}
		
		
			_hashCode = 29 * _hashCode + idgroup;
		
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return MdpBckofficegroupsPk
	 */
	public MdpBckofficegroupsPk createPk()
	{
		return new MdpBckofficegroupsPk(idgroup);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.MdpBckofficegroups: " );
		ret.append( "description=" + description );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

	public List<Application> getAppgrp()
	{
		return appgrp;
	}

	public void setAppgrp(List<Application> appgrp)
	{
		this.appgrp = appgrp;
	}

	public List<MdpBckroles> getRoles()
	{
		return roles;
	}

	public void setRoles(List<MdpBckroles> roles)
	{
		this.roles = roles;
	}

	public List<MdpBckusers> getUsers()
	{
		return users;
	}

	public void setUsers(List<MdpBckusers> users)
	{
		this.users = users;
	}

}
