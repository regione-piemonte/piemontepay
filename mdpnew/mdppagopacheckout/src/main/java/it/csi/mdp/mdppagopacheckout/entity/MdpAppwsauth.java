/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the mdp_appwsauth database table.
 */
@Entity
@Table ( name = "mdp_appwsauth" )
@NamedQuery ( name = "MdpAppwsauth.findAll", query = "SELECT m FROM MdpAppwsauth m" )
@SuppressWarnings ( "unused" )
public class MdpAppwsauth implements Serializable {

	private static final long serialVersionUID = 2426747250439537684L;

	@Id
	@SequenceGenerator ( name = "MDP_APPWSAUTH_APPLICATIONID_GENERATOR", sequenceName = "MDP_APPWSAUTH_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "MDP_APPWSAUTH_APPLICATIONID_GENERATOR" )
	private String applicationid;

	private String wspwd;

	@OneToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "applicationid" )
	private Application application;

	public MdpAppwsauth () {
	}

	public String getApplicationid () {
		return this.applicationid;
	}

	public void setApplicationid ( String applicationid ) {
		this.applicationid = applicationid;
	}

	public String getWspwd () {
		return this.wspwd;
	}

	public void setWspwd ( String wspwd ) {
		this.wspwd = wspwd;
	}

	public Application getApplication () {
		return this.application;
	}

	public void setApplication ( Application application ) {
		this.application = application;
	}

}
