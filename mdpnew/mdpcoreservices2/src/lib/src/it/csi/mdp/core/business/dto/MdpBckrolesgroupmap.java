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

public class MdpBckrolesgroupmap implements Serializable
{
	/** 
	 * This attribute maps to the column idrole in the mdp_bckrolesgroupmap table.
	 */
	protected int idrole;

	/** 
	 * This attribute maps to the column idgroup in the mdp_bckrolesgroupmap table.
	 */
	protected int idgroup;

	/**
	 * Method 'MdpBckrolesgroupmap'
	 * 
	 */
	public MdpBckrolesgroupmap()
	{
	}

	/**
	 * Method 'getIdrole'
	 * 
	 * @return String
	 */
	public int getIdrole()
	{
		return idrole;
	}

	/**
	 * Method 'setIdrole'
	 * 
	 * @param idrole
	 */
	public void setIdrole(int idrole)
	{
		this.idrole = idrole;
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
		
		if (!(_other instanceof MdpBckrolesgroupmap)) {
			return false;
		}
		
		final MdpBckrolesgroupmap _cast = (MdpBckrolesgroupmap) _other;
		if (idrole !=_cast.idrole ) {
			return false;
		}
		
		if (idgroup !=  _cast.idgroup ) {
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
		
			_hashCode = 29 * _hashCode + idrole;
		
		
	
			_hashCode = 29 * _hashCode + idgroup;
		
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return MdpBckrolesgroupmapPk
	 */
	public MdpBckrolesgroupmapPk createPk()
	{
		return new MdpBckrolesgroupmapPk(idrole, idgroup);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.MdpBckrolesgroupmap: " );
		ret.append( "idrole=" + idrole );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

}
