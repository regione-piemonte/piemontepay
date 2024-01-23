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

public class MdpBckusers implements Serializable
{
	/** 
	 * This attribute maps to the column firstname in the mdp_bckusers table.
	 */
	protected String firstname;

	/** 
	 * This attribute maps to the column lastname in the mdp_bckusers table.
	 */
	protected String lastname;

	/** 
	 * This attribute maps to the column codfisc in the mdp_bckusers table.
	 */
	protected String codfisc;

	/** 
	 * This attribute maps to the column email in the mdp_bckusers table.
	 */
	protected String email;

	/** 
	 * This attribute maps to the column iduser in the mdp_bckusers table.
	 */
	protected int iduser;

	/** 
	 * This attribute maps to the column pwdservizio in the mdp_bckusers table.
	 */
	protected String pwdservizio;

	/**
	 * Method 'MdpBckusers'
	 * 
	 */
	
	protected List <MdpBckusersgroup> usergrp = null;
	public MdpBckusers()
	{
	}

	/**
	 * Method 'getFirstname'
	 * 
	 * @return String
	 */
	public String getFirstname()
	{
		return firstname;
	}

	/**
	 * Method 'setFirstname'
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	/**
	 * Method 'getLastname'
	 * 
	 * @return String
	 */
	public String getLastname()
	{
		return lastname;
	}

	/**
	 * Method 'setLastname'
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	/**
	 * Method 'getCodfisc'
	 * 
	 * @return String
	 */
	public String getCodfisc()
	{
		return codfisc;
	}

	/**
	 * Method 'setCodfisc'
	 * 
	 * @param codfisc
	 */
	public void setCodfisc(String codfisc)
	{
		this.codfisc = codfisc;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Method 'getIduser'
	 * 
	 * @return int
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
	 * Method 'getPwdservizio'
	 * 
	 * @return String
	 */
	public String getPwdservizio()
	{
		return pwdservizio;
	}

	/**
	 * Method 'setPwdservizio'
	 * 
	 * @param pwdservizio
	 */
	public void setPwdservizio(String pwdservizio)
	{
		this.pwdservizio = pwdservizio;
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
		
		if (!(_other instanceof MdpBckusers)) {
			return false;
		}
		
		final MdpBckusers _cast = (MdpBckusers) _other;
		if (firstname == null ? _cast.firstname != firstname : !firstname.equals( _cast.firstname )) {
			return false;
		}
		
		if (lastname == null ? _cast.lastname != lastname : !lastname.equals( _cast.lastname )) {
			return false;
		}
		
		if (codfisc == null ? _cast.codfisc != codfisc : !codfisc.equals( _cast.codfisc )) {
			return false;
		}
		
		if (email == null ? _cast.email != email : !email.equals( _cast.email )) {
			return false;
		}
		
		if (iduser != _cast.iduser) {
			return false;
		}
		
		if (pwdservizio == null ? _cast.pwdservizio != pwdservizio : !pwdservizio.equals( _cast.pwdservizio )) {
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
		if (firstname != null) {
			_hashCode = 29 * _hashCode + firstname.hashCode();
		}
		
		if (lastname != null) {
			_hashCode = 29 * _hashCode + lastname.hashCode();
		}
		
		if (codfisc != null) {
			_hashCode = 29 * _hashCode + codfisc.hashCode();
		}
		
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}
		
		_hashCode = 29 * _hashCode + iduser;
		if (pwdservizio != null) {
			_hashCode = 29 * _hashCode + pwdservizio.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return MdpBckusersPk
	 */
	public MdpBckusersPk createPk()
	{
		return new MdpBckusersPk(iduser);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "it.csi.mdp.core.business.dto.MdpBckusers: " );
		ret.append( "firstname=" + firstname );
		ret.append( ", lastname=" + lastname );
		ret.append( ", codfisc=" + codfisc );
		ret.append( ", email=" + email );
		ret.append( ", iduser=" + iduser );
		ret.append( ", pwdservizio=" + pwdservizio );
		return ret.toString();
	}

	public List<MdpBckusersgroup> getUsergrp()
	{
		return usergrp;
	}

	public void setUsergrp(List<MdpBckusersgroup> usergrp)
	{
		this.usergrp = usergrp;
	}

}
