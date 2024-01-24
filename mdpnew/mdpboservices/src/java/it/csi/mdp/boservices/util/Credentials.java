/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.util;

import java.io.Serializable;

public class Credentials implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6450880518667241730L;
	private int userAuth ;
	private String pwdAuth = "";
	private int group;
	private int role;
	String codfisc = "";
	public int getUserAuth()
	{
		return userAuth;
	}
	public void setUserAuth(int userAuth)
	{
		this.userAuth = userAuth;
	}
	public String getPwdAuth()
	{
		return pwdAuth;
	}
	public void setPwdAuth(String pwdAuth)
	{
		this.pwdAuth = pwdAuth;
	}
	public int getGroup()
	{
		return group;
	}
	public void setGroup(int group)
	{
		this.group = group;
	}
	public int getRole()
	{
		return role;
	}
	public void setRole(int role)
	{
		this.role = role;
	}
	public String getCodfisc()
	{
		return codfisc;
	}
	public void setCodfisc(String codfisc)
	{
		this.codfisc = codfisc;
	}
	

}
