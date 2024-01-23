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

public class MdpBckusersgroup implements Serializable
{
	/** 
	 * This attribute maps to the column iduser in the mdp_bckusersgroup table.
	 */
	protected int iduser;

	/** 
	 * This attribute maps to the column idgroup in the mdp_bckusersgroup table.
	 */
	protected int idgroup;

	/**
	 * Method 'MdpBckusersgroup'
	 * 
	 */
	public MdpBckusersgroup()
	{
	}

	/**
	 * Method 'getIduser'
	 * 
	 * @return String
	 */
	public int getIduser()
	{
		return iduser;
	}

	/**
	 * Method 'setIduser'
	 * 
	 * @param iduser
	 */
	public void setIduser(int iduser)
	{
		this.iduser = iduser;
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
		
		if (!(_other instanceof MdpBckusersgroup)) {
			return false;
		}
		
		final MdpBckusersgroup _cast = (MdpBckusersgroup) _other;
		if (iduser != _cast.iduser) {
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
		
			_hashCode = 29 * _hashCode + iduser;
		
		
		
			_hashCode = 29 * _hashCode + idgroup;
		
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return MdpBckusersgroupPk
	 */
	public MdpBckusersgroupPk createPk()
	{
		return new MdpBckusersgroupPk(iduser, idgroup);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.MdpBckusersgroup: " );
		ret.append( "iduser=" + iduser );
		ret.append( ", idgroup=" + idgroup );
		return ret.toString();
	}

}
