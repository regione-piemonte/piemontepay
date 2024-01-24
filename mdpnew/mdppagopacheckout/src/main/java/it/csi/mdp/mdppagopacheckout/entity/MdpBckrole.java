/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the mdp_bckroles database table.
 */
@Entity
@Table ( name = "mdp_bckroles" )
@NamedQuery ( name = "MdpBckrole.findAll", query = "SELECT m FROM MdpBckrole m" )
@SuppressWarnings ( "unused" )
public class MdpBckrole implements Serializable {

	private static final long serialVersionUID = -6935113811855851675L;

	@Id
	@SequenceGenerator ( name = "MDP_BCKROLES_IDROLE_GENERATOR", sequenceName = "MDP_BCKROLES_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "MDP_BCKROLES_IDROLE_GENERATOR" )
	private Integer idrole;

	private String roledescr;

	@OneToMany ( mappedBy = "mdpBckrole" )
	private List<Authorization> authorizations;

	@ManyToMany
	@JoinTable (
					name = "mdp_bckrolesgroupmap"
					, joinColumns = {
					@JoinColumn ( name = "idrole" )
	}
					, inverseJoinColumns = {
					@JoinColumn ( name = "idgroup" )
	}
	)
	private List<MdpBckofficegroup> mdpBckofficegroups;

	public MdpBckrole () {
	}

	public Integer getIdrole () {
		return this.idrole;
	}

	public void setIdrole ( Integer idrole ) {
		this.idrole = idrole;
	}

	public String getRoledescr () {
		return this.roledescr;
	}

	public void setRoledescr ( String roledescr ) {
		this.roledescr = roledescr;
	}

	public List<Authorization> getAuthorizations () {
		return this.authorizations;
	}

	public void setAuthorizations ( List<Authorization> authorizations ) {
		this.authorizations = authorizations;
	}

	public Authorization addAuthorization ( Authorization authorization ) {
		getAuthorizations ().add ( authorization );
		authorization.setMdpBckrole ( this );

		return authorization;
	}

	public Authorization removeAuthorization ( Authorization authorization ) {
		getAuthorizations ().remove ( authorization );
		authorization.setMdpBckrole ( null );

		return authorization;
	}

	public List<MdpBckofficegroup> getMdpBckofficegroups () {
		return this.mdpBckofficegroups;
	}

	public void setMdpBckofficegroups ( List<MdpBckofficegroup> mdpBckofficegroups ) {
		this.mdpBckofficegroups = mdpBckofficegroups;
	}

}
